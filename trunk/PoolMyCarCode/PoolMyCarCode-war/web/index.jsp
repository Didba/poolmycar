<%-- 
    Document   : index
    Created on : 13-dic-2009, 17.18.48
    Author     : Erica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link rel=stylesheet href="style.css" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>welcome to PoolMyCar, netSurfer!</h1>
        <form name="login" action="ServletController" method="POST">
        <table border=0>
        <tr><td>UserName:</td><td> <input name="login" type="text" value="" size=10/> </td></tr>
        <tr><td>Password:</td><td> <input name="password" type="password" value="" size=10/> </td></tr>
        <tr><td align="center"><input type="submit" name="operation" value="login"/></td><td><input type="submit" name="operation" value="registrati"/></td></tr>

        </table>
        </form>
        
    </body>
    <a href="RicercaViaggio.jsp">Ricerca</a>
    <br>
    
</html>
