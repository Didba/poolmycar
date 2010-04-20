<%-- 
    Document   : RegistrazioneAutista
    Created on : 20-apr-2010, 16.20.23
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
        <h1>Inserisci i seguenti campi</h1>
        <form name="dati" action="ServletController" method="POST">
        <table border=0>
            <tr><td><select name="tipoMezzo">
                        <option value="2">Auto a 2 posti</option>
                        <option value="4">Auto a 4 posti</option>
                        <option value="5">Auto a 5 posti</option>
                        <option value="6">Auto a 6 posti</option>
                        <option value="7">Auto a 7 o più posti</option>
                    </select></td></tr>
            <tr><td>Numero Patente</td><td> <input name="patente" type="text" value="" size=15/> </td></tr>
            <tr><td> </td><td align="center"><br> <input type="submit" name="operation" value="registrazioneAutista"/></td></tr>
        </table>
        </form>
    </body>
</html>
