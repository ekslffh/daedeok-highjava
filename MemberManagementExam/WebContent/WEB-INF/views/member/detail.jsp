<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO mv = (MemberVO) request.getAttribute("mv");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 상세</title>
<style>
	table {
		border: 2px solid blue;
   	 	border-collapse : collapse;
	}
	tr, td {
		padding: 5px;
	}
	img {
		width: 300px;
		height: 300px;
		margin: 10px;
	}
</style>
</head>
<body>
	<h3>회원 정보 상세보기</h3>
	<table border="1">
		<tr>
			<td colspan="2" align="center">
				<img alt="프로필사진" src="http://localhost:8888/MemberManagementExam/images/<%=mv.getMemPhoto() %>">
			</td>
		</tr>
		<tr>
			<td>I D:</td>
			<td><%=mv.getMemId() %></td>
		</tr>
		<tr>
			<td>이름:</td>
			<td><%=mv.getMemName() %></td>
		</tr>
		<tr>
			<td>전화번호:</td>
			<td><%=mv.getMemTel() %></td>
		</tr>
		<tr>
			<td>주소:</td>
			<td><%=mv.getMemAddr() %></td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<button onclick="location.href='./update.do?memId=<%=mv.getMemId() %>'">수정</button>
				<button onclick="location.href='./delete.do?memId=<%=mv.getMemId() %>'">삭제</button>
				<button onclick="location.href='./list.do'">회원목록</button>
			</td>
		</tr>
	</table>
</body>
</html>