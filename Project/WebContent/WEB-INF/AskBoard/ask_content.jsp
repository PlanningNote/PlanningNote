<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String path = session.getServletContext().getRealPath("files/askImg"); %>
<%@ include file="../../top.jsp" %>


<%-- <%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SmartEditor</title>

<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="<%=ctx %>/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript" 
src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script type="text/javascript" scr="/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "SE2/SmartEditor2Skin.html",
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              oEditors.getById["ir1"].exec("PASTE_HTML", [""]);
          },
          fCreator: "createSEditor2"
      });
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
          oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#f").submit();
      });    
});
 
var oFileUploader = new jindo.FileUploader(jindo.$("file_select"), {
    //업로드할 서버의 URL(Form 전송 대상)
    sUrl :'SE2/htdocs/smartediotr/php_uploader/file_upload.php',
    //업로드 이후에 IFRMAME이 리다이렉트될 콜백 페이지 주소
    sCallback :'SE2/photo_uploader/popup/callback.html',
    //post할 데이터 셋. 예: { blogId : "testid" }
    htData : {}
}); --%>
 
</script>
 
<tr>
	<td>
		<div align="center">
			<b>글내용 보기</b>
			<table border="1" width="600">
					
			<tr>
					<th bgcolor="yellow" width="15%">작성자</th>
					<td align="center" width="35%">${getAskBoard.writer}</td>
				</tr>	
				<tr>
					<th bgcolor="yellow" width="15%">글번호</th>
					<td align="center" width="35%">${getAskBoard.no}</td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="15%">글제목</th>
					<td align="center"  id="title"  width="85%" colspan="3">
						${getAskBoard.subject}</td>
				</tr> 
				<tr>				
					<th bgcolor="yellow" width="20%">내 용</th>
					<td>
						<textarea name="content"  width="55%" rows="12" cols="55"  readonly>${getAskBoard.content}
						</textarea></td>
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="20%">이 미 지</th>
					<td >
					<img src="<%=path%>/${getAskBoard.img}" width="300" /> 
					
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="15%">조회수</th>
					<td align="center" width="35%">${getAskBoard.count}</td>
				</tr>
				
				<tr>	
					<th bgcolor="yellow" width="15%">작성일</th>
					<td align="center" width="35%">${getAskBoard.day}</td>
				</tr>
	
	
				<tr bgcolor="yellow">
					<td colspan="4" align="right">
						<input type="button" value="글수정"
						onclick="window.location='ask_update.do?no=${getAskBoard.no}'">						
						<input type="button" value="글삭제"
						onclick="window.location='ask_delete.do?no=${getAskBoard.no}'">
						<input type="button" value="글목록"
						onclick="window.location='ask_list.do'"></td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>