<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = session.getServletContext().getRealPath("img");
%>
<script language="javascript">

var index=0;

function addRow() {
	index+=1;
	var oRow = dyntbl1.insertRow();
	oRow.onmouseover = function(){dyntbl1.clickedRowIndex=this.rowIndex};
	var oCell1 = oRow.insertCell();
	var oCell2 = oRow.insertCell();
	var oCell3 = oRow.insertCell(); 
	var oCell4 = oRow.insertCell();
	oCell1.width = "5%";
	oCell2.width = "50%";
	oCell3.width = "10%"; 
	oCell4.width = "3%";
	oCell1.innerHTML = "${dtoS.board_num}";
	oCell2.innerHTML = "(*필수)제목 <br>"
			+ "<input type='text' name='targets["+index+"].subject' border='1' style='width: 100%; height: 25;'>"
			+ "<br>(*필수)비용 <br>"
			+ "<input type='number' name='targets["+index+"].price' border='1' style='width: 95%; height: 25;'>원"
			+ "<br>(*필수)내용 <br>"
			+ "<textarea name='targets["+index+"].content' rows='5' border='1' style='width: 95%; height: 80;'>"
			+ "</textarea>"
			+ "<br>(*필수)교통 <br>"
			+ "<input type='text'name='targets["+index+"].traffic' border='1' style='width: 100%; height: 25;'>";
	oCell3.innerHTML = "(*필수)<br><input type='file' name='file'>"
	oCell4.innerHTML = "<input type=button name=dyntbl1_delRow value=' 삭제 ' onClick='delRow()'>";
	document.recalc();
}
function delRow() {
	dyntbl1.deleteRow(dyntbl1.clickedRowIndex);
}
function renameForModelAttribute() {
    $("#form").each( function (index) {
        $(this).find("input[name=subject]").attr("name", "targets["+index+"].subject");
        $(this).find("input[name=price]").attr("name", "targets["+index+"].price");
        $(this).find("input[name=content]").attr("name", "targets["+index+"].content");
        $(this).find("input[name=traffic]").attr("name", "traffic");
        $(this).find("input[name=file]").attr("name", "file");
    });
}

</script>
<html>
<head>
<title>플랜리스트자세보기</title>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="updatePlan.do"enctype="multipart/form-data">
			<table WIDTH="800" HEIGHT="500" class="outline"
				background="${path}/${dtoP.thumbnail}">
				<tr>
					<td>(*필수)나라: <INPUT TYPE="TEXT" NAME="country"
						value="${dtoP.country}">/(*필수)도시: <INPUT TYPE="TEXT"
						NAME="city" value="${dtoP.city}"></td>
				</tr>
				<tr>
					<td>(*필수)
						<h2>
							제목: <INPUT TYPE="TEXT" NAME="subject" value="${dtoP.subject}">
					</td>
					<td ALIGN="RIGHT">(*필수)<br> <INPUT TYPE="RADIO"
						NAME="travel_period" VALUE="1~5일">1~5일 <INPUT TYPE="RADIO"
						NAME="travel_period" VALUE="5~10일">5~10일 <INPUT
						TYPE="RADIO" NAME="travel_period" VALUE="10~15일">10~15일 <INPUT
						TYPE="RADIO" NAME="travel_period" VALUE="15일이상">15일이상<br>

						<INPUT TYPE="RADIO" NAME="travel_seasion" VALUE="봄">봄 <INPUT
						TYPE="RADIO" NAME="travel_seasion" VALUE="여름">여름 <INPUT
						TYPE="RADIO" NAME="travel_seasion" VALUE="가을">가을 <INPUT
						TYPE="RADIO" NAME="travel_seasion" VALUE="겨울">겨울 <br>

						<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="나혼자">나 혼자 <INPUT
						TYPE="RADIO" NAME="travel_theme" VALUE="친구와함께">친구와함께 <INPUT
						TYPE="RADIO" NAME="travel_theme" VALUE="연인과함께">연인과함께 <INPUT
						TYPE="RADIO" NAME="travel_theme" VALUE="가족여행">가족여행
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">작성자: <INPUT TYPE="hidden" NAME="writer"
						value="${dtoP.writer}" disabled>님
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">작성일: <c:set var="day"
							value="<%=new java.util.Date()%>" /> <fmt:formatDate
							value="${day}" type="date" dateStyle="full" />
					</td>
				</tr>
				<tr>
					<td>태그:<br> &nbsp;<INPUT TYPE="TEXT" NAME="tag1"
						value="#"><br> &nbsp;<INPUT TYPE="TEXT" NAME="tag2"
						value="#"><br> &nbsp;<INPUT TYPE="TEXT" NAME="tag3"
						value="#"><br> &nbsp;<INPUT TYPE="TEXT" NAME="tag4"
						value="#"><br> &nbsp;<INPUT TYPE="TEXT" NAME="tag5"
						value="#"><br>
					</td>
				</tr>
				<tr>
					<td ALIGN="RIGHT" COLSPAN="2"><font color="black">(*필수)배경사진</font>
						<INPUT TYPE="FILE" name="thumbfile"
						value="${path}/${dtoP.thumbnail}"></td>
				</tr>
			</table>
	</div>
	<div id="pre_set" align="center">
		<div id="pre_set" align="center">
			<table id=dyntbl1 border=1 height="290" width="850">
				<c:forEach items="${dtoS.getTargets()}" var="dtoS" varStatus="status">
				<input type="hidden" name="targets[${index}].board_num" value="${dtoS.board_num }">
					(*필수)제목 <br>
					<input type='text' name='targets[${index}].subject' value="${dtoS.subject }" border='1' style='width: 100%; height: 25;'>
					<br>비용 <br>
					<input type='number' name='targets[${index}].price' value="${dtoS.price }"border='1' style='width: 95%; height: 25;'>원
					<br>내용 <br>
					<textarea name='targets[${index}].content' rows='5' value="${dtoS.content }"border='1' style='width: 95%; height: 80;'>
					</textarea>
					<br>교통 <br>
					<input type='text'name='targets[${index}].traffic' value="${dtoS.traffic}"border='1' style='width: 100%; height: 25;'>
					사진<br>
					<input type='file' name='file' value="${path}/${dtoS.img}">
					<input type=button name=dyntbl1_delRow value=' 삭제 ' onClick='delRow()'>
				</c:forEach>
				
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">
				</tr>
			</table>
			<input type="button" value="일정추가" onClick="addRow()"><br>
			<input type="reset" value="취소">   <input type="submit" value="수정">
			<button onclick="location='list.do?group_no=${dtoS.getGroup_no()}'">취소</button>
			</FORM>
		</div>
</body>
</html>