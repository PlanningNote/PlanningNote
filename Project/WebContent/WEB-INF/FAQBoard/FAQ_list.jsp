<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>   
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ include file="../../top.jsp" %>
<tr>
	<td valign="top">
		<div align="center">
			<b> FAQ  </b>
			<table border="0" width="600">
				<tr bgcolor="sky blue">
					<td align="right"><a href="FAQ_write.do">FAQ</a></td>
				</tr>
			</table>
			<table border="1" width="600">
				<tr bgcolor="pink">
					<th>번호</th>
					<th width="50%">제목</th>
					<th>조회수</th>
					<th>사진</th>
					<th>날짜</th>
				</tr>
		
			<c:if test="${empty FAQList}">
				<tr> 
				
					<td colspan="5">게시된 글이 없습니다.</td>
				</tr>
			</c:if>	
			
			<c:forEach var="dto" items="${FAQList}">
				<tr>
					<td>${dto.no}</td>
					<td> <a href="FAQ_content.do?no=${dto.no}">	
						${dto.subject}</a>
					</td>
					<td>${dto.count}</td>
					<td>${dto.img}</td>
					<td>${dto.day}</td>	
				</tr>		
			</c:forEach>				
			</table>
			
			<form>
				<select name="opt">
					<option value="0">제목</option>					
					<option value="3">글쓴이</option>
				</select>
				<input type="text" size="20" name="condition"/>&nbsp;
				<input type="submit" value="검색"/>
			</form>	
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>









