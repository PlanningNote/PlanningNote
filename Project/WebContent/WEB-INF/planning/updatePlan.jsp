<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String path = session.getServletContext().getRealPath("img"); %>
<html>
<head>
<title>�÷�����Ʈ�ڼ�����</title>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="list.do" enctype="multipart/form-data">
			<table WIDTH="800" HEIGHT="500" class="outline"
				background="${path}/${dtoP.thumbnail}">
				<tr>
					<td>����: ${dtoP.country} /����: ${dtoP.city}</td>
				</tr>
				<tr>
					<td WIDTH="75%"><h2>
							����:${dtoP.subject}</td>
					<td ALIGN="left">
					�Ⱓ: ${dtoP.travel_period}<br> 
					����: ${dtoP.travel_seasion}<br> 
					�׸�: ${dtoP.travel_theme}<br>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: ${dtoP.writer} ��
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: ${dtoP.day}
					</td>
				</tr>
				<tr>
					<td COLSPAN="2"><INPUT TYPE="IMAGE" name="recom" SRC="recom.png">
						/count:<INPUT TYPE="hidden" name="count" VALUE="count"></td>
				</tr>
				<tr>
					<td WIDTH="75%">�±�:<br>${dtoT.tag1}   ${dtoT.tag2}   ${dtoT.tag3}   ${dtoT.tag4} ${dtoT.tag5}
					<td ALIGN="left">�ѿ���: ${dtoP.totalprice} ��</td>
					</td>
				</tr>
			</table>
	</div> 
	<div id="pre_set" align="center">
			<table id=dyntbl1 border=1 height="290" width="850">
			<c:forEach items="${dtoS.getTargets()}" var="dtoS" varStatus="status">
				<tr>
					<td width="5%">${dtoS.board_num}</td>
				 	<td whidth="50%" height="100%">
						���� <br>${dtoS.subject}<br>
						��� <br>${dtoS.price }
						<br>���� <br> 
						${dtoS.content}
						<br>���� <br>
						${dtoS.traffic}</td>
					<td width="10%" height="100%">
					<a href="subPlanContent.do?board_num=${dtoS.getBoard_num()}">
					<img src="${path}/${dtoS.img}"></a></td>
					<td width="3%"></td>
				</tr>
			</c:forEach>
			</table><br>
			</table>
			<input type="submit" value="����">
			<button onclick="location='list.do?group_no=${dtoS.getGroup_no()}'">���</button>
	</FORM>
	</div>
</body>
</html>