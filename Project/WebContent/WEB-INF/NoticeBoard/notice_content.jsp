<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../top.jsp" %>
<tr>
	<td>
		<div align="center">
			<b>글내용 보기</b>
			<table border="1" width="600">
				<tr>
					<th bgcolor="yellow" width="20%">글번호</th>
					<td align="center" width="80%">${getNoticeBoard.no}</td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="20%">글제목</th>
					<td align="center" width="80%" colspan="3">
						${getNoticeBoard.subject}</td>
				</tr>
				<tr>					
					<th bgcolor="yellow" width="20%">내 용</th>
					<td width="80%">
						<textarea name="content" width="55%"  align="center" rows="12" cols="55" class="box" readonly>${getNoticeBoard.content}</textarea></td>
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="20%">이 미 지</th>
					<td width="80%">
						<c:if test="${not empty  getNotcieBoard.img}">
							<img src="imgfile/noticeImg/${getNotcieBoard.img}" width="200">
						</c:if>										
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="20%">조회수</th>
					<td align="center" width="35%">${getNoticeBoard.count}</td>
				</tr>
				
				<tr>	
					<th bgcolor="yellow" width="20%">작성일</th>
					<td align="center" width="35%">${getNoticeBoard.day}</td>
				</tr>
	
				<tr bgcolor="yellow">
					<td colspan="2" align="right">
						<input type="button" value="글목록" 	onclick="window.location='notice_list.do'">
					</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>
