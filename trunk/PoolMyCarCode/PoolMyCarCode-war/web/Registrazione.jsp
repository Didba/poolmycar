<%-- 
    Document   : Registrazione
    Created on : 6-apr-2010, 15.45.40
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel=stylesheet href="style.css" type="text/css">

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <div id="mainPan">
            <jsp:include page="/header.jsp"/>
            <div id="bodyPan">
                <jsp:include page="/leftpanel.jsp"/>
                <div id="rightPan">

                    <center>
                        <h1>Registrazione Utente</h1>
                        <form name="dati" action="ServletController" method="POST">
                            <table border=0>
                                <%-- <tr><td>autista?</td><td><input name="autista" type="checkbox"></td></tr> --%>
                                <tr><td>UserName:</td><td> <input name="login" type="text" value="" size=10/> </td></tr>
                                <tr><td>Password:</td><td> <input name="password" type="password" value="" size=10/> </td></tr>
                                <tr><td>Nome</td><td> <input name="nome" type="text" value="" size=10/> </td></tr>
                                <tr><td>Cognome</td><td> <input name="cognome" type="text" value="" size=10/> </td></tr>
                                <tr><td>Codice Fiscale</td><td> <input name="cf" type="text" value="" size=10/> </td></tr>
                                <tr><td>Telefono</td><td> <input name="telefono" type="text" value="" size=10/> </td></tr>
                                <tr><td>Via</td><td> <input name="via" type="text" value="" size=10/> </td></tr>
                                <tr><td>Numero</td><td> <input name="numero" type="text" value="" size=10/> </td></tr>
                                <tr><td>Città</td><td> <input name="citta" type="text" value="" size=10/> </td></tr>
                                <tr><td>CAP</td><td> <input name="cap" type="text" value="" size=10/> </td></tr>
                                <tr><td>Provincia</td><td> <input name="provincia" type="text" value="" size=10/> </td></tr>
                                <tr><td>Stato</td><td> <input name="stato" type="text" value="Italia" size=10 /> </td></tr>
                                <tr><td>fumatore?</td><td><input name="fumatore" type="checkbox"></td></tr>
                                <tr><td>Note?</td><td> <input name="nota" type="text" value="" size=10/> </td></tr>
                                <tr><td> </td><td align="center"> </tr>


                            </table>
                            <input type="submit" name="operation" value="registrazione"/>
                        </form>
                    </center>

                </div>
            </div>
        </div>
        <jsp:include page="/foot.jsp"/>




    </body>
</html>
