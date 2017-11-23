<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="admin_top.jsp" %>
<script>
	function deleteMember(){
		
	}
</script>
<div align="center" >
	<table border="1" width="600">
		<tr>
			<th>회원번호</th>
			<th>닉네임</th>
			<th width="50%">이메일</th>
			<th>성별</th>
			<th>삭제</th>
		</tr>
				
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
<%@ include file="admin_bottom.jsp"%>