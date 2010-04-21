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
<%!
    RisultatiRicercaViaggi ris;
    List<Pacchetto> pacchetti;
    
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Risultati Ricerca</title>
    </head>
    
    <body onload="inizializza()">
        <center>
            <form action="" method="GET">
                <h1>Ecco i tuoi viaggi</h1>


                    <%
                        ris = (RisultatiRicercaViaggi) session.getAttribute("risulatatoRicerca");
                        pacchetti = ris.getNextPacchetti(3);
                        if(pacchetti==null){%>
                           Nulla
                    <%  }else{%>
                    <table border=0>
                    <tr>
                        <td>Partenza</td>
                        <td>Arrivo</td>
                        <td>Autista</td>
                        <td>Date Partenza</td>
                        <td>Visualizza Mappa</td>
                        <td></td>
                    </tr>

                    <%    for(int i = 0; i<pacchetti.size();i++){
                          Pacchetto p = pacchetti.get(i);

                    %>
                    <tr>
                        <td>
                            <%= p.getPartenza().getIndirizzo().getCitta()%>
                        </td>
                        <td>
                            <%= p.getArrivo().getIndirizzo().getCitta()%>
                        </td>
                        <td>
                            <%= p.getAutista().getCognome()%>
                        </td>
                        <td>
                            <%= p.getInizio()%>
                        </td>
                        <td>
                            mappa
                        </td>
                        <td>
                            <input type="button" id="prenota" value="Prenota">
                        </td>
                    </tr>
                    <%} // chiusura for
                    %>
                    </table>
                    <a href="/RisultatiRicerca.jsp">next</a>

                    <a href="/RisultatiRicerca.jsp" onclick="">precedente</a>
                    <%} //chiusura else%>
                
            </form>
        </center>


    </body>
</html>


