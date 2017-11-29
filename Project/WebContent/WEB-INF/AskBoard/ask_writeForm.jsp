<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@include file="../../top.jsp" %>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="java.util.*"%>

<%
	String uploadPath = request.getRealPath("upload");

	int size = 10 * 1024 * 1024;

	String name = "";
	String subject = "";
	String filename1 = "";
	String filename2 = "";
	String origfilename1 = "";
	String origfilename2 = "";

	try {
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8",
				new DefaultFileRenamePolicy());
		/*
		MultipartRequest 이 코드가 실제로 화일 업로드를 담당하는 부분이다.
		첫번째 인자는 폼에서 가져온 인자 값을 얻기 위해 request 객체를 전달해 주는 것이고
		두번째 인자는 업로드 될 화일의 위치를 의미한다.
		세번째는 한번에 업로드할 크기를 의미하며
		최대 크기를 넘는 경우 Exception이 발생한다.
		다섯번재 인자는 똑같은 화일을 업로드 할 경우 중복되지 않도록 화일 이름을 변환해주는 
		기능을 갖는다.
		*/

		name = multi.getParameter("name");
		subject = multi.getParameter("subject");

		Enumeration files = multi.getFileNames();

		String file1 = (String) files.nextElement();
		filename1 = multi.getFilesystemName(file1);
		origfilename1 = multi.getOriginalFileName(file1);

		String file2 = (String) files.nextElement();
		filename2 = multi.getFilesystemName(file2);
		origfilename2 = multi.getOriginalFileName(file2);

	} catch (Exception e) {
		e.printStackTrace();
	}
%>




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
			action="ask_write.do" onsubmit="return checkMember()" enctype="multipart/form-data">	
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