<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../../top.jsp" %>
<tr>
	<td>
		<div align="center">
			<b>글 삭 제</b>
			<form name="f" action="comu_delete.do" method="post">
				<input type="hidden" name="no" value="${param.no}"/>
					<table border="1" width="300">
						<tr bgcolor="yellow">
							<th>비밀번호를 입력해 주세요</th>
						</tr>
						<tr>
							<td align="center">
								비밀번호 : <input type="password" name="pwd" class="box">
							</td>
						</tr>
						<tr bgcolor="yellow">
							<td align="center">
								<input type="submit" value="글삭제">
								<input type="button" value="글목록" 
											onclick="window.location='comu_list.do'">
							</td>								
						</tr>
					</table>
			</form>
		</div>
	</td>
</tr>
<%@include file="../../bottom.jsp" %>