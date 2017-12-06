<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../admin_top.jsp"%>
 <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <script>
	function revert(){
		location.href="admin_noticeList.do";
	}
</script>
<div align="center">
	<img src="img/noticeimg.PNG"><br>
	<b> 공 지 사 항 </b>
	<table border="0" width="600">
		<tr bgcolor="sky blue">
			<td align="right"><a href="admin_noticeWrite.do">공지쓰기</a></td>
		</tr>
	</table>
	<table border="1" width="600">
		<tr bgcolor="pink">
			<th>번호</th>
			<th width="50%">제목</th>
			<th>조회수</th>

			<th>작성일</th>
		</tr>
		<c:if test="${empty noticeList}">
			<tr>
				<td colspan="5">게시된 글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="dto" items="${noticeList}">
			<tr>
				<td>${dto.no}</td>
				<td><a href="admin_noticeContent.do?no=${dto.no}">
						${dto.subject} </a></td>
				<td>${dto.count}</td>

				<td>${dto.day}</td>
			</tr>
		</c:forEach>
	</table>

	<form name="ff" action="admin_noticeFind.do" method="post">
		<select name="search">
			<option value="subject">제목</option>
		</select>
		<input type="text" size="20" name="searchString">&nbsp;
		<input type="submit" value="검색">
	</form>	
			
			<input type="button" value="전체목록" onClick="revert()">
</div>
<%@ include file="../admin_bottom.jsp"%>








