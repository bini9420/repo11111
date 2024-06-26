<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<title>memberLists</title>
<style>
	body{
		text-align: center;
	}
	table{
		border-collapse: collapse;
		margin: 5 auto;
		text-align: center;
		width: 700
	}
	td, th{
		border: 1px solid black;
		padding: 4
	}
	th{
		background: #FFFFE0
	}
</style>

<script>
	function mUpdate(num, pageNumber, whatColumn, keyword){
		location.href="update.mb?num="+num+"&pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+keyword;
	}
	function mDelete(num, pageNumber, whatColumn, keyword){
		location.href="delete.mb?num="+num+"&pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+keyword;
	}
</script>

<h2>회원 목록</h2>
<form action="list.mb">
	<select name="whatColumn">
		<option value="all">전체검색
		<option value="name">이름
		<option value="job">직업
		<option value="menu">좋아하는음식
	</select>
	<input type="text" name="keyword" value="">&nbsp;<input type="submit" value="검색">
</form>
<table>
	<tr>
		<td colspan="10" align="right"><input type="button" value="추가" onClick="location='insert.mb'"></td>
	</tr>
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>성별</th>
		<th>직업</th>
		<th>나이</th>
		<th>좋아하는메뉴</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	<c:forEach var="member" items="${mlists}">
		<tr>
			<td>${member.num}</td>
			<td>${member.id}</td>
			<td>${member.pw}</td>
			<td>${member.name}</td>
			<td>${member.gender}</td>
			<td>${member.job}</td>
			<td>${member.age}</td>
			<td>${member.menu}</td>
			<td><input type="button" value="수정" onClick="mUpdate('${member.num}','${pageInfo.pageNumber}','${param.whatColumn}','${param.keyword}')"></td>
			<td><input type="button" value="삭제" onClick="mDelete('${member.num}','${pageInfo.pageNumber}','${param.whatColumn}','${param.keyword}')"></td>
		</tr>
	</c:forEach>
</table>
${pageInfo.pagingHtml}