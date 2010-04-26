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
        <title>Inserimento Viaggio</title>
        <link rel=stylesheet href="style.css" type="text/css">
        <script src=" http://maps.google.com/?file=api&amp;v=2.x&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
        type="text/javascript"></script>
        <script language="javascript" type="text/javascript" src="Autocompletamento/autocomplete.js"></script>
        <script type="text/javascript" src="ajaxjs.js"></script>

        <script language="JavaScript" type="text/javascript">
            <!--
            var num=2;
            var percorso="";
            function accoda(){
                if(document.createElement && document.getElementById && document.getElementsByTagName) {
                    // crea elementi
                    var oTr=document.createElement("TR");
                    var oTd1=document.createElement("TD");
                    var oField=document.createElement("INPUT");

                    // setta attributi
                    oField.setAttribute("type","text");
                    oField.setAttribute("name","tappa"+num);
                    oField.setAttribute("id","tappa"+num);
                    oField.setAttribute("onchange","disattiva('tastosubmit')");

                    // appendi al relativo padre
                    oTd1.appendChild(oField);
                    oTr.appendChild(oTd1);
                    document.getElementById('tabella').getElementsByTagName('TBODY')[0].appendChild(oTr);
                    disattiva('tastosubmit');
                    attiva('tastorimuovi');
                    completamento("tappa"+num);
                    // incrementa variabile globale
                    num++;
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
                disattiva('tastosubmit');
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

                }
            }

            function setDirections() {
                var t0 = document.getElementById("tappa0").value;
                var t1 = document.getElementById("tappa1").value;
                if(t0==""||t1==""){
                    alert("Devi inserire almeno una partenza ed una destinazione");
                }
                else{
                    percorso="from: " + t0;
                    for(var j=1;j<num;j++){
                        var t = document.getElementById("tappa"+j).value;
                        percorso = percorso + " to: "+ t;
                    }
                    gdir.load(percorso);
                }
                attiva('tastosubmit');
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
                // Use this function to access information about the latest load()
                // results.

                // e.g.
                // document.getElementById("getStatus").innerHTML = gdir.getStatus().code;
                // and yada yada yada...
            }


            /*function keypressed(){
            if(event.keyCode=='13'){ setDirections(); }
        }*/
            function checkReturn(evt) {
                var evt = (evt) ? evt : ((event) ? event : null);
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
                if ((evt.keyCode == 13) && (node.type=="text")) { return false;}
                disattiva('tastosubmit');
            }


            document.onkeypress = checkReturn;

            //-->
        </script>
    </head>
    <body onload="initialize();loadContent(['tappa0','tappa1']);" onkeyup="keypressed()" onunload="GUnload()">
        <div id="mainPan">
            <jsp:include page="/header.jsp"/>
            <div id="bodyPan">
                <jsp:include page="/leftpanel.jsp"/>
                <div id="rightPan">


                    <!-- Contenuto principale della pagina -->
                    <h3>Definisci il percorso del viaggio</h3>

                    <form name="modulo" action="ServletController" method="POST">


                        <div align="left">
                            <table border="1" id="tabella">
                                <tbody>
                                    <tr>
                                        <td><input type="text" id="tappa0" name="tappa0" onchange="disattiva('tastosubmit');" /></td><!--<td><input type="button" disabled="disabled" value="rimuovi" /></td>-->
                                    </tr>
                                    <tr>
                                        <td><input type="text" id="tappa1" name="tappa1" onchange="disattiva('tastosubmit');" /></td><!--<td><input type="button" disabled="disabled" value="rimuovi" /></td>-->
                                    </tr>
                                </tbody>
                            </table>
                            <button type="button" value="accoda" style="border-right-style: none; border-left-style: none;border-bottom-style: none;border-top-style: none;background-color: #ffffff;" onclick="accoda();">Aggiungi Tappa</button><br>
                            <button type="button" id="tastorimuovi" disabled value="rimuovi" style="border-right-style: none; border-left-style: none;border-bottom-style: none;border-top-style: none;background-color: #ffffff;" onclick="rimuovi();">Rimuovi Tappa</button>
                        </div>
                        <div align="right">



                            <input type="button" id="tastovisualizza" value="Visualizza Percorso" onclick="setDirections();"/>
                            <input type="submit" id="tastosubmit" disabled name="op" value="Conferma Percorso" onclick="document.getElementById('distanza').value = gdir.getDistance().meters;"/>

                        </div>
                        <input type="hidden" value="" id="distanza" name="distanza"/>
                        <input type="hidden" value="inserisciTappe" id="distanza" name="operation"/>


                    </form>


                    <table class="directions">
                               <tr><th>Formatted Directions</th><th>Map</th></tr>

                           <tr>
                               <td valign="top"><div id="directions" style="width: 275px"></div></td>
                               <td valign="top"><div id="map_canvas" style="width: 310px; height: 400px"></div></td>

                               </tr>
                    </table>





                </div>
            </div>
        </div>
        <jsp:include page="/foot.jsp"/>
    </body>



</html>
