<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
 <%@ include file="../../top.jsp" %>
<tr height="600">
	<td valign="top">
		<div align="center">
			<b> ���� </b>
			<table border="0" width="600">
				<tr bgcolor="orange">
					<td align="right"><a href="ask_write.do">�����ϱ�</a></td>
				</tr>
			</table>
			<table border="1" width="600" >
				<tr bgcolor="pink" >
					<th>��ȣ</th>
					<th>�ۼ���</th>
					<th width="50%">����</th>
					<th>��ȸ��</th>
					<th>����</th>
					<th>��¥</th>
				</tr>
			<!-- ���⿡ db�� �ڷḦ ������ ǥ���� ���� -->
			<c:if test="${empty askList}">
				<tr>
					<td colspan="6">�Խõ� ���� �����ϴ�.</td>
				</tr>
			</c:if>
			
			<c:forEach var="dto" items="${askList}">
				<tr>
					<td>${dto.no}</td>
						<td>	<a href="ask_content.do?no=${dto.no}">
					<td>${dto.writer}</td>
					<td>
						<c:if test="${dto.re_level>0}">
							<img src="../img/level.gif" width="${dto.re_level*10}">
							<img src="../img/re.gif">
						</c:if> 
						<a href="ask_content.do?no=${dto.no}"> ${dto.subject} </a> 
						<c:if
							test="${dto.count>10}">	<img src="../img/hot.gif">
						</c:if>
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
			<input type="text" size="20" name="condition" />&nbsp;
			 <input type="submit" value="�˻�" />
		</form>
	</div>
		</td>
	
	</tr>	
				
	<%@include file="../../bottom.jsp" %>			