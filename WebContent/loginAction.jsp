<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %> <!-- 자바스크립트 문장을 작성하기 위함이다. -->
<%  request.setCharacterEncoding("UTF-8"); %> <!-- 건너오는 모든 댕요을 UTF-8로 인코딩 할 것이다. --> 
<jsp:useBean id="user" class="user.User" scope="page" /> 
<!-- user라는 id를 만들 것이다. scope 현재 페이지에서만 사용되게 만들 것이다. -->
<jsp:setProperty name="user" property="userID" /> 
<!-- login.jsp에서 넘겨준 userID를 받아서 한명의 사용자의 userID에 넣어주는 것이다. -->
<jsp:setProperty name="user" property="userPassword" /> 

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/custom.css">
		<title>JSP 계시판 웹 사이트</title>
	</head>
	<body>
	
	<%
		String userID = null;
		if(session.getAttribute("userID") != null){
			userID = (String) session.getAttribute("userID"); // string으로 바꾼다.
		}
		if(userID!=null){
			 PrintWriter script = response.getWriter();
			 script.println("<script>");
			 script.println("alert('이미 로그인디 되어있습니다.')"); // 로그인에 성공하면 여기로 이동하게 만들어준다.
			 script.println("location.href = 'main.jsp'");
			 script.println("</script>");
		}
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(user.getUserID(), user.getUserPassword());
		 if(result == 1){
			 session.setAttribute("userID", user.getUserID()); // 해당 회원의 id를 세션에 넣어준다.
			 PrintWriter script = response.getWriter();
			 script.println("<script>");
			 script.println("location.href = 'main.jsp'"); // 로그인에 성공하면 여기로 이동하게 만들어준다.
			 script.println("</script>");
		 }
		 else if(result == 0){ // 비밀번호 틀렸을 때
			 PrintWriter script = response.getWriter();
			 script.println("<script>");
			 script.println("alert('비밀번호가 틀립니다.')"); // 로그인에 성공하면 여기로 이동하게 만들어준다.
			 script.println("history.back();");
			 script.println("</script>");
		 } 
		 else if(result == -1){ // 비밀번호 틀렸을 때
			 PrintWriter script = response.getWriter();
			 script.println("<script>");
			 script.println("alert('비밀번호가 틀립니다.')"); // 로그인에 성공하면 여기로 이동하게 만들어준다.
			 script.println("history.back();");
			 script.println("</script>");
		 } 
		 else if(result == -2){ // 비밀번호 틀렸을 때
			 PrintWriter script = response.getWriter();
			 script.println("<script>");
			 script.println("alert('데이터베이스 오류가 발생했습니다.')"); // 로그인에 성공하면 여기로 이동하게 만들어준다.
			 script.println("history.back();");
			 script.println("</script>");
		 } 
		 else { // 비밀번호 틀렸을 때
			 PrintWriter script = response.getWriter();
			 script.println("<script>");
			 script.println("alert('알수 없는 오류가 발생했습니다. 관리자에게 문의주세요')"); // 로그인에 성공하면 여기로 이동하게 만들어준다.
			 script.println("history.back();");
			 script.println("</script>");
		 } 
	%>

	</body>
</html>