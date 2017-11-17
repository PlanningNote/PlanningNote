<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>플랜리스트</title>
</head>
<script language="javascript">
function renameForModelAttribute() {
    $("#form").each( function (index) {
        $(this).find("input[name=subject]").attr("name", "targets["+index+"].subject");
        $(this).find("input[name=price]").attr("name", "targets["+index+"].price");
        $(this).find("input[name=content]").attr("name", "targets["+index+"].content");
        $(this).find("input[name=traffic]").attr("name", "targets["+index+"].traffic");
        $(this).find("input[name=img]").attr("name", "targets["+index+"].img");
    });
}
</script>
<body>
	<div align="center">
		<<form name="f" method="post" action="list.do" enctype="multipart/form-data">
			<table WIDTH="800" HEIGHT="500" class="outline"
				background="${thumbnail}">
				<tr>
					<td>나라: <INPUT TYPE="hidden" name="country" VALUE="${country}"> /도시:
						<INPUT TYPE="hidden" name="city" VALUE="${city}"></td>
				</tr>
				<tr>
					<td WIDTH="75%"><h2>
							제목: <INPUT TYPE="hidden" name="city" VALUE="${subject}"></td>
					<td ALIGN="left">
					기간: <INPUT TYPE="hidden" name="travel_period" VALUE="${travel_period}"><br> 
					시즌: <INPUT TYPE="hidden" name="travel_season" VALUE="${travel_season}"><br> 
					테마: <INPUT TYPE="hidden" name="travel_theme" VALUE="${travel_theme}"><br>
				</tr>
				<tr>
					<td COLSPAN="2">작성자: <INPUT TYPE="hidden" name="writer" VALUE="${writer}">님
					</td>
				</tr>
				<tr>
					<td COLSPAN="2">작성일: <INPUT TYPE="hidden" name="day" VALUE="${day}">
					</td>
				</tr>
				<tr>
					<td COLSPAN="2"><INPUT TYPE="IMAGE" name="recom" SRC="recom.png">
						/count:<INPUT TYPE="hidden" name="count" VALUE="count"></td>
				</tr>
				<tr>
					<td WIDTH="75%">태그:<br>
					<INPUT TYPE="hidden" name="" VALUE="${tag_no}"></td>
					<td ALIGN="left">총예산: <INPUT TYPE="hidden" name="totalprice" VALUE="${totalprice}">원
					</td>
				</tr>
			</table>
	</div>
	<div id="pre_set" align="center">
			<table id=dyntbl1 border=1 height="290" width="850">
				<tr>
					<td width="5%">board_num</td>
					<td whidth="50%" height="100%">
						<!-- <div style="overflow-y: scroll; height: 100%; width: 100%">-->
						<!-- 여기에 subplan이 들어갑니다. --> 
						제목 <br>${dto.getTargets().get(0).getSubject()}<br>
						비용 <br>${dto.getTargets().get(0).getPrice() }
						<br>내용 <br>
						${dto.getTargets().get(0).getContent() }
						<br>교통 <br>
						${dto.getTargets().get(0).getTraffic() }</td>
					<td width="10%" height="100%">
					<a href="subPlanContent.do">
					<img src="${dto.getImgName().get(0) }"></a></td>
					<td width="3%"></td>
				</tr>
			</table><br>
			<input type="button" value="글수정"
				onclick="window.location='updatePlan.do?no=${group_no}'">
			<%-- <input type="button" value="글삭제"
				onclick="window.location='planning_deletePlan.do?num=${getBoard.num}'"> --%>
	</div>
	</FORM>
</body>
</html>