<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
	<script>
		function check(){
			var x = document.f.auth.value;
			var a = '${pwd}';
			if(x == a){
				window.alert('�����Ϸ�');
				opener.document.userInfo.auth.value = "Y";
				self.close();
			}else{
				window.alert('������ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է����ּ���.');
			}
			
		}
	</script>
   <html>
   <head></head>
   <body>
   		<form name="f">
   		<input type="text" name="auth">
   		<input type="button" value="Ȯ��"  onclick="check()">
   	</form>
   </body>
   </html>