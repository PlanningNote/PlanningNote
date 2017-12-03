<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="../../top.jsp" %>
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
				
					<td colspan="5">게시된 글이 없습니다.</td>
				</tr>
			</c:if>	
			
			<c:forEach var="dto" items="${noticeList}">
				<tr>
					<td>${dto.no}</td>
				<td>	
				<a href="notice_content.do?no=${dto.no}">
						${dto.subject}
					</a>
					</td>
					<td>${dto.count}</td>
					
					<td>${dto.day}</td>	
				</tr>		
			</c:forEach>				
			</table>
			
	

			<form>
				<select name="opt">
					<option value="0">제목</option>
					
					<option value="1">글쓴이</option>
				</select>
				<input type="text" size="20" name="condition"/>&nbsp;
				<input type="submit" value="검색"/>
			</form>	
	</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>








