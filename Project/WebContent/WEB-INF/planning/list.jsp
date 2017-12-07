<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <!DOCTYPE html> -->
<%@ include file="../../top.jsp"%>
<style>
	#map{
        width: 800px;
        height:400px;
		}
		.max-small {
		width: auto; height: auto;
		max-width: 30px;
		max-height: 30px;
}
</style>
<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCrt3e9BFpP0dfZJuTnfAnaAiKszMoJGm4&callback=initMap">
<script language="javascript">
var img = new Array("images1.png","images2.png")
var cnt=0;
function recom_click() {
	
	if(cnt==0){
		document.recom.src=img[1];
		cnt++;
	}else if(cnt==1){
		document.recom.src=img[0];
		cnt--;
	}
}
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
       
       
     
   }
</script>
<tr>
	<td>
		<div align="center">
			<form name="f" method="post" action="list.do" enctype="multipart/form-data">
				<table width="100%" height="500" class="outline"
					background="imgfile/plan/${dtoP.thumbnail}">
					<tr>
						<td>나라: ${dtoP.country} /도시: ${dtoP.city}</td>
					</tr>
					<tr>
						<td WIDTH="75%"><h2>
								제목:${dtoP.subject}</td>
						<td ALIGN="left">
						기간: ${dtoP.travel_period}<br> 
						시즌: ${dtoP.travel_seasion}<br> 
						테마: ${dtoP.travel_theme}<br>
					</tr>
					<tr>
						<td COLSPAN="2">작성자: ${dtoP.writer} 님
						</td>
					</tr>
					<tr>
						<td COLSPAN="2">작성일: ${dtoP.day}
						</td>
					</tr>
					<tr>
						<td WIDTH="75%"><br>${dtoT.tag1}   ${dtoT.tag2}   ${dtoT.tag3}   ${dtoT.tag4} ${dtoT.tag5}
						<td ALIGN="left">총예산: ${dtoP.totalprice} 원
						<img src="images1.png" name="recom" class="max-small" onclick="javascript:recom_click();"></td>
						</td>
					</tr>
				</table>
		</div>
		<div id="map"></div>
		<div id="pre_set" align="center">
				<table id=dyntbl1 border=1 width="100%" height="1000">
				<c:forEach items="${dtoS.getTargets()}" var="dtoS" varStatus="status">
					<tr>
						<td width="5%">${dtoS.board_num}</td>
						<td whidth="50%" height="100%">
							제목 <br>${dtoS.subject}<br>
							비용 <br>${dtoS.price }
							<br>내용 <br>
							${dtoS.content}
							<br>교통 <br>
							${dtoS.traffic}</td>
						<td width="45%" height="100%">
						<a href="subPlanContent.do?board_num=${dtoS.getBoard_num()}">
						<img src="imgfile/plan/${dtoS.img}" style="max-width: 250; height: 250;"></a></td>
					</tr>
				</c:forEach>
				</table><br>
				<input type="button" value="글목록"onclick="window.location='listPlanA.do'">
		</FORM>
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp"%>