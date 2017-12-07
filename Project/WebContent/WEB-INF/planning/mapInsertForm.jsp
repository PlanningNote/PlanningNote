<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String location = null;
%>
<html>
<head>
<title>����</title>
<style>
#map {
	height: 400px;
	width: 100%;
}
</style>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCrt3e9BFpP0dfZJuTnfAnaAiKszMoJGm4&callback=initMap">
	
</script>
<script>
	var temp, lat, lng;
	function initMap() {
		var cnt = 1; //��Ŀ ī��Ʈ �ؼ� �켱 2���� ����
		var initLatLng = {
			lat : 37.366184,
			lng : 127.107905
		};
		var geocoder = new google.maps.Geocoder;
		var map = new google.maps.Map(document.getElementById('map'), {
			center : initLatLng,
			zoom : 16
		});

		// �⺻ ��Ŀ - ���� ����� �߽ɿ� �����ִ� ��
		var basicMarker = new google.maps.Marker({
			position : initLatLng,
			//map: map, //map�� ���� �����ָ� ��Ŀ�� ���������� ǥ�ô� �ȵ� �� ���� setmap���� ���߿� ȣ���� �� ����
			draggable : true
		});

		google.maps.event.addListener(map, 'click', function(event) {
			if (cnt < 2) {
				addMarker(event.latLng, map);
				cnt++;
				temp = event.latLng;
				lat = event.latLng.lat();
				lng = event.latLng.lng();
			}
		});
	}

	function addMarker(location, map) {
		var marker = new google.maps.Marker({
			position : location,
			map : map,
			draggable : true
		//label : 1,2 ������ ���ڴ�
		});
	}
	
	function sendLocation() {
		var i = ${index};
		if(i==0){
			opener.document.f.mapLat.value = lat;
			opener.document.f.mapLng.value = lng;
			opener.document.f.mapIndex.value = i;
		}else{
			opener.document.f.mapLat.value = opener.document.f.mapLat.value +","+ lat;
			opener.document.f.mapLng.value = opener.document.f.mapLng.value +","+ lng;
			opener.document.f.mapIndex.value = opener.document.f.mapIndex.value +","+ i;
		}
		
		
		
		
		if (opener != null) {
			opener.insertMap = null;
			self.close();
		}
	}
</script>
</head>

<body>
	<div align="center">
		<h1>��Ŀ�� üũ���ּ���.</h1>
		<h4>��Ŀ�� �ѹ� ��� ���� �巡�׷� �̵� ��ų �� �ֽ��ϴ�.</h4>
		<form id="checkmap" name="checkmap">
			<table border="1" width="1000" height="400">
				<tr>
					<td><div id="map"></div></td>
				</tr>
				<tr>
					<td><input type="reset" value="���" onclick="window.close()">
						<input type="submit" value="����" onclick="sendLocation()">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>