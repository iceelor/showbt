/*
Copyright (c) 2007-2009, Yusuke Yamamoto
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
 * Neither the name of the Yusuke Yamamoto nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY Yusuke Yamamoto ``AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL Yusuke Yamamoto BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.showbt.weibo;

import java.util.Date;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * A data class representing Basic user information element
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 3473349966713163765L;
	static final String[] POSSIBLE_ROOT_NAMES = new String[]{"user", "sender", "recipient", "retweeting_user"};
	private Weibo weibo;
	private long id;                      //用户id
	private String screenName;            //微博昵称
	private String name;                  //友好显示名称，如Bill Gates(此特性暂不支持)
	private int province;                 //省份编码（参考省份编码表）
	private int city;                     //城市编码（参考城市编码表）
	private String location;              //地址
	private String description;           //个人描述
	private String url;                   //用户博客地址
	private String profileImageUrl;       //自定义图像
	private String userDomain;            //用户个性化URL
	private String gender;                //性别,m--男，f--女,n--未知
	private int followersCount;           //粉丝数
	private int friendsCount;             //关注数
	private int statusesCount;            //微博数
	private int favouritesCount;          //收藏数
	private Date createdAt;               //创建时间
	private boolean following;            //保留字段,是否已关注(此特性暂不支持)
	private boolean verified;             //加V标示，是否微博认证用户
	private boolean geoEnabled;           //地理
	private boolean allowAllActMsg;       //保留字段（暂时不支持）
	
	public User(){}
	public User(JSONObject objUser){
		try {
			init(objUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init(JSONObject json) throws Exception {
		if(json!=null){
			try {
				id = json.getLong("id");
				name = json.getString("name");
				screenName = json.getString("screen_name");
				location = json.getString("location");
				description = json.getString("description");
				profileImageUrl = json.getString("profile_image_url");
				url = json.getString("url");
				allowAllActMsg = json.getBoolean("allow_all_act_msg");
				followersCount = json.getIntValue("followers_count");
				friendsCount = json.getIntValue("friends_count");
//				createdAt = parseDate(json.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
				favouritesCount = json.getIntValue("favourites_count");
//				following = getBoolean("following", json);
//				verified=getBoolean("verified", json);
				statusesCount = json.getIntValue("statuses_count");
				userDomain = json.getString("domain");
				gender = json.getString("gender");
				province = json.getIntValue("province");
				city = json.getIntValue("city");
//				if (!json.isNull("status")) {
//					setStatus(new Status(json.getJSONObject("status")));
//				}
			} catch (JSONException jsone) {
				throw new Exception(jsone.getMessage() + ":" + json.toString(), jsone);
			}
		}
	}
	
	public Weibo getWeibo() {
		return weibo;
	}
	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public String getUserDomain() {
		return userDomain;
	}
	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getFollowersCount() {
		return followersCount;
	}
	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}
	public int getFriendsCount() {
		return friendsCount;
	}
	public void setFriendsCount(int friendsCount) {
		this.friendsCount = friendsCount;
	}
	public int getStatusesCount() {
		return statusesCount;
	}
	public void setStatusesCount(int statusesCount) {
		this.statusesCount = statusesCount;
	}
	public int getFavouritesCount() {
		return favouritesCount;
	}
	public void setFavouritesCount(int favouritesCount) {
		this.favouritesCount = favouritesCount;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isFollowing() {
		return following;
	}
	public void setFollowing(boolean following) {
		this.following = following;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public boolean isGeoEnabled() {
		return geoEnabled;
	}
	public void setGeoEnabled(boolean geoEnabled) {
		this.geoEnabled = geoEnabled;
	}
	public boolean isAllowAllActMsg() {
		return allowAllActMsg;
	}
	public void setAllowAllActMsg(boolean allowAllActMsg) {
		this.allowAllActMsg = allowAllActMsg;
	}
	@Override
	public String toString() {
		return "User [weibo=" + weibo + ", id=" + id + ", screenName="
				+ screenName + ", name=" + name + ", province=" + province
				+ ", city=" + city + ", location=" + location
				+ ", description=" + description + ", url=" + url
				+ ", profileImageUrl=" + profileImageUrl + ", userDomain="
				+ userDomain + ", gender=" + gender + ", followersCount="
				+ followersCount + ", friendsCount=" + friendsCount
				+ ", statusesCount=" + statusesCount + ", favouritesCount="
				+ favouritesCount + ", createdAt=" + createdAt + ", following="
				+ following + ", verified=" + verified + ", geoEnabled="
				+ geoEnabled + ", allowAllActMsg=" + allowAllActMsg + "]";
	}

}
