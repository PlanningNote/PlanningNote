<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>�ۻ���</title>
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
		<b>�ۻ���</b><br>
		<form name="f" action="notice_deletePro.jsp" method="post">
			<input type="hidden" name="no" value="<%=no%>">
			<table border="1" width="300">
				<tr bgcolor="pink">
					<th>��й�ȣ�� �Է��� �ּ���</th> 
				</tr>
				<tr>
					<td align="center">��й�ȣ : <input type="password" name="passwd"></td>
				</tr>
				<tr>
					<td bgcolor="yellow" align="center">
						<input type="submit" value="�ۻ���">
						<input type="button" value="�۸��" onclick="window.location='notice_list.jsp'">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

