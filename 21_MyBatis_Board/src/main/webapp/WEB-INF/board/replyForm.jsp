<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>writeForm</title>
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
</style>

<script>
	function boardLists(pageNumber, whatColumn, keyword){
		location.href="list.bd?pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+keyword;
	}
</script>

<h2>답글쓰기</h2>
<form action="reply.bd" method="post">
	<input type="hidden" name="pageNumber" value="${param.pageNumber}">
	<input type="hidden" name="whatColumn" value="${param.whatColumn}">
	<input type="hidden" name="keyword" value="${param.keyword}">
	<input type="hidden" name="ref" value="${param.ref}">
	<input type="hidden" name="re_step" value="${param.re_step}">
	<input type="hidden" name="re_level" value="${param.re_level}">
	<table>
		<tr>
			<td colspan="2" align="right"><input type="button" value="글목록" onClick="boardLists('${param.pageNumber}','${param.whatColumn}','${param.keyword}')"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="writer" value="${loginInfo.name}"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><input type="text" name="content" placeholder="최대 한글 6글자"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="passwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="답글쓰기">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" onClick="boardLists('${param.pageNumber}','${param.whatColumn}','${param.keyword}')">
			</td>
		</tr>
	</table>
</form>