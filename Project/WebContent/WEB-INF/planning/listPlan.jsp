<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>�÷�����Ʈ�ڼ�����</title>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="list.do" enctype="multipart/form-data">
			<table WIDTH="800" HEIGHT="500" class="outline"
				background="${dto.thumbnail}">
				<tr>
					<td>����: <INPUT TYPE="label" name="country" VALUE="${dtoP.country}"> /����:
						<INPUT TYPE="label" name="city" VALUE="${dtoP.city}"></td>
				</tr>
				<tr>
					<td WIDTH="75%"><h2>
							����: <INPUT TYPE="label" name="city" VALUE="${dtoP.subject}"></td>
					<td ALIGN="left">
					�Ⱓ: <INPUT TYPE="label" name="travel_period" VALUE="${dtoP.travel_period}"><br> 
					����: <INPUT TYPE="label" name="travel_season" VALUE="${dtoP.travel_seasion}"><br> 
					�׸�: <INPUT TYPE="label" name="travel_theme" VALUE="${dtoP.travel_theme}"><br>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: <INPUT TYPE="label" name="writer" VALUE="${dtoP.writer}">��
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: <INPUT TYPE="label" name="day" VALUE="${dtoP.day}">
					</td>
				</tr>
				<tr>
					<td COLSPAN="2"><INPUT TYPE="IMAGE" name="recom" SRC="recom.png">
						/count:<INPUT TYPE="hidden" name="count" VALUE="count"></td>
				</tr>
				<tr>
					<td WIDTH="75%">�±�:<br>
					<INPUT TYPE="label" name="tag" VALUE="# ${dtoT.tag1}# ${dtoT.tag2}# ${dtoT.tag3}# ${dtoT.tag4}# ${dtoT.tag5}"></td>
					<td ALIGN="left">�ѿ���: <INPUT TYPE="label" name="totalprice" VALUE="${dtoP.totalprice}">��
					</td>
				</tr>
			</table>
	</div>
	<div id="pre_set" align="center">
			<table id=dyntbl1 border=1 height="290" width="850">
			<c:forEach items="${dtoS.getTargets()}" var="dtoS" varStatus="status">
				<tr>
					<td width="5%">board_num</td>
					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
						<!-- ���⿡ subplan�� ���ϴ�. --> 
						���� <br>${dtoS.subject}<br>
						��� <br>${dtoS.price }
						<br>���� <br>
						${dtoS.content}
						<br>���� <br>
						${dtoS.traffic}</td>
					<td width="10%" height="100%">
					<a href="subPlanContent.do">
					<img src="${dtoS.path}/${dtoS.img}"></a></td>
					<td width="3%"></td>
				</tr>
			</c:forEach>
			</table><br>
			<input type="button" value="�ۼ���"
				onclick="window.location='updatePlan.do?no=${group_no}'">
			<%-- <input type="button" value="�ۻ���"
				onclick="window.location='planning_deletePlan.do?num=${getBoard.num}'"> --%>
	</div>
	</FORM>
</body>
</html>