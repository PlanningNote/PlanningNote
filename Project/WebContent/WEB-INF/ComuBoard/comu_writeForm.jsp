<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ include file="../../top.jsp" %>
<style type="text/css">
		#title{
			height : 16;
			font-family :'궁서';
			font-size : 12;
			text-align :center;
		}
	</style>
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
<tr>
	<td>
		<div align="center">
			<form name="f" method="post"  
				action="comu_write.do" onsubmit="return checkMember()">							
				<table border="1" width="600">
					<tr bgcolor="pink">
						<th colspan="2">커뮤니티 글  쓰 기</th>
					</tr> 
				
					<tr>
						<th bgcolor="pink" width="20%">제 목</th>
						<td width="80%"><input type="text" name="subject" size="50"></td>
					</tr>
					
					<tr>
						<th bgcolor="pink" width="20%">내 용</th>
						<td width="80%">
							<textarea name="content" rows="10" cols="50"></textarea>
						</td>
					</tr>
					
					 <tr>
						<th>이미지</th>
						<td><input type="file" name="img"></td>
					</tr>
				
					<tr>
						<th bgcolor="pink" width="20%">비밀번호</th>
						<td width="80%"><input type="password" name="pwd"></td>
					</tr>
					<tr>
						<td bgcolor="pink" colspan="2" align="center">
							<input type="submit" value="글쓰기">
							<input type="reset" value="다시작성">
							<input type="button" value="목록보기" 
														onclick="window.location='comu_list.do'">
						</td>						
					</tr>
				</table>
			</form>
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>