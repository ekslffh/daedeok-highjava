<%@page import="member.service.MemberServiceImpl"%>
<%@page import="member.service.IMemberService"%>
<%@page import="member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	IMemberService memService = MemberServiceImpl.getInstance();
	List<MemberVO> memList = memService.findAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>기본주소</th>
			<th>상세주소</th>
		</tr>
<%
	int size = memList.size();
	
	if (size == 0) {
%>
	<tr>
		<td colspan="4">회원정보가 존재하지 않습니다.</td>
	</tr>
<%
	} else {
		for (MemberVO mv : memList) {
%>
		<tr>
			<td><a href="memberDetail.jsp?memId=<%=mv.getMemId() %>"><%=mv.getMemId() %></a></td>
			<td><%=mv.getMemName() %></td>
			<td><%=mv.getMemMail() %></td>
			<td><%=mv.getMemHp() %></td>
			<td><%=mv.getMemAdd1() %></td>
			<td><%=mv.getMemAdd2() %></td>
		</tr>	
<%
		}
	}
%>
	</table>
</body>
</html>