package com.example.spring04.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	//요청 전에 실행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session=request.getSession();
		//관리자 세션 검사
		if(session.getAttribute("admin_userid")==null) { //로그아웃 상태
			response.sendRedirect(request.getContextPath()+"/admin/login.do?message=nologin"); //로그인 화면으로 이동
			return false; // 메인 액션으로 이동하지 못하게 차단
		}else { //로그인 상태
			return true; // 메인 액션으로 이동 
		}
	}
}
