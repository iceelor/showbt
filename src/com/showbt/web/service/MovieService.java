package com.showbt.web.service;

import java.util.List;

import com.showbt.web.pojo.Movie;

public interface MovieService {
	
	public boolean addMovie(Movie movie);
	
	public List<Movie> movieList();
	
	public Movie getMovie(Long id);
}
