package com.showbt.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.showbt.web.pojo.Movie;
import com.showbt.web.pojo.UserInfo;
import com.showbt.web.service.MovieService;
import com.showbt.web.util.UserSessionUtil;

@Controller
public class IndexController {

	private @Autowired MovieService movieService;
	
//	@RequestMapping("/index")
//	public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session){
//		UserInfo userInfo = UserSessionUtil.getInstance().getUserInfo(request);
//		request.setAttribute("movieList", movieService.movieList());
//		request.setAttribute("user", userInfo);
//		return "index";
//	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		UserInfo userInfo = UserSessionUtil.getInstance().getUserInfo(request);
		List<Movie> movieList = movieService.movieList();
		request.setAttribute("movieList", movieList);
		request.setAttribute("user", userInfo);
		request.setAttribute("navId", 0);
		return "html5/index";
	}
	
	@RequestMapping("/reg")
	public String reg(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		request.setAttribute("navId", 2);
		return "html5/reg";
	}
	
	@RequestMapping("/content")
	public String content(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		request.setAttribute("navId", 0);
		String id = request.getParameter("id");
		Long mid = 0l;
		if(null != id && id.length()>0){
			try {
				mid = Long.parseLong(id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return "";
			}
		}else{
			return "";
		}
		Movie m = movieService.getMovie(mid);
		request.setAttribute("movie", m);
		return "html5/content";
	}
}
