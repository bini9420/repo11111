<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>delCheck</title>

<style>
	body{
		text-align: center;
	}
	table{
		border-collapse: collapse;
		margin: 5 auto;
		text-align: center;
		width: 500
	}
	td, th{
		border: 1px solid black;
		padding: 10
	}
	th{
		background: #FFE4E1
	}
	#input{
		height: 70
	}
</style>
<script>
	function goList(pageNumber,whatColumn,keyword){
		location.href="list.bd?pageNumber="+pageNumber+"&whatColumn="+whatColumn+"&keyword="+keyword;
	}
</script>
<h2>글삭제 / ${param.num}</h2>
<form action="delete.bd?num=${param.num}&pageNumber=${param.pageNumber}&whatColumn=${param.whatColumn}&keyword=${param.keyword}" method="post">
	<table>
		<tr>
			<th>글을 삭제하려면 비밀번호를 입력하세요.</th>
		</tr>
		<tr>
			<td id="input">
				비밀번호 : <input type="password" name="passwd">
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="글삭제"> &nbsp;&nbsp; <input type="button" value="글목록" onClick="goList('${param.pageNumber}','${param.whatColumn}','${param.keyword}')">
			</td>
		</tr>
	</table>
</form>