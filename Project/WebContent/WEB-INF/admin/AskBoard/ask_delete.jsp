<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../admin_top.jsp"%>
<script type="text/javascript">
	function check(){
		if(confirm("정말 삭제하시겠습니까?")){
			if(confirm("진짜로 삭제하시겠습니까??")){
				return true
			}else return false
		}else return false
	}
</script>
<div align="center">
	<b>글 삭 제</b>
	<form name="f" action="admin_askDelete.do" method="post" onsubmit="return check()">
		<input type="hidden" name="no" value="${param.no}" >
		<input type="hidden" name="board_pwd" value="${param.pwd}">
		<table border="1" width="300">
			<tr bgcolor="yellow">
				<th>관리자비밀번호를 입력해 주세요</th>
			</tr>
			<tr>
				<td align="center">비밀번호 : <input type="password" name="pwd"
					class="box">
				</td>
			</tr>
			<tr bgcolor="yellow">
				<td align="center">
					<input type="submit" value="글삭제">
				 	<input type="button" value="글목록" onclick="window.location='admin_askList.do'">
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="../admin_bottom.jsp"%>











