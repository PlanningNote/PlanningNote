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
					<td align="center" width="80%">${getComuBoard.no}</td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="20%">작성자</th>
					<td align="center" width="80%">${getComuBoard.writer}</td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="20%">글제목</th>
					<td align="center" width="80%" colspan="3">
						${getComuBoard.subject}</td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="20%">내 용</th>
					<td>
						<textarea name="content" width="80%"  align="center" rows="12" cols="55" class="box" /readonly>${getComuBoard.content}</textarea>
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="20%">이 미 지</th>
					<td>
						<c:if test="${not empty  getComuBoard.img}">
							<img src="imgfile/comuImg/${getComuBoard.img}" width="200">
						</c:if>
					</td>
				</tr>
								
				<tr>
					<th bgcolor="yellow" width="20%">조회수</th>
					<td align="center" width="80%">${getComuBoard.count}</td>
				</tr>
				
				<tr>	
					<th bgcolor="yellow" width="20%">작성일</th>
					<td align="center" width="80%">${getComuBoard.day}</td>
				</tr>	
	
				<tr bgcolor="yellow">
					<td colspan="4" align="right">
						<c:if test="${getComuBoard.writer == sessionScope.mynick}">
							<input type="button" value="글수정"
								onclick="window.location='comu_update.do?no=${getComuBoard.no}'">					
						<input type="button" value="글삭제"
						onclick="window.location='comu_delete.do?no=${getComuBoard.no}'">
						</c:if>						
						<input type="button" value="글목록"
						onclick="window.location='comu_list.do'">
					</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>














