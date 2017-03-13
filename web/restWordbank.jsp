
<%@ page import="dk.due.RestHelper" %><%--
  Created by IntelliJ IDEA.
  User: casper
  Date: 13/03/2017
  Time: 09.36
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validateForm() {
            var guess = document.forms["formAddWord"]["add"].value;
            //var regex = /(\d+)/g;
            var regex = /[a-zA-ZøæåØÆÅ]+/g;
            if (guess == "" ||  !(guess.match(regex))) {
                alert("Ugyldigt input");
                return false;
            }
        }
    </script>
    <title>Wordbank</title>
</head>
<body>
<%

    String[] words = null;
    if (request.getParameter("delete") != null){
        RestHelper.getInstance().deleteWord(request.getParameter("delete"));
    }
    if (request.getParameter("add") != null){
        RestHelper.getInstance().addWord(request.getParameter("add"));
    }
    try {
        words = RestHelper.getInstance().getWords();
    }catch (Exception e){
        e.printStackTrace();
    }
%>
<div class="container">
    <h1>REST API administration</h1>
    <div class="col-md-6">
        <div class="row">
<table class="table table-striped">
    <thead>
        <th>Ord i databasen</th>
        <th></th>
    </thead>
    <tbody>
    <%
        if (words != null)
            for(String s: words)
              out.println("<tr><td>" + s + "</td><td><a href =\"restWordbank.jsp?delete=" + s+ "\">Slet</a>");
    %>
    </tbody>
</table>
        </div>
        <div class="row">

<form name="formAddWord" action="restWordbank.jsp" onsubmit="return validateForm()">
    <div class="form-group">

    <label for="add1">Tilføj ord</label>
    <input id="add1" class="form-control" type="text" name="add">
    </div>
    <input class="btn btn-primary" type="submit">
</form>
        </div>
    </div>
</div>
</body>
</html>
