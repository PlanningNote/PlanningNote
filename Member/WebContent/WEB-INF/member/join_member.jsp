<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>회원가입</title>
	<script type="text/javascript">
		function check(){
			if(f.email.value==""){
				alert("이메일을 입력해 주세요");
				f.email.focus()
				return false
			}
			if(f.verify_no.value==""){
				alert("인증번호을 입력해 주세요");
				f.verify_no.focus()
				return false
			}
			if(f.pwd.value==""){
				alert("비밀번호을 입력해 주세요");
				f.pwd.focus()
				return false
			}
			if(f.nickname.value==""){
				alert("닉네임을 입력해 주세요");
				f.nickname.focus()
				return false
			}
			if(f.gender.value==""){
				alert("성별을 입력해 주세요");
				f.gender.focus()
				return false
			}
			if(f.age.value==""){
				alert("나이을 입력해 주세요");
				f.age.focus()
				return false
			}
			return true
		}
	</script>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="join_member.me" onsubmit="return check()">
			<table border="1" align="center">
				<b>회 원 가 입</b>
				<tr>
					<td>이메일 : <input type="text" name="email">
							<input type="button" name="verify" value="인증하기"></td>
				</tr>
				<tr>
					<td>인증번호 : <input type="text" name="verify_no">
							<input type="button" name="verify_ok" value="확인"></td>
				</tr>
				<tr>
					<td>비밀번호 : <input type="password" name="pwd"></td>
					<td>비밀번호확인 : <input type="password" name="pwd1"></td>
				</tr>
				<tr>
					<td>닉네임 : <input type="text" name="nickname"></td>
				</tr>
				<tr>
					<td>성별 : <input type="radio" name="gender" value="m">남
								  <input type="radio" name="gender" value="w">여
					</td>
				</tr>
				<tr>
					<td>나이 : <input type="text" name="age" size="3" maxlength="2"></td>
				</tr>
				<tr>
					<td><input type="button" name="join" value="회원가입"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>