<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>플랜리스트</title>
</head>
<script language="javascript">
var index=0;
	function addRow() {
		index+=1;
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
				+ "<input type='text' name='targets["+index+"].subject' border='1' style='width: 100%; height: 25;'>"
				+ "<br>비용 <br>"
				+ "<input type='text' name='targets["+index+"].price' border='1' style='width: 100%; height: 25;'>"
				+ "<br>내용 <br>"
				+ "<textarea name='targets["+index+"].content' rows='5' border='1' style='width: 95%; height: 80;'>"
				+ "</textarea>"
				+ "<br>교통 <br>"
				+ "<input type='text'name='targets["+index+"].traffic' border='1' style='width: 100%; height: 25;'>";
		oCell3.innerHTML = "<input type='file' name='targets["+index+"].img'>";
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
	        $(this).find("input[name=traffic]").attr("name", "targets["+index+"].traffic");
	        $(this).find("input[name=img]").attr("name", "targets["+index+"].img");
	    });
	}
	
	//이미지 업로드 외의 데이터는 db에 올릴수 있다.
	//----------이미지 업로드 구현중----------
	
	
</script>
<body>
	<div id="pre_set" align="center">
		<form name="f" method="post" action="goView.do" enctype="multipart/form-data">
			<table id=dyntbl1 border=1 height="290" width="850">
				<tr onMouseOver="dyntbl1.clickedRowIndex=this.rowIndex">
					<td width="5%">board_num</td>

					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
						<!-- 여기에 subplan이 들어갑니다. --> 제목 <br> 
						<input type="text"name="targets[0].subject" border="1"style="width: 100%; height: 25;">
						<br>비용 <br>
						<input type="text" name="targets[0].price" border="1"style="width: 100%; height: 25;">
						<br>내용 <br>
						<textarea name="targets[0].content" rows="5" border="1"style="width: 95%; height: 80;">
						</textarea>
						<br>교통 <br>
						<input type="text"name="targets[0].traffic" border="1"style="width: 100%; height: 25;">
					</td>

					<td width="10%" height="100%"><input type="file"name="targets[0].img"></td>

					<td width="3%"></td>
				</tr>
			</table>
			<input type="button" value="추가" onClick="addRow()"> <input
				type="submit" value="저장">
		</form>
	</div>
</body>
</html>


