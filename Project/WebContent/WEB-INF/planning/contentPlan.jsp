<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>플랜상세보기</title>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="content.do">
			<table border="1" WIDTH="1000" HEIGHT="900">
				<tr HEIGHT="40%">
					<td COLSPAN="3">이미지 
					<input type="hidden" name="img" border="1" style="width: 100%; height: 25;" VALUE="${img}">
					</td>
				</tr>
				<tr>
					<td COLSPAN="3">제목
					<input type="hidden" name="subject" border="1" style="width: 100%; height: 25;" VALUE="${subject}">
					</td>
				</tr>
				<tr HEIGHT="50%">
					<td COLSPAN="2">내용 
					<input type="hidden" name="content" border="1" style="width: 100%; height: 25;" VALUE="${content}">
					</td>
					<td ROWSPAN="2" WIDTH="40%">지도 
					<input type="hidden" name="map" border="1" style="width: 100%; height: 25;" VALUE="${map}">
					</td>
				</tr>
				<tr>
					<td WIDTH="37%">교통 
					<input type="hidden" name="trffic" border="1" style="width: 100%; height: 25;" VALUE="${trffic}">
					</td>
					<td>예산: <input type="hidden" name="price" border="1" style="width: 100%; height: 25;" VALUE="${price}">원
					</td>
				</tr>
				<tr>
					
				</tr>
			</table>
			<a href="list.do">뒤로가기</a>
		</form>
	</div>
</body>
</html>