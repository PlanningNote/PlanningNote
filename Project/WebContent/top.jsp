<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- top.jsp -->
<html>
<head>
	<title>홈페이지</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript">
	
	</script>
</head>
<body>
	<div align="center">
		<table border="1" width="900" height="400">
			<tr height="10%">
				<td colspan="2" align="right">
				<img src="img/logo.jpg">
					<a href="<%=request.getContextPath()%>/index.jsp">main</a> | 
					<a href="<%=request.getContextPath()%>/login/login.jsp">로그인</a> | 
					<a href="javascript:joinMember()">회원가입</a> 
					
				</td>
			</tr>
			<tr>
				<td width="15%" valign="top" align="left">
				<br><br><br><li>
				<a href="<%=request.getContextPath()%>/inform.jsp">공지사항</a> <br>
				<br><li>
				<a href="<%=request.getContextPath()%>/index.jsp">추천여행지</a> <br>
				<br><li>
				<a href="<%=request.getContextPath()%>/index.jsp">여행후기</a> <br>
				<br><li>
				<a href="<%=request.getContextPath()%>/index.jsp">내일정만들기</a> <br>
				<br><li>
				<a href="<%=request.getContextPath()%>/howto.jsp">이용방법</a> <br>
				<br>
				
				</li>
				</tr>
				

				
				
				</td>
				<td width="80%">
				
				
				
				
				
				
				
				