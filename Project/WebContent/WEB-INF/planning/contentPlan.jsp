<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>플랜상세보기</title>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="content.do">
			<table border="1" WIDTH="1000" HEIGHT="750">
				<tr>
					<td COLSPAN="2">이미지</td>
				</tr>
				<tr>
					<td COLSPAN="2">제목</td>
					<input type="hidden" name="subject" border="1" yle="width: 100%; height: 25;" VALUE="${subject}">
				</tr>
				<tr>
					<td COLSPAN="2">내용</td>
				</tr>
				<tr>
					<td WIDTH="50%">교통</td>
					<td>예산: 원</td>
				</tr>
				<tr>
					<td COLSPAN="2">지도</td>
				</tr>
			</table>
			<a href="list.do">뒤로가기</a>
		</form>
	</div>
</body>
</html>