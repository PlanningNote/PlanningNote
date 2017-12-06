<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
Calendar cal = Calendar.getInstance();

String strYear = request.getParameter("year");
String strMonth = request.getParameter("month");

int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH);
int date = cal.get(Calendar.DATE);

if(strYear != null)
{
  year = Integer.parseInt(strYear);
  month = Integer.parseInt(strMonth);
  
}else{

}
//년도/월 셋팅
cal.set(year, month, 1);

int startDay = cal.getMinimum(java.util.Calendar.DATE);
int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
int start = cal.get(java.util.Calendar.DAY_OF_WEEK);
int newLine = 0;

//오늘 날짜 저장.
Calendar todayCal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");
int intToday = Integer.parseInt(sdf.format(todayCal.getTime()));

%>


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

<html lang="ko">
<HEAD>
	<TITLE>캘린더</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">


	<script type="text/javaScript" language="javascript">


	
	</script>
	<style TYPE="text/css">
		body {
		scrollbar-face-color: #F6F6F6;
		scrollbar-highlight-color: #bbbbbb; 
		scrollbar-3dlight-color: #FFFFFF;
		scrollbar-shadow-color: #bbbbbb;
		scrollbar-darkshadow-color: #FFFFFF;
		scrollbar-track-color: #FFFFFF;
		scrollbar-arrow-color: #bbbbbb;
		margin-left:"0px"; margin-right:"0px"; margin-top:"0px"; margin-bottom:"0px";
		}

		td {font-family: "돋움"; font-size: 9pt; color:#595959;}
		th {font-family: "돋움"; font-size: 9pt; color:#000000;}
		select {font-family: "돋움"; font-size: 9pt; color:#595959;}


		.divDotText {
		overflow:hidden;
		text-overflow:ellipsis;
		}

A:link { font-size:9pt; font-family:"돋움";color:#000000; text-decoration:none; }
A:visited { font-size:9pt; font-family:"돋움";color:#000000; text-decoration:none; }
A:active { font-size:9pt; font-family:"돋움";color:red; text-decoration:none; }
A:hover { font-size:9pt; font-family:"돋움";color:red;text-decoration:none;}


	</style>
</HEAD>
<BODY>
<form name="calendarFrm" id="calendarFrm" action="" method="post">
<DIV id="content" style="width:712px;">

<table width="100%" border="0" cellspacing="1" cellpadding="1">

</table>
<!--날짜 네비게이션  -->
<table width="1100" border="0" cellspacing="1" cellpadding="1" id="KOO" bgcolor="#F3F9D7" style="border:1px solid #CED99C">

<tr>
<td height="60">

	<table width="1100" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="10">
		</td>
	</tr>
	
	<tr>
		<td align="center" >
			<a href="<c:url value='/calendar.do' />?year=<%=year-1%>&amp;month=<%=month%>" target="_self">
				<b>&lt;&lt;</b><!-- 이전해 -->
			</a>
			<%if(month > 0 ){ %>
			<a href="<c:url value='/calendar.do' />?year=<%=year%>&amp;month=<%=month-1%>" target="_self">
				<b>&lt;</b><!-- 이전달 -->
			</a>
			<%} else {%>
				<b>&lt;</b>
			<%} %>
			&nbsp;&nbsp;
			<%=year%>년
			
			<%=month+1%>월
			&nbsp;&nbsp;
			<%if(month < 11 ){ %>
			<a href="<c:url value='/calendar.do' />?year=<%=year%>&amp;month=<%=month+1%>" target="_self">
				<!-- 다음달 --><b>&gt;</b>
			</a>
			<%}else{%>
				<b>&gt;</b>
			<%} %>
			<a href="<c:url value='/calendar.do' />?year=<%=year+1%>&amp;month=<%=month%>" target="_self">
				<!-- 다음해 --><b>&gt;&gt;</b>
			</a>
		</td>
		<tr>
	<td align ="right"><br><br>
		<input type="button" onclick="javascript:location.href='<c:url value='/calendar.do' />'" value="오늘날짜 바로가기" align="center"/>
	</td>

</tr>
	</tr>
	</table>

</td>
</tr>
</table>
<br>
<table border="0" cellspacing="1" cellpadding="1" bgcolor="#FFFFFF">
<THEAD>
<TR bgcolor="#CECECE">
	<TD width='100px'>
	<DIV align="center"><font color="red">일</font></DIV>
	</TD>
	<TD width='100px'>
	<DIV align="center">월</DIV>
	</TD>
	<TD width='100px'>
	<DIV align="center">화</DIV>
	</TD>
	<TD width='100px'>
	<DIV align="center">수</DIV>
	</TD>
	<TD width='100px'>
	<DIV align="center">목</DIV>
	</TD>
	<TD width='100px'>
	<DIV align="center">금</DIV>
	</TD>
	<TD width='100px'>
	<DIV align="center"><font color="#529dbc">토</font></DIV>
	</TD>
</TR>
</THEAD>
<TBODY>
<TR>
<%

//처음 빈공란 표시
for(int index = 1; index < start ; index++ )
{
  out.println("<TD >&nbsp;</TD>");
  newLine++;
}

for(int index = 1; index <= endDay; index++)
{
	String color = "";

	if(newLine == 0){			color = "RED";
	}else if(newLine == 6){ 	color = "#529dbc";
	}else{						color = "BLACK"; };

	String sUseDate = Integer.toString(year);

	sUseDate += Integer.toString(month+1).length() == 1 ? "0" + Integer.toString(month+1) : Integer.toString(month+1);
	sUseDate += Integer.toString(index).length() == 1 ? "0" + Integer.toString(index) : Integer.toString(index);

	int iUseDate = Integer.parseInt(sUseDate);
	
	
	String backColor = "#EFEFEF";
	if(iUseDate == intToday ) {
		backColor = "#c9c9c9";
	} 
	out.println("<TD valign='top' align='left' height='92px' bgcolor='"+backColor+"' nowrap>");
	%>
	<font color='<%=color%>'>
		<%=index %>
	</font>

	<%
	
/* 	out.println("<BR>");
	out.println(iUseDate);
	out.println("<BR>");
	 */
	
	//기능 제거	
	out.println("</TD>");
	newLine++;

	if(newLine == 7)
	{
	  out.println("</TR>");
	  if(index <= endDay)
	  {
	    out.println("<TR>");
	  }
	  newLine=0;
	}
}
//마지막 공란 LOOP
while(newLine > 0 && newLine < 7)
{
  out.println("<TD>&nbsp;</TD>");
  newLine++;
}
%>
</TR>

</TBODY>
</TABLE>
</DIV>
</form>
</BODY>
</HTML>

				<tr height="50" align="center" >
					<td>
						<img src="img/madeby.PNG"  border="0">  
					</td>
				</tr>
			</table>
		</div> 
</body>
</html>