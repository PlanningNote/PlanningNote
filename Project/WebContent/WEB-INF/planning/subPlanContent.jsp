<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<% String path="/resources/subplan/img/"; %>
<div align="center">
	<table border="1" width="100%" height="100%">
		<tr height="30%" align="center">
			<td colspan="2">${path}+${img}</td>
		</tr>
		<tr align="center">
			<td colspan="2">${subject}</td>
		</tr>
		<tr height="40%" align="center">
			<td colspan="2">${content}</td>
		</tr>
		<tr align="center">
			<td width="70%">${traffic}</td>
			<td>price</td>
		</tr>
		<tr height="40%" align="center">
			<td colspan="2">map</td>
		</tr>
		<tr align="center">
			<td colspan="2"><input type="button" value="뒤로가기"
				onclick="window.location=''"></td>
		</tr>
	</table>
</div>