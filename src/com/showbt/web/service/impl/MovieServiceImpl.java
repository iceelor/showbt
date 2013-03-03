package com.showbt.web.service.impl;

import com.showbt.web.dao.MovieDao;
import com.showbt.web.dao.impl.MovieDaoImpl;
import com.showbt.web.pojo.Movie;
import com.showbt.web.service.MovieService;

public class MovieServiceImpl implements MovieService {
	
	private MovieDao movieDao = new MovieDaoImpl();
	
	@Override
	public boolean addMovie(Movie movie) {
		return movieDao.addObj(movie);
	}

}
