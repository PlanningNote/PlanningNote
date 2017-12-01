<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		if (f.pwd.value==""){
			alert("비밀번호를 입력해 주세요!!")
			f.pwd.focus()
			return false
		}
		return true
	}
	</script>
<div align="center">
	<h3>답 글 요 ~~~~~</h3>
	<form name="f" action="admin_askReply.do" method="post"
		onsubmit="return check()" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${param.no}" /> <input
			type="hidden" name="re_group" value="${param.re_group}" /> <input
			type="hidden" name="re_step" value="${param.re_step}" /> <input
			type="hidden" name="re_level" value="${param.re_level}" />
		<table border="1" width="600">
			<tr bgcolor="red">
			<tr bgcolor="grey">
				<td align="center" colspan="2">답 글 쓰 기</td>
			</tr>
			<tr>
				<th bgcolor="grey" width="20%">이 름</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th bgcolor="grey" width="80%">제목</th>
				<td><input type="text" name="subject" size="50" value="[답글]"></td>
			</tr>
			<tr>
				<th bgcolor="grey" width="25%">내 용</th>
				<td><textarea name="content" rows="12" cols="65" size="50"></textarea></td>
			</tr>
			<tr>
				<th bgcolor="grey" width="20%">이미지</th>
				<td><input type="file" name="img"></td>
			</tr>
			<tr>
				<th bgcolor="grey" width="20%">비밀번호</th>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr bgcolor="grey">
				<td align="center" colspan="2"><input type="submit"
					value="답글달기"> <input type="reset" value="취소"> <input
					type="button" value="목록보기" onclick="window.location='admin_askList.do'">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="../admin_bottom.jsp"%>













