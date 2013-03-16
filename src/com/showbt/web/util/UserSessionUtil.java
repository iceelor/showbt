package com.showbt.web.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.showbt.web.pojo.UserInfo;
import com.showbt.web.service.UserInfoService;
import com.showbt.weibo.Weibo;
import com.showbt.weibo.util.HttpUrlFetch;

/**
 * 用于缓存用户数据
 * @author dell
 *
 */
public class UserSessionUtil {
	public static UserSessionUtil session; 
	public static Map<Long, UserInfo> userInfoMap;
	private @Autowired UserInfoService userInfoService;
	
	private UserSessionUtil(){
		userInfoMap = new HashMap<Long, UserInfo>();
	}
	
	public static UserSessionUtil getInstance(){
		if(session == null){
			session = new UserSessionUtil();
		}
		return session;
	}
	
	public UserInfo getUserInfo(HttpServletRequest request){
		Cookie uidCookie = HttpUrlFetch.getCookieByName(request, "sina_uid");
		UserInfo userInfo = new UserInfo();
		if(uidCookie != null){
			String uid = uidCookie.getValue();
			userInfo = userInfoMap.get(Long.parseLong(uid));
		}
		return userInfo;
	}
	
	public boolean getLoginOut(HttpServletRequest request, HttpServletResponse response){
		Cookie uidCookie = HttpUrlFetch.getCookieByName(request, "sina_uid");
		if(uidCookie != null){
			String uid = uidCookie.getValue();
			userInfoMap.remove(Long.parseLong(uid));
		}
		Cookie cookies[] = request.getCookies();
		for(int i=0;i<cookies.length;i++){
			cookies[i].setMaxAge(0);
			response.addCookie(cookies[i]);
		}
		return true;
	}
	
	public boolean isLogin(HttpServletRequest request){
		if(getUserInfo(request) != null){
			return true;
		}
		return false;
	}
	
	public boolean sinaCallback(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		Weibo weibo = new Weibo();
		String res = weibo.getAccessToken(code,1);
		if(res == null)return false;
		JSONObject obj = JSON.parseObject(res);
		String uid = obj.getString("uid");
		if(uid != null){
			String accessToken = obj.getString("access_token");
			int expiresIn = obj.getIntValue("expires_in");
			HttpUrlFetch.addCookie(response, "access_token", accessToken, expiresIn);
			HttpUrlFetch.addCookie(response, "sina_uid", uid, expiresIn);
			JSONObject json = weibo.getUserInfo(accessToken, uid);
			UserInfo userInfo = userInfoService.getUserInfo(Long.parseLong(uid), "weibo");
			if(userInfo == null){
				userInfo = new UserInfo();
				userInfo = userInfo.getObj(json);
				userInfoService.addUserInfo(userInfo);
				userInfo = userInfoService.getUserInfo(Long.parseLong(uid), "weibo");
			}
			userInfoMap.put(Long.parseLong(uid), userInfo);
			return true;
		}else{
			return false;
		}
	}
}
