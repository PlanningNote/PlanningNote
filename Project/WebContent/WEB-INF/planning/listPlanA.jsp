<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>플랜리스트</title>
</head>
<body>
	<div align="center">
		<table border="1" WIDTH="800" HEIGHT="500">
			<tr>
				<td COLSPAN="3" HEIGHT="7%" ALIGN="MIDDLE">
					<form name="f">
				 		<input type="text" name="nation" size="30">
						<input type="submit" value="검색">
						<input type="reset" value="취소">
					</form>
				</td>
			</tr>
			<c:forEach items="${dtoP}" var="dtoP">
			<tr WIDTH="100">
				<td><a href="list.do"><img src="recom.png"></a><br>
				나라: <INPUT TYPE="hidden" name="country" VALUE="${dtoP.country}"><br>
				기간: <INPUT TYPE="hidden" name="travel_period" VALUE="${dtoP.travel_period}"><br>
				총예산: <INPUT TYPE="hidden" name="totalprice" VALUE="${dtoP.totalprice}">원<br>
				작성자: <INPUT TYPE="hidden" name="writer" VALUE="${dtoP.writer}">님</td>
			</tr>
			</c:forEach>
			<tr HEIGHT="5%">
				<td ALIGN="center"  COLSPAN="3">이전 1 2 3 4 5 다음</td>
			</tr>
		</table>
	</div>
</body>
</html>