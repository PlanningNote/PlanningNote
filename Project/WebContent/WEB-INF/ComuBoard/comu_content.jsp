<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<title>공 지 사 항 보 기</title>
</head>
<body>
	<div align="center">
		<b>글내용 보기</b>
		<table border="1" width="600">
			<tr>
				<th bgcolor="yellow" width="15%">글번호</th>
				<td align="center" width="35%">${getComuBoard.no}</td>
			</tr>
			<tr>
				<th bgcolor="yellow" width="15%">글제목</th>
				<td align="center" width="85%" colspan="3">
					${getComuBoard.subject}</td>
			</tr>
			<tr>
				<tr>
			<th bgcolor="yellow" width="20%">내 용</th>
			<td ><textarea name="content" width="55%"  align="center" rows="12" cols="55" class="box" /readonly>${getComuBoard.content}</textarea></td>
	
				
					</td>
			</tr>
			
			<tr>
			<th bgcolor="yellow" width="15%">조회수</th>
			<td align="center" width="35%">${getComuBoard.count}</td>
			</tr>
			
			<tr>

				<th bgcolor="yellow" width="15%">작성일</th>
				<td align="center" width="35%">${getComuBoard.day}</td>
			</tr>


			<tr bgcolor="yellow">
				<td colspan="4" align="right">
					<input type="button" value="글수정"
					onclick="window.location='comu_update.do?no=${getComuBoard.no}'">
					<input type="button" value="답글달기"
					onclick="window.location='comu_reply.do?no=${getComuBoard.no}&re_group=${getComuBoard.re_group}&re_step=${getComuBoard.re_step}&re_level=${getComuBoard.re_level}'">
					
					<input type="button" value="글삭제"
					onclick="window.location='comu_delete.do?no=${getComuBoard.no}'">
					<input type="button" value="글목록"
					onclick="window.location='comu_list.do'"></td>
			</tr>
		</table>
	</div>
</body>
</html>
















