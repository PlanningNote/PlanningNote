<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<<<<<<< HEAD
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>메시지</title>
</head>
<body>
	<h1>${message}</h1>
	<input type="button" value="확인" onclick="window.close()">
</body>
</html>
=======

<script type="text/javascript">
	var message = '${request.getParameter("msg")}';
	var url ='${request.getParameter("url")}';
	if(message != null){
		alert(message);
	}
	document.location.href=url;
</script>
>>>>>>> refs/remotes/origin/master
