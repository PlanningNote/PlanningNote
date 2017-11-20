<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	
</head>
<body>	
<div align="center">
	
		<input type="hidden" name="no" value="${param.no}"/>
		<input type="hidden" name="re_group" value="${param.re_group}"/>
		<input type="hidden" name="re_step" value="${param.re_step}"/>
		<input type="hidden" name="re_level" value="${param.re_level}"/>
	<table border="1" width="600">
		<tr bgcolor="grey">
			<td align="center" colspan="2">답 글 쓰 기</td>
		</tr>
				<tr>
			<th bgcolor="yellow" width="20%">작 성 자</th>
			<td><input type="text" name="writer" class="box"></td>
		</tr>
		
		<tr>
			<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	
	<link rel="stylesheet" type="text/css" href="style.css">
	<title>업데이트폼</title>
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
</head>
<body>
<div align="center">
	<h3>글  수  정~~~~</h3>
	<form name="f" action="notice_update.do" 
									method="post" onsubmit="return check()">
	<input type="hidden" name="no" value="${getNoticeBoard.no}"/> 
	<table border="1" width="600">
<tr bgcolor="red">
				<th colspan="2">공 지  수 정 란 입니다</th>
			</tr> 
		<tr>
			<th bgcolor="pink" width="80%">제목</th>
			<td><input type="text" name="subject" class="box" size="50" 
													value="${getNoticeBoard.subject}"></td>
		

		<tr>
			<th bgcolor="pink" width="25%">내 용</th>
			<td><textarea name="content"  rows="12" cols="65" size="50"  class="box">${getNoticeBoard.content}</textarea></td>
		</tr>
				<tr>
			<th bgcolor="pink" width="20%">이미지</th>
			<td><input type="text" name="img" class="box" size="50" 
													value="${getNoticeBoard.img}"></td>
		</tr>
		<tr>
			<th bgcolor="pink" width="20%">비밀번호</th>
			<td><input type="password" name="passwd" class="box"></td>
		</tr>
		<tr bgcolor="pink">
			<td align="center" colspan="2">
				<input type="submit" value="글수정">
				<input type="reset" value="취소">
				<input type="button" value="목록보기" 
										onclick="window.location='notice_list.do'">
			</td>
		</tr>
	</table>
	</form>	
</div>
</body>
</html>













			<th bgcolor="yellow" width="20%">제 목</th>
			<td><input type="text" name="subject" class="box" size="50" value="[답글]"></td>
		</tr>
	
	
		<tr>
			<th bgcolor="yellow" width="20%">내 용</th>
			<td><textarea name="content" rows="12" cols="55" class="box" ></td>
		</tr>
	
		<tr>
			<th bgcolor="yellow" width="20%">비밀번호</th>
			<td><input type="password" name="pwd" class="box"></td>
		</tr>
	
		
		<tr bgcolor="yellow">
			<td align="center" colspan="2">
				<input type="submit" value="글쓰기">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" onclick="window.location='ask_list.do'">
			</td>
		</tr>
		
	</table>

</div>
</body>
</html>