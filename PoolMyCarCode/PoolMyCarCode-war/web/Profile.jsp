<%-- 
    Document   : Profile
    Created on : 6-apr-2010, 16.02.04
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utenti.Viaggiatore" %>
<%@page import="utenti.Autista" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PoolMyCar - Pagina di profilo</title>
    </head>
    <body>
        <jsp:useBean id="viaggiatore" scope="session" class="utenti.Viaggiatore" />
        <h1>Pagina di profilo</h1>
        <% if(viaggiatore!=null) {%>
            <h2>Cose che può fare il viaggiatore</h2>
            <%
            try{
                Autista autista=(Autista) viaggiatore;
                %>
                <h2>Cose che può fare l'autista</h2>
                <form action="ServletController" method="POST">
                <input type="SUBMIT" value="inserisciViaggio" name="operation">
                </form>
                <a href="ServletController?operation=inserisciViaggio">crea un viaggio</a>
                <%
            }
            catch(ClassCastException e){
            }
       } %>
    </body>
</html>
