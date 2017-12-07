<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ include file="../../top.jsp"%>
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
			<form name="f" action="comu_update.do" 
									method="post" onsubmit="return check()">
			<input type="hidden" name="no" value="${getComuBoard.no}"/>
				<table border="1" width="600">
					<tr bgcolor="pink">
						<th colspan="2">글   수   정</th>
					</tr> 
					<tr>
						<th bgcolor="pink" width="80%">제목</th>
						<td><input type="text" name="subject" class="box" size="50" 
																value="${getComuBoard.subject}"></td>
					</tr>	
					<tr>
						<th bgcolor="pink" width="80%">작성자</th>
						<td><input type="text" name="writer" class="box" size="50" 
																value="${getComuBoard.writer}" readonly></td>
					</tr> 					
					<tr>
						<th bgcolor="pink" width="20%">내 용</th>
						<td><textarea name="content" rows="12" cols="55" class="box">${getComuBoard.content}</textarea></td>
					</tr>
					<tr>
						<th bgcolor="pink" width="20%">이미지</th>
						<td>
							<input type="file" name="img"  size="50">
							<input type="hidden" name="beforeimg" value="${getComuBoard.img}">	
						</td>
					</tr>
					<tr>
						<th bgcolor="pink" width="20%">비밀번호</th>
						<td><input type="password" name="pwd" class="box"></td>
					</tr>
					<tr bgcolor="pink">
						<td align="center" colspan="2">
							<input type="submit" value="글수정">
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
<%@ include file="../../bottom.jsp"%>