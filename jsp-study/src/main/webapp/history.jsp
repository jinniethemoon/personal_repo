<%@page import="pubWifi.model.MyHistoryModel"%>
<%@page import="pubWifi.service.MyHistoryService"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
	<script src="/res/js/history.js"></script>
</head>
<body>

	<% 
	MyHistoryService myHistoryService = new MyHistoryService();
	List<MyHistoryModel> histList = myHistoryService.list();
     %>	
        
	<h1>위치 히스토리 목록</h1>
	<jsp:include page="menu.jsp"/>
	<table class="table-list">
		<thead>
			<th>ID</th>
			<th>X좌표</th>
			<th>Y좌표</th>
			<th>조회일자</th>
			<th>비고</th>
		</thead>
		<tbody>
			<tr>
				<% 
			        for(MyHistoryModel list : histList) {
	        	%>
			        	<tr>
				        	<td> <%=list.getID()%></td>
				        	<td> <%=list.getMyLat()%> </td>
				        	<td> <%=list.getMyLnt()%> </td>
				        	<td> <%=list.getDate()%> </td>
				        	<td align="center"><input type="button" value="삭제" 
				        	 onclick="location.href='deleteHistory.jsp?id=<%=list.getID()%>'"></td>
			        	</tr>
	        	<%
			        }
				%>	
			</tr>
		</tbody>
		
	</table>

</body>
</html>