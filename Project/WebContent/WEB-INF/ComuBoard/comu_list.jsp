<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
   <%@ include file="sidetop.jsp"%>  
<html>
<head>
	<title>Ŀ�´�Ƽ �Խ���</title>
</head>
<body>
	<div align="center">
		
		<table border="0" width="600" >
		<b>Ŀ�´�Ƽ�Խ��� �̿�  </b>
			<tr bgcolor="yellow">
				
				<td align="right"><a href="comu_write.do">�۾���</a></td>
			</tr>
		</table>
	
		<table border="1" width="600" background="b11.jpg">
			<tr bgcolor="#7DFE74">
				<th>��ȣ</th>
				<th width="50%">����</th>
				<th>��ȸ��</th>
				<th>����</th>
				<th>��¥</th>
			</tr>
			<!-- ���⿡ db�� �ڷḦ ������ ǥ���� ���� -->
				<td>${dto.no}</td>
		<c:if test="${empty comuList}">
			<tr> 
			
				<td colspan="5">�Խõ� ���� �����ϴ�.</td>
			</tr>
		</c:if>	
		
		<c:forEach var="dto" items="${comuList}">
			<tr>
				<td>${dto.no}</td>
			<td>	<a href="comu_content.do?no=${dto.no}">
					${dto.subject}
				</a>
				</td>
				<td>${dto.count}</td>
				<td>${dto.img}</td>
				<td>${dto.day}</td>	
			</tr>		
		</c:forEach>				
		</table>
		
		<br>
	<div id="searchForm">
		<form>
			<select name="opt">
				<option value="0">����</option>
				
				<option value="3">�۾���</option>
			</select>
			<input type="text" size="20" name="condition"/>&nbsp;
			<input type="submit" value="�˻�"/>
		</form>	
	</div>
</body>
</html>


 <%@ include file="../../bottom.jsp"%>









