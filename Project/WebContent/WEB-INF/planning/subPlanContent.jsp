<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String path = session.getServletContext().getRealPath("img"); %>
<!DOCTYPE>
<%@ include file="../../top.jsp"%>

<style>
	#map{
        width: 700px;
        height:300px;
	}
</style>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCrt3e9BFpP0dfZJuTnfAnaAiKszMoJGm4&callback=initMap">
</script>
<script>
var map;
function initMap() {		 
	var initLatLng = {lat : 37.366184,	lng : 127.107905	};
       var geocoder = new google.maps.Geocoder;
       map = new google.maps.Map(document.getElementById('map'), {
           center: initLatLng,
           zoom: 13
       });              
       
       // 기본 마커 - 지도 생기면 중심에 찍혀있는 것
       var basicMarker = new google.maps.Marker({
           position: initLatLng
       });
       
		var lat = ${dtoS.lat};       
		var lng = ${dtoS.lng};
		var sub = "${dtoS.subject}";    
		var c =  new google.maps.LatLng(lat,lng);    	  
		addMarker(c,sub);
      
function addMarker(location,sub){
	var marker = new google.maps.Marker({
					position: location,
					map:map
			});
	var infoWindow = new google.maps.InfoWindow({
		content:sub
	});
	
	marker.addListener('click',function(){
		infoWindow.open(map, marker);
	});

			}	
    
  } 
</script>
<tr>
	<td>
		<div align="center">
			<form name="f" method="post" action="list.do"
				enctype="multipart/form-data">
				<table border="1" width="700" height="600">
					<tr height="43%" align="center">
						<td colspan="2"><img src="imgfile/plan/${dtoS.img}"></td>
					</tr>
					<tr height="10%">
						<td colspan="2"><font size="5">제목: ${dtoS.subject}</font></td>
					</tr>
					<tr height="40%" ALIGN="TOP">
						<td colspan="2">내용: <br>${dtoS.content}</td>
					</tr>
					<tr>
						<td width="70%">교통: ${dtoS.traffic}</td>
						<td width="30%">비용: ${dtoS.price}원</td>
					</tr>
				</table>
			</form>
			<div id="map"></div>
		<br><button onclick="history.back()">뒤로가기</button> 
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp"%>