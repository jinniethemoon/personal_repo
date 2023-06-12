<%@page import="pubWifi.model.MyHistoryModel"%>
<%@page import="pubWifi.service.MyHistoryService"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
	<script src="/res/js/history.js"></script>
</head>
<body>

	<%
	
	String id = request.getParameter("id");
	int Id = Integer.parseInt(id);
	
	MyHistoryService myHistoryService = new MyHistoryService();
	myHistoryService.delete(Id);
	response.sendRedirect("/history.jsp");
	List<MyHistoryModel> histList = myHistoryService.list();
     %>	
        
	<h1>위치 히스토리 목록</h1>
	<div>
		<a href="home.jsp">홈</a> | 
		<a href="history.jsp">위치 히스토리 목록</a> | 
		<a href="list.jsp">Open API 와이파이 정보 가져오기</a>
	</div>
	<table>
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