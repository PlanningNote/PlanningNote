<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <% String location = null; %>
<!DOCTYPE html>
<html>
<head>
	<title>테스트</title>
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
    	var temp;
    	function initMap() {
        var cnt = 1; //마커 카운트 해서 우선 2개만 제한
        var initLatLng = {lat: 37.366184, lng: 127.107905};
        var geocoder = new google.maps.Geocoder;
        var map = new google.maps.Map(document.getElementById('map'), {
            center: initLatLng,
            zoom: 16
        });        
        
        // 기본 마커 - 지도 생기면 중심에 찍혀있는 것
        var basicMarker = new google.maps.Marker({
            position: initLatLng,
            //map: map, //map을 선택 안해주면 마커는 생성되지만 표시는 안됨 이 경우는 setmap으로 나중에 호출할 수 있음
            draggable: true
        });
        
       
        google.maps.event.addListener(map, 'click', function(event) {
            if(cnt < 2) {
                addMarker(event.latLng, map);
                cnt++;
                //window.alert('체크'+cnt+event.latLng);
                //window.alert(event.latLng);
                //document.f.num.value=cnt;
                temp = event.latLng;      
            }
});
    }
    
    function addMarker(location, map) {
        var marker = new google.maps.Marker({
            position: location,
            map: map,
            draggable: true
            //label : 1,2 넣으면 좋겠다
        });
    }
    
    function send(form){
    	 document.f.location.value = temp;
    }
    
    
    </script>
</head>
 
<body>  
	<div align="center">
		<form name="f" action="insert.do" method="post" onsubmit="return send(this)">
		  <table border="1" width="1000" height="400">
		  	<tr  height="15%">
		  		<th>제목</th>
		  		<td><input type="text" name="subject"></td>
		  		<td rowspan="3" width="60%"><div id="map"></div> </td>
		  	</tr>
		  	<tr>
		  		<th>내용</th>
		  		<td> <textarea name="content" rows="20" cols="50"></textarea></td>
		  	</tr>
		  	<tr>
		  		<td colspan="2">
		  			<input type="hidden" name="location"  value="<%=location%>">
		  			<input type="submit" value="전송">
		  			<input type="reset" value="취소">
		  		</td>
		  	</tr>
		  </table>
		</form>
	</div>
</body>
</html>