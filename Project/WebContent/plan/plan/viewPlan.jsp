<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>�÷�����Ʈ</title>
</head>
<body>
	<div id="pre_set" align="center">
		<form name="f" method="post" action="">
			<table id="addTable" border="1" align="center" height="290"
				width="850">
				<tr>
					<td width="5%">board_num</td>
					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
						<!-- ���⿡ subplan�� ���ϴ�. --> ���� <br>
					<input type="text" name="subject" border="1"
						style="width: 100%; height: 25;"> <br>��� <br>
					<input type="text" name="price" border="1"
						style="width: 100%; height: 25;"> <br>���� <br>
					<textarea name="content" rows="5" border="1"
							style="width: 95%; height: 80;"></textarea> <br>���� <br>
					<input type="text" name="trffic" border="1"
						style="width: 100%; height: 25;">
					</td>
					<td width="10%" height="100%"><input type="file" name="img">
					</td>
					<td width="3%" height="100%"><input type="button" value="����"
						onclick="remove_item(this)" align="center"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="field"></div>
	<!-- ���̰� �׳� �ٹٲް����� -->

	<div align="center">
		<input type="button" value=" �߰� " onclick="add_item()"> <input
			type="submit" value="�����ϱ�">
	</div>

</body>
</html>