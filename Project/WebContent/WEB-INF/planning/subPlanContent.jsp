<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String path = session.getServletContext().getRealPath("img"); %>
<%@ include file="/top.jsp"%>
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
       
       var x = ${size};
       window.alert('size : '+x);
       
       <c:forEach items="${dtoS.getTargets()}" var="dtoS">	    
		var lat = ${dtoS.lat};       
		window.alert(lat);
		var lng = ${dtoS.lng};
		window.alert(lng);
		var sub = "${dtoS.subject}";    
		window.alert(sub);
		var c =  new google.maps.LatLng(lat,lng);    	  
		addMarker(c,sub);
	</c:forEach>  
       
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
<div align="center">
	<form name="f" method="post" action="list.do"
		enctype="multipart/form-data">
		<table border="1" width="700" height="900">
			<tr height="20%" align="center">
				<td colspan="2"><img src="<%=path %>/${dtoS.img}"></td>
			</tr>
			<tr>
				<td colspan="2"><font size="5">제목: ${dtoS.subject}</font></td>
			</tr>
			<tr height="30%">
				<td colspan="2">내용: ${dtoS.content}</td>
			</tr>
			<tr>
				<td width="70%">교통: ${dtoS.traffic}</td>
				<td width="30%">비용: ${dtoS.price}원</td>
			</tr>
			<tr height="40%" align="center">
				<td colspan="2" id="map">
				</td>
			</tr>
		</table>
		<button onclick="history.back()">뒤로가기</button> 
	</form>
</div>
<%@ include file="/bottom.jsp"%>