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
<html>
<head>
<title>�÷�����Ʈ�ڼ�����</title>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="updatePlan.do"enctype="multipart/form-data">
			<table WIDTH="800" HEIGHT="500" class="outline"
				background="${path}/${dtoP.thumbnail}">
				<tr>
					<td>(*�ʼ�)����: <INPUT TYPE="TEXT" NAME="country"
						value="${dtoP.country}">/(*�ʼ�)����: <INPUT TYPE="TEXT"
						NAME="city" value="${dtoP.city}"></td>
				</tr>
				<tr>
					<td>(*�ʼ�)
						<h2>
							����: <INPUT TYPE="TEXT" NAME="subject" value="${dtoP.subject}">
					</td>
					<td ALIGN="RIGHT">(*�ʼ�)<br> <INPUT TYPE="RADIO"
						NAME="travel_period" VALUE="1~5��">1~5�� <INPUT TYPE="RADIO"
						NAME="travel_period" VALUE="5~10��">5~10�� <INPUT
						TYPE="RADIO" NAME="travel_period" VALUE="10~15��">10~15�� <INPUT
						TYPE="RADIO" NAME="travel_period" VALUE="15���̻�">15���̻�<br>

						<INPUT TYPE="RADIO" NAME="travel_seasion" VALUE="��">�� <INPUT
						TYPE="RADIO" NAME="travel_seasion" VALUE="����">���� <INPUT
						TYPE="RADIO" NAME="travel_seasion" VALUE="����">���� <INPUT
						TYPE="RADIO" NAME="travel_seasion" VALUE="�ܿ�">�ܿ� <br>

						<INPUT TYPE="RADIO" NAME="travel_theme" VALUE="��ȥ��">�� ȥ�� <INPUT
						TYPE="RADIO" NAME="travel_theme" VALUE="ģ�����Բ�">ģ�����Բ� <INPUT
						TYPE="RADIO" NAME="travel_theme" VALUE="���ΰ��Բ�">���ΰ��Բ� <INPUT
						TYPE="RADIO" NAME="travel_theme" VALUE="��������">��������
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: <INPUT TYPE="hidden" NAME="writer"
						value="${dtoP.writer}" disabled>��
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">�ۼ���: <c:set var="day"
							value="<%=new java.util.Date()%>" /> <fmt:formatDate
							value="${day}" type="date" dateStyle="full" />
					</td>
				</tr>
				<tr>
					<td>�±�:<br> &nbsp;<INPUT TYPE="TEXT" NAME="tag1"
						value="#"><br> &nbsp;<INPUT TYPE="TEXT" NAME="tag2"
						value="#"><br> &nbsp;<INPUT TYPE="TEXT" NAME="tag3"
						value="#"><br> &nbsp;<INPUT TYPE="TEXT" NAME="tag4"
						value="#"><br> &nbsp;<INPUT TYPE="TEXT" NAME="tag5"
						value="#"><br>
					</td>
				</tr>
				<tr>
					<td ALIGN="RIGHT" COLSPAN="2"><font color="black">(*�ʼ�)������</font>
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
					(*�ʼ�)���� <br>
					<input type='text' name='targets[${index}].subject' value="${dtoS.subject }" border='1' style='width: 100%; height: 25;'>
					<br>��� <br>
					<input type='number' name='targets[${index}].price' value="${dtoS.price }"border='1' style='width: 95%; height: 25;'>��
					<br>���� <br>
					<textarea name='targets[${index}].content' rows='5' value="${dtoS.content }"border='1' style='width: 95%; height: 80;'>
					</textarea>
					<br>���� <br>
					<input type='text'name='targets[${index}].traffic' value="${dtoS.traffic}"border='1' style='width: 100%; height: 25;'>
					����<br>
					<input type='file' name='file' value="${path}/${dtoS.img}">
					<input type=button name=dyntbl1_delRow value=' ���� ' onClick='delRow()'>
				</c:forEach>
				
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">
				</tr>
			</table>
			<input type="button" value="�����߰�" onClick="addRow()"><br>
			<input type="reset" value="���">   <input type="submit" value="����">
			<button onclick="location='list.do?group_no=${dtoS.getGroup_no()}'">���</button>
			</FORM>
		</div>
</body>
</html>