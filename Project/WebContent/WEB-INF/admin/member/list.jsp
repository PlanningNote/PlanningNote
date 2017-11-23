<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../admin_top.jsp" %>
<script>
	function deleteMember(memberNo){
		if(confirm("���� �����Ͻðڽ��ϱ�?") == true){
			if(confirm("��¥�� �����Ͻðڽ��ϱ�??") == true){
				location.href="admin_memberDeleteForm.do?no="+memberNo;
			}
		}
		
	}
</script>
<div align="center" >
	<table border="1" width="600">
		<tr>
			<th>ȸ����ȣ</th>
			<th>�г���</th>
			<th width="50%">�̸���</th>
			<th>����</th>
			<th>����</th>
		</tr>
				
		<c:forEach var="dto" items="${getList}">
			<c:if test="${dto.nickname != 'admin'}">	
				<tr>
					<td>${dto.no} </td>
					<td>${dto.nickname}</td>
					<td>${dto.email}</td>
					<td>${dto.gender}</td>
					<td><input type="button" value="����" onclick="deleteMember(${dto.no})"></td>
				</tr>			
			</c:if>
			
			
		</c:forEach>
	</table>
</div>
<%@ include file="../admin_bottom.jsp"%>