<%@ page import="dk.due.HangmanLogic" %>
<%@ page import="javax.xml.namespace.QName" %>
<%@ page import="javax.xml.ws.Service" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.MalformedURLException" %>

<%-- Created by IntelliJ IDEA.
  User: casper
  Date: 11/03/2017
  Time: 13.05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css.css">
    <!-- Latest compiled and minified CSS-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript-->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Hangman the game</title>
    <script>
        function validateForm() {
            var guess = document.forms["formGuess"]["guess"].value;
            //var regex = /(\d+)/g;
            var regex = /[a-zA-ZøæåØÆÅ]+/g;
            if (guess == "" || guess.length > 1 || !(guess.match(regex))) {
                alert("Ugyldigt input");
                return false;
            }
        }
    </script>
    
</head>
<body>
<%  String connectionId = null;
    URL url = null;
    try {
        url = new URL("http://casdan@ubuntu4.javabog.dk:8511/hangman?wsdl");
    } catch (MalformedURLException e) {
        e.printStackTrace();
        return;
    }
    QName qname = new QName("http://due.dk/", "HangmanLogicImplService");
    Service service = Service.create(url, qname);
    HangmanLogic logic = service.getPort(HangmanLogic.class);

    if(request.getParameter("id") !=null)
        connectionId = request.getParameter("id");

    if (request.getParameter("username") !=null && request.getParameter("password")!=null) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean succes = false;
        try {
            connectionId = logic.connect(username, password);
            succes = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(succes) {response.sendRedirect("game.jsp?id=" + connectionId);}
        else{response.sendRedirect("index.jsp?loginfailed=true"); return;}
    }

    //If connectionId is still null at this point, return to the login screen
    if (connectionId == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    if (request.getParameter("guess") !=null){
        logic.guessLetter(connectionId, request.getParameter("guess"));
    }

    //Checks to see if game is over
    if (logic.isGameOver(connectionId)){
        if (logic.isGameWon(connectionId)){
            response.sendRedirect("end.jsp?win=true");
        }else{
            response.sendRedirect("end.jsp?win=false");
        }
        return;
    }
%>

<div class="content">
    <div class="title">
        <h2>Spillet kan spilles</h2><br>
    </div>

    <img src="<%out.println("images/image" + logic.getAntalForkerteBogstaver(connectionId) + ".jpg");%>" alt="hangman image" name="image" class="image">
    <div class="guess">
    <p>Ordet du skal gætte:</p> <% out.println(logic.getSynligtOrd(connectionId)); %>
    <br>

<form action="game.jsp" name="formGuess" onsubmit="return validateForm()">
    <input type="hidden" name="id" value=<%out.println(connectionId);%>>
    <input type="text" name="guess">
    <input type="submit" value="Gæt">
</form>
        <p>Forkerte bogstaver: <% out.println(logic.getBrugteBogstaver(connectionId));%></p>
    </div>
</div>
</body>
</html>
