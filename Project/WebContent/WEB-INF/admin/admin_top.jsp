<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<title>관리자페이지</title>
	</head>
<body>
	<div style="padding:0px 0px 0px 300px;">
			<table border="0"  >
				<tr>
					<td>
						<a href="admin_main.do"><img src="img/planlogo.jpg" border="0"></a><!-- 로고 -->
						<a href="admin_logout.do"><img src="img/logout.PNG" border="0"></a><!-- 로그아웃--> 						
					</td>
				</tr>
			</table>	
	</div>
	<div align="center">			
			<table border="1" width="1100" height="700" >
				<tr height="60" align="center">
					<td colspan="2">
						<a href="admin_main.do"><img src="img/main.PNG" border="0"></a> <!--main --> 
						<a href="admin_memberList.do"><img src="img/share.PNG" border="0"></a><!-- 회원관리 -->  <!-- ★ -->
						<a href="center_main.do"><img src="img/center.PNG" border="0"></a> <!--고객센터 --> 
						<a href="comu_main.do"><img src="img/comu.PNG" border="0"></a> <!--커뮤니티 --> 
						<a href=""><img src="img/share.PNG" border="0"></a>	<!--일정공유-->  <!-- ★ -->
						<a href=""><img src="img/share.PNG" border="0"></a>	<!--신고관리-->   <!-- ★ -->
					</td>
				</tr>
				<tr>
					<td width="15%">
						<div align="left">
							<h4>회원관리</h4>
								<ul>
									<a href="admin_memberList.do"><li>회원목록</li></a>
									<a href="admin_memberFind.do"><li>회원검색</li></a>
								</ul>
							<h4>고객센터</h4>
								<ul>
									<a href=""><li>공지사항</li></a>
									<a href=""><li>FAQ</li></a>
									<a href=""><li>Q&A</li></a>
								</ul>
							<a href=""><h4>커뮤니티</h4></a>
							<a href=""><h4>일정공유</h4></a>
							<h4>신고관리</h4>
								<ul>
									<a href=""><li>회원신고</li></a>
									<a href=""><li>게시물신고</li></a>
								</ul>
						</div>
					</td>
					<td width="85%">
				