<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var ="list" items="${contentList}" >
	<script>
		markerInit('${list.location}','${list.subject}')
	</script>
</c:forEach>

<!DOCTYPE html>
<html>
<head>
	<title>My Map</title>
	<style>
		#map{
		height:100%;
		width:100%;
		}
	</style>
<script>	
	var markers=[];
	var map;
	function insertContent(){
		window.open("insertForm.do");
	}
	function markerInit(location, subject){
		window.alert('���⿡ ���Դ�'+location+subject);
		var x = {
				coords: '{'+location+'}',
				content: '<h4>'+subject+'</h4>'
		}
		markers.push(x);
	}
	
	 function initMap() {
	        var initLatLng = {lat: 37.366184, lng: 127.107905};
	        var geocoder = new google.maps.Geocoder;
	        map = new google.maps.Map(document.getElementById('map'), {
	            center: initLatLng,
	            zoom: 16
	        });        
	        
	        // �⺻ ��Ŀ - ���� ����� �߽ɿ� �����ִ� ��
	        var basicMarker = new google.maps.Marker({
	            position: initLatLng,
	            map: map, //map�� ���� �����ָ� ��Ŀ�� ���������� ǥ�ô� �ȵ� �� ���� setmap���� ���߿� ȣ���� �� ����
	            draggable: true
	        });   
	        
	        for(var i=0;i<markers.length;i++){
	        	addMarker(markes[i]);
	        }
	    }
	 
	 function addMarker(props) {
	    	//window.alert(props.coords+','+props.content);
	        var marker = new google.maps.Marker({
	            position: props.coords,
	            map: map
	            //label : 1,2 ������ ���ڴ�
	        });
	        if(props.content){
	        	var infoWindow = new google.maps.InfoWindow({
	        		content:props.content
	        	});
	        	
	        	marker.addListener('click',function(){
	        		infoWindow.open(map, marker);
	        	});
	        }
	    }
</script>
</head>


<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCrt3e9BFpP0dfZJuTnfAnaAiKszMoJGm4&callback=initMap">
</script>
	
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
				<c:if test="${status.begin}">
					<td>${list.no}</td>
					<td>${list.subject}</td>
					<td>${list.content}</td>
					<td rowspan="${contentList.size()}" ><div id="map"></div></td>
				</c:if>
				<tr>
					<td>${list.no}</td>
					<td>${list.subject}</td>
					<td>${list.content}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>