<%@page import="java.util.List"%>
<%@page import="pubWifi.dto.WifiListDto"%>
<%@page import="pubWifi.service.WifiApiComponent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 DB 저장 결과</title>

</head>
<body>

	<%
	WifiApiComponent wifiApiInsert = new WifiApiComponent();
	wifiApiInsert.getApiresult();
     %>
        
	<h1 align="center"> <%=wifiApiInsert.total%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	<div align="center">
		<a href="index.jsp">홈으로 가기</a>
	</div>

</body>
</html>