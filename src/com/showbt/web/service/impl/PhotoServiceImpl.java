package com.showbt.web.service.impl;

import com.showbt.web.dao.PhotoDao;
import com.showbt.web.dao.impl.PhotoDaoImpl;
import com.showbt.web.pojo.Photo;
import com.showbt.web.service.PhotoService;

public class PhotoServiceImpl implements PhotoService {
	
	private PhotoDao photoDao = new PhotoDaoImpl();
	
	@Override
	public boolean addPhoto(Photo photo) {
		return photoDao.addObj(photo);
	}

}
