<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<script type="text/javascript">
	var message = '${request.getParameter("msg")}';
	var url ='${request.getParameter("url")}';
	if(message != null){
		alert(message);
	}
	document.location.href=url;
</script>