package com.example.spring04.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring04.model.member.MemberDAO;
import com.example.spring04.model.member.MemberDTO;

@Controller
@RequestMapping("/member/*")	//공통적인 url pattern
public class MemberController {
	@Inject	// 의존관계 주입
	MemberDAO memberDao;
	
	@RequestMapping("address.do")
	public String address() {
		return "member/join"; 
	}
	
	@RequestMapping("login.do")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("login_check.do")
	public ModelAndView login_check(MemberDTO dto, HttpSession session) {
		String name=memberDao.login(dto);
		System.out.println(name);
		if(name!=null) {	//로그인 성공하면
			session.setAttribute("userid", dto.getUserid()); //세션변수 등록 
			session.setAttribute("name", name);
		}
		ModelAndView mav=new ModelAndView();
		if(name!=null) {
			mav.setViewName("main"); //로그인 성공=> main.jsp로 이동
		}else {
			mav.setViewName("member/login"); //로그인 실패=> login.jsp로 이동 
			mav.addObject("message", "error");
		}
		return mav;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		session.invalidate(); // 세션 클리어
		mav.setViewName("member/login");
		mav.addObject("message", "logout");
		return mav;
	}
}
