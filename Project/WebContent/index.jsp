<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <%@page import="java.util.*"%>
<%@ include file="top.jsp"%>
	<tr height="25%" >
		<td align="center">
				<img src="img/a1.jpg" align ="center" width="100%" height="100%">
		</td>
	</tr>
	<tr>
	   
		<td align="center">
		
		<table WIDTH="1100" HEIGHT="150" 
		background="img/pink2.jpg">
		<tr>
		<td align="center">
			<b>검색할 나라를 입력하세요</b>
			
			<form name="f">
			<input type="text" value="  가고픈 나라 입력해보세요!"  name="nation" size="50"
			onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
		
				<input type="submit" value="검색">
				<input type="reset" value="취소">
			</form>
			
		</td>
		</tr>
		</table><br><br>
			
			<b>여행자들의 일정보기 (예상)</b>
			<table class="type07">
			
			<tr>
			<th>여행지</th>
			<td><a href="notice_list.do"> <img src="img/la1.PNG" border="0"></a></td>
			<td><a href="notice_list.do"> <img src="img/la2.PNG" border="0"></a></td>
			<td><a href="notice_list.do"> <img src="img/la3.PNG" border="0"></a></td>
			<td><a href="notice_list.do"> <img src="img/la4.PNG" border="0"></a></td>
			<td><a href="notice_list.do"> <img src="img/la5.PNG" border="0"></a></td>
		
			
			</tr>
			<tr>
				
			<th>여행시기</th>
			<td><a href="notice_list.do"> <img src="img/we1.PNG" border="0"></a></td>
			<td><a href="notice_list.do"> <img src="img/we2.PNG" border="0"></a></td>
			<td><a href="notice_list.do"> <img src="img/we3.PNG" border="0"></a></td>
			<td><a href="notice_list.do"> <img src="img/we4.PNG" border="0"></a></td>
			<td></td>
			</tr>
			
					<tr>
				
			<th>여행테마</a></th>
			<td><a href="notice_list.do"> <img src="img/th1.PNG" border="0"></td>
			<td><a href="notice_list.do"> <img src="img/th2.PNG" border="0"></td>
			<td><a href="notice_list.do"> <img src="img/th3.PNG" border="0"></td>
			<td><a href="notice_list.do"> <img src="img/th4.PNG" border="0"></td>
			<td></td>
			</tr>
			
			</table >
			<br><br>
			
			<br>
			<b>인기 여행일정<br><br></b>
다른 여행자들의 일정을 참고해 나만의 여행을 계획해보세요!<br><br>
			
			<table>
			<tr>
			<td><a href="notice_list.do"><img src="img/co1.png" border="0"><img src="img/co2.png" border="0"></td>
			</tr>
			<tr>
			<td><a href="notice_list.do"> <img src="img/best1.PNG" border="0"></td>
			<td><a href="notice_list.do"> <img src="img/best2.PNG" border="0"></td>
			<td><a href="notice_list.do"> <img src="img/best3.PNG" border="0"></td>
			</tr>
			<tr>			
			<td><a href="notice_list.do"> <img src="img/best4.png" border="0"></td>
			<td><a href="notice_list.do"> <img src="img/best5.png" border="0"></td>
			<td><a href="notice_list.do"> <img src="img/best6.png" border="0"></td>
			
			</tr>
			</table>
		
		
			
			
		
	</tr>

<%@ include file="bottom.jsp"%>