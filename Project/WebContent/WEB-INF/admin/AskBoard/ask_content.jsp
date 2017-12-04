<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../admin_top.jsp"%>
<div align="center">
	<b>문의글내용 보기</b>
	<table border="1" width="650">
		<tr>
			<th bgcolor="yellow" width="15%" >글번호</th>
			<td align="center" width="85%">${getAskBoard.no}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%" >글제목</th>
			<td align="center" width="85%" >
				${getAskBoard.subject}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%" >내 용</th>
			<td>
				<textarea name="content" width="85%" rows="12" cols="65"  readonly>
				${getAskBoard.content}</textarea>
			</td>			
		</tr>

		<tr>
			<th bgcolor="yellow" width="85%">이 미 지</th>
			<td>
				<c:if test="${not empty getAskBoard.img}">
					<img src="imgfile/askImg/${getAskBoard.img}" width="200">
				</c:if>
			</td>
		</tr>

		<tr>
			<th bgcolor="yellow" width="85%">조회수</th>
			<td align="center" width="35%">${getAskBoard.count}</td>
		</tr>

		<tr>
			<th bgcolor="yellow" width="85%" >작성일</th>
			<td align="center" width="35%">${getAskBoard.day}</td>
		</tr>


		<tr bgcolor="yellow">
			<td colspan="2" align="right">
				<c:if test="${getAskBoard.writer == sessionScope.mynick}">
				<input type="button" value="글수정"
							onclick="window.location='admin_askUpdate.do?no=${getAskBoard.no}'">		
				</c:if>
				<input type="button" value="답글달기"
				onclick="window.location='admin_askReply.do?no=${getAskBoard.no}&re_group=${getAskBoard.re_group}&re_step=${getAskBoard.re_step}&re_level=${getAskBoard.re_level}'">
				<input type="button" value="글삭제"
				onclick="window.location='admin_askDelete.do?no=${getAskBoard.no}&pwd=${getAskBoard.pwd}'">
				<input type="button" value="글목록"
				onclick="window.location='admin_askList.do'">
			</td>
		</tr>
	</table>
</div>
<%@ include file="../admin_bottom.jsp"%>
