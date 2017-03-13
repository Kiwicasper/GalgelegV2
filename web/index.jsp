<%@ page import="java.net.URL" %>
<%@ page import="java.net.MalformedURLException" %>
<%@ page import="javax.xml.namespace.QName" %>
<%@ page import="javax.xml.ws.Service" %>
<%@ page import="dk.due.HangmanLogic" %><%--
  Created by IntelliJ IDEA.
  User: casper
  Date: 10/03/2017
  Time: 10.14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>

      <!-- Latest compiled and minified CSS-->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

      <!-- jQuery library-->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

      <!-- Latest compiled JavaScript-->
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <link rel="stylesheet" type="text/css" href="css.css">
      <title>Hangman</title>
      <script>
          function validateForm() {
              var first = document.forms["login"]["username"].value;
              var secound = document.forms["login"]["password"].value;
              if (first == "" || secound == "") {
                  alert("Alle felter skal udfyldes");
                  return false;
              }
          }
      </script>
  </head>
  <body>
  <div>

      <div class="form-signin">
          <div class="form-signin-heading">
              <h2>Hangman</h2>
          </div>
      <form name="login" action="game.jsp" onsubmit="return validateForm()">
          <div class="form-group">
              <label for="nameInput">Brugernavn:</label>
              <input class="form-control" id="nameInput" type="text" name="username"> <br>

              <label for="passwordInput">Password:</label>
              <input class="form-control" id="passwordInput" type="password" name="password"><br>
              <input class="btn btn-primary" type="submit" value="Login">
          </div>

      </form>
    <%
        if (request.getParameter("loginfailed") != null)
            if (request.getParameter("loginfailed").equals("true"))
                 out.println("<h3>Forkert brugernavn eller kode");
    %>


  <a href="restWordbank.jsp"><span class="glyphicon glyphicon-pencil"> </span> Wordbank adminpanel</a>
  </div>
  </div>
  </body>
</html>
