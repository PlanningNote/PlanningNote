<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ include file="../../top.jsp" %>
 
 <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	function revert(){
		location.href="ask_list.do";
	}
</script> 

<%!
	public Integer toInt(String x){
		int a = 0;
		try{
			a = Integer.parseInt(x);
		}catch(Exception e){}
		return a;
	}
%>
<%
	int pageno = toInt(request.getParameter("pageno"));
	if(pageno<1){//현재 페이지
		pageno = 1;
	}
	int total_record = 754;		   //총 레코드 수
	int page_per_record_cnt = 5;  //페이지 당 레코드 수
	int group_per_page_cnt =5;     //페이지 당 보여줄 번호 수[1],[2],[3],[4],[5]
//[6],[7],[8],[9],[10]

	int record_end_no = pageno*page_per_record_cnt;				
	int record_start_no = record_end_no-(page_per_record_cnt-1);
	if(record_end_no>total_record){
		record_end_no = total_record;
	}
	int total_page = total_record / page_per_record_cnt + (total_record % page_per_record_cnt>0 ? 1 : 0);
	if(pageno>total_page){
		pageno = total_page;
	}
	
// 	현재 페이지(정수) / 한페이지 당 보여줄 페지 번호 수(정수) + (그룹 번호는 현제 페이지(정수) % 한페이지 당 보여줄 페지 번호 수(정수)>0 ? 1 : 0)
	int group_no = pageno/group_per_page_cnt+( pageno%group_per_page_cnt>0 ? 1:0);
//		현재 그룹번호 = 현재페이지 / 페이지당 보여줄 번호수 (현재 페이지 % 페이지당 보여줄 번호 수 >0 ? 1:0)	
//	ex) 	14		=	13(몫)		=	 (66 / 5)		1	(1(나머지) =66 % 5)			  
	int page_eno = group_no*group_per_page_cnt;		
//		현재 그룹 끝 번호 = 현재 그룹번호 * 페이지당 보여줄 번호 
//	ex) 	70		=	14	*	5
	int page_sno = page_eno-(group_per_page_cnt-1);	
// 		현재 그룹 시작 번호 = 현재 그룹 끝 번호 - (페이지당 보여줄 번호 수 -1)
//	ex) 	66	=	70 - 	4 (5 -1)
	if(page_eno>total_page){
//	   현재 그룹 끝 번호가 전체페이지 수 보다 클 경우		
		page_eno=total_page;
//	   현재 그룹 끝 번호와 = 전체페이지 수를 같게
	}
	int prev_pageno = page_sno-group_per_page_cnt;  // <<  *[이전]* [21],[22],[23]... [30] [다음]  >>
//		이전 페이지 번호	= 현재 그룹 시작 번호 - 페이지당 보여줄 번호수	
//	ex)		46		=	51 - 5				
	int next_pageno = page_sno+group_per_page_cnt;	// <<  [이전] [21],[22],[23]... [30] *[다음]*  >>
//		다음 페이지 번호 = 현재 그룹 시작 번호 + 페이지당 보여줄 번호수
//	ex)		56		=	51 - 5
	if(prev_pageno<1){
//		이전 페이지 번호가 1보다 작을 경우
		prev_pageno=1;
//		이전 페이지를 1로
	}
	if(next_pageno>total_page){
//		다음 페이지보다 전체페이지 수보가 클경우		
		next_pageno=total_page/group_per_page_cnt*group_per_page_cnt+1;
//		next_pageno=total_page
//		다음 페이지 = 전체페이지수 / 페이지당 보여줄 번호수 * 페이지당 보여줄 번호수 + 1 
//	ex)			   = 	76 / 5 * 5 + 1	???????? 		
	}
%>
<tr height="600">
	<td valign="top">
		<div align="center">
		
		<img src="img/askimg.PNG"><br>
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
					<th>작성일</th>
				</tr>
				
			<c:if test="${empty askList}">
				<tr>
					<td colspan="5">게시된 글이 없습니다.</td>
				</tr>				
			</c:if>
			
			<c:forEach var="dto" items="${askList}">
				<tr>
					<td>${dto.no}</td>
					<td>${dto.writer}</td>
					<td>						
						<c:if test="${dto.re_level>0}">
							<img src="img/level.gif" width="${dto.re_level*10}">
							<img src="img/re.gif">
						</c:if> 
						<a href="ask_content.do?no=${dto.no}">${dto.subject}</a> 
						
						<c:if test="${dto.count>10}">	
							<img src="img/hot.gif">
						</c:if>						
					</td>
					<td>${dto.count}</td>				
					<td>${dto.day}</td>	
				</tr>
			</c:forEach>
			
			
			
			<tr >
				<td ALIGN="center"  COLSPAN="5" style="text-decoration:none">
				<a href="ask_list.do?pageno=<%=prev_pageno%>">[이전]</a> 
				<%for(int i =page_sno;i<=page_eno;i++){%>
				<%if(pageno == i){ %>
				<a href="ask_list.do?pageno=<%=i %>">
				[<%=i %>]
				</a> 
				<%}else{ %>
				<a href="ask_list.do?pageno=<%=i %>">
				[<%=i %>]
				</a> 
				<%} %>
				<%--	콤마	 --%>
				<%if(i<page_eno){%>,
				<%} %>
				<%} %>
				<a href="ask_list.do?pageno=<%=next_pageno%>" > [다음]</a>
		</table>
		
		
			<!-- 페이지 넘버 부분 -->
<%-- <%	if (count>0){ 
			int pageCount = count/pageSize + (count%pageSize==0 ? 0 : 1);
			int pageBlock = 3;
			int startPage = (currentPage-1) / pageBlock * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage>pageCount) endPage = pageCount;
			
			if (startPage>pageBlock){%>
				<a href="list.jsp?pageNum=<%=startPage-pageBlock%>">[이전]</a>
	<%	}
			for(int i=startPage; i<=endPage; ++i){%>
				<a href="list.jsp?pageNum=<%=i%>">[<%=i%>]</a>
<%		} 
			if (endPage<pageCount){%>
				<a href="list.jsp?pageNum=<%=startPage+pageBlock%>">[다음]</a>	
<%		} %>				
<%	}%>
	 --%>
	<!-- 검색부분 -->
		
		<form name="ff" action="ask_find.do" method="post">
			<select name="search">
				<option value="subject">제목</option>	
				<option value="writer">글쓴이</option>
			</select> 
			<input type="text" size="20" name="searchString">&nbsp;
			 <input type="submit" value="검색">
		</form>
		
		<input type="button" value="전체목록" onClick="revert()">
	</div>
		</td>	
	</tr>	
				
	<%@include file="../../bottom.jsp" %>			