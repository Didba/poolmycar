<%-- 
    Document   : ViaggioInserito
    Created on : 29-apr-2010, 22.17.04
    Author     : Erica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Viaggio inserito</title>
        <link rel=stylesheet href="style.css" type="text/css">
    </head>
    <body>
        <div id="mainPan">
            <jsp:include page="/header.jsp"/>
            <div id="bodyPan">
                <jsp:include page="/leftpanel.jsp"/>
                <div id="rightPan">


                    <!-- Contenuto principale della pagina -->
                    <h1>Viaggio inserito correttamente!</h1>

                    Clicca <a href="PaginaPacchetto.jsp">QUI</a> per visualizzarlo


                </div>
            </div>
        </div>
        <jsp:include page="/foot.jsp"/>
    </body>
</html>
