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
<title>회원정보 변경</title>
</head>

<body>
	<form action="update.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="memId" value="<%=mv.getMemId()%>" />
		<input type="hidden" name="memPhoto" value="<%=mv.getMemPhoto()%>" />
		<table border=1>
			<tr>
				<td>회원ID:</td>
				<td><%=mv.getMemId()%></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="memPass"
					value="<%=mv.getMemPass()%>"></td>
			</tr>
			<tr>
				<td>회원이름</td>
				<td><input type="text" name="memName"
					value="<%=mv.getMemName()%>"></td>
			</tr>
			<tr>
				<td>전화번호:</td>
				<td><input type="text" name="memTel"
					value="<%=mv.getMemTel()%>"></td>
			</tr>
			<tr>
				<td>주소:</td>
				<td><textarea name="memAddr"><%=mv.getMemAddr()%></textarea></td>
			</tr>
			<tr>
				<td>새로운 첨부파일</td>
				<td><input type="file" name="atchFile" multiple="multiple" /></td>		
			</tr>
		</table>
		<input type="submit" value="회원정보 수정">

	</form>
</body>
</html>