package com.example.spring04.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	// url 요청 전에 실행되는 코드 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session=request.getSession(); //세션 객체 생성
		//세션 검사
		if(session.getAttribute("userid")==null) {
			//로그인 페이지로 이동
			response.sendRedirect(request.getContextPath()+"/member/login.do?message=nologin");
			//메인 url로 이동하지 못하도록 차단
			return false;
		}else { 
			return true; //메인 url로 이동 
		}
	}
}





