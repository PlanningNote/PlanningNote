<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ include file="top.jsp"%>
	<tr height="50%">
		<td align="center">
				<img src="img/a1.jpg" align ="center" width="100%" height="100%">
		</td>
	</tr>
	<tr>
	 
		<td align="center">
			검색할 나라를 입력하세요<p>
			<form name="f">
				<input type="text" name="nation">
				<input type="submit" value="검색">
				<input type="reset" value="취소">
			</form>
			<br><br>
			<a href="comu_list.do"> <img src="img/plain.png"> </a> <!-- 커뮤니티 -->
			<a href="notice_list.do"> <img src="img/paris.jpg"></a> <!-- 공지사항-->
			<a href="ask_list.do"> <img src="img/travel2.png">	</a> </td><!-- 문의하기 -->
		</td>
	</tr>

<%@ include file="bottom.jsp"%>