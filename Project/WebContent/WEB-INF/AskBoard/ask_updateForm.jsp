<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	
	<link rel="stylesheet" type="text/css" href="style.css">
	<title>문의 수정폼</title>
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
	<h3>글수정</h3>
	<form name="f" action="notice_updatePro.do" 
									method="post" onsubmit="return check()">
	<input type="hidden" name="no" value="${getNoticeBoard.num}"/>
	<table border="1" width="600">
<tr bgcolor="pink">
				<th colspan="2">문 의 수 정 란 입니다</th>
			</tr> 
		<tr>
			<th bgcolor="pink" width="80%">제목</th>
			<td><input type="text" name="subject" class="box" size="50" 
													value="${getNoticeBoard.subject}"></td>
		</tr>
		
			<tr>
			<th bgcolor="pink" width="80%">작성자</th>
			<td><input type="text" name="writer" class="box" size="50" 
													value="${getNoticeBoard.writer}"></td>
		</tr> 
				<tr>
			<th bgcolor="pink" width="20%">내용</th>
			<td><input type="text" name="content" class="box" 
													value="${getNoticeBoard.content}"></td>
		</tr>

		<tr>
			<th bgcolor="pink" width="20%">내 용</th>
			<td><textarea name="content" rows="12" cols="55" class="box">${getBoard.content}</textarea></td>
		</tr>
				<tr>
			<th bgcolor="pink" width="20%">이미지</th>
			<td><input type="file" name="img" class="box" size="50" 
													value="${getNoticeBoard.img}"></td>
		</tr>
		<tr>
			<th bgcolor="pink" width="20%">비밀번호</th>
			<td><input type="password" name="passwd" class="box"></td>
		</tr>
		<tr bgcolor="pink">
			<td align="center" colspan="2">
				<input type="submit" value="글수정">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" 
										onclick="window.location='Notice.do'">
			</td>
		</tr>
	</table>
	</form>	
</div>
</body>
</html>













