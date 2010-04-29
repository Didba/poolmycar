<%-- 
    Document   : PaginaPacchetto
    Created on : 28-apr-2010, 17.15.44
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="viaggi.*" %>
<%@page import="utenti.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualizza Pacchetto</title>
        <link rel=stylesheet href="style.css" type="text/css">
        <script src=" http://maps.google.com/?file=api&amp;v=2.x&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
        type="text/javascript"></script>
        <jsp:useBean id="pacchetto" scope="session" class="viaggi.Pacchetto" />
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

        String chiamata = "mappa('" + pacchetto.getPercorso() + "','map','dir');";
    %>
    <body onload="GUnload();<%=chiamata%>">

        <div id="mainPan">
            <jsp:include page="/header.jsp"/>
            <div id="bodyPan">
                <jsp:include page="/leftpanel.jsp"/>
                <div id="rightPan">
                    <div  id="map" style="width: 300px; height: 300px"></div>

                    <%  String partenza = pacchetto.getPartenza().getIndirizzo().getCitta();
                        String arrivo = pacchetto.getArrivo().getIndirizzo().getCitta();
                        Viaggiatore autista=pacchetto.getAutista();
                        Bacheca bacheca = pacchetto.getBacheca();
                        List<Viaggio> viaggi = pacchetto.getViaggi();
                        String nota = pacchetto.getNota();
                        boolean richiestaContributi = pacchetto.isRichiestaContributi();
                        float lunghezzaPercorso = pacchetto.getLunghezzaPercorso();

                        List<String> tappe = new LinkedList<String>();
                        for (Tappa t : pacchetto.getTappeIntermedie()) {
                            tappe.add(t.getIndirizzo().getCitta());
                        }
                    %>
                    partenza:<%=partenza%><br>
                    arrivo:<%=arrivo%><br>

                    <%
                    if(tappe.size()>0){ %>
                        Tappe intermedie:
                        <%
                        int i = 0;
                        for (i = 0; i < tappe.size(); i++) %>
                            <%=tappe.get(i)%> <br>
                    <%} if(nota!=null && !nota.equals("")){%>
                    <br>Lughezza percorso: <%=lunghezzaPercorso %>
                    <br>Autista: <a href='ServletController?operation=visualizzaProfilo&id='<%=autista.getId() %>><%=autista.getLogin() %></a>
                    <br><br><%=nota %> <%}%>
                    <br>Ora partenza: <%=viaggi.get(0).getDataPartenza().get(Calendar.HOUR) %>:%=viaggi.get(0).getDataPartenza().get(Calendar.MINUTE) %>
                    <br>Richiesta contributi: <%if(richiestaContributi){%>Si<%}else{%>No<%}%>
                    Viaggi:
                    <% int i=0;
                    for(i=0; i<viaggi.size(); i++){
                        Viaggio v=viaggi.get(i);
                        %>
                        <br><%=v.getDataPartenza().get(Calendar.DAY_OF_MONTH) %>/<%=v.getDataPartenza().get(Calendar.MONTH) %>/<%=v.getDataPartenza().get(Calendar.YEAR) %>
                        <%if(v.isModificato()){%><b>*</b><%}%>
                    <%}%>

                    <br><br>Bacheca:<br>
                    <table border="1" width="80%">
                    <%
                    i=0;
                    List<Post> posts = bacheca.getMessaggi();
                    for(i=0;i<posts.size();i++){
                        %><tr><td><%=posts.get(i).toString() %></td></tr>
                    <%}%>
                    </table>
                    <form action="ServletController" method="Post">
                        <input type="HIDDEN" name="operation" value="addPostBacheca">
                        <input type="HIDDEN" name="pacchettoId" value="<%=pacchetto.getId() %>">
                        Testo:
                        <input type="textarea" name="text" value="">
                        <input type="SUBMIT" value="Aggiungi commento">
                    </form>

                    <br><br>
                    <b>*</b> i viaggi contrassegnati da * hanno tappe diverse da quelle del pacchetto
                    <!--<a href="ServletController?operation=modificaPacchetto">modifica il pacchetto</a>-->
                </div>
            </div>
        </div>
    </body>
</html>
