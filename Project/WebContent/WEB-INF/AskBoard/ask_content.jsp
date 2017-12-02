<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%String path = session.getServletContext().getRealPath("files/askImg"); %> --%>



<%@ include file="../../top.jsp" %>


 
<tr>
	<td>
		<div align="center"> 
			<b>글내용 보기</b>
			<table border="1" width="600">
			 		
			<tr>
					<th bgcolor="yellow" width="15%">작성자</th>
					<td align="center" width="35%">${getAskBoard.writer}</td>
				</tr>	
				<tr>
					<th bgcolor="yellow" width="15%">글번호</th>
					<td align="center" width="35%">${getAskBoard.no}</td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="15%">글제목</th>
					<td align="center"  id="title"  width="85%" colspan="3">
						${getAskBoard.subject}</td>
				</tr> 
				<tr>				
					<th bgcolor="yellow" width="20%">내 용</th>
					<td>
						<textarea name="content"  width="55%" rows="12" cols="55"  readonly>${getAskBoard.content}
						</textarea></td>
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="20%">이 미 지</th>
					<td >
					<img src="imgfile/askImg/${getAskBoard.img}" width="500">
				<%-- 	<img src="<%=savePath%>/${getAskBoard.img}" width="300" />  --%>
					
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="15%">조회수</th>
					<td align="center" width="35%">${getAskBoard.count}</td>
				</tr>
				
				<tr>	
					<th bgcolor="yellow" width="15%">작성일</th>
					<td align="center" width="35%">${getAskBoard.day}</td>
				</tr>
	
	
				<tr bgcolor="yellow">
					<td colspan="4" align="right">
						<input type="button" value="글수정"
						onclick="window.location='ask_update.do?no=${getAskBoard.no}'">						
						<input type="button" value="글삭제"
						onclick="window.location='ask_delete.do?no=${getAskBoard.no}'">
						<input type="button" value="글목록"
						onclick="window.location='ask_list.do'"></td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>