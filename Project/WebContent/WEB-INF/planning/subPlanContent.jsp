<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = "/resources/subplan/img/";
%>
<div align="center">
	<form name="f" method="post" action="list.do"
		enctype="multipart/form-data">
		<table border="1" width="100%" height="100%">
			<tr height="30%" align="center">
				<td colspan="2"><%=path %>/${dtoS.img}</td>
			</tr>
			<tr align="center">
				<td colspan="2">${dtoS.subject}</td>
			</tr>
			<tr height="40%" align="center">
				<td colspan="2">${dtoS.content}</td>
			</tr>
			<tr align="center">
				<td width="70%">${dtoS.traffic}</td>
				<td>${dtoS.price}</td>
			</tr>
			<tr height="40%" align="center">
				<td colspan="2">map</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<button onclick="location='list.do?no=${dtoS.group_no}'">뒤로가기</button>
				</td>
			</tr>
		</table>
	</form>
</div>