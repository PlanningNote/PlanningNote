<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../top.jsp" %>
<script>
	function backPage(){
		location.href="comu_list.do";
	}
</script>
<tr>
<td>
	<div align="center">
		<h2>신 고 하 기</h2>
		<form name="f" method="post"  action="memReport.do" enctype="multipart/form-data">
			<table border="1">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="reporter" value="${reporter}" readonly></td>
				</tr>
				<tr>
					<th>신고 닉네임</th>
					<td><input type="text" name="suspecter" value="${suspecter}" readonly></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" rows="10" cols="50"></textarea></td>
				</tr>			
				<tr>
					<th>이미지</th>
					<td><input type="file" name="img"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="신고" >
						<input type="reset" value="다시쓰기">
						<input type="button" value="취소" onClick="backPage()">
					</td>
				</tr>
			</table>
		</form>
	</div>
</td>
</tr>
<%@ include file="../../../bottom.jsp" %>