<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>글삭제</title>
</head>
<%
		String num = request.getParameter("no");
		if (num==null || num.trim().equals("")){
			response.sendRedirect("notice_list.jsp");
			return;
		}
%> 
<body>
	<div align="center">
		<b>글삭제</b><br>
		<form name="f" action="notice_deletePro.jsp" method="post">
			<input type="hidden" name="no" value="<%=no%>">
			<table border="1" width="300">
				<tr bgcolor="pink">
					<th>비밀번호를 입력해 주세요</th> 
				</tr>
				<tr>
					<td align="center">비밀번호 : <input type="password" name="passwd"></td>
				</tr>
				<tr>
					<td bgcolor="yellow" align="center">
						<input type="submit" value="글삭제">
						<input type="button" value="글목록" onclick="window.location='notice_list.jsp'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

