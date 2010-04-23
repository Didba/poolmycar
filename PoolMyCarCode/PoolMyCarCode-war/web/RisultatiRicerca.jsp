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


    <link rel=stylesheet href="style.css" type="text/css"/>

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
    <style>
        .sub{
           color: #007163;
}
    </style>

    </head>
    
    <body  onunload="GUnload();" onload="seleziona('0');">
        <center>
            <form action="RisultatiRicerca.jsp" method="POST" >
                <h1>Viaggi Trovati</h1>
                <div border="1">
                    Criteri<br>
                    <%
                        //String paramPartenza = session.getAttribute("partenza")==null? null : (String)(session.getAttribute("partenza"));
                        //String paramArrivo = session.getAttribute("arrivo")==null? null : (String)(session.getAttribute("arrivo"));
                        String paramPartenza = (String)(session.getAttribute("partenza"));
                        String paramArrivo = (String)(session.getAttribute("arrivo"));
                    %>
                    <% if(paramPartenza!=null && !paramPartenza.equals("")){%><i class="sub">partenza: </i> <%= paramPartenza%>  <br><%}%>
                    <% if(paramArrivo!=null && !paramArrivo.equals("")){%><i class="sub">arrivo: </i><%= paramArrivo%><br><%}%>
                    <%
                        if((Boolean)session.getAttribute("intervallo")){
                            Calendar paramD1 = (Calendar)session.getAttribute("data1");
                            Calendar paramD2 = (Calendar)session.getAttribute("data2");
                            String dp1 = "" + paramD1.get(Calendar.DAY_OF_MONTH);
                            dp1 = dp1 + "/" + (paramD1.get(Calendar.MONTH)+1);
                            dp1 = dp1 + "/" + paramD1.get(Calendar.YEAR);
                            String dp2 = "" + paramD2.get(Calendar.DAY_OF_MONTH);
                            dp2 = dp2 + "/" + (paramD2.get(Calendar.MONTH)+1);
                            dp2 = dp2 + "/" + paramD2.get(Calendar.YEAR);
                    %>
                    <i class="sub">dal: </i> <%= dp1%> <i class="sub"> al: </i><%= dp2%><br>
                     <%   }else{
                            Calendar paramDop = (Calendar)session.getAttribute("dataOraPartenza");
                            String dp = "" + paramDop.get(Calendar.DAY_OF_MONTH);
                            dp = dp + "/" + (paramDop.get(Calendar.MONTH)+1);
                            dp = dp + "/" + paramDop.get(Calendar.YEAR);
                            String ora = "" + (paramDop.get(Calendar.MINUTE)<10? "0" : "" )+paramDop.get(Calendar.MINUTE);
                            ora = ""+ paramDop.get(Calendar.HOUR_OF_DAY)+ ":" +ora;
                     %>
                     <i class="sub">il: </i> <%= dp%> <i class="sub"> dalle: </i><%= ora%><br>
                      <%  }
                    %>
                    <br>
                    <a href="./RicercaViaggio.jsp">Nuova Ricerca</a>
                </div>


                    <%
                        ris = (RisultatiRicercaViaggi) session.getAttribute("risulatatoRicerca");
                        String d = request.getParameter("direzione");
                        ris.setNumGruppoPacchetti(3);
                        if(d==null || d.equals("avanti"))
                            pacchetti = ris.getNextPacchetti();
                        else
                            pacchetti = ris.getPredPacchetti();
                        if(pacchetti==null || pacchetti.size()==0){%>
                           Nulla
                           <%
                        }else{
                        %>
                    <div  id="map" style="width: 600px; height: 300px"></div>
                    <table border=1 width="600px" align="center" >

                    <%    for(int i = 0; i<pacchetti.size();i++){
                          Pacchetto p = pacchetti.get(i);
                          String percorso="";
                          percorso += ("from: " + p.getPartenza().getIndirizzo().toString());
                          if(p.getTappeIntermedie()!=null){
                              for(Tappa t: p.getTappeIntermedie())
                                  percorso += (" to: " + t.getIndirizzo().toString());
                          }

                          percorso += ("to: " + p.getArrivo().getIndirizzo().toString());
                          String id = "id"+i;
                          int k = pacchetti.size();
                          String chiamata = "mappa('"+ percorso +"','map','dir'); deseleziona('"+k+"'); document.getElementById('" + id+ "').style.backgroundColor = '#d3f0ef'";
                    %>
                    <tr class ="<%=id%>" id ="<%=id%>" onclick="<%=chiamata%>" >
                        <td>
                            
                            <i class="sub">partenza: </i>  <%= p.getPartenza().getIndirizzo().toString()%><br>
                            <i class="sub">arrivo: </i><%= p.getArrivo().getIndirizzo().toString()%><br>
                            <i class="sub">autista: </i><a href=""><%= p.getAutista().getCognome()%></a><br>
                            descrizione<br><br>
                        </td>
                        <td>
                            <%
                                for(Viaggio v :p.getViaggi()){
                                    String dp = "" + v.getDataPartenza().get(Calendar.DAY_OF_MONTH);
                                    dp = dp + "/" + (v.getDataPartenza().get(Calendar.MONTH)+1);
                                    dp = dp + "/" + v.getDataPartenza().get(Calendar.YEAR);
                                    String ora = "" + (v.getDataPartenza().get(Calendar.MINUTE)<10? "0" : "" )+v.getDataPartenza().get(Calendar.MINUTE);
                                    ora = ""+ v.getDataPartenza().get(Calendar.HOUR_OF_DAY)+ ":" +ora;
                            %>

                            <i class="sub">il </i><%=dp %>
                            <i class="sub"> alle ore </i><%=ora %>
                                <%
                                    if(session.getAttribute("utente")!=null){

                                 %>
                                    <a href="">prenota</a>
                                <%}%>
                                <br>
                            <%}
                            %>
                            
                        </td>
                        
                    </tr>
                    <%} // chiusura for
                    %>
                    </table>
                    <input type="hidden" value="avanti" id="idDirezione" name="direzione"/>
                        
                    <%
                        if(ris.indietro()){%>
                            <input type="submit" name="indietro" value="Indietro" title="indietro" style="border: hidden" onclick="document.getElementById('idDirezione').value ='indietro'"/>
                       
                   <%}
                        if(ris.avanti()){
                    %>
                            <input type="submit" name="avanti" value="Avanti" title="avanti" style="border: hidden" onclick="document.getElementById('idDirezione').value ='avanti'"/>
                   <%}%>
                    <%} //chiusura due else%>
                
            </form>
        </center>


    </body>
</html>


