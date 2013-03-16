package com.showbt.weibo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.showbt.weibo.util.HttpUrlFetch;

public class Weibo implements Serializable{

	private static final long serialVersionUID = 1L;
	public static String CONSUMER_KEY = "1815120700";
	public static String CONSUMER_SECRET = "13112471f2929838ee74d97e42721189";
	
	private String loginBaseURL = "https://api.weibo.com/oauth2/";

	public String getAccessToken(String code, int type){
		String spec = loginBaseURL+"access_token?";
		String params = "client_id="+Weibo.CONSUMER_KEY+"&client_secret="+Weibo.CONSUMER_SECRET+"&grant_type=authorization_code&code="+code+"&redirect_uri=http://weibo.showbt.com";
		String res = null;
System.out.println("========="+spec+params);
		HTTPResponse response = HttpUrlFetch.getResponsePost(spec,params);
		byte[] xx = response.getContent();
		res = new String(xx);
		return res;
	}
	
	public String getTokenInfo(String accessToken){
		String spec = loginBaseURL+"get_token_info?";
		String params = "access_token="+accessToken;
		String res = null;
		System.out.println("====111111====="+spec+params);
		HTTPResponse response = HttpUrlFetch.getResponsePost(spec,params);
		byte[] xx = response.getContent();
		res = new String(xx);
		return res;
	}
	
	public JSONObject getUserInfo(String accessToken, String uid){
		String userUrl = "https://api.weibo.com/2/users/show.json?";
		String params = "access_token="+accessToken+"&uid="+uid.trim();
		String res = null;
		System.out.println("====2222====="+userUrl+params);
		HTTPResponse response = HttpUrlFetch.getResponseGet(userUrl,params);
		byte[] xx = response.getContent();
		try {
			res = new String(xx,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return JSON.parseObject(res);
	}
}
