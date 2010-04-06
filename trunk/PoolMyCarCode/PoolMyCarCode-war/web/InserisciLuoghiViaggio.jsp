<%--
    Document   : InserisciLuoghiViaggio
    Created on : 6-apr-2010, 16.45.00
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src=" http://maps.google.com/?file=api&amp;v=2.x&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
      type="text/javascript"></script>
        <style type="text/css">
          body {
            font-family: Verdana, Arial, sans serif;
            font-size: 11px;
            margin: 2px;
          }
          table.directions th {
            background-color:#EEEEEE;
          }

          img {
            color: #000000;
          }
        </style>

        <script language="JavaScript" type="text/javascript">
        <!--
        var num=2;
        function accoda(){
                if(document.createElement && document.getElementById && document.getElementsByTagName) {
                        // crea elementi
                        var oTr=document.createElement("TR");
                        var oTd1=document.createElement("TD");
                        //var oTd2=document.createElement("TD");
                        var oField=document.createElement("INPUT");
                        //var oButt=document.createElement("INPUT");

                        // setta attributi
                        oField.setAttribute("type","text");
                        oField.setAttribute("name","tappa"+num);
                        //oButt.setAttribute("type","button");
                        //oButt.setAttribute("value","rimuovi");

                        // setta gestore evento
                        //if(oButt.attachEvent) oButt.attachEvent('onclick',function(e){rimuovi(e);})
                        //else if(oButt.addEventListener) oButt.addEventListener('click',function(e){rimuovi(e);},false)

                        // appendi al relativo padre
                        oTd1.appendChild(oField);
                        //oTd2.appendChild(oButt);
                        oTr.appendChild(oTd1);
                        //oTr.appendChild(oTd2);
                        document.getElementById('tabella').getElementsByTagName('TBODY')[0].appendChild(oTr);

                        // incrementa variabile globale
                        num++
                }
        }


        function rimuovi(e){
                if(document.removeChild && document.getElementById && document.getElementsByTagName) {
                        if(!e) e=window.event;
                        var srg=(e.target)?e.target:e.srcElement;

                        // risali al tr del td che contiene l' elemento che ha scatenato l' evento
                        while(srg.tagName!="TR"){srg=(srg.parentNode)?srg.parentNode:srg.parentElement}

                        // riferimento al tbody
                        var tb=document.getElementById('tabella').getElementsByTagName('TBODY')[0];

                        // rimuovi
                        tb.removeChild(srg);
                }
        }



        var map;
        var gdir;
        var geocoder = null;
        var addressMarker;

        function initialize() {
          if (GBrowserIsCompatible()) {
            map = new GMap2(document.getElementById("map_canvas"));
            gdir = new GDirections(map, document.getElementById("directions"));
            GEvent.addListener(gdir, "load", onGDirectionsLoad);
            GEvent.addListener(gdir, "error", handleErrors);
            //map.setCenter("new GLatLng(37.4419, -122.1419)", 13);

            //setDirections("San Francisco", "Mountain View", "en_US");

            gdir.load("from: Torino to: milano to: genova");
          }
        }

        function setDirections() {
          //gdir.load("from: " + fromAddress + " to: " + toAddress,
          //          ,{ "locale": locale });
          var percorso="from: "+modulo.tappa0.value+" to: "+modulo.tappa1.value;
          /*for(var j=1; j<num; j++){
              percorso=percorso+" to: "+modulo..value";
          }*/
          gdir.load(percorso);
        }

        function handleErrors(){
               if (gdir.getStatus().code == G_GEO_UNKNOWN_ADDRESS)
                 alert("No corresponding geographic location could be found for one of the specified addresses. This may be due to the fact that the address is relatively new, or it may be incorrect.\nError code: " + gdir.getStatus().code);
               else if (gdir.getStatus().code == G_GEO_SERVER_ERROR)
                 alert("A geocoding or directions request could not be successfully processed, yet the exact reason for the failure is not known.\n Error code: " + gdir.getStatus().code);

               else if (gdir.getStatus().code == G_GEO_MISSING_QUERY)
                 alert("The HTTP q parameter was either missing or had no value. For geocoder requests, this means that an empty address was specified as input. For directions requests, this means that no query was specified in the input.\n Error code: " + gdir.getStatus().code);

            //   else if (gdir.getStatus().code == G_UNAVAILABLE_ADDRESS)  <--- Doc bug... this is either not defined, or Doc is wrong
            //     alert("The geocode for the given address or the route for the given directions query cannot be returned due to legal or contractual reasons.\n Error code: " + gdir.getStatus().code);

               else if (gdir.getStatus().code == G_GEO_BAD_KEY)
                 alert("The given key is either invalid or does not match the domain for which it was given. \n Error code: " + gdir.getStatus().code);

               else if (gdir.getStatus().code == G_GEO_BAD_REQUEST)
                 alert("A directions request could not be successfully parsed.\n Error code: " + gdir.getStatus().code);

               else alert("An unknown error occurred.");

            }

            function onGDirectionsLoad(){
          // Use this function to access information about the latest load()
          // results.

          // e.g.
          // document.getElementById("getStatus").innerHTML = gdir.getStatus().code;
              // and yada yada yada...
            }



        //-->
        </script>
    </head>
    <body onload="initialize()" onunload="GUnload()">
        <form name="modulo" action="ServletController" method="POST">
        <input type="button" value="accoda" onclick="accoda()" />
        <table border="1" id="tabella">
        <tbody>
        <tr>
        <td><input type="text" name="tappa0" /></td><!--<td><input type="button" disabled="disabled" value="rimuovi" /></td>-->
        </tr>
        <tr>
        <td><input type="text" name="tappa1" /></td><!--<td><input type="button" disabled="disabled" value="rimuovi" /></td>-->
        </tr>
        </tbody>
        </table>
        <input type="submit" name="operation" value="inserisciTappe"/>
        <input type="button" value="visualizza" onclick="setDirections();">
        </form>


        <table class="directions">
        <tr><th>Formatted Directions</th><th>Map</th></tr>

        <tr>
        <td valign="top"><div id="directions" style="width: 275px"></div></td>
        <td valign="top"><div id="map_canvas" style="width: 310px; height: 400px"></div></td>

        </tr>
        </table>

    </body>



</html>