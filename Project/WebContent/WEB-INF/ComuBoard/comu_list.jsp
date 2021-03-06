<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="../../top.jsp" %> 
<script>
	function revert(){
		location.href="comu_list.do";
	}
	function writeComu(){
		location.href="comu_write.do";
	}
	function member(writer){
		window.alert()
	}
</script> 
<tr>
	<td>
		<div align="center">		
			<b>커뮤니티게시판 </b><br><br>
			<table border="0" width="600">
			<tr>
				<td>
					<input type="button" value="글쓰기" onClick="writeComu()">
				</td>
			</tr>
			</table>
			<table border="1" width="600">
				<tr >
					<th>번호</th>
					<th>작성자</th>
					<th width="50%">제목</th>
					<th>조회수</th>					
					<th>날짜</th>
				</tr>
			
			<c:if test="${empty comuList}">
				<tr align="center"> 				
					<td colspan="5">게시된 글이 없습니다.</td>
				</tr>
			</c:if>	
			
			<c:forEach var="dto" items="${comuList}">
				<tr>
					<td>${dto.no}</td>
					<td><a href="#" onclick="return member('${dto.writer}');">${dto.writer}</a></td>
					<td>	<a href="comu_content.do?no=${dto.no}">${dto.subject}</a>						
					</td>
					<td>${dto.count}</td>			
					<td>${dto.day}</td>	
				</tr>		
			</c:forEach>				
			</table>
			
			<form name="ff" action="comu_find.do" method="post">
				<select name="search">
					<option value="subject">제목</option>	
					<option value="writer">작성자</option>
				</select> 
				<input type="text" size="20" name="searchString">&nbsp;
				 <input type="submit" value="검색">
			</form>
		
		<input type="button" value="전체목록" onClick="revert()">
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>









