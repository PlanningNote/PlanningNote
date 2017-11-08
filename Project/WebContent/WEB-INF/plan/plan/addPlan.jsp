<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>플랜리스트</title>
</head>
<script language="javascript">

var board_num=0;
	function add_item() {
		// pre_set 에 있는 내용을 읽어와서 처리..
		board_num+=1;
		var div = document.createElement('div');
		div.innerHTML = document.getElementById('pre_set').innerHTML;
		document.getElementById('field').appendChild(div);
	}

	function remove_item(obj) {
		// obj.parentNode 를 이용하여 삭제
		document.getElementById('field').removeChild(obj.parentNode);
	}
	function reset() { 
        document.f.subject.value=""; 
        document.f.price.value="";
        document.f.content.value="";
        document.f.traffic.value="";
} 
	
</script>
<!-- 테이블 열 하나 추가하면 맨 처음게 똑같이 복사되서 추가가 된다.ㅠㅠ
	추후 고칠 예정.
	board_num 테이블 열을 구해서 넣을것임. 추가 예정 
	placeholder란 것을 알아냈는데, 어떻게 쓰는지 모르겠음.. 추가예정
 -->
<body>
	<div id="pre_set" align="center">
		<form name="f" method="post" action="addplan.do">
			<table id="addTable" border="1" align="center" height="290"	width="850">
				<tr>
					<td width="5%">board_num</td>
					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
							<!-- 여기에 subplan이 들어갑니다. -->
							제목 <br><input type="text" name="subject" border="1" style="width:100%; height:25;">
							<br>비용 <br><input type="text" name="price" border="1" style="width:100%; height:25;">
							<br>내용 <br><textarea name="content" rows="5" border="1"  style="width:95%; height:80;"></textarea>
							<br>교통	<br><input type="text" name="trffic" border="1" style="width:100%; height:25;">
					</td>
					<td  width = "10%" height="100%">
						<input type="file" name = "img">
					</td>
					<td width="3%" height="100%"><input type="button" value="삭제"
						onclick="remove_item(this)" align="center"></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="field"></div> <!-- ←이거 그냥 줄바꿈같은거 -->
	
	<div align="center">
		<input type="button" value=" 추가 " onclick="add_item()"> <input
			type="submit" value="저장하기">
	</div>
</body>
</html>

