<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="../../top.jsp" %> 
<tr>
	<td>
		<div align="center">		
			<table border="0" width="600" >
				<b>커뮤니티게시판 미완  </b>
				<tr bgcolor="yellow">
					<td align="right"><a href="comu_write.do">글쓰기</a></td>
				</tr>
			</table>
					
			<table border="1" width="600" background="b11.jpg">
				<tr bgcolor="#7DFE74">
					<th>번호</th>
					<th width="50%">제목</th>
					<th>조회수</th>
					
					<th>날짜</th>
				</tr>
				<!-- 여기에 db의 자료를 꺼내서 표현을 하자 -->
					<td>${dto.no}</td>
			<c:if test="${empty comuList}">
				<tr> 				
					<td colspan="5">게시된 글이 없습니다.</td>
				</tr>
			</c:if>	
			
			<c:forEach var="dto" items="${comuList}">
				<tr>
					<td>${dto.no}</td>
				<td>	<a href="comu_content.do?no=${dto.no}">
						${dto.subject}
					</a>
					</td>
					<td>${dto.count}</td>
			
					<td>${dto.day}</td>	
				</tr>		
			</c:forEach>				
			</table>
			
			<br>
		<div id="searchForm">
			<form>
				<select name="opt">
					<option value="0">제목</option>
					
					<option value="3">글쓴이</option>
				</select>
				<input type="text" size="20" name="condition"/>&nbsp;
				<input type="submit" value="검색"/>
			</form>	
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>









