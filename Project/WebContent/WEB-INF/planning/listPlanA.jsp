<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = session.getServletContext().getRealPath("img"); %>
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
	int total_record = (int)request.getAttribute("size");//총 레코드 수
	int page_per_record_cnt = 4;  //페이지 당 레코드 수
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
	if(next_pageno>total_page){//		다음 페이지보다 전체페이지 수가 클경우
		next_pageno=total_page/group_per_page_cnt*group_per_page_cnt+1;
//		next_pageno=total_page
//		다음 페이지 = 전체페이지수 / 페이지당 보여줄 번호수 * 페이지당 보여줄 번호수 + 1 
//	ex)			   = 	76 / 5 * 5 + 1	???????? 		
	}
%>
<%@ include file="../../top.jsp"%>
<tr>
	<td>
		<div align="center">
			<table border="1" WIDTH="800" HEIGHT="500">
				<tr>
					<td COLSPAN="3" HEIGHT="7%" ALIGN="MIDDLE">
						<form name="f">
						<select name="searchPlan">
						<option value="나라">나라</option>
						<option value="작성자">작성자</option>
						<option value="시기">시기</option>
						<option value="기간">기간</option>
						<option value="테마">테마</option>
						</select>
					 		<input type="text" name="searching" size="30">
							<input type="submit" value="검색">
							<input type="reset" value="취소">
						</form>
					</td>
				</tr>
			
				<c:if test="${empty dtoP}">
					<tr>
						<td colspan="3">게시된 글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${dtoP}" var="dtoP" end="<%=record_end_no %>">
				<tr WIDTH="100">
				<td>${dtoP.getGroup_no()}</td>
				<td><a href="list.do?group_no=${dtoP.getGroup_no()}">
				<img src="imgfile/plan/${dtoP.thumbnail}" style="max-width: 200; height: 200;"></a></td>
				<td>
					나라: ${dtoP.country}<br>
					기간: ${dtoP.travel_period}<br>
					총예산: ${dtoP.totalprice} 원<br>
					작성자: ${dtoP.writer}님<br>
					조회수: ${dtoP.count}
				</td>
				</tr>
				</c:forEach>
				<tr HEIGHT="5%">
					<td ALIGN="center"  COLSPAN="3">
					현재 페이지<!--    (pageno)    -->: <%=pageno%>페이지<br />
					<%--전체 데이터 수   (total_record) : <%=total_record %><br />
					한페이지 당 레코드 수   (page_per_record_cnt) : <%=page_per_record_cnt %><br />
					한페이지 당 보여줄 페지 번호 수   (group_per_page_cnt) : <%=group_per_page_cnt %><br />
					<hr />
					레코드 시작 번호  (record_start_no) : <%=record_start_no%><br />
					레코드 끝 번호    (record_end_no) : <%=record_end_no %><br />
					전체페이지 수     (total_page)  : <%=total_page %><br />
					<hr />
					현재 그룹번호 [1] (group_no):  <%=group_no %><br />
					현재 그룹 시작 번호(page_sno): <%= page_sno%><br />
					현재 그룹 끝 번호  (page_eno): <%= page_eno%><br />
					이전 페이지 번호   (prev_pageno) <%=prev_pageno%><br />
					다음 페이지 번호   (next_pageno) <%=next_pageno%><br />
					<hr />   --%>
					[<a href="listPlanA.do?pageno=<%=prev_pageno%>">이전</a>]
					<%for(int i =page_sno;i<=page_eno;i++){%>
					<%if(pageno == i){ %>
					[<a href="listPlanA.do?pageno=<%=i %>"><%=i %></a>]
					<%}else{ %>
					[<a href="listPlanA.do?pageno=<%=i %>"><%=i %></a>]
					<%} %>
					<%--	콤마	 --%>
					<%if(i<page_eno){ %>,
					<%} %>
					<%} %>
					[<a href="listPlanA.do?pageno=<%=next_pageno%>" >다음</a>]
					</td>
				</tr>
		</table>
	</div>
<%@ include file="/bottom.jsp"%>