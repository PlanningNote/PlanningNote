<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>�÷�����Ʈ</title>
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
		oCell2.innerHTML = "���� <br>"
				+ "<input type='text' name='subject' border='1' style='width: 100%; height: 25;'>"
				+ "<br>��� <br>"
				+ "<input type='text' name='price' border='1' style='width: 100%; height: 25;'>"
				+ "<br>���� <br>"
				+ "<textarea name='content' rows='5' border='1' style='width: 95%; height: 80;'>"
				+ "</textarea>"
				+ "<br>���� <br>"
				+ "<input type='text'name='trffic' border='1' style='width: 100%; height: 25;'>";
		oCell3.innerHTML = "<input type='file' name='img'>";
		oCell4.innerHTML = "<input type=button name=dyntbl1_delRow value=' ���� ' onClick='delRow()'>";
		document.recalc();
	}
	function delRow() {
		dyntbl1.deleteRow(dyntbl1.clickedRowIndex);
	}
</script>
<body>
	<div align="center">
		<FORM name="f" method="post" action="update.do">
			<table WIDTH="800" HEIGHT="500" class="outline"
				background="${backgroundimg}">
				<tr>
					<td>����: <INPUT TYPE="text" name="country" VALUE="${country}"> /����:
						<INPUT TYPE="text" name="city"  VALUE="${city}"></td>
				</tr>
				<tr>
					<td><h2>
							����: <INPUT TYPE="text" name="subject" VALUE="${subject}"></td>
					<td ALIGN="RIGHT">
					<INPUT TYPE="RADIO" NAME="travel_period" VALUE="1~5��">1~5�� 
						<INPUT TYPE="RADIO" NAME="travel_period" VALUE="5~10��">5~10�� 
						<INPUT TYPE="RADIO" NAME="travel_period" VALUE="10~15��">10~15�� 
						<INPUT TYPE="RADIO" NAME="travel_period" VALUE="15���̻�">15���̻�<br>
						<INPUT TYPE="RADIO" NAME="travel_season" VALUE="��">�� 
						<INPUT TYPE="RADIO" NAME="travel_season" VALUE="����">���� 
						<INPUT TYPE="RADIO" NAME="travel_season" VALUE="����">���� 
						<INPUT TYPE="RADIO" NAME="travel_season" VALUE="�ܿ�">�ܿ� <br>
						<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="�� ȥ��">�� ȥ��
						<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="ģ���� �Բ�">ģ���� �Բ� 
						<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="���ΰ� �Բ�">���ΰ� �Բ� 
						<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="��������">��������</td>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: <INPUT TYPE="hidden" name="writer" VALUE="${writer}">��
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: <INPUT TYPE="hidden" name="day" VALUE="${day}">��
					</td>
				</tr>
				<tr>
					<td>�±�: <INPUT TYPE="text" name="tag_no" VALUE="${tag_no}"></td>
					<td ALIGN="RIGHT">�ѿ���:
					<INPUT TYPE="hidden" name="totalprice"  VALUE="${totalprice}">��
					</td>
				</tr>
				<tr>
					<td ALIGN="RIGHT" COLSPAN="2">������</font><INPUT
						TYPE="FILE" name="backgroundimg"></td>
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
						<!-- ���⿡ subplan�� ���ϴ�. --> ����: 
						<input type="text" name="subject" border="1" style="width: 100%; height: 25;" VALUE="${subject}">
						<br>���: 
						<input type="text" name="price" border="1" style="width: 100%; height: 25;" VALUE="${price}">�� 
						<br>���� <br> 
						<textarea rows="5" name="content" border="1" style="width: 95%; height: 80;" VALUE="${content}"></textarea> 
						<br>���� <br> 
						<input type="text" name="trffic" border="1" style="width: 100%; height: 25;" VALUE="${trffic}">
					</td>
					<td width="10%" height="100%">�̹��� ����<input type="file" name="img"></td>
					<td width="3%"></td>
				</tr>
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">
					<td width="5%">num${board_num}</td>
					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
						<!-- ���⿡ subplan�� ���ϴ�. --> ����: 
						<input type="text" name="subject" border="1" style="width: 100%; height: 25;" VALUE="${subject}"> 
						<br>���:
						<input type="text" name="price" border="1" style="width: 100%; height: 25;" VALUE="${price}">��
						<br>���� <br> 
						<textarea rows="5" name="content" border="1" style="width: 95%; height: 80;"VALUE="${content}"></textarea> 
							<br>���� <br> 
							<input type="text" name="trffic" border="1" style="width: 100%; height: 25;" VALUE="${trffic}">
					</td>
					<td width="10%" height="100%">�̹��� ����<input type="file" name="img"></td>
					<td width="3%"></td>
				</tr>
			</table>
			<%-- <input type="button" value="�ۼ���"
				onclick="window.location='board_updateForm.do?num=${getBoard.num}'">
			<input type="button" value="�ۻ���"
				onclick="window.location='board_deleteForm.do?num=${getBoard.num}'"> --%>
	</div>
	</FORM>
</body>
</html>