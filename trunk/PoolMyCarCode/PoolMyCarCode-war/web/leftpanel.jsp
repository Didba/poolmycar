<%@page import="utenti.*" %>
<div id="leftPan">
    <ul>
        <li><a href="index.jsp"><span>Home</span></a></li>
        <li><a href="RicercaViaggio.jsp"><span>Cerca Viaggio</span></a></li>
        <li><a href=""><span>Cerca Autista</span></a></li>
        <%
             Viaggiatore v = (Viaggiatore)session.getAttribute("utente");
             if(v!=null){
        %>
        <li><a href=""><span>I miei viaggi</span></a></li>
        <li><a href="ServletController"><span>Il mio profilo</span></a></li>
        <%      if(v.isAutista()){
        %>
                <li><a href="InserisciLuoghiViaggio.jsp"><span>Inserisci viaggio</span></a></li>
        <%
                }
             }%>
        <li><a href=""><span>Partner</span></a></li>
        <li><a href=""><span>Chi siamo</span></a></li>
        <li><a href=""><span>Contatti</span></a></li>
    </ul>


</div>