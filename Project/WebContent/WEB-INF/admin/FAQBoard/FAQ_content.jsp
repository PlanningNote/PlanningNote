<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../admin_top.jsp"%>
<div align="center">
	<b>FAQ내용 보기(관리자용)</b>
	<table border="1" width="650">
		<tr>
			<th bgcolor="yellow" width="15%">글번호</th>
			<td align="center" width="35%">${getFAQBoard.no}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">글제목</th>
			<td align="center" width="85%" colspan="3">
				${getFAQBoard.subject}</td>
		</tr>

		<tr>
			<th bgcolor="yellow" width="15%">내 용</th>
			<td><textarea name="content" width="55%" align="center"
					rows="12" cols="90" size="50"class="box" /readonly>${getFAQBoard.content}</textarea></td>
			</td>
		</tr>

		<tr>
			<th bgcolor="yellow" width="15%">이 미 지</th>
			<td><img
				src="imgfile/faqImg/${getFAQBoard.img}"	width="300" /></td>
		</tr>

		<tr>
			<th bgcolor="yellow" width="15%">조회수</th>
			<td align="center" width="35%">${getFAQBoard.count}</td>
		</tr>

		<tr>
			<th bgcolor="yellow" width="15%">작성일</th>
			<td align="center" width="35%">${getFAQBoard.day}</td>
		</tr>


		<tr bgcolor="yellow">
			<td colspan="4" align="right">
				<input type="button" value="글수정"
				onclick="window.location='admin_FAQUpdate.do?no=${getFAQBoard.no}'">
				<input type="button" value="글삭제"
				onclick="window.location='admin_FAQDelete.do?no=${getFAQBoard.no}'">
				<input type="button" value="글목록"
				onclick="window.location='admin_FAQList.do'"></td>
		</tr>
	</table>
</div>
<%@ include file="../admin_bottom.jsp"%>
