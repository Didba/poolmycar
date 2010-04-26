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
        <title>Pool My Car Home</title>
    </head>
    <body>
        <div id="mainPan">
            <jsp:include page="/header.jsp"/>
            <div id="bodyPan">
                <jsp:include page="/leftpanel.jsp"/>
                <div id="rightPan">


<!-- Contenuto principale della pagina -->
                    <h1>Hai bisogno di un passaggio?</h1>
                    <p>Contatta e incontra persone per i tuoi trasferimenti, piccole tratte, brevi spostamenti. Un nostro associato potrebbe accompagnarti. Ecco cosa fare:
                    </p>
                    <img width="530" height=680" align="center" src="images/index.jpg" alt=""/>

                    <p>Per avere più informazioni, cliccare sul pulsante. </p>
                    <p class="more"><a href="">Read more</a></p>



                </div>
            </div>
        </div>
        <jsp:include page="/foot.jsp"/>
    </body>
</html>
