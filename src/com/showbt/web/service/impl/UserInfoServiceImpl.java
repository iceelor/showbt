package com.showbt.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.showbt.web.dao.UserInfoDao;
import com.showbt.web.pojo.UserInfo;
import com.showbt.web.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	private @Autowired UserInfoDao userInfoDao;
	@Override
	public boolean addUserInfo(UserInfo userInfo) {
		return userInfoDao.addObj(userInfo);
	}

	@Override
	public UserInfo getUserInfo(Long id) {
		return userInfoDao.getObj(UserInfo.class, id);
	}

	@Override
	public UserInfo getUserInfo(Long userId, String source) {
		String sqlWhere = " where u.userId=:userId and u.source=:source";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("source", source);
		List<UserInfo> uList = userInfoDao.getObj(UserInfo.class, sqlWhere, params, null, 0, 10);
		if(uList != null && uList.size()>0){
			return uList.get(0);
		}
		return null;
	}

}
