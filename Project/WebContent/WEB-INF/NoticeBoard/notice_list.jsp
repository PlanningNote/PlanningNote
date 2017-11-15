<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
   <%@ include file="top.jsp"%>  
<html>
<head>
	<title>공지사항 게시판</title>
</head>
<body>
	<div align="center">
		<b> 공 지 사 항  </b>
		<table border="0" width="600">
			<tr bgcolor="sky blue">
				<td align="right"><a href="notice_write.do">공지쓰기</a></td>
			</tr>
		</table>
		<table border="1" width="600">
			<tr bgcolor="pink">
				<th>번호</th>
				<th width="50%">제목</th>
				<th>조회수</th>
				<th>사진</th>
				<th>날짜</th>
			</tr>
			<!-- 여기에 db의 자료를 꺼내서 표현을 하자 -->
				<td>${dto.no}</td>
		<c:if test="${empty noticeList}">
			<tr> 
			
				<td colspan="5">게시된 글이 없습니다.</td>
			</tr>
		</c:if>	
		
		<c:forEach var="dto" items="${noticeList}">
			<tr>
				<td>${dto.no}</td>
			<td>	<a href="notice_content.do?no=${dto.no}">
					${dto.subject}
				</a>
				</td>
				<td>${dto.count}</td>
				<td>${dto.img}</td>
				<td>${dto.day}</td>	
			</tr>		
		</c:forEach>				
		</table>
	</div>
</body>
</html>


 <%@ include file="bottom.jsp"%>









