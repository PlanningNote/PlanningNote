<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../top.jsp"%>
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
		<form name="userInfo">
			<table align="center" height="60%">
			<img src="img/mp2.PNG">
			<div id="sidebar">
				<tr>
					<a href="mypage_update.do">비밀번호 변경</a>
					<a href="mypage_delete.do">회원탈퇴</a>	
				</tr>
			</div>
			<div id="d1" >
				<h1>내가 쓴 글</h1>
			</div>
			<div id="d2">
				<h1>내가 좋아요 누른 글</h1>
			</div>
			</table>
		</form>
	</div>
</body>
</html>
<%@ include file="../../bottom.jsp"%>