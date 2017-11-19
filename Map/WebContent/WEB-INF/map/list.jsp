<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
	<title>My Map</title>
	<style>
		#map{
		 height: 400px;
        width: 100%;
		}
	</style>
	<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCrt3e9BFpP0dfZJuTnfAnaAiKszMoJGm4&callback=initMap">
    </script>
<script>		
	function insertContent(){
		location.href="insertForm.do";
		//window.open("insertForm.do");
	}	
	
	 function initMap() {		 
		 	var initLatLng = {lat: 37.366184, lng: 127.107905};
	        var geocoder = new google.maps.Geocoder;
	        var map = new google.maps.Map(document.getElementById('map'), {
	            center: initLatLng,
	            zoom: 14
	        });              
	        
	        // �⺻ ��Ŀ - ���� ����� �߽ɿ� �����ִ� ��
	        var basicMarker = new google.maps.Marker({
	            position: initLatLng,
	            map: map //map�� ���� �����ָ� ��Ŀ�� ���������� ǥ�ô� �ȵ� �� ���� setmap���� ���߿� ȣ���� �� ����
	        });   
	       
	        $(function(){
	        	var result = new Array();
	        	<c:out value="function����"/>
	        	<c:forEach items="${contentList}" var="list">
	        		var json = new Object();
	        		json.lat ="${list.lat}";
	        		json.lng ="${list.lng}";
	        		json.subject = "${list.subject}";
	        		<c:out value="${json.lat}"/>
	        	</c:forEach>
	        })
	        <c:forEach var="list" items="${contentList}" >
	        
 				var lat = Number("${list.lat}");
 				var lng = Numeber("${list.lng}");
 				window.alert(lat+"//"+lng);
 				
 			var coords =  new google.maps.LatLng(lat,lng);
 			window.alert("coords"+coords);
 			addMarker(coords);	 		
 			</c:forEach>
	        
	 		 function addMarker(location){
	 			 window.alert('���� : '+location);
	 				var marker = new google.maps.Marker({
	 					position: location,
	 					map:map
	 				});
	 			}
	        
	      
	    }
	
</script>
</head>



	
<body>
	<h1>����¥��</h1>
	<input type="button" value="�����߰�" onclick="insertContent()">
	<div align="center">
		<table border="1" width="1000">
			<tr bgcolor="yellow">
				<th width="10%">��ȣ</th>
				<th width="10%">����</th>
				<th width="20%">����</th>
				<th width="60%">����</th>
			</tr>
			<c:if test="${empty contentList}">
				<tr>
					<td colspan="4">������ �߰����ּ���.</td>
				</tr>
			</c:if>
			<c:forEach var="list" items="${contentList}" varStatus="status">		
				<tr>		
				<td>${list.no}</td>
				<td>${list.subject}</td>
				<td>${list.content}</td>
			<c:if test="${status.first}">
				<td rowspan="${fn:length(contentList)}" ><div id="map"></div></td>
			</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>