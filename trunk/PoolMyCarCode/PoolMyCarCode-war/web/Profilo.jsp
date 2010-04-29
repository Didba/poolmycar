<%-- 
    Document   : Profilo
    Created on : 6-apr-2010, 16.02.04
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="utenti.Viaggiatore" %>
<%@page import="viaggi.*" %>
<%@page import="utenti.TipoMezzo" %>
<%@page import="java.util.*" %>
<link rel=stylesheet href="style.css" type="text/css">

<%!    List<Pacchetto> pacchetti;
    int k = 0;
    int n = 0;
    String s = "";
    List<Viaggio> viaggi;
    Set<TipoMezzo> mezzi;
    TipoMezzo elem;
    String dp = "";
    String color;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PoolMyCar - Pagina di profilo</title>
    </head>
    <body>






        <div id="mainPan">
            <jsp:include page="/header.jsp"/>
            <div id="bodyPan">
                <jsp:include page="/leftpanel.jsp"/>
                <div id="rightPan">




                    <!-- Contenuto principale della pagina -->
                    <center>
                        <h1>Profilo Utente</h1>

                        <%
            Viaggiatore utente = (Viaggiatore) session.getAttribute("utente");
            if (utente != null) {%>
                        <table border=0>
                            <%-- <tr><td>autista?</td><td><input name="autista" type="checkbox"></td></tr> --%>
                            Dati Utente
                            <tr><td>UserName:</td><td> <input name="login" type="text" size=10 disabled value="<%= utente.getLogin()%>"/> </td>
                                <td>Password:</td><td> <input name="password" type="password" size=10 disabled value="<%= utente.getPassword()%>"/> </td></tr>
                            <tr><td>Nome</td><td> <input name="nome" type="text" size=10 disabled value="<%= utente.getNome()%>"/> </td>
                                <td>Cognome</td><td> <input name="cognome" type="text" size=10 disabled value="<%= utente.getCognome()%>"/> </td></tr>
                            <tr><td>Codice Fiscale</td><td> <input name="cf" type="text" size=10 disabled value="<%= utente.getCf()%>"/> </td>
                                <td>Telefono</td><td> <input name="telefono" type="text" size=10 disabled value="<%= utente.getTelefono()%>"/> </td></tr>
                            <tr><td>Via</td><td> <input name="via" type="text" size=10 disabled value="<%= utente.getIndirizzo().getVia()%>"/> </td>
                                <td>Numero</td><td> <input name="numero" type="text"  size=10 disabled value="<%= utente.getIndirizzo().getNumerocivico()%>"/> </td></tr>
                            <tr><td>Città</td><td> <input name="citta" type="text"  size=10 disabled value="<%= utente.getIndirizzo().getCitta()%>"/> </td>
                                <td>CAP</td><td> <input name="cap" type="text" size=10 disabled value="<%= utente.getIndirizzo().getCap()%>"/> </td></tr>
                            <tr><td>Provincia</td><td> <input name="provincia" type="text" size=10 disabled value="<%= utente.getIndirizzo().getProvincia()%>"/> </td>
                                <td>Stato</td><td> <input name="stato" type="text" value="Italia" size=10 disabled value="<%= utente.getIndirizzo().getStato()%>"/> </td></tr>
                            <tr><td>Note?</td><td> <input name="nota" type="text" size=10 disabled value="<%= utente.getNote()%>"/> </td>

                                <td>fumatore?</td>

                                <td><%if (utente.isFumatore()) {%><input name="fumatore" type="checkbox" disabled checked>
                                    <%} else {%><input name="fumatore" type="checkbox" disabled><%}%></td></tr>
                            <tr><td> </td><td align="center"> </tr>
                        </table>

                        <%

                if (utente.isAutista()) {
                        %>
                        <br>
                        <br>
                        Dati Autista

                        <form name="dati" action="ServletController" method="POST">
                            <table border=0 >
                                <tr><td>Tipo Mezzo <select name="tipoMezzo"  disabled>
                                            <%mezzi = utente.getMezzi();
         for (k = 0; k < mezzi.size(); k++) {
             elem = mezzi.iterator().next();%>
                                            <option value="<%=k%>"> <%= elem.getNome()%> </option>
                                            <%}%>


                                        </select></td>
                                    <td>Numero Patente</td><td> <input name="patente" type="text" value="<%= utente.getNumeroPatente()%>"  disabled size=15/> </td></tr>
                            </table>
                        </form>
                        <input type="submit" name="mod" value="modifica dati"/>


                        <% pacchetti = utente.getPacchettiDaAutista();
         if (pacchetti != null && pacchetti.size() > 0) {%>
                        <br>
                        <br>
                        <br>
                        Pacchetti Utente
                        <table border=0 align="center">
                            <tr align="center" bgcolor="#d3f0ef" >
                                <td>Indirizzo Partenza</td>
                                <td>Indirizzo Arrivo</td>
                                <td>Date</td>
                                <td></td>
                            </tr>
                            <%    for (k = 0; k < pacchetti.size(); k++) {
                                    if(k%2==0){color="white";}
                                    else {color="#d3f0ef";}
                            %>

                            <tr align="center" bgcolor="<%=color%>">

                                <td><%=pacchetti.get(k).getPartenza().getIndirizzo().getVia() + " " + pacchetti.get(k).getPartenza().getIndirizzo().getCitta()%></td>
                                <td><%=pacchetti.get(k).getArrivo().getIndirizzo().getVia() + " " + pacchetti.get(k).getArrivo().getIndirizzo().getCitta()%></td>
                                <% viaggi = pacchetti.get(k).getViaggi();%>
                                <td>

                                    <%
                                   for (Viaggio v : viaggi) {
                                       dp = "" + v.getDataPartenza().get(Calendar.DAY_OF_MONTH);
                                       dp = dp + "/" + (v.getDataPartenza().get(Calendar.MONTH) + 1);
                                       dp = dp + "/" + v.getDataPartenza().get(Calendar.YEAR);
                                       String ora = "" + (v.getDataPartenza().get(Calendar.MINUTE) < 10 ? "0" : "") + v.getDataPartenza().get(Calendar.MINUTE);
                                       ora = "" + v.getDataPartenza().get(Calendar.HOUR_OF_DAY) + ":" + ora;

                                    %>

                                    <table border=0>
                                        <tr>
                                            <td><%=dp%>  </td>
                                            <td><i class="sub"><a href="">visualizza</a></i></td>
                                        </tr>
                                    </table>

                                    <%}%>



                                    








                                </td>


                                <td> <input type="submit" name="mod" value="cancella"/></td>
                            </tr>

                            <%}%>
                        </table>
                        <%}%>

                        <%   } else {
                        %>
                        <form action="ServletController" method="POST">
                            <input type="SUBMIT" value="diventaAutista" name="operation">
                        </form>
                        <%                }
                }%>


                    </center>
                </div>
            </div>
        </div>
        <jsp:include page="/foot.jsp"/>












    </body>
</html>
