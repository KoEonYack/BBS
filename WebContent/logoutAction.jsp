<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/custom.css">
		<title>JSP 계시판 웹 사이트</title>
	</head>
	<body>
	
	<%
		session.invalidate(); // 세션을 뺴앗아서 로그아웃 시키는 코드
	%>
	<script>
		location.href = 'main.jsp';
	</script>



	</body>
</html>