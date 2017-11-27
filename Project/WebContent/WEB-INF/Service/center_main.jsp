<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../top.jsp" %>
		<tr align="center">
			<td>
				<br><br>
				<img src="img/c11.PNG" border="0"><br>
				<c:if test="${isLogin eq false}">
					<a href="notice_list.do"> <img src="img/notice.PNG" border="0"></a> <!-- 공지사항 -->
					<a href="FAQ_list.do"> <img src="img/FAQ.PNG" border="0"></a> <!-- FAQ-->
					<a href="notLogin.do"> <img src="img/ask.PNG" border="0"></a> <!-- 문의하기 -->
					<a href="howto_main.do"> <img src="img/howto2.PNG" border="0"></a> <!-- 이용방법 -->
					<a href="intro_company.do"> <img src="img/intro.PNG" border="0"></a><!-- 회사소개게시판 -->
				</c:if>
				<c:if test="${isLogin eq true}">
					<a href="notice_list.do"> <img src="img/notice.PNG" border="0"></a> <!-- 공지사항 -->
					<a href="FAQ_list.do"> <img src="img/FAQ.PNG" border="0"></a> <!-- FAQ-->
					<a href="ask_list.do"> <img src="img/ask.PNG" border="0"></a> <!-- 문의하기 -->
					<a href="howto_main.do"> <img src="img/howto2.PNG" border="0"></a> <!-- 이용방법 -->
					<a href="intro_company.do"> <img src="img/intro.PNG" border="0"></a><!-- 회사소개게시판 -->
				</c:if>
				
			</td>
	</tr>
	<%@ include file="../../bottom.jsp"%>