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
<!-- ���̺� �� �ϳ� �߰��ϸ� �� ó���� �Ȱ��� ����Ǽ� �߰��� �ȴ�.�Ф�
	���� ��ĥ ����.
	board_num ���̺� ���� ���ؼ� ��������. �߰� ���� 
	placeholder�� ���� �˾Ƴ´µ�, ��� ������ �𸣰���.. �߰�����
 -->
<body>
	<div align="center">
		<form name="f" method="post" action="insert.do">
			<table WIDTH="1000" HEIGHT="450" class="outline"
				background="Desert.jpg">
				<tr>
					<td>����: <INPUT TYPE="TEXT" NAME="country"> /����: <INPUT
						TYPE="TEXT" NAME="city"></td>
				</tr>
				<tr>
					<td><h2>
							����: <INPUT TYPE="TEXT" NAME="subject"></td>
					<td ALIGN="RIGHT"><INPUT TYPE="RADIO" NAME="travel_period"
						VALUE="1~5��">1~5�� <INPUT TYPE="RADIO" NAME="travel_period"
						VALUE="5~10��">5~10�� <INPUT TYPE="RADIO"
						NAME="travel_period" VALUE="10~15��">10~15�� <INPUT
						TYPE="RADIO" NAME="travel_period" VALUE="15���̻�">15���̻�<br>
						<INPUT TYPE="RADIO" NAME="travel_season" VALUE="��">�� <INPUT
						TYPE="RADIO" NAME="travel_season" VALUE="����">���� <INPUT
						TYPE="RADIO" NAME="travel_season" VALUE="����">���� <INPUT
						TYPE="RADIO" NAME="travel_season" VALUE="�ܿ�">�ܿ� <br>
						<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="�� ȥ��">�� ȥ��
						<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="ģ���� �Բ�">ģ����
						�Բ� <INPUT TYPE="RADIO" NAME="travel_theme" VALUE="���ΰ� �Բ�">���ΰ�
						�Բ� <INPUT TYPE="RADIO" NAME="travel_theme" VALUE="��������">��������</td>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: <INPUT TYPE="hidden" NAME="writer">��
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: <c:set var="now"
							value="<%=new java.util.Date()%>" /> <fmt:formatDate
							value="${now}" type="date" dateStyle="full" /></td>
				</tr>
				<tr>
					<td><font color="white">�±�:</font> <INPUT TYPE="TEXT"
						NAME="tag_no"></td>
					<td ALIGN="RIGHT"><font color="white">�ѿ���: <INPUT
							TYPE="hidden" NAME="totalprice">��
					</font></td>
				</tr>
				<tr>
					<td ALIGN="RIGHT" COLSPAN="2"><font color="white">������</font><INPUT
						TYPE="FILE"></td>
				</tr>
			</table>
			<br>
	</div>
	<div id="pre_set" align="center">
		<form name="f" method="post" action="addPlan.do">
			<table id=dyntbl1 border=1 height="290" width="850">
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
					<td width="10%" height="100%"><input type="file" name="img"></td>
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
			<input type="button" value="+" onClick="addRow()"><br>
			<br> <input type="submit" value="����"> <input
				type="reset" value="���">
	</div>
	</form>
</body>
</html>