<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
	<script>
		function check(){
			var x = document.f.auth.value;
			var a = '${pwd}';
			if(x == a){
				window.alert('인증완료');
				opener.document.userInfo.auth.value = "Y";
				self.close();
			}else{
				window.alert('인증번호가 틀렸습니다. 다시 입력해주세요.');
			}
			
		}
	</script>
   <html>
   <head></head>
   <body>
   		<form name="f">
   		<input type="text" name="auth">
   		<input type="button" value="확인"  onclick="check()">
   	</form>
   </body>
   </html>