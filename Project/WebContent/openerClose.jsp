<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<script>
var url = '${url}';
var msg = '${msg}';


 if (opener != null) {
	window.opener.location  = url;
	window.alert(msg);
	self.close();	
}	 
</script>