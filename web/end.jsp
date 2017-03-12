<%--
  Created by IntelliJ IDEA.
  User: casper
  Date: 12/03/2017
  Time: 10.05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css.css">
    <script>function goToIndex() {
        window.location = "index.jsp";
    }</script>
    <title>End</title>
</head>
<body>
<%
    if (request.getParameter("win") == null) {
        response.sendRedirect("index.jsp");
        return;
    }else if (request.getParameter("win").equals("true")){
        out.println("<h1>Du har vundet spillet</h1>");
    }else if (request.getParameter("win").equals("false") ){
        out.println("<h1>Du har tabt spillet</h1>");
    }else {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<button type="button" onclick="goToIndex()">Nulstil</button>
</body>
</html>
