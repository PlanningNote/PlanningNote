<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ include file="top.jsp"%>
<br>

<h3 align="center">나만의 플래닝노트(대충시안버전)</h3>
<br>

<div align="center">
	<img src="img/travel.png">
	<table align="center">
		<br>
		<h3>
			검색할 나라를 입력하세요
		</h3>
		<input type="text" name="nation">
		<input type="submit" value="등록">
		<input type="reset" value="취소"><br>
	</table><br>
	<table align="center">   
		<td>
		<a href="notice_writeForm.do"> <img src="img/plain.png"> </a> <!-- 커뮤니티 -->
		<a href="notice_list.do"> <img src="img/paris.jpg"></a> <!-- 공지사항-->
		<a href="ask_list.do"> <img src="img/travel2.png">	</a> </td><!-- 문의하기 -->
	
	</table><br> 


	<h4><br>
			<a href="index.jsp"> 메인 돌아가기 </a>
		</h4>

</div> 
	<%@ include file="bottom.jsp"%>