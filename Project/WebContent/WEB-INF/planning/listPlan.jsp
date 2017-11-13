<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>플랜리스트</title>
</head>
<body>
	<div align="center">
		<FORM name="f" method="post" action="list.do">
			<table WIDTH="800" HEIGHT="500" class="outline"
				background="${backgroundimg}">
				<tr>
					<td>나라: <INPUT TYPE="hidden" name="country" VALUE="${country}"> /도시:
						<INPUT TYPE="hidden" name="city" VALUE="${city}"></td>
				</tr>
				<tr>
					<td><h2>
							제목: <INPUT TYPE="hidden" name="city" VALUE="${subject}"></td>
					<td ALIGN="RIGHT">
					기간: <INPUT TYPE="hidden" name="travel_period" VALUE="${travel_period}"><br> 
					시즌: <INPUT TYPE="hidden" name="travel_season" VALUE="${travel_season}"><br> 
					테마: <INPUT TYPE="hidden" name="travel_theme" VALUE="${travel_theme}"><br>
				</tr>
				<tr>
					<td COLSPAN="2">작성자: <INPUT TYPE="hidden" name="writer" VALUE="${writer}">님
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">작성일: <INPUT TYPE="hidden" name="day" VALUE="${day}">
					</td>
				</tr>
				<tr>
					<td COLSPAN="2"><INPUT TYPE="IMAGE" name="recom" SRC="recom.png">
						/count:<INPUT TYPE="hidden" name="count" VALUE="count"></td>
				</tr>
				<tr>
					<td>태그:<br>
					<INPUT TYPE="hidden" name="tag1" VALUE="${tag1}">
					<INPUT TYPE="hidden" name="tag2" VALUE="${tag2}">
					<INPUT TYPE="hidden" name="tag3" VALUE="${tag3}">
					<INPUT TYPE="hidden" name="tag4" VALUE="${tag4}">
					<INPUT TYPE="hidden" name="tag5" VALUE="${tag5}"></td>
					<td ALIGN="RIGHT">총예산: <INPUT TYPE="hidden" name="totalprice" VALUE="${totalprice}">원
					</td>
				</tr>
			</table>
	</div>
	<div id="pre_set" align="center">
		<form name="f" method="post" action="list.do" enctype="multipart/form-data">
			<table id=dyntbl1 border=1 height="290" width="850">
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">
					<td width="5%">board_num</td>
					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
						<!-- 여기에 subplan이 들어갑니다. --> 제목 <br> 
						<input type="hidden"name="targets[0].subject" border="1"style="width: 100%; height: 25;" VALUE="${targets[0].subject}">
						<br>비용 <br>
						<input type="hidden" name="targets[0].price" border="1"style="width: 100%; height: 25;" VALUE="${targets[0].price}">
						<br>내용 <br>
						<textarea name="targets[0].content" rows="5" border="1"style="width: 95%; height: 80;" VALUE="${targets[0].content}">
						</textarea>
						<br>교통 <br>
						<input type="hidden"name="targets[0].traffic" border="1"style="width: 100%; height: 25;" VALUE="${targets[0].traffic}">
					</td>
					<!-- <td width="10%" height="100%"><input type="image"name="targets[0].img"src="button_ok.gif"></td> -->
					<td width="3%"></td>
				</tr>
			</table>
			<input type="button" value="글수정"
				onclick="window.location='planning_updatePlan.do?num=${board_num}'">
			<%-- <input type="button" value="글삭제"
				onclick="window.location='planning_deletePlan.do?num=${getBoard.num}'"> --%>
		</form>
	</div>
	</FORM>
</body>
</html>