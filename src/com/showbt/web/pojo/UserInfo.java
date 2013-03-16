package com.showbt.web.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alibaba.fastjson.JSONObject;
import com.showbt.weibo.User;

@Entity
public class UserInfo implements Serializable{
	private static final long serialVersionUID = 4639786569313556540L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;                      //用户id
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
	private Long userId;				  //外来用户ID
	private String source;					//用户来源(weibo,weixin,qq)
	
	public UserInfo(){}
	
	public UserInfo(User user){
		this.userId = user.getId();
		this.screenName = user.getScreenName();
		this.name = user.getName();
		this.province = user.getProvince();
		this.city = user.getCity();
		this.location = user.getLocation();
		this.description = user.getDescription();
		this.url = user.getUrl();
		this.profileImageUrl = user.getProfileImageUrl();
		this.userDomain = user.getUserDomain();
		this.gender = user.getGender();
		this.followersCount = user.getFollowersCount();
		this.friendsCount = user.getFriendsCount();
		this.statusesCount = user.getStatusesCount();
		this.favouritesCount = user.getFavouritesCount();
		this.createdAt = user.getCreatedAt();
		this.following = user.isFollowing();
		this.verified = user.isVerified();
		this.geoEnabled = user.isGeoEnabled();
		this.allowAllActMsg = user.isAllowAllActMsg();
		this.source = "weibo";
	}
	public JSONObject getJsonObj(){
		JSONObject json = new JSONObject();
		json.put("id",this.getId());
		json.put("name",this.getName());
		json.put("screen_name",this.getScreenName());
		json.put("location",this.getLocation());
		json.put("description",this.getDescription());
		json.put("profile_image_url",this.getProfileImageUrl());
		json.put("url",this.getUrl());
		json.put("allow_all_act_msg",this.isAllowAllActMsg());
		json.put("followers_count",this.getFollowersCount());
		json.put("friends_count",this.getFriendsCount());
		json.put("favourites_count",this.getFavouritesCount());
		json.put("statuses_count",this.getStatusesCount());
		json.put("gender",this.getGender());
		json.put("province",this.getProvince());
		json.put("city",this.getCity());
		return json;
	}
	public UserInfo getObj(JSONObject json){
		this.id = json.getLong("id");
		this.name = json.getString("name");
		this.screenName = json.getString("screen_name");
		this.location = json.getString("location");
		this.description = json.getString("description");
		this.profileImageUrl = json.getString("profile_image_url");
		this.url = json.getString("url");
		this.allowAllActMsg = json.getBoolean("allow_all_act_msg");
		this.followersCount = json.getIntValue("followers_count");
		this.friendsCount = json.getIntValue("friends_count");
		this.favouritesCount = json.getIntValue("favourites_count");
		this.statusesCount = json.getIntValue("statuses_count");
		this.userDomain = json.getString("domain");
		this.gender = json.getString("gender");
		this.province = json.getIntValue("province");
		this.city = json.getIntValue("city");
		this.source = "weibo";
		this.userId= json.getLongValue("id");
		return this;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
