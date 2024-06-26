<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<title>updateForm</title>
<style>
	body{
		text-align: center;
	}
	table{
		border-collapse: collapse;
		margin: 5 auto;
		width: 500
	}
	td, th{
		border: 1px solid black;
		padding: 10
	}
	th{
		background: #FFE4E1
	}
	.err{
		color: red;
		font-weight: bold;
	}
</style>

<h2>글수정</h2>
<form:form commandName="board" action="update.bd" method="post">
	<input type="hidden" name="num" value="${board.num}">
	<input type="hidden" name="pageNumber" value="${param.pageNumber}">
	<input type="hidden" name="whatColumn" value="${param.whatColumn}">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<table>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" name="writer" value="${board.writer}">
				<form:errors path="writer" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>
				<input type="text" name="subject" value="${board.subject}">
				<form:errors path="subject" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<input type="text" name="email" value="${board.email}">
				<form:errors path="email" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<input type="text" name="content" value="${board.content}">
				<form:errors path="content" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="text" name="passwd" value="${board.passwd}">
				<form:errors path="passwd" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글수정">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" onClick="location='list.bd?pageNumber=${param.pageNumber}&whatColumn=${param.whatColumn}&keyword=${param.keyword}'">
			</td>
		</tr>
	</table>
</form:form>