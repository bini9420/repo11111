<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<title>insertForm</title>

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
		padding: 10 15
	}
	th{
		background: #FFFFE0
	}
	.err{
		color: red;
		font-weight: bold
	}
</style>

<h2>회원 추가</h2>
<form:form commandName="member" action="insert.mb" method="post">
	<table>
		<tr>
			<th>아이디</th>
			<td>
				<input type="text" name="id" value="${member.id}">
				<form:errors path="id" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="text" name="pw" value="${member.pw}">
				<form:errors path="pw" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" name="name" value="${member.name}">
				<form:errors path="name" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>
				<%
					String[] gender = {"남자", "여자"};
				%>
				<c:forEach var="gender" items="<%=gender%>">
					<input type="radio" name="gender" value="${gender}" <c:if test="${member.gender eq gender}">checked</c:if>>${gender}
				</c:forEach>
				<form:errors path="gender" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>
				<%
					String[] job = {"학생","회사원","선생님","가수"};
				%>
				<select name="job">
					<option value="">선택 안 함
					<c:forEach var="job" items="<%=job%>">
						<option value="${job}" <c:if test="${member.job eq job}">selected</c:if>>${job}
					</c:forEach>
				</select>
				<form:errors path="job" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>
				<input type="text" name="age" value="${member.age}">
				<form:errors path="age" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<th>좋아하는음식</th>
			<td>
				<%
					String[] menu = {"한식","양식","일식","중식"};
				%>
				<c:forEach var="menu" items="<%=menu%>">
					<input type="checkbox" name="menu" value="${menu}" <c:if test="${fn:contains(member.menu, menu)}">checked</c:if>>${menu}
				</c:forEach>
				<form:errors path="menu" cssClass="err"/>
			</td>
		</tr>
		<tr>
			<td colspan="10"><input type="submit" value="추가하기"></td>
		</tr>
	</table>
</form:form>