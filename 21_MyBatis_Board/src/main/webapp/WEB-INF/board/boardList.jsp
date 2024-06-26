<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<title>boardList</title>


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
		padding: 3
	}
	th{
		background: #FFE4E1
	}
</style>

<h2>게시판 목록 ( 전체: ${pageInfo.totalCount} )</h2>
<form action="list.bd">
	<select name="whatColumn">
		<option value="all">전체검색
		<option value="writer">작성자
		<option value="subject">제목
	</select>
	<input type="text" name="keyword" value="">&nbsp;<input type="submit" value="검색">
</form>
<table>
	<tr>
		<td colspan="6" align="right"><input type="button" value="글쓰기" onClick="location='write.bd'"></td>
	</tr>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
		<th>IP</th>
	</tr>
	<c:set var="rowNum" value="${pageInfo.totalCount-(pageInfo.pageNumber-1)*pageInfo.pageSize}"/>
	<c:forEach var="board" items="${blists}">
		<tr>
			<td>${rowNum}</td>
			<td align="left">
				<!-- 답글일 때 이미지 첨부 -->
				<c:if test="${board.re_level > 0 }">
					<c:set var="wid" value="${board.re_level*20}"/>
					<img src="resources/img/level.gif" width="${wid}" height="15">
					<img src="resources/img/re.gif">
				</c:if>
				
				<!-- 조회수가 10 이상일 때 이미지 첨부 -->
				<c:if test="${board.readcount >= 10 }">
					<img src="resources/img/hot.gif" width="25">
				</c:if>
				
				<a href="detail.bd?num=${board.num}&pageNumber=${pageInfo.pageNumber}&whatColumn=${param.whatColumn}&keyword=${param.keyword}">${board.subject}</a>
			</td>
			<td>${board.writer}</td>
			<td>
				<fmt:parseDate value="${board.reg_date}" var="date" pattern="yyyy-MM-dd"/>
				<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
			</td>
			<td>${board.readcount}</td>
			<td>${board.ip}</td>
		</tr>
		<c:set var="rowNum" value="${rowNum-1}"/>
	</c:forEach>
</table>
${pageInfo.pagingHtml}