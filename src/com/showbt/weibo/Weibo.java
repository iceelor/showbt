package com.showbt.weibo;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.showbt.weibo.util.HttpUrlFetch;

public class Weibo extends WeiboSupport implements Serializable{

	private static final long serialVersionUID = 1L;
	public static String CONSUMER_KEY = "1815120700";
	public static String CONSUMER_SECRET = "13112471f2929838ee74d97e42721189";
	
	private String loginBaseURL = "https://api.weibo.com/oauth2/";

	public String getAccessToken(String code, int type){
		String spec = loginBaseURL+"access_token?";
		String params = "client_id="+Weibo.CONSUMER_KEY+"&client_secret="+Weibo.CONSUMER_SECRET+"&grant_type=authorization_code&code="+code+"&redirect_uri=http://weibo.showbt.com";
		String res = null;
		HTTPResponse response = HttpUrlFetch.getResponsePost(spec,params);
		byte[] xx = response.getContent();
		res = new String(xx);
		return res;
	}
	
	public String getTokenInfo(String accessToken){
		String spec = loginBaseURL+"get_token_info?";
		String params = "access_token="+accessToken;
		String res = null;
		HTTPResponse response = HttpUrlFetch.getResponsePost(spec,params);
		byte[] xx = response.getContent();
		res = new String(xx);
		return res;
	}
	
	public JSONObject getUserInfo(String accessToken, String uid){
		String userUrl = "https://api.weibo.com/2/users/show.json?";
		String params = "access_token="+accessToken+"&uid="+uid.trim();
		String res = null;
		HTTPResponse response = HttpUrlFetch.getResponseGet(userUrl,params);
		byte[] xx = response.getContent();
		try {
			res = new String(xx,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return JSON.parseObject(res);
	}
	
	public String getAccessToken(String code){
		HttpClient httpClient = new DefaultHttpClient();
//		HttpHost host = new HttpHost("202.84.17.41",8080);
//		httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, host);
		String url = loginBaseURL+"access_token";
		
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		formParams.add(new BasicNameValuePair("client_id", Weibo.CONSUMER_KEY));
		formParams.add(new BasicNameValuePair("client_secret", Weibo.CONSUMER_SECRET));
		formParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		formParams.add(new BasicNameValuePair("code", code));
		formParams.add(new BasicNameValuePair("redirect_uri", "http://weibo.showbt.com"));
		HttpPost httpPost = new HttpPost(url);
		String res = "";
		try {
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParams);
			httpPost.setEntity(formEntity);
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			res = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
}
