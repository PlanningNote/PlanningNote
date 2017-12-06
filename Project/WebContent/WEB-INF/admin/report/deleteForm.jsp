<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../admin_top.jsp" %>
<div align="center" >
	<hr color="green" width="300">
	<font size="2"><b>신고대상자에게 보내는 메일</b></font>
	<hr color="green" width="300">
	<form name="f" action="breportDelete.do" method="post">
		<table border="1" width="600">
			<input type="hidden" name="no" value="${no}">
			<tr>
				<th  align="center">신고대상자</th>
				<td><input type="text" name="nickname" value="${nickname}" readonly></td>
			</tr>
			<tr >
				<th align="center">제목</th>
				<td><input type="text" name="subject" value="녀행자들 게시물 신고" size="40"></td>
			</tr>
			<tr >
				<th align="center">내용</th>
				<td>
					<textarea name="content" rows="10" cols="50"></textarea>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="전송">
					<input type="button" value="취소" onclick="window.location='goBReport.do'">
				</td>
			</tr>
		
		</table>
	</form>
	
</div>
<%@ include file="../admin_bottom.jsp"%>