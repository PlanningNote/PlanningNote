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
	
	
	if(subject != null){
		location = location.substring(1,location.length()-1);
		String a[] = location.split("\\s*,\\s*");
		location = "lat: "+a[0]+", lng: "+a[1];
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
    var map;
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
    }
    
    function addMarker(location) {
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(location),
            map: map
            //label : 1,2 ������ ���ڴ�
        });
    }
    
    function writeMessage(msg){
    	window.alert('�޽��� : '+msg);
    }
    
   
    </script>
</head>

<body>
	<h1>����¥��</h1>
	<form name="f" action="insertForm.jsp">
		<input type="submit" value="�����߰��ϱ�">
	</form>
	<div align="center">
		<table border="1" width="1000" >
			<tr bgcolor="yellow">
				<th width="10%">��ȣ</th>
				<th width="10%">����</th>
				<th width="20%">����</th>
				<th width="60%">����</th>
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
				<script>
				addMarker('<%=list.get(i).getLocation()%>')
				</script>
			<% }			
			
			%>
			
		</table>
	</div>
</body>
</html>