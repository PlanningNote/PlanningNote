<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
						<a href="logout.do"><img src="img/logout.PNG" border="0"></a><!-- 로그아웃--> 	
											<img src="img/managerPage.PNG" border="0"></a>
					</td>
				</tr>
			</table>	
	</div>
	<div align="center">			
			<table border="1" width="1100" height="700" >
				<tr height="60" align="center">
					<td colspan="2">
						<a href="admin_main.do"><img src="img/main.PNG" border="0"></a> <!--main --> 
						<a href="admin_memberList.do"><img src="img/member.png" border="0"></a><!-- 회원관리 -->  <!-- ★ -->
						<a href="admin_noticeList.do"><img src="img/center.PNG" border="0"></a> <!--고객센터 --> 
						<a href="comu_main.do"><img src="img/comu.PNG" border="0"></a> <!--커뮤니티 --> 
						<a href=""><img src="img/share.PNG" border="0"></a>	<!--일정공유-->  <!-- ★ -->
						<a href=""><img src="img/report.png" border="0"></a>	<!--신고관리-->   <!-- ★ -->
					</td>
				</tr>
				<tr>
					<td width="15%">
						<div align="left">
							<h4><img src="img/M1.png" border="0"></h4><!-- 회원관리 -->
								<ul>
									<a href="admin_memberList.do"><li><img src="img/no5.PNG" border="0"></li></a><!-- 회원목록-->
									<a href="admin_memberFind.do"><li><img src="img/no6.PNG" border="0"></li></a> <!-- 회원검색 -->
								</ul>
							<h4><img src="img/M2.png" border="0"></h4><!-- 고객센터 -->
								<ul>
									<a href="admin_noticeList.do"><li><img src="img/no1.PNG" border="0"></li></a><!-- 공지 -->
									<a href="admin_FAQList.do"><li><img src="img/no2.PNG" border="0"></li></a>
									<a href="admin_askList.do"><li><img src="img/no3.PNG" border="0"></li></a>
								</ul>
							<a href=""><img src="img/M3.png" border="0"><h4></h4></a>
							<a href=""><img src="img/M4.png" border="0"><h4></h4></a>
							<h4><img src="img/M5.png" border="0"></h4><!-- 신고관리 -->
								<ul>
									<a href=""><li><img src="img/no7.PNG" border="0"></li></a><!-- 회원신고 -->
									<a href="goBReport.do"><li><img src="img/no8.PNG" border="0"></li></a><!-- 게시물신고 -->
								</ul>
						</div>
					</td>
					<td width="85%">
				