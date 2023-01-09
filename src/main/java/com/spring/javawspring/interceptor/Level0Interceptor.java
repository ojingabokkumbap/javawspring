package com.spring.javawspring.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Level0Interceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(); //무조건 아래쪽에 선언해서 사용
		int level = session.getAttribute("sLevel") == null? 99 : (int) session.getAttribute("sLevel");
		if(level !=0) { //관리자가 아닌 경우에 무조건 초기화면 창으로 보내준다.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/msg/adminNo");
			dispatcher.forward(request, response);
			return false;
		}
		return true;
	}

}
