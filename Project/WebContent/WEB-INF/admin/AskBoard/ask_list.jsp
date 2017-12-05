<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../admin_top.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function revert(){
		location.href="admin_askList.do";
	}
</script>
<div align="center">
	<b> 문의 (관리자용)</b>
	<table border="1" width="600">
		<tr bgcolor="pink">
			<th>번호</th>
			<th>작성자</th>
			<th width="50%">제목</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
		
		<c:if test="${empty askList}">
			<tr>
				<td colspan="5">게시된 글이 없습니다.</td>
			</tr>
		</c:if>

		<c:forEach var="dto" items="${askList}">
			<tr>
				<td>${dto.no}</td>
				<td>${dto.writer}</td>
				<td>
					<c:if test="${dto.re_level>0}">
						<img src="img/level.gif" width="${dto.re_level*10}">
						<img src="img/re.gif">
					</c:if> 
					<a href="admin_askContent.do?no=${dto.no}"> ${dto.subject}</a> 
					<c:if 	test="${dto.count>10}">
						<img src="img/hot.gif">
					</c:if>
				</td>
				<td>${dto.count}</td>
				<td>${dto.day}</td>
			</tr>
		</c:forEach>
	</table>

	<form name="ff" action="admin_askFind.do" method="post">
		<select name="search">
			<option value="subject">제목</option>
			<option value="writer">글쓴이</option>
		</select>
		<input type="text" size="20" name="searchString">&nbsp;
		<input type="submit" value="검색">
	</form>	
			
	<input type="button" value="전체목록" onClick="revert()">
</div>

<%@ include file="../admin_bottom.jsp"%>
