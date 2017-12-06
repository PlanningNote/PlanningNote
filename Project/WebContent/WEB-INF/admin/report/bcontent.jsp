<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../admin_top.jsp" %>
<div align="center" >
	<hr color="green" width="300">
	<font size="6"><b>게 시 물 신 고 내 용</b></font>
	<hr color="green" width="300">
	<table border="1" width="800">
		<tr align="center">
			<th width="15%">번호</th>
			<td width="35%">${dto.no}</td>
			<th width="15%">게시물번호</th>
			<td width="35%">${dto.board_no}</td>
		</tr>
		<tr align="center">
			<th>신고자</th>
			<td>${dto.reporter}</td>
			<th>신고닉네임</th>
			<td>${dto.suspecter}</td>
		</tr>
		<tr align="center">
			<th>내용</th>
			<td colspan="3">
				<textarea rows="8" cols="80" readonly>${dto.content}</textarea>
			</td>
		</tr>
		<tr align="center">
			<th>이미지</th>
			<td colspan="3">
				<c:if test="${not empty dto.img}">
					<img src="imgfile/report/${dto.img}" width="300">
				</c:if>
			</td>
		</tr>
		<tr align="center">
			<th>신청날짜</th>
			<td>${dto.askday}</td>
			<th>처리날짜</th>
			<td>
				<c:if test="${not empty dto.handleday}">	${dto.handleday}</c:if>
				<c:if test="${empty dto.handleday}">-</c:if>
			</td>
		</tr>
		<tr align="center">
			<td colspan="4">
				<input type="button" value="삭제하기" onclick="window.location='getBContent.do?no=${dto.no}'">
				<input type="button" value="뒤로가기" onclick="window.location='getBContent.do?no=${dto.no}'">
			</td>
		</tr>
			
	</table>
</div>
<%@ include file="../admin_bottom.jsp"%>