<%-- 
    Document   : RisultatiRicerca
    Created on : 15-apr-2010, 15.16.16
    Author     : Erica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="ejb.RisultatiRicercaViaggi" %>
<%@page import="viaggi.*" %>
<%@page import="viaggi.*" %>
<%@page import="java.util.*" %>

<%!    HttpSession session;
    RisultatiRicercaViaggi ricercaViaggi;
    List<Pacchetto> pacchetti;
    int numPacchetti = 0;
    int j = 0, prossimoPacchetto = 0;
    Pacchetto p;
    List<Viaggio> listViaggi;
    String idP, idA, idAutista, idViaggio, idDataPartenza;
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Risultati Ricerca</title>


        <script language="JavaScript" type="text/javascript">

            var i=1;
                var k=0;


            function inizializza(){
            <%
            session = request.getSession();
            ricercaViaggi = (RisultatiRicercaViaggi) session.getAttribute("risulatatoRicerca");
            numPacchetti = ricercaViaggi.getPacchetti().size();
            pacchetti = ricercaViaggi.getPacchetti();
            %>
                    //loadElement();
                }

                function add(id)
                {
                    for(k = 0; k<1; k++){
                        campi = document.createElement('div');
                        campi.id = "mine"; //assegna al div un nome
                        document.getElementById(id).appendChild(campi);
                        campi.innerHTML="<tr><td><input type=\"text\" id=\"partenza"+i+"\"></td><td><input type=\"text\" id=\"arrivo"+i+"\"></td><input type=\"text\" id=\"autista"+i+"\"></td><input type=\"text\" id=\"datePartenza"+i+"\"></td><input type=\"text\" id=\"mappa"+i+"\"></td><input type=\"button\" value=\"Prenota\" id=\"prenota"+i+"\"></td></tr>";
                        i++;
                        <%p = pacchetti.get(0);%>
                        var str = "partenza"+i;
                        document.getElementById(str).value="<%=p.getPartenza().getIndirizzo().getVia() + " " + p.getPartenza().getIndirizzo().getCitta()%>"

                    }
                }


        </script>


    </head>
    <!--<body onload="inizializza()">

        <h1>Ecco i tuoi viaggi</h1>


        <div id="viaggio1" style="visibility: hidden;">
            Partenza <input name="partenza" id="idPartenza1" type="text"  disabled/>
            Arrivo <input name="arrivo" id="idArrivo1" type="text" disabled/>
            Autista <input name="autista" id="idAutista1" type="text" disabled/>
            Date Partenza <select id="idDataPartenza1" name="dataPartenza"/>
            <input type="submit" value="Prenota"/>
        </div>

        <div id="viaggio2" style="visibility: hidden;">
            Partenza <input name="partenza2" id="idPartenza2" type="text"  disabled/>
            Arrivo <input name="arrivo2" id="idArrivo2" type="text" disabled/>
            Autista <input name="autista" id="idAutista2" type="text" disabled/>
            Date Partenza <select id="idDataPartenza2" name="dataPartenza"/>
            <input type="submit" value="Prenota"/>
        </div>

        <div id="viaggio3" style="visibility: hidden;">
            Partenza <input name="partenza3" id="idPartenza3" type="text"  disabled/>
            Arrivo <input name="arrivo3" id="idArrivo3" type="text" disabled/>
            Autista <input name="autista" id="idAutista3" type="text" disabled/>
            Date Partenza <select id="idDataPartenza3" name="dataPartenza"/>
            <input type="submit" value="Prenota"/>
        </div>

        <div id="viaggio4" style="visibility: hidden;">
            Partenza <input name="partenza4" id="idPartenza4" type="text"  disabled/>
            Arrivo <input name="arrivo4" id="idArrivo4" type="text" disabled/>
            Autista <input name="autista" id="idAutista4" type="text" disabled/>
            Date Partenza <select id="idDataPartenza4" name="dataPartenza"/>
            <input type="submit" value="Prenota"/>
        </div>

        <div id="viaggio5" style="visibility: hidden;">
            Partenza <input name="partenza5" id="idPartenza5" type="text"  disabled/>
            Arrivo <input name="arrivo5" id="idArrivo5" type="text" disabled/>
            Autista <input name="autista" id="idAutista5" type="text" disabled/>
            Date Partenza <select id="idDataPartenza5" name="dataPartenza"/>
            <input type="submit" value="Prenota"/>
        </div>
        <input type="button" value="Avanti" id="avanti" onclick="loadElement()"/>

    -->
    <body onload="inizializza()">
        <center>
            <form action="" method="GET">
                <h1>Ecco i tuoi viaggi</h1>

                <table border=0>
                    <tr>
                        <td>Partenza</td>
                        <td>Arrivo</td>
                        <td>Autista</td>
                        <td>Date Partenza</td>
                        <td>Visualizza Mappa</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="text" id="partenza">
                        </td>
                        <td>
                            <input type="text" id="arrivo">
                        </td>
                        <td>
                            <input type="text" id="autista">
                        </td>
                        <td>
                            <input type="text" id="datePartenza">
                        </td>
                        <td>
                            <input type="text" id="mappa">
                        </td>
                        <td>
                            <input type="button" id="prenota" value="Prenota">
                        </td>
                    </tr>
                    <tr><td colspan=6>
                            <div id="prova"> </div>
                        </td></tr>
                    <tr><td colspan=6>
                            <input type="button" value="aggiungi" onclick="javascript:add('prova');">
                        </td></tr>
                    <tr><td colspan=6>
                            <input type="submit" value="Genera">
                        </td></tr>
                </table>
            </form>
        </center>


    </body>
</html>


