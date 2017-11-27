<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../admin_top.jsp" %>
<script>
	function deleteMember(memberNo){
		if(confirm("정말 삭제하시겠습니까?") == true){
			if(confirm("진짜로 삭제하시겠습니까??") == true){
				location.href="admin_memberDeleteForm.do?no="+memberNo;
			}
		}
		
	}
	
</script>
	<table id="tablesort" border="1" width="100%"  >      
        <thead>
			<tr>
				<th>회원번호</th>
				<th>닉네임</th>
				<th width="50%">이메일</th>
				<th>성별</th>
				<th>삭제</th>
			</tr>
		</thead>		
		<tbody>		
			<c:forEach var="dto" items="${getList}">
				<c:if test="${dto.nickname != 'admin'}">	
					<tr>
						<td>${dto.no} </td>
						<td>${dto.nickname}</td>
						<td>${dto.email}</td>
						<td>${dto.gender}</td>
						<td><input type="button" value="삭제" onclick="deleteMember(${dto.no})"></td>
					</tr>			
				</c:if>					
			</c:forEach>
		</tbody>
	</table>
</div>
<%@ include file="../admin_bottom.jsp"%>