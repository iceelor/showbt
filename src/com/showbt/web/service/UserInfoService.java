package com.showbt.web.service;

import com.showbt.web.pojo.UserInfo;

public interface UserInfoService {
	
	public boolean addUserInfo(UserInfo userInfo);
	
	public UserInfo getUserInfo(Long id);
	
	public UserInfo getUserInfo(Long userId, String source);
	
}
