<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>�÷�����Ʈ</title>
</head>
<script language="javascript">

var index=-1;

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
	oCell2.innerHTML = "(*�ʼ�)���� <br>"
			+ "<input type='text' name='targets["+index+"].subject' border='1' style='width: 100%; height: 25;'>"
			+ "<br>(*�ʼ�)��� <br>"
			+ "<input type='number' name='targets["+index+"].price' border='1' style='width: 95%; height: 25;'>��"
			+ "<br>(*�ʼ�)���� <br>"
			+ "<textarea name='targets["+index+"].content' rows='5' border='1' style='width: 95%; height: 80;'>"
			+ "</textarea>"
			+ "<br>(*�ʼ�)���� <br>"
			+ "<input type='text'name='targets["+index+"].traffic' border='1' style='width: 100%; height: 25;'>";
	oCell3.innerHTML = "(*�ʼ�)<br><input type='file' name='file'>"
	oCell4.innerHTML = "<input type=button name=dyntbl1_delRow value=' ���� ' onClick='delRow()'>";
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
		<FORM name="f" method="post" action="update.do">
			<table WIDTH="800" HEIGHT="500" class="outline"
				background="${dtoP.thumbnail}">
				<tr>
					<td>����: <INPUT TYPE="text" name="country" VALUE="${dtoP.country}"> /����:
						<INPUT TYPE="text" name="city"  VALUE="${dtoP.city}"></td>
				</tr>
				<tr>
					<td><h2>
							����: <INPUT TYPE="text" name="subject" VALUE="${dtoP.subject}"></td>
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
					<td COLSPAN="2">�ۼ���: ${dtoP.writer}��
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: 
					<c:set var="day" value="<%=new java.util.Date()%>" /> 
					<fmt:formatDate value="${day}" type="date" dateStyle="full"/>
					</td>
				</tr>
				<tr>
					<td>�±�: <br>
					<INPUT TYPE="TEXT" NAME="tag1" value="${dtoP.tag1}"><br>
					<INPUT TYPE="TEXT" NAME="tag2" value="${dtoP.tag2}"><br>
					<INPUT TYPE="TEXT" NAME="tag3" value="${dtoP.tag3}"><br>
					<INPUT TYPE="TEXT" NAME="tag4" value="${dtoP.tag4}"><br>
					<INPUT TYPE="TEXT" NAME="tag5" value="${dtoP.tag5}"><br>
					</td>
				</tr>
				<tr>
					<td ALIGN="RIGHT" COLSPAN="2">������</font><INPUT
						TYPE="FILE" name="thumbnail"></td>
				</tr>
			</table>
	</div>
		<div id="pre_set" align="center">
			<table id=dyntbl1 border=1 height="290" width="850">
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">

				</tr>
			</table>
			<input type="button" value="�����߰�" onClick="addRow()"><br><br>
			<input type="submit" value="����">
			<button onclick="location='list.do'">���</button>
	</FORM>
	</div>
</body>
</html>