<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="/spring04/upload/input.do">업로드 테스트</a> | 
<a href="/spring04/shop/product/list.do">상품목록</a> |
<a href="/spring04/shop/cart/list.do">장바구니</a> |     

<a href="/spring04/upload/ajax_form">파일업로드(ajax)</a> |

<a href="/spring04/board/list.do">게시판</a> |    

<a href="/spring04/pdf/list.do">PDF</a> | 

<a href="/spring04/member/address.do">도로명주소</a> |

<a href="/spring04/email/write.do">이메일 발송</a> |   

<a href="/spring04/chart/chart1.do">구글차트(json)</a> |
<a href="/spring04/chart/chart2.do">구글차트(db)</a> |
<a href="/spring04/jchart/chart1.do">JFreeChart(png)</a> |
<a href="/spring04/jchart/chart2.do">JFreeChart(pdf)</a> | 

<div style="text-align:right">
<c:choose>
	<c:when test="${sessionScope.userid == null }">
		<a href="/spring04/member/login.do">로그인</a> | 
		<a href="/spring04/admin/login.do">관리자 로그인</a>
	</c:when>
	<c:otherwise>
		${sessionScope.name}님이 로그인중입니다.
		<a href="/spring04/member/logout.do">로그아웃</a> |
		<a href="/spring04/admin/logout.do">관리자 로그아웃</a>   
	</c:otherwise>
</c:choose>
</div>

<hr>








