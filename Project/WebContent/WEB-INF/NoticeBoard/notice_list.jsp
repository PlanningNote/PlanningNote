<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="../../top.jsp" %>
<tr height="600">
	<td valign="top">
		<div align="center">
			<b> �� �� �� ��  </b>
			<table border="0" width="600">
				<tr bgcolor="sky blue">
					<td align="right"><a href="notice_write.do">��������</a></td>
				</tr>
			</table>
			<table border="1" width="600">
				<tr bgcolor="pink">
					<th>��ȣ</th>
					<th width="50%">����</th>
					<th>��ȸ��</th>
					<th>����</th>
					<th>��¥</th>
				</tr>
				<!-- ���⿡ db�� �ڷḦ ������ ǥ���� ���� -->
					<%-- <td>${dto.no}</td> --%>
			<c:if test="${empty noticeList}">
				<tr> 
				
					<td colspan="5">�Խõ� ���� �����ϴ�.</td>
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
			
			<form>
				<select name="opt">
					<option value="0">����</option>
					
					<option value="3">�۾���</option>
				</select>
				<input type="text" size="20" name="condition"/>&nbsp;
				<input type="submit" value="�˻�"/>
			</form>	
	</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>








