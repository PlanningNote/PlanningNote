<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = session.getServletContext().getRealPath("img"); %>

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
					<select name="searchPlan">
					<option value="나라">나라</option>
					<option value="작성자">작성자</option>
					<option value="시기">시기</option>
					<option value="기간">기간</option>
					<option value="테마">테마</option>
					</select>
				 		<input type="text" name="searching" size="30">
						<input type="submit" value="검색">
						<input type="reset" value="취소">
					</form>
				</td>
			</tr>
			<c:forEach items="${dtoP}" var="dtoP">
			<tr WIDTH="100">
			<td>${dtoP.getGroup_no()}</td>
			<td><a href="list.do?group_no=${dtoP.getGroup_no()}">
			<img src="<%=path %>/${dtoP.thumbnail}" style="max-width: 200; height: 200;"></a></td>
			<td>
				나라: ${dtoP.country}<br>
				기간: ${dtoP.travel_period}<br>
				총예산: ${dtoP.totalprice} 원<br>
				작성자: ${dtoP.writer}님<br>
				조회수: ${dtoP.count}
			</td>
			</tr>
			</c:forEach>
			<tr HEIGHT="5%">
				<td ALIGN="center"  COLSPAN="3">이전 1 2 3 4 5 다음</td>
			</tr>
		</table>
	</div>
</body>
</html>
