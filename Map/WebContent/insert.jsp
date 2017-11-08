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
        var cnt = 1; //��Ŀ ī��Ʈ �ؼ� �켱 2���� ����
        var initLatLng = {lat: 37.366184, lng: 127.107905};
        var geocoder = new google.maps.Geocoder;
        var map = new google.maps.Map(document.getElementById('map'), {
            center: initLatLng,
            zoom: 16
        });
        
        
        <!--
        // �⺻ ��Ŀ - ���� ����� �߽ɿ� �����ִ� ��
        var basicMarker = new google.maps.Marker({
            position: initLatLng,
            map: map, //map�� ���� �����ָ� ��Ŀ�� ���������� ǥ�ô� �ȵ� �� ���� setmap���� ���߿� ȣ���� �� ����
            draggable: true
        });         -->    
        
        
        
        
    }
    
    function addMarker(location, map) {
        var marker = new google.maps.Marker({
            position: location,
            map: map
            //label : 1,2 ������ ���ڴ�
        });
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
			<% } %>
			
		</table>
	</div>
</body>
</html>