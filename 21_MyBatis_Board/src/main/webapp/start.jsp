<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>start</title>

<%
	String blists = request.getContextPath()+"/list.bd";
	String mlogin = request.getContextPath()+"/list.mb";
%>
<input type="button" value="게시판 목록보기" onClick="location='<%=blists%>'"><br><br>
<input type="button" value="회원 목록보기" onClick="location='<%=mlogin%>'">