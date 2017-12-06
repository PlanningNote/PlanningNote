<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>녀행자들홈페이지</title>
<style>
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

body {
	font-family: 나눔고딕, NanumGothic, NanumBarunGothic, 'Nanum Gothic', arial,
		verdana, sans-serif;
}

ul {
	list-style: none;
	margin: 0px;
	padding: 0px;
}

.one {
	float: center;
} /* 전체 메뉴를 float 시킴 */
.one>li {
	float: left;
} /*1단 메뉴를 일렬로 늘어놓기 */
.one ul {
	display: none;
	position: absolute;
	left: 450px;
	top: 25px;
} /*2단 메뉴를 숨기고, 절대 위치 */
.one li:hover>ul {
	display: block;
} /*1단 메뉴에 마우스 오버했을 때 2단 메뉴 드러내가 */
ul li a {
	text-decoration: none;
	display: block;
	width: 90px;
	height: 30px;
	line-height: 30px;
	text-align: center;
}

ul li {
	width: 90px;
	background-color: white;
}

ul li:hover {
	background-color: #FFE08C;
}

#header {
	position: relative;
	margin-left: 5px;
	zoom: 1;
}

#header:before, #header:after {
	content: " ";
	display: block;
	clear: both;
}

#header:after {
	clear: both;
}

#contents {
	border: 1px solid green;
	width: 50%;
	height: 300px;
	float: left;
	margin: 5px;
}

#footer {
	border: 1px solid green;
	width: 50%;
	height: 30px;
	clear: both;
	margin-left: 5px;
}
</style>
</head>
<body>

	<c:set var="isLogin" value="false" scope="session" />
	<c:if test="${not empty sessionScope.mynick}">
		<c:set var="isLogin" value="true" scope="session" />
	</c:if>

	<div style="padding: 0px 0px 0px 200px;">
		<table border="0">
			<tr>
				<td>
				<a href="index.jsp"><img src="img/newmain.jpg"	border="0"></a><!-- 로고 -->
				 <c:if test="${isLogin eq false}">
						<a href="login.do"><img src="img/loginIcon.png" border="0"></a><!-- 로그인 -->
						<a href="join_member.do"><img src="img/joinmember.png" border="0"></a><!-- 회원가입 -->
					</c:if> 
					<c:if test="${isLogin eq true}">
					<font face="verdana" size="5" style="text-decoration: none">
						<br>${sessionScope.mynick} 님 환영합니다. </font>
						<a href="mypage.do?nickname=${sessionScope.nickname}"><img src="img/mypage.png" border="0"></a><!-- 마이페이지 -->
						<a href="logout.do"><img src="img/logout.PNG" border="0"></a>	<!-- 로그아웃 -->

					</c:if></td>
			</tr>



		</table>
	</div>

	<div id="header" align="center" >
		<table border="1" width="1100">
			<tr height="60" align="center">
				<td style="padding: 0px 0px 0px 250px;">
					
						<ul class="one">
						  <c:if test="${isLogin eq false}">
							<li><a href="index.jsp"><img src="img/main.PNG" border="0"></a></li>
							<li><a href="howto_main.do"><img src="img/howto.PNG" border="0"></a></li>
							<li><a href="center_main.do"><img src="img/no11.PNG" border="0"></a>
								<ul>
								<li><a href="center_main.do"><img src="img/no11.PNG" border="0"></a></li><!-- 고객센터메인 -->
									<li><a href="notice_list.do"><img src="img/no1.PNG" border="0"></a></li><!-- 공지,faq,q&A -->
									<li><a href="FAQ_list.do"><img src="img/no2.PNG" border="0"></a></li>
									<li><a href="ask_list.do"><img src="img/no3.PNG" border="0"></a></li>
								</ul></li>
							<li><a href="notLogin.do"> <img src="img/comu.PNG" border="0"></a></li>
							<li><a href="notLogin.do"><img src="img/make.PNG" border="0"></a></li>
							<li><a href="notLogin.do"><img src="img/no10.PNG" border="0"></a></li>
							<li><a href="calendar.do"><img src="img/cal.PNG" border="0"></a></li>
					  </c:if>
					   <c:if test="${isLogin eq true}">
					 <li>  <a href="index.jsp"><img src="img/main.PNG" border="0"></a></li> <!--main --> 
                 	  <li>  <a href="howto_main.do"><img src="img/howto.PNG" border="0"></a></li><!--이용방법 --> 
                  	  <li>  <a href="center_main.do"><img src="img/no11.PNG" border="0"></a> <!--고객센터 --> 
                  	  								<ul>
								<li><a href="center_main.do"><img src="img/no11.PNG" border="0"></a></li><!-- 고객센터메인 -->
									<li><a href="notice_list.do"><img src="img/no1.PNG" border="0"></a></li><!-- 공지,faq,q&A -->
									<li><a href="FAQ_list.do"><img src="img/no2.PNG" border="0"></a></li>
									<li><a href="ask_list.do"><img src="img/no3.PNG" border="0"></a></li>
										</ul></li>
                  	  <li><a href="comu_main.do"> <img src="img/comu.PNG" border="0"></a></li> <!--커뮤니티 --> 
                  	 <li><a href="plan.do"><img src="img/make.PNG" border="0"></a></li><!--일정만들기-->
                  	 <li><a href="listPlanA.do"><img src="img/no10.PNG" border="0"></a></li>  <!--일정공유-->
                  	 <li><a href="calendar.do"><img src="img/cal.PNG" border="0"></a></li>   <!--일정공유-->
                 
					
					</c:if>
					</td>
			</tr>
			