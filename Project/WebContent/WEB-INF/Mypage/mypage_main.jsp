<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>마이페이지</title>
<style type="text/css">
	div
	#d1 {
        width: 580px;
        padding: 20px;
        margin-bottom: 20px;
        float: left;
        border: 1px solid #bcbcbc;
      }
      #d2 {
        width: 260px;
        padding: 20px;
        margin-bottom: 20px;
        float: right;
        border: 1px solid #bcbcbc;
      }
</style>
</head>
<body>
	<div align="center">
			<table align="center" >
			<img src="img/mp2.PNG" >
				<tr>
					<a href="mypage_update.do">비밀번호 변경</a>
					<a href="mypage_delete.do">회원탈퇴</a>	
				</tr>
			</table>
			<table border="0" width="70%">
				<tr>
				
					<td width="50%">
						<table border="1" width="100%">
							<caption>내가 쓴 글</caption>
							<tr>
								<th width="10%">번호</th>
								<th width="30%">제목</th>
								<th width="30%">나라</th>
								<th width="30%">도시</th>
							</tr>
							<c:forEach items="${dtoP}" var="dtoP" >
							<tr>
								<td>${dtoP.getGroup_no()}</td>
								<td><a href="">${dtoP.getSubject()}</a></td>
								<td>${dtoP.country}</td>
								<td>${dtoP.city}</td>
							</tr>
							</c:forEach>
						</table>	
					</td>
					
					<td width="50%">
						<table border="1" width="100%">
							<caption>내가 좋아요 누른 글</caption>
							<tr>
								<td>제목</td>
								<td>내용</td>
							</tr>
						</table>	
					</td>
				</tr>
			</table>
			
			
			
		</div>
</body>
</html>
<%@ include file="../../bottom.jsp"%>