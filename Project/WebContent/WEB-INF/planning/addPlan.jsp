<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>플랜리스트</title>
</head>
<script language="javascript">
var index=2;
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
	oCell1.innerHTML = "board_num";
	oCell2.innerHTML = "제목 <br>"
			+ "<input type='text' name='targets["+index+"].subject' border='1' style='width: 100%; height: 25;'>"
			+ "<br>비용 <br>"
			+ "<input type='number' name='targets["+index+"].price' border='1' style='width: 95%; height: 25;' placeholder='숫자만 입력하세요'>원"
			+ "<br>내용 <br>"
			+ "<textarea name='targets["+index+"].content' rows='5' border='1' style='width: 95%; height: 80;'>"
			+ "</textarea>"
			+ "<br>교통 <br>"
			+ "<input type='text'name='targets["+index+"].traffic' border='1' style='width: 100%; height: 25;'>";
	oCell3.innerHTML = "<input type='file' name='file'>";
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
<body>
	<div align="center">
		<form name="f" class="f" method="post" action="goView.do" modelAttribute="FileUpload" enctype="multipart/form-data">
			<table WIDTH="1000" HEIGHT="450" class="outline"
				background="Desert.jpg">
				<tr>
					<td>나라: <INPUT TYPE="TEXT" NAME="country"> /도시: <INPUT
						TYPE="TEXT" NAME="city"></td>
				</tr>
				<tr>
					<td><h2>
							제목: <INPUT TYPE="TEXT" NAME="subject"></td>
					<td ALIGN="RIGHT">
					<INPUT TYPE="RADIO" NAME="travel_period"VALUE="1~5일">1~5일 
					<INPUT TYPE="RADIO" NAME="travel_period"VALUE="5~10일">5~10일 
					<INPUT TYPE="RADIO"	NAME="travel_period" VALUE="10~15일">10~15일 
					<INPUT TYPE="RADIO" NAME="travel_period" VALUE="15일이상">15일이상<br>
					<INPUT TYPE="RADIO" NAME="travel_seasion" VALUE="봄">봄 
					<INPUT TYPE="RADIO" NAME="travel_seasion" VALUE="여름">여름 
					<INPUT	TYPE="RADIO" NAME="travel_seasion" VALUE="가을">가을 
					<INPUT	TYPE="RADIO" NAME="travel_seasion" VALUE="겨울">겨울 <br>
					<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="나혼자">나 혼자
					<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="친구와함께">친구와	함께 
					<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="연인과함께">연인과	함께 
					<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="가족여행">가족여행</td>
				</tr>
				<tr>
					<td COLSPAN="2">작성자: <INPUT TYPE="hidden" NAME="writer" value="${writer}">님
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">작성일: 
					<c:set var="day" value="<%=new java.util.Date()%>" /> 
					<fmt:formatDate value="${day}" type="date" dateStyle="full"/> </td>
				</tr>
				<tr>
					<td>태그:<br>
						&nbsp;<INPUT TYPE="TEXT" NAME="tag1" value="#"><br>
						&nbsp;<INPUT TYPE="TEXT" NAME="tag2" value="#"><br>
						&nbsp;<INPUT TYPE="TEXT" NAME="tag3" value="#"><br>
						&nbsp;<INPUT TYPE="TEXT" NAME="tag4" value="#"><br>
						&nbsp;<INPUT TYPE="TEXT" NAME="tag5" value="#"><br>
					</td>
				</tr>
				<tr>
					<td ALIGN="RIGHT" COLSPAN="2"><font color="white">배경사진</font>
					<INPUT TYPE="FILE" name="thumbfile"></td>
				</tr>
			</table> 
			<br>
	</div>
		<div id="pre_set" align="center">
			<table id=dyntbl1 border=1 height="290" width="850">
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">
					<td width="5%">board_num</td>
				</tr>
			</table>
			<input type="button" value="일정추가" onClick="addRow()"><br><br>
			<input type="submit" value="저장">
			<input type="reset" value="취소">
		</form>
	</div> 
	</div>
	</form>
</div>
</body>
</html>