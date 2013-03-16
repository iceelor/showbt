package com.showbt.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.showbt.web.dao.MovieDao;
import com.showbt.web.pojo.Movie;
import com.showbt.web.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	private @Autowired MovieDao movieDao;
	
	@Override
	public boolean addMovie(Movie movie) {
		return movieDao.addObj(movie);
	}

	@Override
	public List<Movie> movieList() {
		String sqlWhere = " ";
		String sqlOrder = " order by addTime desc";
		return movieDao.getObj(Movie.class, sqlWhere, sqlOrder, 1, 10);
	}

	@Override
	public Movie getMovie(Long id) {
		return movieDao.getObj(Movie.class, id);
	}

}