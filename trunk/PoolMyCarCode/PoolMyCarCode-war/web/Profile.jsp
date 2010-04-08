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
        <%
            boolean isAutista=true;
            Autista autista=null;
            try{
                autista=(Autista) viaggiatore;
            }
            catch(ClassCastException e){
               isAutista=false;
            }
        %>
        <h2>Cose che può fare il viaggiatore</h2>
        <% if(isAutista) { %>
        <form action="ServletController" method="POST">
            <input type="SUBMIT" value="inserisciViaggio" name="operation">
        </form>
        <a href="ServletController?operation=inserisciViaggio">crea un viaggio</a>
        <% } %>
    </body>
</html>
