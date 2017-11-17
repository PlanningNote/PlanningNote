<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ include file="top.jsp"%>

<div align="center" >

	<table align="center" align ="center" >
		<img src="img/back.jpg" align ="center"><br>
		
	</table><br>
	
	<table algin="center" >
	검색할 나라를 입력하세요<br>
	<br>
	<input type="text" name="nation">
		<input type="submit" value="등록">
		<input type="reset" value="취소"><br> 
	</table> 
	<br>
	<table align="center">   
		<td>
		<a href="comu_list.do"> <img src="img/plain.png"> </a> <!-- 커뮤니티 -->
		<a href="notice_list.do"> <img src="img/paris.jpg"></a> <!-- 공지사항-->
		<a href="ask_list.do"> <img src="img/travel2.png">	</a> </td><!-- 문의하기 -->
	
	</table><br> 


	<h4><br>
			<a href="index.jsp"> 메인 돌아가기 </a>
		</h4>

</div> 
	<%@ include file="bottom.jsp"%>