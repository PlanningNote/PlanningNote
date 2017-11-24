<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <% String location = null; %>
<!DOCTYPE html>
<html>
<head>
	<title>�׽�Ʈ</title>
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
        var cnt = 1; //��Ŀ ī��Ʈ �ؼ� �켱 2���� ����
        var initLatLng = {lat: 37.366184, lng: 127.107905};
        var geocoder = new google.maps.Geocoder;
        var map = new google.maps.Map(document.getElementById('map'), {
            center: initLatLng,
            zoom: 16
        });        
        
        // �⺻ ��Ŀ - ���� ����� �߽ɿ� �����ִ� ��
        var basicMarker = new google.maps.Marker({
            position: initLatLng,
            //map: map, //map�� ���� �����ָ� ��Ŀ�� ���������� ǥ�ô� �ȵ� �� ���� setmap���� ���߿� ȣ���� �� ����
            draggable: true
        });
        
       
        google.maps.event.addListener(map, 'click', function(event) {
            if(cnt < 2) {
                addMarker(event.latLng, map);
                cnt++;
                //window.alert('üũ'+cnt+event.latLng);
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
            //label : 1,2 ������ ���ڴ�
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
		  		<th>����</th>
		  		<td><input type="text" name="subject"></td>
		  		<td rowspan="3" width="60%"><div id="map"></div> </td>
		  	</tr>
		  	<tr>
		  		<th>����</th>
		  		<td> <textarea name="content" rows="20" cols="50"></textarea></td>
		  	</tr>
		  	<tr>
		  		<td colspan="2">
		  			<input type="hidden" name="location"  value="<%=location%>">
		  			<input type="submit" value="����">
		  			<input type="reset" value="���">
		  		</td>
		  	</tr>
		  </table>
		</form>
	</div>
</body>
</html>