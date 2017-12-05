<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="../../top.jsp" %>
<script>
	function revert(){
		location.href="notice_list.do";
	}
</script>

<tr height="600">
	<td valign="top">
		<div align="center">
		
		<img src="img/noticeimg.PNG"><br>
			<b> 공 지 사 항  </b>
			<table border="1" width="600">
				<tr bgcolor="pink">
					<th>번호</th>
					<th width="50%">제목</th>
					<th>조회수</th>				
					<th>작성일</th>
				</tr>
			<c:if test="${empty noticeList}">
				<tr> 				
					<td colspan="4">게시된 글이 없습니다.</td>
				</tr>
			</c:if>	
			
			<c:forEach var="dto" items="${noticeList}">
				<tr>
					<td>${dto.no}</td>
					<td>	
						<a href="notice_content.do?no=${dto.no}">${dto.subject}</a>
					</td>
					<td>${dto.count}</td>					
					<td>${dto.day}</td>	
				</tr>		
			</c:forEach>				
			</table>
			
	

			<form name="ff" action="notice_find.do" method="post">
				<select name="search">
					<option value="subject">제목</option>
				</select>
				<input type="text" size="20" name="searchString">&nbsp;
				<input type="submit" value="검색">
			</form>	
			
			<input type="button" value="전체목록" onClick="revert()">
	</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>








