<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import ="my.location.*, java.util.*"%>  
    
 <jsp:useBean id="ldao" class="my.location.LocationDAO" />
<jsp:useBean id="pool" class="my.db.ConnectionPoolBean" scope="application" />
<jsp:setProperty property="pool" name="ldao" value="<%=pool%>"/>
<%
	request.setCharacterEncoding("EUC-KR");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	String location = request.getParameter("location");	
	System.out.println("############");
	System.out.println("location : " +location);
	
	if(subject != null){
		LocationDTO dto = new LocationDTO();
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setLocation(location);
		int res = ldao.insertContent(dto);		
	}
	ArrayList<LocationDTO> list = ldao.listContent();
	int size = list.size();
		
%>

<!DOCTYPE html>
<html>
<head>
	<title>test</title>
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
    function initMap() {
        var cnt = 1; //마커 카운트 해서 우선 2개만 제한
        var initLatLng = {lat: 37.366184, lng: 127.107905};
        var geocoder = new google.maps.Geocoder;
        var map = new google.maps.Map(document.getElementById('map'), {
            center: initLatLng,
            zoom: 16
        });
        
        
        <!--
        // 기본 마커 - 지도 생기면 중심에 찍혀있는 것
        var basicMarker = new google.maps.Marker({
            position: initLatLng,
            map: map, //map을 선택 안해주면 마커는 생성되지만 표시는 안됨 이 경우는 setmap으로 나중에 호출할 수 있음
            draggable: true
        });         -->    
        
        
        
        
    }
    
    function addMarker(location, map) {
        var marker = new google.maps.Marker({
            position: location,
            map: map
            //label : 1,2 넣으면 좋겠다
        });
    }
    
   
    </script>
</head>

<body>
	<h1>일정짜기</h1>
	<form name="f" action="insertForm.jsp">
		<input type="submit" value="일정추가하기">
	</form>
	<div align="center">
		<table border="1" width="1000" >
			<tr bgcolor="yellow">
				<th width="10%">번호</th>
				<th width="10%">제목</th>
				<th width="20%">내용</th>
				<th width="60%">지도</th>
			</tr>
			
			<%for(int i=0;i<list.size();i++){ %>
				<tr>
				<% if(i==0) {%>
					<td><%=list.get(i).getNo() %></td>
					<td><%=list.get(i).getSubject() %></td>
					<td><%=list.get(i).getContent() %></td>
					<td rowspan="<%=size%>"><div id="map"></div></td>
				<%}  else { %>
					<td><%=list.get(i).getNo() %></td>
					<td><%=list.get(i).getSubject() %></td>
					<td><%=list.get(i).getContent() %></td>
				<% }  %>
				</tr>
			<% } %>
			
		</table>
	</div>
</body>
</html>