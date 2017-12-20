<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../top.jsp" %>
<script>
	 function member(writer){
		 window.open("nicknameClick.do?nickname="+writer,"chkForm", "width=300, height=250, resizable = no, scrollbars = no");
	}
</script>
<tr>
	<td>
		<div align="center">
		<br>
			<table border="1" width="600">
				<tr>
					<td width="40%">
						<a href="#" onclick="member('${getComuBoard.writer}');">${getComuBoard.writer}</a>
					</td>
					<td width="40%">
						${getComuBoard.day}
					</td>
					<td width="20%">
						${getComuBoard.count}
					</td>
				</tr>
				<tr>
					<td colspan="3">
						${getComuBoard.subject}
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<textarea name="content" rows="5" cols="70" class="box" readonly>${getComuBoard.content}</textarea>
					</td>
				</tr>
				<c:if test="${not empty  getComuBoard.img}">
					<tr>
						<td colspan="3">
							<img src="imgfile/comuImg/${getComuBoard.img}" width="400">
						</td>
					</tr>							
				</c:if>				
			</table>
			<table border="0">
				<tr>
					<td align="right">
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
			<hr>
			<form name="f" action="insertComuReply.do" method="post">
				<textarea name="content" rows="5" cols="70" class="box">댓글을 입력해주세요</textarea><br>
				<input type="submit" value="댓글작성">
				<input type="hidden" name="comu_no" value="${getComuBoard.no}">
				<input type="hidden" name="writer" value="${sessionScope.mynick}">
			</form>
			
			<table border="0" width="600">
			<c:forEach var="list" items="${comuReplyList}">
				<tr align="left">
					<td align="left">
						<a href="#" onclick="member('${list.writer}');">${list.writer}</a>(${list.day})<br>
						${list.content}
					</td>				
				</tr>			
			</c:forEach>
			</table>
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>














