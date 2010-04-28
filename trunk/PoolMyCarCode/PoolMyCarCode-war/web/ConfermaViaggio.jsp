<%-- 
    Document   : ConfermaViaggio
    Created on : 12-apr-2010, 14.52.06
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="viaggi.*" %>
<%@page import="utenti.*" %>
<%@page import="ejb.CarrelloInserimentoViaggioBean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conferma Viaggio</title>
        <link rel=stylesheet href="style.css" type="text/css">
        <script src=" http://maps.google.com/?file=api&amp;v=2.x&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
        type="text/javascript"></script>
        <script language="JavaScript" type="text/javascript">

            var i=0;
            var map;
            var gdir;
            var geocoder = null;
            var addressMarker;

            function deseleziona(k) {
                var i;
                for( i= 0;i<k;i++)
                    document.getElementById("id" + i).style.backgroundColor = 'white';
            }
            function mappa(percorso,idMap,idDir) {
                if (GBrowserIsCompatible()) {
                    map = new GMap2(document.getElementById(idMap));
                    gdir = new GDirections(map, document.getElementById(idDir));
                    GEvent.addListener(gdir, "load", onGDirectionsLoad);
                    GEvent.addListener(gdir, "error", handleErrors);

                    map.setCenter(new GLatLng(45.0904249, 7.660769), 15);
                    map.setUIToDefault();
                    gdir.load(percorso);
                }
            }



            function handleErrors(){
                if (gdir.getStatus().code == G_GEO_UNKNOWN_ADDRESS)
                    alert("Una o più destinazioni errate");
                //alert("No corresponding geographic location could be found for one of the specified addresses. This may be due to the fact that the address is relatively new, or it may be incorrect.\nError code: " + gdir.getStatus().code);
                else if (gdir.getStatus().code == G_GEO_SERVER_ERROR)
                    alert("A geocoding or directions request could not be successfully processed, yet the exact reason for the failure is not known.\n Error code: " + gdir.getStatus().code);

                else if (gdir.getStatus().code == G_GEO_MISSING_QUERY)
                    alert("The HTTP q parameter was either missing or had no value. For geocoder requests, this means that an empty address was specified as input. For directions requests, this means that no query was specified in the input.\n Error code: " + gdir.getStatus().code);

                //   else if (gdir.getStatus().code == G_UNAVAILABLE_ADDRESS)  <--- Doc bug... this is either not defined, or Doc is wrong
                //     alert("The geocode for the given address or the route for the given directions query cannot be returned due to legal or contractual reasons.\n Error code: " + gdir.getStatus().code);

                else if (gdir.getStatus().code == G_GEO_BAD_KEY)
                    alert("The given key is either invalid or does not match the domain for which it was given. \n Error code: " + gdir.getStatus().code);

                else if (gdir.getStatus().code == G_GEO_BAD_REQUEST)
                    alert("percoso= "+percorso+ " A directions request could not be successfully parsed.\n Error code: " + gdir.getStatus().code);

                else alert("An unknown error occurred.");

            }

            function onGDirectionsLoad(){

            }

            function seleziona(i){
                var clickevent=document.createEvent("MouseEvents");
                clickevent.initEvent("click", true, true);
                document.getElementById("id"+i).dispatchEvent(clickevent);

            }

        </script>
    </head>
    <%
                CarrelloInserimentoViaggioBean c = (CarrelloInserimentoViaggioBean) session.getAttribute("creazioneViaggiBean");
                String chiamata = "mappa('" + c.getPercorso() + "','map','dir');";
    %>
    <body onload="GUnload();<%=chiamata%>">

        <div id="mainPan">
            <jsp:include page="/header.jsp"/>
            <div id="bodyPan">
                <jsp:include page="/leftpanel.jsp"/>
                <div id="rightPan">


                    <!-- Contenuto principale della pagina -->

                    <jsp:useBean id="utente" scope="session" class="utenti.Viaggiatore" />
                    <h1>Conferma viaggio</h1>
                    <table border="0">
                        <tr>
                            <td>
                                <div  id="map" style="width: 300px; height: 300px"></div>

                            </td>
                            <td>
                                <table>
                                    <tr>
                                        <%
                                                    for (Tappa t : c.getTappe()) {

                                        %>
                                        <%= t.getIndirizzo().toString()%><br>

                                    <%   }
                                    %>
                                    </tr>
                                    <tr>
                                        <%
                                                    for (Calendar d : c.getDate()) {
                                                        String dp = "" + d.get(Calendar.DAY_OF_MONTH);
                                                        dp = dp + "/" + (d.get(Calendar.MONTH) + 1);
                                                        dp = dp + "/" + d.get(Calendar.YEAR);
                                                        String ora = "" + (d.get(Calendar.MINUTE) < 10 ? "0" : "") + d.get(Calendar.MINUTE);
                                                        ora = "" + d.get(Calendar.HOUR_OF_DAY) + ":" + ora;
                                        %>
                                        il <%= dp%> alle <%= ora%> <br>

                                    <%   }
                                    %>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>


                    <form action="ServletController" method="POST">
                        scegli il tipo mezzo del viaggio
                        <select name="tipoMezzo">
                            <%
                                        for (TipoMezzo tm : utente.getMezzi()) {
                            %>
                            <option value="<%=tm.getId()%>"><%=tm.getNome()%></option>
                            <%}%>
                        </select>
                        contributi?<input name="contributi" type="checkbox"/>
                        nota:<input name="nota" type="text"/>

                        <input type='hidden' name='operation' value='viaggioConfermato'/>
                        <input type='submit' value='Conferma viaggio'/>
                    </form>


                </div>
            </div>
        </div>
        <jsp:include page="/foot.jsp"/>
    </body>
</html>
