<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>녀행자들홈페이지</title>
	<link rel="stylesheet" href="style.css">

</head>
<c:set var="isLogin" value="false" scope="session"/>
<c:if test="${not empty sessionScope.mynick}">
	<c:set var="isLogin" value="true" scope="session"/>
</c:if>
<body> 
		<div style="padding:0px 0px 0px 300px;">
			<table border="0"  >
				<tr>
					<td>
						<a href="index.jsp"><img src="img/planlogo.jpg" border="0"></a><!-- 로고 -->
						<c:if test="${isLogin eq false}">
							<a href="login.do"><img src="img/login2.PNG" border="0"></a><!-- 로그인 -->
							<a href="join_member.do"><img src="img/join.PNG" border="0"></a><!-- 회원가입 -->
						</c:if>
						<c:if test="${isLogin eq true}">
							<a href=""><img src="img/logout.PNG" border="0"></a><!-- 로그아웃 --> 
							<a href=""><font face="verdana"  size="5">${sessionScope.mynick}님</font></a><!-- 마이페이지 -->
						</c:if>  
						
					</td>
				</tr>
			</table>	
		</div>		
		<div align="center">			
			<table border="1" width="1100" >
				<tr height="60" align="center">
					<td>
						<a href="index.jsp"><img src="img/main.PNG" border="0"></a> <!--main --> 
						<a href="howto_main.do"><img src="img/howto.PNG" border="0"></a><!--이용방법 --> 
						<a href="center_main.do"><img src="img/center.PNG" border="0"></a> <!--고객센터 --> 
						<a href="comu_main.do"> <img src="img/comu.PNG" border="0"></a> <!--커뮤니티 --> 
						<a href="plan.do"><img src="img/make.PNG" border="0"></a> <!--일정만들기-->
						<a href="listPlanA.do"><img src="img/share.PNG" border="0"></a>	<!--일정공유-->
					</td>
				</tr>
