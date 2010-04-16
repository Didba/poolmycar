<%--
    Document   : InserisciLuoghiViaggio
    Created on : 6-apr-2010, 16.45.00
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">



<!-- TO-DO: GESTIONE DELLE ECCEZIONI -->



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
                        var oField=document.createElement("INPUT");

                        // setta attributi
                        oField.setAttribute("type","text");
                        oField.setAttribute("name","tappa"+num);
                        oField.setAttribute("onchange","disattiva('tastosubmit')");

                        // appendi al relativo padre
                        oTd1.appendChild(oField);
                        oTr.appendChild(oTd1);
                        document.getElementById('tabella').getElementsByTagName('TBODY')[0].appendChild(oTr);

                        // incrementa variabile globale
                        num++

                        disattiva('tastosubmit');
                        attiva('tastorimuovi');
                }
        }


        function rimuovi(){
                //if(document.removeChild && document.getElementById && document.getElementsByTagName) {
                if(num>2){

                // risali al tr
                var tr=document.getElementById('tabella').getElementsByTagName('TR')[num-1];

                // riferimento al tbody
                var tb=document.getElementById('tabella').getElementsByTagName('TBODY')[0];

                // rimuovi
                tb.removeChild(tr);

                num--;
                }

                //}

                setDirections();

                if(num<=2)
                    disattiva('tastorimuovi');
        }


        function disattiva(valore)
        {
            for(i=0;i<document.forms[0].elements.length;i++)
            {
                    if(document.forms[0].elements[i].type=="submit" || document.forms[0].elements[i].type=="button")
                    {
                            document.getElementById(valore).disabled = true;
                    }
            }
        }

        function attiva(valore)
        {
            for(i=0;i<document.forms[0].elements.length;i++)
            {
                    if(document.forms[0].elements[i].type=="submit" || document.forms[0].elements[i].type=="button")
                    {
                            document.getElementById(valore).disabled = false;
                    }
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

            map.setCenter(new GLatLng(45.0904249, 7.660769), 15);
            map.setUIToDefault();

            disattiva('tastosubmit');
            disattiva('tastorimuovi');

            //setDirections("San Francisco", "Mountain View", "en_US");

            //gdir.load("from: Torino to: milano to: genova");
          }
        }

        function setDirections() {
         //gdir.load("from: " + fromAddress + " to: " + toAddress,
          //          ,{ "locale": locale });
          //var percorso="from: "+modulo.tappa0.value+" to: "+modulo.tappa1.value;
          var percorso="from: "+document.forms[0].elements[2].value+" to: "+document.forms[0].elements[3].value;
          for(var j=2+2; j<num+2; j++){
              percorso=percorso+" to: "+document.forms[0].elements[j].value;
          }
          gdir.load(percorso);

          attiva('tastosubmit');
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
        <input type="button" value="accoda" onclick="accoda()"/>
        <input type="button" id="tastorimuovi" disabled value="rimuovi" onclick="rimuovi()"/>
        <table border="1" id="tabella">
        <tbody>
        <tr>
        <td><input type="text" name="tappa0" onchange="disattiva('tastosubmit')"/></td><!--<td><input type="button" disabled="disabled" value="rimuovi" /></td>-->
        </tr>
        <tr>
        <td><input type="text" name="tappa1" onchange="disattiva('tastosubmit')"/></td><!--<td><input type="button" disabled="disabled" value="rimuovi" /></td>-->
        </tr>
        </tbody>
        </table>
        <input type="hidden" value="" id="distanza" name="distanza"/>
        <input type="submit" id="tastosubmit" disabled name="operation" value="inserisciTappe" onclick="document.getElementById('distanza').value = gdir.getDistance().meters;
"/>
        <input type="button" value="visualizza" onclick="setDirections();"/>
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
