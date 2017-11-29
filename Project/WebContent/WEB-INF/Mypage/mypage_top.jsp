<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>마이페이지</title>
	</head>
<body>
	<div style="padding:0px 0px 0px 300px;">
			<table border="0"  >
				<tr>
					<td>
						<a href="mypage_main.do"><img src="img/planlogo.jpg" border="0"></a><!-- 로고 -->
						<a href="logout.do"><img src="img/logout.PNG" border="0"></a><!-- 로그아웃--> 	
											
					</td>
				</tr>
			</table>	
	</div>
	<div align="center">			
			<table border="1" width="1100" height="700" >
				<tr height="60" align="center">
					<td colspan="2">
							<a href="index.jsp"><img src="img/main.PNG" border="0"></a> <!--main --> 
							<a href="howto_main.do"><img src="img/howto.PNG" border="0"></a><!--이용방법 --> 
							<a href="center_main.do"><img src="img/no11.PNG" border="0"></a> <!--고객센터 --> 
							<a href="notLogin.do"> <img src="img/comu.PNG" border="0"></a> <!--커뮤니티 --> 
							<a href="notLogin.do"><img src="img/make.PNG" border="0"></a><!--일정만들기-->
							<a href="notLogin.do"><img src="img/no10.PNG" border="0"></a><!--일정공유-->
					</td>
				</tr>
				<tr>
					<td width="15%">
						<div align="left">
								<ul>
									<a href="mypage_update.do"><li>회원수정</li></a><!-- 회원수정-->
									<a href=""><li>내가 쓴 글</li></a> <!-- 내가 쓴 글 -->
									<a href=""><li>내가 좋아요 누른 글</li></a>
								</ul>
							
						</div>
					</td>
					<td width="85%">
				