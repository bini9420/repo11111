<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<title>boardDetail</title>

<style>
	body{
		text-align: center;
	}
	table{
		border-collapse: collapse;
		margin: 5 auto;
		text-align: center;
		width: 500;
	}
	td, th{
		border: 1px solid black;
		padding: 15 3
	}
	th{
		background: #FFE4E1
	}
</style>
<script>
	function goUpdate(num,pageNumber,whatColumn,keyword){
		location.href="update.bd?num="+num+"&pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+keyword;
	}
	function goDelete(num,pageNumber,whatColumn,keyword){
		location.href="delete.bd?num="+num+"&pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+keyword;
	}
	function goReply(pageNumber,whatColumn,keyword,ref,re_step,re_level){
		location.href="reply.bd?pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+keyword+"&ref="+ref+"&re_step="+re_step+"&re_level="+re_level;
	}
	function goList(pageNumber,whatColumn,keyword){
		location.href="list.bd?pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+keyword;
	}
</script>
<h2>글 상세보기</h2>
<table>
	<tr>
		<th>글번호</th>
		<td>${board.num}</td>
		<th>조회수</th>
		<td>${board.readcount}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${board.writer}</td>
		<th>작성일</th>
		<td>
			<fmt:parseDate value="${board.reg_date}" var="date" pattern="yyyy-MM-dd"/>
			<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<th>글제목</th>
		<td colspan="3">${board.subject}</td>
	</tr>
	<tr>
		<th>글내용</th>
		<td colspan="3">${board.content}</td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="button" value="글수정" onClick="goUpdate('${board.num}','${param.pageNumber}','${param.whatColumn}','${param.keyword}')">&nbsp;&nbsp;
			<input type="button" value="글삭제" onClick="goDelete('${board.num}','${param.pageNumber}','${param.whatColumn}','${param.keyword}')">&nbsp;&nbsp;
			<input type="button" value="답글작성" onClick="goReply('${param.pageNumber}','${param.whatColumn}','${param.keyword}','${board.ref}','${board.re_step}','${board.re_level}')">&nbsp;&nbsp;
			<input type="button" value="목록보기" onClick="goList('${param.pageNumber}','${param.whatColumn}','${param.keyword}')">
		</td>
	</tr>
</table>