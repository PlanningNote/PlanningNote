<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
	oCell2.innerHTML = "���� <br>"+
						"<input type='text' name='subject' border='1' style='width: 100%; height: 25;'>"+
						"<br>��� <br>"+
						"<input type='text' name='price' border='1' style='width: 100%; height: 25;'>"+
						"<br>���� <br>"+
						"<textarea name='content' rows='5' border='1' style='width: 95%; height: 80;'>"+
						"</textarea>"+
						"<br>���� <br>"+
						"<input type='text'name='trffic' border='1' style='width: 100%; height: 25;'>";
	oCell3.innerHTML = "<input type='file' name='img'>";
	oCell4.innerHTML = "<input type=button name=dyntbl1_delRow value=' ���� ' onClick='delRow()'>";
	document.recalc();
}
function delRow() {
	dyntbl1.deleteRow(dyntbl1.clickedRowIndex);
}
</script>
<body>
	<div id="pre_set" align="center">
		<form name="f" method="post" action="addPlan.do">
				<table id=dyntbl1 border=1 height="290"	width="850">
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">
					<td width="5%">board_num</td>

					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
						<!-- ���⿡ subplan�� ���ϴ�. --> ���� <br> <input type="text"
						name="subject" border="1" style="width: 100%; height: 25;">
						<br>��� <br> <input type="text" name="price" border="1"
						style="width: 100%; height: 25;"> <br>���� <br> <textarea
							name="content" rows="5" border="1"
							style="width: 95%; height: 80;"></textarea> <br>���� <br>
						<input type="text" name="trffic" border="1"
						style="width: 100%; height: 25;">
					</td>

					<td width="10%" height="100%"><input
						type="file" name="img"></td>

					<td width="3%"></td>
				</tr>
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">
					<td width="5%">board_num</td>

					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
						<!-- ���⿡ subplan�� ���ϴ�. --> ���� <br> <input type="text"
						name="subject" border="1" style="width: 100%; height: 25;">
						<br>��� <br> <input type="text" name="price" border="1"
						style="width: 100%; height: 25;"> <br>���� <br> <textarea
							name="content" rows="5" border="1"
							style="width: 95%; height: 80;"></textarea> <br>���� <br>
						<input type="text" name="trffic" border="1"
						style="width: 100%; height: 25;">
					</td>

					<td width="10%" height="100%"><input type="file" name="img">
					</td>

					<td width="3%"></td>
				</tr>
			</table>

			<input type="button" value="�߰�" onClick="addRow()">
			<input type="submit" value="����">
	</div>
</body>
</html>

