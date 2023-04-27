<%@page import="member.service.MemberServiceImpl"%>
<%@page import="member.service.IMemberService"%>
<%@page import="member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	IMemberService memService = MemberServiceImpl.getInstance();
	MemberVO mv = memService.findById(request.getParameter("memId"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 상세</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>I D:</td>
			<td><%=mv.getMemId() %></td>
		</tr>
		<tr>
			<td>이름:</td>
			<td><%=mv.getMemName() %></td>
		</tr>
		<tr>
			<td>이메일주소:</td>
			<td><%=mv.getMemMail() %></td>
		</tr>
		<tr>
			<td>전화번호:</td>
			<td><%=mv.getMemHp() %></td>
		</tr>
		<tr>
			<td>기본주소:</td>
			<td><%=mv.getMemAdd1() %></td>
		</tr>
		<tr>
			<td>상세주소:</td>
			<td><%=mv.getMemAdd2() %></td>
		</tr>
		<tr>
			<td colspan="2">
			<a href="memberList.jsp">[목록으로 돌아가기]</a>
			</td>
		</tr>
	</table>
</body>
</html>