<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../top.jsp" %>

<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SmartEditor</title>

<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="<%=ctx %>../../SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript" 
src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script type="text/javascript" scr="../../SE2/js/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "../../SE2/SmartEditor2Skin.html",
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
              oEditors.getById["ir1"].exec("PASTE_HTML", ["기존 DB에 저장된 내용을 에디터에 적용할 문구"]);
          },
          fCreator: "createSEditor2"
      });
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
          oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#frm").submit();
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
 

<script type="text/javascript">
		function checkMember(){
			
			if (f.subject.value==""){
				alert("제목을 입력해 주세요!!")
				f.subject.focus()
				return false
			}
			if (f.content.value==""){
				alert("내용을 입력해 주세요!!")
				f.content.focus()
				return false
			}
			if (f.passwd.value==""){
				alert("비밀번호를 입력해 주세요!!")
				f.passwd.focus()
				return false
			}
			return true
		}
	</script>

<tr height="600">
	<td>
	<div align="center">
		<form name="f" method="post"  
			action="ask_write.do" onsubmit="return checkMember()">	
			<table border="1" width="600">
				<tr bgcolor="orange">
					<th colspan="2">문 의 쓰 기</th>
				</tr> 				
				<tr>
					<th bgcolor="pink" width="20%">작성자</th>
					<td width="80%">
						<input type ="text" name="writer" rows="10" >
					</td>
				</tr> 			
				<tr>
					<th bgcolor="pink" width="20%">제 목</th>
					<td width="80%"><input type="text" name="subject" size="50"></td>
				</tr>		
				<tr>
					<th bgcolor="pink" width="20%">내 용</th>
					<td width="80%">
						<textarea name="content" rows="10" cols="50"></textarea>
					</td>
				</tr>			
			 	<tr>
					<th>이미지</th>
					<td><input type="file" name="img"></td>
				</tr>
		
				<tr>
					<th bgcolor="pink" width="20%">비밀번호</th>
					<td width="80%"><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td bgcolor="pink" colspan="2" align="center">
						<input type="submit" value="글쓰기">
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
<%@include file="../../bottom.jsp" %>