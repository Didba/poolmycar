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
        <script src=" http://maps.google.com/?file=api&amp;v=2.x&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
      type="text/javascript"></script>
        
        <script language="JavaScript" type="text/javascript">

        var i=0;
        var map;
        var gdir;
        var geocoder = null;
        var addressMarker;

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


        </script>


    </head>
    
    <body onload="initialize();" onkeyup="keypressed()" onunload="GUnload()">
        <center>
            <form action="" method="GET">
                <h1>Ecco i tuoi viaggi</h1>


                    <%
                        ris = (RisultatiRicercaViaggi) session.getAttribute("risulatatoRicerca");
                        pacchetti = ris.getNextPacchetti(3);
                        if(pacchetti==null){%>
                           Nulla
                    <%  }else{%>
                    <table border=1>
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
                          String percorso="";
                          percorso += ("from: " + p.getPartenza().getIndirizzo().toString());
                          //tappe intermedie
                          percorso += ("to: " + p.getArrivo().getIndirizzo().toString());
                          String idMap = "map"+i;
                          String idDir = "dir"+i;
                          String idPren = "pre"+i;
                          String chiamata = "mappa('"+ percorso +"','"+idMap+"','" + idDir + "')";
                    %>
                    <tr>
                        <td>
                            <%= p.getPartenza().getIndirizzo().toString()%>
                        </td>
                        <td>
                            <%= p.getArrivo().getIndirizzo().toString()%>
                        </td>
                        <td>
                            <%= p.getAutista().getCognome()%>
                        </td>
                        <td>
                            <%= p.getInizio().getTime().toString()%>
                        </td>
                        <td valign="top">

                          <div  id="<%=idMap%>" style="width: 310px; height: 400px"></div>
                        <!-- <div id="<%=idDir%>" style="width: 275px"></div>-->

                        </td>
                        <td>
                            <input type="button" id="<%=idPren%>" value="Vis Mappa"  onclick="<%=chiamata%>">
                        </td>
                    </tr>
                    <%} // chiusura for
                    %>
                    </table>
                    <a href="RisultatiRicerca.jsp">next</a>

                    <a href="RisultatiRicerca.jsp" onclick="">precedente</a>
                    <%} //chiusura else%>
                
            </form>
        </center>


    </body>
</html>


