<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../admin_top.jsp" %>
<script>
	function getContent(no){
		a
	}
</script>
<div align="center" >
	<hr color="green" width="300">
	<font size="6"><b>게 시 물 신 고 목 록</b></font>
	<hr color="green" width="300">
	<table border="1" width="800">
		<tr>
			<th>회원번호</th>
			<th>신고 닉네임</th>	
			<th>신청날짜</th>
			<th>처리날짜</th>
			<th>처리</th>
			<th>보기</th>
		</tr>
		
		<c:forEach var="dto" items="${getList}">
					<tr>
						<td>${dto.no} </td>
						<td>${dto.suspecter}</td>
						<td>${dto.askday}</td>						
						<td>
							<c:if test="${not empty dto.handleday}">
								${dto.handleday}
							</c:if>
							<c:if test="${empty dto.handleday}">
							
							</c:if>
						</td>
						<td>${dto.handling}</td> 
						<td><input type="button" value="처리" onClick="location.href=getBContent.do?no=${dto.no}"></td>
					</tr>							
		</c:forEach>
		
			
	</table>
</div>
<%@ include file="../admin_bottom.jsp"%>