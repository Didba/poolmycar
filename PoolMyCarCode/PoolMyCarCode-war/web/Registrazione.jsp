<%-- 
    Document   : Registrazione
    Created on : 6-apr-2010, 15.45.40
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="dati" action="ServletController" method="POST">
        <table border=0>
            <tr><td>autista?</td><td><input name="autista" type="checkbox"></td></tr>
            <tr><td>UserName:</td><td> <input name="login" type="text" value="" size=10/> </td></tr>
            <tr><td>Password:</td><td> <input name="password" type="password" value="" size=10/> </td></tr>
            <tr><td> </td><td align="center"><br> <input type="submit" name="operation" value="registrazione"/></td></tr>
        </table>
        </form>

    </body>
</html>
