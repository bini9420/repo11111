<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>loginForm</title>

<style>
	body{
		text-align: center;
	}
	table{
		border-collapse: collapse;
		margin: 5 auto;
		text-align: center;
	}
	td, th{
		border: 1px solid black;
		padding: 10 20
	}
	th{
		background: #FFFFE0
	}
</style>

<h2>회원 로그인</h2>
<form action="login.mb" method="post">
	<input type="hidden" name="pageNumber" value="${param.pageNumber}">
	<input type="hidden" name="whatColumn" value="${param.whatColumn}">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="ref" value="${param.ref}">
	<input type="hidden" name="re_step" value="${param.re_step}">
	<input type="hidden" name="re_level" value="${param.re_level}">
	<table>
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" value="winter"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pw" value="1234"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="로그인">
				<input type="reset" value="취소">
				<input type="button" value="회원가입" onClick="location='insert.mb'">
			</td>
		</tr>
	</table>
</form>