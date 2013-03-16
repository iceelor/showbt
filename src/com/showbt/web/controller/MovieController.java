package com.showbt.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.showbt.web.pojo.Movie;
import com.showbt.web.pojo.UserInfo;
import com.showbt.web.service.MovieService;
import com.showbt.web.util.BaseController;
import com.showbt.web.util.UserSessionUtil;

@Controller
@RequestMapping("/movie")
public class MovieController extends BaseController{
	
	private @Autowired MovieService movieService;

	@RequestMapping(value="/list/{page}")
	public String movieList(@PathVariable String page,HttpServletRequest request){
		request.setAttribute("movieList", movieService.movieList());
		UserInfo userInfo = UserSessionUtil.getInstance().getUserInfo(request);
		request.setAttribute("user", userInfo);
		return "movie/movieList";
	}
	
	@RequestMapping(value="/addMovie")
	public String addMovieForm(HttpServletRequest request){
		UserInfo userInfo = UserSessionUtil.getInstance().getUserInfo(request);
		request.setAttribute("user", userInfo);
		return "movie/addMovie";
	}
	
	@RequestMapping(value="/addMovieDo")
	public String addMovieDo(HttpServletRequest request){
		UserInfo userInfo = UserSessionUtil.getInstance().getUserInfo(request);
		request.setAttribute("user", userInfo);
		String title = request.getParameter("title");
		String picture = request.getParameter("picture");
		String content = request.getParameter("content");
		String tag = request.getParameter("tag");
		String downUrl = request.getParameter("downUrl");
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setPicture(picture);
		movie.setContent(content);
		movie.setTag(tag);
		movie.setDownUrl(downUrl);
		movie.setAddTime(new Date());
		movie.setLike(0);
		movie.setUserId(userInfo.getUserId());
		movie.setNickName(userInfo.getScreenName());
		movie.setRecommend(0);
		movieService.addMovie(movie);
		return "redirect:/index";
	}
}
