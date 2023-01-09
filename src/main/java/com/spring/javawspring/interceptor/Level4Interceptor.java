package com.spring.javawspring.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Level4Interceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(); //무조건 아래쪽에 선언해서 사용
		int level = session.getAttribute("sLevel") == null? 99 : (int) session.getAttribute("sLevel");
		
		if(level > 4) { //회원이 아닌 경우 차단
			RequestDispatcher dispatcher;
			dispatcher = request.getRequestDispatcher("/msg/memberNo");
			dispatcher.forward(request, response);
			return false;
		}
		return true;
	}
}
