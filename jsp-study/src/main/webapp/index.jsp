<%@page import="pubWifi.model.WifiListModel"%>
<%@page import="pubWifi.service.WifiService"%>
<%@page import="pubWifi.model.MyHistoryModel"%>
<%@page import="pubWifi.service.MyHistoryService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
	<script src="/res/js/index.js"></script>
	
</head>
<body>
<form method="get">

        
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="menu.jsp"/>
		<div class="position-info">
		LAT: <input type="text" name="myLat" id="inputLat" value="0.0"> ,
		LNT: <input type="text" name="myLnt" id="inputLnt" value="0.0">
		
		<input type="button" value="내 위치 가져오기" id="getPositionButton" 
		onclick="showPosition()"></td>
		<input type="submit" value="근처 WIFI 정보 보기">
	</div>

<table class="table-list">
		<thead>
			<th>거리(km)</th>
			<th>관리번호</th>
			<th>자치구</th>
			<th>와이파이명</th>
			<th>도로명주소</th>
			<th>상세주소</th>
			<th>설치위치(층)</th>
			<th>설치유형</th>
			<th>설치기관</th>
			<th>서비스구분</th>
			<th>망종류</th>
			<th>설치년도</th>
			<th>실내외구분</th>
			<th>WIFI접속환경</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>작업일자</th>
		</thead>
		<tbody>
			<tr>
	        	<tr>
	        	<%	
	        		String myLat = request.getParameter("myLat");
	        		String myLnt = request.getParameter("myLnt");
	        		
	        		if(myLat !=null & myLnt != null) {
	        			
	        			WifiService wifiList = new WifiService();
	        			List<WifiListModel> resultList = wifiList.list(myLat, myLnt);
	        			
	        			MyHistoryService myHistoryService = new MyHistoryService();
	        			myHistoryService.update(myLat, myLnt);
	        			
	        			for(WifiListModel list : resultList) {
	        			
	        	%>
	        	
	        			<tr>
				        	<td> <%=list.getDistance()%></td>
				        	<td> <%=list.getMrgNo()%> </td>
				        	<td> <%=list.getWrdofc()%> </td>
				        	<td> <%=list.getMainNm()%> </td>
				        	<td> <%=list.getAdresMn()%></td>
				        	<td> <%=list.getAdresSb()%></td>
				        	<td> <%=list.getInstlFloor()%></td>
				        	<td> <%=list.getInstlTy()%></td>
				        	<td> <%=list.getInstlMby()%></td>
				        	<td> <%=list.getSvcSe()%></td>
				        	<td> <%=list.getCmcwr()%></td>
				        	<td> <%=list.getCnstcYear()%></td>
				        	<td> <%=list.getInoutDoor()%></td>
				        	<td> <%=list.getRemars()%></td>
				        	<td> <%=list.getLat()%></td>
				        	<td> <%=list.getLnt()%></td>
				        	<td> <%=list.getWorkDttm()%></td>
			        	</tr>
	        	<%
	        			}
	        		} else {
	        	%>	        	
	        	 <td colspan='17' align="center">위치 정보를 입력한 후에 조회해 주세요.</td>
	        	<%
	        		}
	        	%>	        	        	 
	        	 
	        	</tr>
			</tr>
		
		</tbody>
		
</table>

	
</form>

</body>
</html>