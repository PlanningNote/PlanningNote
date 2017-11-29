<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../top.jsp" %>
<%
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
});
 
</script>
 
<tr height="600">
<td>
<div align="center">

<form id="f" method="post" action="ask_write.do" onsubmit="return checkMember()"  enctype="multipart/form-data" >
<table width="100%">
				<tr>
					<th width="20%" bgcolor="pink">작성자</th>
					<td width="80%">
						<input type ="text" name="writer" rows="10" >
					</td>
				</tr> 	

        <tr>
            <th bgcolor="pink" >제목</th>
            <td><input type="text" id="title" name="subject" style="width:650px"/></td>
        </tr>
        <tr>
            <th bgcolor="pink" >내용</th>
            <td>
                <textarea id="ir1" name="content" style="width:650px; height:350px; "></textarea>
            </td>
        </tr>
        <tr>
        <th bgcolor="pink" >이미지</th>
        <td><input type="file" id="img" name="img"></td>
        </tr>
        
        	<tr>
					<th >비밀번호</th>
					<td width="80%"><input type="password" name="pwd"></td>
				</tr>
       
        <tr>
            <td colspan="2" align="center">
                <input type="submit" id="save" value="글쓰기"/>
                	<input type="reset" value="다시작성">
                	<input type="button" value="목록보기" 
													onclick="window.location='ask_list.do'">
					
              
            </td>
        </tr>
</table>
</form>
</div>
</td>
</tr>
</head>
</html>

<%@ include file="../../bottom.jsp"%>
