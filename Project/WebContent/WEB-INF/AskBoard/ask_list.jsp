<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
 <%@ include file="../../top.jsp" %>
<tr height="600">
	<td valign="top">
		<div align="center">
			<b> 문의 </b>
			<table border="0" width="600">
				<tr bgcolor="orange">
					<td align="right"><a href="ask_write.do">문의하기</a></td>
				</tr>
			</table>
			<table border="1" width="600" >
				<tr bgcolor="pink" >
					<th>번호</th>
					<th>작성자</th>
					<th width="50%">제목</th>
					<th>조회수</th>
					<th>사진</th>
					<th>날짜</th>
				</tr>
			<!-- 여기에 db의 자료를 꺼내서 표현을 하자 -->
			<c:if test="${empty askList}">
				<tr>
					<td colspan="6">게시된 글이 없습니다.</td>
				</tr>
			</c:if>
			
			<c:forEach var="dto" items="${askList}">
				<tr>
					<td>${dto.no}</td>
						<td>	<a href="ask_content.do?no=${dto.no}">
					<td>${dto.writer}</td>
					<td>
						<c:if test="${dto.re_level>0}">
							<img src="../img/level.gif" width="${dto.re_level*10}">
							<img src="../img/re.gif">
						</c:if> 
						<a href="ask_content.do?no=${dto.no}"> ${dto.subject} </a> 
						<c:if
							test="${dto.count>10}">	<img src="../img/hot.gif">
						</c:if>
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
			<input type="text" size="20" name="condition" />&nbsp;
			 <input type="submit" value="검색" />
		</form>
	</div>
		</td>
	
	</tr>	
				
	<%@include file="../../bottom.jsp" %>			