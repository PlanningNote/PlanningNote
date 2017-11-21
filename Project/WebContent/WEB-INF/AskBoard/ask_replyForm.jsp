<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="../../top.jsp" %>
<script type="text/javascript">
	function checkMember(){
		
		if (f.subject.value==""){
			alert("제목을 입력해 주세요!!")
			f.subject.focus()
			return false
		}
		if (f.content.value==""){
			alert("내용을 입력해 주세요!!")
			f.content.focus()
			return false
		}
		if (f.pwd.value==""){
			alert("비밀번호를 입력해 주세요!!")
			f.pwd.focus()
			return false
		}
		return true
	}
	</script>
<tr>
	<td>
		<div align="center">
			<h3>답 글 요 ~~~~~</h3>
			<form name="f" action="ask_reply.do" 
									method="post" onsubmit="return check()">
			<input type="hidden" name="no" value="${getAskBoard.no}"/> 
				<table border="1" width="600">
					<tr bgcolor="red">
					<tr bgcolor="grey">
						<td align="center" colspan="2">답 글 쓰 기</td>
					</tr>
					<tr>
						<th bgcolor="grey" width="20%">이 름</th>
						<td><input type="text" name="writer" class="box"></td>
				   </tr>
					<tr>
						<th bgcolor="grey" width="80%">제목</th>
						<td><input type="text" name="subject" class="box" size="50" 
																value="[답글]${getAskBoard.subject}"></td>
					</tr>
					<tr>
						<th bgcolor="grey" width="25%">내 용</th>
						<td><textarea name="content"  rows="12" cols="65" size="50"  class="box">${getAskBoard.content}</textarea></td>
					</tr>
					<tr>
						<th bgcolor="grey" width="20%">이미지</th>
						<td><input type="text" name="img" class="box" size="50" 
																value="${getAskBoard.img}"></td>
					</tr>
					<tr>
						<th bgcolor="grey" width="20%">비밀번호</th>
						<td><input type="password" name="pwd" class="box"></td>
					</tr>
					<tr bgcolor="grey">
						<td align="center" colspan="2">
							<input type="submit" value="답글달기">
							<input type="reset" value="취소">
							<input type="button" value="목록보기" 
													onclick="window.location='ask_list.do'">
						</td>
					</tr>
				</table>
			</form>	
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>












