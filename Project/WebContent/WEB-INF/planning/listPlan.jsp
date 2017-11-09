<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>플랜리스트</title>
</head>
<script language="javascript">
	function addRow() {
		var oRow = dyntbl1.insertRow();
		//oRow.onmouseover = function(){dyntbl1.clickedRowIndex=this.rowIndex};
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
				+ "<input type='text' name='subject' border='1' style='width: 100%; height: 25;'>"
				+ "<br>비용 <br>"
				+ "<input type='text' name='price' border='1' style='width: 100%; height: 25;'>"
				+ "<br>내용 <br>"
				+ "<textarea name='content' rows='5' border='1' style='width: 95%; height: 80;'>"
				+ "</textarea>"
				+ "<br>교통 <br>"
				+ "<input type='text'name='trffic' border='1' style='width: 100%; height: 25;'>";
		oCell3.innerHTML = "<input type='file' name='img'>";
		oCell4.innerHTML = "<input type=button name=dyntbl1_delRow value=' 삭제 ' onClick='delRow()'>";
		document.recalc();
	}
	function delRow() {
		dyntbl1.deleteRow(dyntbl1.clickedRowIndex);
	}
</script>
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
					<td COLSPAN="2">작성일: <INPUT TYPE="hidden" name="day" VALUE="${day}">원
					</td>
				</tr>
				<tr>
					<td COLSPAN="2"><INPUT TYPE="IMAGE" name="recom" SRC="recom.png">
						/count:<INPUT TYPE="hidden" name="count" VALUE="count"></td>
				</tr>
				<tr>
					<td>태그: <INPUT TYPE="hidden" name="tag_no" VALUE="${tag_no}"></td>
					<td ALIGN="RIGHT">총예산: <INPUT TYPE="hidden" name="totalprice" VALUE="${totalprice}">원
					</td>
				</tr>
			</table>
	</div>
	<div id="pre_set" align="center">
		<form>
			<table id=dyntbl1 border=1 height="290" width="850">
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">
					<td width="5%">num${board_num}</td>
					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
						<!-- 여기에 subplan이 들어갑니다. --> 
						제목: <input type="hidden" name="subject" border="1" tyle="width: 100%; height: 25;" VALUE="${subject}"> 
						<br>비용:
						<input type="hidden" name="price" border="1" style="width: 100%; height: 25;" VALUE="${price}">원 
						<br>내용 <br> 
						<textarea rows="5" name="content" border="1" style="width: 95%; height: 80;" VALUE="${content}"></textarea> 
						<br>교통 <br> 
						<input type="hidden" name="trffic" border="1" style="width: 100%; height: 25;" VALUE="${trffic}">
					</td>
					<td width="10%" height="100%">이미지<img name="img" VALUE="${img}"></td>
					<td width="3%"></td>
				</tr>
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">
					<td width="5%">num${board_num}</td>
					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
						<!-- 여기에 subplan이 들어갑니다. --> 
						제목: <input type="hidden" name="subject" border="1" tyle="width: 100%; height: 25;" VALUE="${subject}"> 
						<br>비용:
						<input type="hidden" name="price" border="1" style="width: 100%; height: 25;" VALUE="${price}">원 
						<br>내용 <br> 
						<textarea rows="5" name="content" border="1" style="width: 95%; height: 80;" VALUE="${content}"></textarea> 
						<br>교통 <br> 
						<input type="hidden" name="trffic" border="1" style="width: 100%; height: 25;" VALUE="${trffic}">
					</td>
					<td width="10%" height="100%">이미지<img name="img" VALUE="${img}"></td>
					<td width="3%"></td>
				</tr>
			</table>
			<input type="button" value="글수정"
				onclick="window.location=planning_updatePlan?<%-- num=${getBoard.num}' --%>">
			<%-- <input type="button" value="글삭제"
				onclick="window.location='board_deleteForm.do?num=${getBoard.num}'">  --%>
	</div>
	</FORM>
</body>
</html>