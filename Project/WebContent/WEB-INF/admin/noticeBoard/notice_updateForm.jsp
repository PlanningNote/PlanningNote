<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<%@ include file="../admin_top.jsp"%>

<div align="center">
	<h3>공지사항 글 수 정~~~~(관리자모드)</h3>
	<form name="f" action="admin_noticeUpdate.do" method="post"
		onsubmit="return check()" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${getNoticeBoard.no}" />
		<table border="1" width="600">
			<tr bgcolor="red">
				<th colspan="2">공 지 수 정 란(관리자) 입니다</th>
			</tr>
			<tr>
				<th bgcolor="pink" width="80%">제목</th>
				<td><input type="text" name="subject" class="box" size="50"
					value="${getNoticeBoard.subject}"></td>
			</tr>
			<tr>
				<th bgcolor="pink" width="25%">내 용</th>
				<td><textarea name="content" rows="12" cols="65" size="50"
						class="box">${getNoticeBoard.content}</textarea></td>
			</tr>
			<tr>
				<th bgcolor="pink" width="20%">이미지</th>
				<td><input type="file" name="img" size="50"></td>
					<input type="hidden" name="beforeimg" value="${getNoticeBoard.img}">
			</tr>
			<tr>
				<th bgcolor="pink" width="20%">비밀번호</th>
				<td><input type="password" name="pwd" class="box"></td>
			</tr>
			<tr bgcolor="pink">
				<td align="center" colspan="2"><input type="submit" value="글수정">
					<input type="reset" value="취소"> <input type="button"
					value="목록보기" onclick="window.location='admin_noticeList.do'"></td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="../admin_bottom.jsp"%>











