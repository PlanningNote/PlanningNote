<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../admin_top.jsp"%>
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
		if (f.passwd.value==""){
			alert("비밀번호를 입력해 주세요!!")
			f.passwd.focus()
			return false
		}
		return true
	}
	</script>
<div align="center">
	<h3>글 수 정</h3>
	<form name="f" action="admin_FAQUpdate.do" method="post"
		onsubmit="return check()">
		<input type="hidden" name="no" value="${getFAQBoard.no}" />
		<table border="1" width="600">
			<tr bgcolor="red">
				<th colspan="2">FAQ 수 정 란 입니다</th>
			</tr>
			<tr>
				<th bgcolor="pink" width="20%">제목</th>
				<td width="80%">
					<input type="text" name="subject" class="box" 
					value="${getFAQBoard.subject}">
				</td>
			<tr>
				<th bgcolor="pink" width="20%">내 용</th>
				<td width="80%">
					<textarea name="content" rows="12" cols="55" 
						class="box">${getFAQBoard.content}</textarea>
				</td>
			</tr>

			<tr>
				<th bgcolor="pink" width="20%">이미지</th>
				<td width="80%">
					<input type="file" name="img" size="50">
					<input type="hidden" name="beforeimg" value="${getFAQBoard.img}">	
				</td>
			</tr>

			<tr>
				<th bgcolor="pink" width="20%">비밀번호</th>
				<td width="80%"><input type="password" name="pwd" class="box"></td>
			</tr>

			<tr bgcolor="pink">
				<td align="center" colspan="2">
					<input type="submit" value="글수정">
					<input type="reset" value="취소"> 
					<input type="button" 	value="목록보기" onclick="window.location='admin_FAQList.do'">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="../admin_bottom.jsp"%>













