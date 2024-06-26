<%@ page import="member.model.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>common</title>

<!-- 자주 쓰는 코드 모아두고 include 지시어로 편하게 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%
	MemberBean loginInfo = (MemberBean)session.getAttribute("loginInfo");
	String loginId	= "";
	if(loginInfo != null){
		loginId = loginInfo.getId();
	}
%>
접속자 아이디1 : <%=loginId%><br>
접속자 아이디2 : ${loginInfo.id} <br>
접속자 아이디3 : ${sessionScope.loginInfo.id} <br><br>

<%-- 
아래 코드는 처음 페이지를 시작할 때 접속자의 정보가 없어서 오류가 생김.
null이 아닐 때만 출력하도록 해야 함.
접속자 아이디4 : <%= ((MemberBean)session.getAttribute("loginInfo")).getId() %><br> 
--%>


<!-- 
접속 성공 시 session 설정된 것은 몇 개인가?
1. destination(상품리스트에서 추가버튼 클릭하고 로그인 성공 시 이뤄질 요청 정보)
2. loginInfo(아이디, 비밀번호)
 -->

<input type="button" value="시작페이지" onClick="location='start.jsp'">
<input type="button" value="로그아웃" onClick="location='logout.jsp'">
