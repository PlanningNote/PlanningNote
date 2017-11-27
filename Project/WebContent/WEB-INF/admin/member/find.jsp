<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../admin_top.jsp" %>
<div align="center">
	<hr color="green" width="300">
	<h2>회 원 찾 기</h2>
	
	<form name="f" method="post" action="">	
		<select name="search">
			<option value="no">회원번호</option>
			<option value="nickname">닉네임</option>
			<option value="email">이메일</option>
		</select>
		<input type="text" name="searchString">
		<input type="submit" value="찾기">
	</form>
	<hr color="green" width="300">
		
	<table border="1" width="600">
		<tr>
			<th>회원번호</th>
			<th>닉네임</th>
			<th width="50%">이메일</th>
			<th>성별</th>
			<th>삭제</th>
		</tr>
		
	<c:if test="${getList eq null}">
		<tr>
			<td colspan="5">검색해주세요</td>
		</tr>
	</c:if>	
		<c:forEach var="dto" items="${getList}">
			<tr>
				<td></td>
				<td>${dto.nickname}</td>
				<td>${dto.email}</td>
				<td>${dto.gender}</td>
				<td><input type="button" value="삭제" onclick="deleteMember()"></td>
			</tr>
		</c:forEach>	
	</table>
</div>
<%@ include file="../admin_bottom.jsp"%>  