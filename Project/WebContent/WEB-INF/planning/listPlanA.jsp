<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>�÷�����Ʈ</title>
</head>
<body>
	<div align="center">
		<table border="1" WIDTH="800" HEIGHT="500">
			<tr>
				<td COLSPAN="3" HEIGHT="7%" ALIGN="MIDDLE">
					<form name="f">
				 		<input type="text" name="nation" size="30">
						<input type="submit" value="�˻�">
						<input type="reset" value="���">
					</form>
				</td>
			</tr>
			<c:forEach items="${dtoP}" var="dtoP">
			<tr WIDTH="100">
				<td><a href="list.do"><img src="recom.png"></a><br>
				����: <INPUT TYPE="hidden" name="country" VALUE="${dtoP.country}"><br>
				�Ⱓ: <INPUT TYPE="hidden" name="travel_period" VALUE="${dtoP.travel_period}"><br>
				�ѿ���: <INPUT TYPE="hidden" name="totalprice" VALUE="${dtoP.totalprice}">��<br>
				�ۼ���: <INPUT TYPE="hidden" name="writer" VALUE="${dtoP.writer}">��</td>
			</tr>
			</c:forEach>
			<tr HEIGHT="5%">
				<td ALIGN="center"  COLSPAN="3">���� 1 2 3 4 5 ����</td>
			</tr>
		</table>
	</div>
</body>
</html>