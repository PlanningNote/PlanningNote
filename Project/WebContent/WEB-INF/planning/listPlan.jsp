<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String path = session.getServletContext().getRealPath("img"); %>
<html>
<head>
<title>플랜리스트자세보기</title>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="list.do" enctype="multipart/form-data">
			<table WIDTH="800" HEIGHT="500" class="outline"
				background="<%=path %>/${dtoP.thumbnail}">
				<tr>
					<td>나라: ${dtoP.country} /도시: ${dtoP.city}</td>
				</tr>
				<tr>
					<td WIDTH="75%"><h2>
							제목:${dtoP.subject}</td>
					<td ALIGN="left">
					기간: ${dtoP.travel_period}<br> 
					시즌: ${dtoP.travel_seasion}<br> 
					테마: ${dtoP.travel_theme}<br>
				</tr>
				<tr>
					<td COLSPAN="2">작성자: ${dtoP.writer} 님
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">작성일: ${dtoP.day}
					</td>
				</tr>
				<tr>
					<td COLSPAN="2"><INPUT TYPE="IMAGE" name="recom" SRC="recom.png">
						/count:<INPUT TYPE="hidden" name="count" VALUE="count"></td>
				</tr>
				<tr>
					<td WIDTH="75%"><br>${dtoT.tag1}   ${dtoT.tag2}   ${dtoT.tag3}   ${dtoT.tag4} ${dtoT.tag5}
					<td ALIGN="left">총예산: ${dtoP.totalprice} 원</td>
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
						제목 <br>${dtoS.subject}<br>
						비용 <br>${dtoS.price }
						<br>내용 <br>
						${dtoS.content}
						<br>교통 <br>
						${dtoS.traffic}</td>
					<td width="10%" height="100%">
					<a href="subPlanContent.do?board_num=${dtoS.getBoard_num()}">
					<img src="<%=path %>/${dtoS.img}"style="max-width: 250; height: 250;"></a></td>
					<td width="3%"></td>
				</tr>
			</c:forEach>
			</table><br>
			<input type="button" value="글수정"
				onclick="window.location='updatePlan.do?group_no=${dtoP.group_no}'">
			<input type="button" value="글삭제"
				onclick="window.location='deletePlan.do?group_no=${dtoP.group_no}'"> 
	</FORM>
	</div>
</body>
</html>