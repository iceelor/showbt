package com.showbt.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.showbt.web.pojo.UserInfo;
import com.showbt.web.service.MovieService;
import com.showbt.web.util.UserSessionUtil;

@Controller
public class IndexController {

	private @Autowired MovieService movieService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		UserInfo userInfo = UserSessionUtil.getInstance().getUserInfo(request);
		request.setAttribute("movieList", movieService.movieList());
		request.setAttribute("user", userInfo);
		return "index";
	}
}
