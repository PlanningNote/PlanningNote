<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../top.jsp" %>
<tr>
	<td>
		<div align="center">
			<b>�� �� ��</b>
			<form name="f" action="notice_delete.do" method="post">
				<input type="hidden" name="no" value="${param.no}"/>
				<table border="1" width="300">
					<tr bgcolor="yellow">
						<th>��й�ȣ�� �Է��� �ּ���</th>
					</tr>
					<tr>
						<td align="center">
							��й�ȣ : <input type="password" name="pwd" class="box">
						</td>
					</tr>
					<tr bgcolor="yellow">
						<td align="center">
							<input type="submit" value="�ۻ���">
							<input type="button" value="�۸��" 
										onclick="window.location='notice_list.do'">
						</td>								
					</tr>
				</table>
			</form>
		</div>		
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>










