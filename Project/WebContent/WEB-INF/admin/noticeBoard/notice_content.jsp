<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../admin_top.jsp"%>
<div align="center">
	<b>공지사항 글내용 보기(관리자)</b>
	<table border="1" width="650">
		<tr>
			<th bgcolor="yellow" width="20%">글번호</th>
			<td align="center" width="80%">${getNoticeBoard.no}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">글제목</th>
			<td align="center" width="80%" colspan="3">${getNoticeBoard.subject}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">내 용</th>
			<td width="80%">
				<textarea name="content" rows="12" cols="60"  readonly>
				${getNoticeBoard.content}</textarea>
			</td>
		</tr>

		<tr>
			<th bgcolor="yellow" width="20%">이 미 지</th>
			<td width="80%">
				<c:if test="${not empty getNoticeBoard.img}">
					<img src="imgfile/noticeImg/${getNoticeBoard.img}" width="200">
				</c:if>
			</td>
		</tr>

		<tr>
			<th bgcolor="yellow" width="20%">조회수</th>
			<td align="center" width="80%">${getNoticeBoard.count}</td>
		</tr>

		<tr>
			<th bgcolor="yellow" width="20%">작성일</th>
			<td align="center" width="80%">${getNoticeBoard.day}</td>
		</tr>

		<tr bgcolor="yellow">
			<td colspan="2" align="right">
				<input type="button" value="글수정" onclick="window.location='admin_noticeUpdate.do?no=${getNoticeBoard.no}'">
				<input type="button" value="글삭제" onclick="window.location='admin_noticeDelete.do?no=${getNoticeBoard.no}'">
				<input type="button" value="글목록" onclick="window.location='admin_noticeList.do'"></td>
		</tr>
	</table>
</div>
<%@ include file="../admin_bottom.jsp"%>
