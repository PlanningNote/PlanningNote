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
			<img src="img/imgmy.PNG" ><br><img src="img/myduck.gif" >
				<tr >
					<a href="mypage_update.do"><img src="img/changepw.png" border="0"></a>&nbsp <!-- 비번변경 / 밑은 회원탈퇴 -->
					<a href="mypage_delete.do"><img src="img/bye.png" border="0"></a>	
				</tr>
			</table>
			<table border="0" width="70%">
				<tr  style="vertical-align:top">
				
					<td style="width:190px; border-right:1px ; padding-right:12px; text-align:justify"> 
						<table border="1" width="100%">
							<caption><img src="img/mywrite.png" border="0"></caption><!-- 내가쓴글 -->
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
							<caption><img src="img/mylike.png" border="0"></caption>
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