<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<MemberVO> memList = 
		(List<MemberVO>) request.getAttribute("memList");
	
	String msg = (String) session.getAttribute("msg");
	session.removeAttribute("msg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<style>
	table {
		border-color: blue;
	}
	tr, td {
		padding: 5px;
	}
</style>
</head>

<body>
	<h3>회원 목록 보기</h3>
	<table border="1">
		<tr align="right">
			<td colspan="5"><button onclick="location.href='./insert.do'">회원추가</button></td>
		</tr>
		<tr>
			<th>ID</th>
			<th>비밀번호</th>
			<th>이 름</th>
			<th>전 화</th>
			<th>주 소</th>
		</tr>
<%
	int size = memList.size();
	
	if (size == 0) {
%>
	<tr>
		<td colspan="5">회원정보가 존재하지 않습니다.</td>
	</tr>
<%
	} else {
		for (MemberVO mv : memList) {
%>
		<tr>
			<td><a href="./detail.do?memId=<%=mv.getMemId() %>"><%=mv.getMemId() %></a></td>
			<td><%=mv.getMemPass() %></td>
			<td><%=mv.getMemName() %></td>
			<td><%=mv.getMemTel() %></td>
			<td><%=mv.getMemAddr() %></td>
		</tr>	
<%
		}
	}
%>
	</table>
<% if (msg != null && msg.equals("성공")) { %>
<script>
	alert('정상적으로 처리되었습니다.');
</script>
<% } 
%>
</body>
</html>