<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>메시지</title>
</head>
<body>
	<h1>${message}</h1>
</body>
</html>

<script type="text/javascript">
	var message = '${msg}';
	var url ='${url}';
	if(message != null){
		alert(message);
	}
	document.location.href=url;
</script>
