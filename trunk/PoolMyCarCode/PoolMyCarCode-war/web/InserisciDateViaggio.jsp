<%-- 
    Document   : InserisciDateViaggio
    Created on : 12-apr-2010, 12.02.14
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="ejb.CarrelloInserimentoViaggioBean" %>

<html>
    <head>
        <title>Definizione date del viaggio</title>
        <link rel=stylesheet href="style.css" type="text/css">
        <!-- calendar stylesheet -->
        <link rel="stylesheet" type="text/css" media="all" href="calendar/calendar-blue.css" title="win2k-cold-1" /> 
        <!-- main calendar program -->
        <script type="text/javascript" src="calendar/calendar.js"></script>
        <!-- language for the calendar -->
        <script type="text/javascript" src="calendar/lang/calendar-it.js"></script>
        <!-- the following script defines the Calendar.setup helper function, which makes
             adding a calendar a matter of 1 or 2 lines of code. -->
        <script type="text/javascript" src="calendar/calendar-setup.js"></script>
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
    <%
        CarrelloInserimentoViaggioBean c = (CarrelloInserimentoViaggioBean)session.getAttribute("creazioneViaggiBean");
        String chiamata = "mappa('"+ c.getPercorso() +"','map','dir');";
    %>

    <body onload="GUnload();<%=chiamata%>">
        <div id="mainPan">
            <jsp:include page="/header.jsp"/>
            <div id="bodyPan">
                <jsp:include page="/leftpanel.jsp"/>
                <div id="rightPan">


                    <!-- Contenuto principale della pagina -->
                                       

                    <div  id="map" style="width: 600px; height: 300px"></div><br><br>
                    <form name="dati" id="formDate" action="ServletController" method="POST" onSubmit="return(scriviDate());">
                    <table border="0">
                        <tr>
                            <td> <div id="output" align="left"> <h3>Date selezionate</h3>   </div></td>
                            <td><div align="right"> <a id="trigger" href="#" title="Apri calendario e seleziona le date"> <img src="calendarSingleDate/calendar_ico.gif" alt=""></a></div></td>
                        </tr>
                        <tr>
                            <td align="right"><h3>Ora</h3></td>
                            <td align="right"><input name="ora" id="ora" type="text" size="2" maxlength="2"/>:</td>
                            <td align="left"><input id="min" name="min" type="text" size="2" maxlength="2"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td><input type="submit" name="operation" value="inserisciDate" /></td>
                        </tr>

                    </table>
                    
                    
                   
                    
                   
                    
                     
                        <input type="hidden" name="date" id="campoDate" value=""/>
                        
                    </form>



                </div>
            </div>
        </div>
        <jsp:include page="/foot.jsp"/>
<script type="text/javascript">//<![CDATA[
    // the default multiple dates selected, first time the calendar is instantiated
    var MA = [];
    var stringDate="";

    function scriviDate(){
        if(document.getElementById("ora").value<=23 && document.getElementById("ora").value>=0 && document.getElementById("min").value<=59 && document.getElementById("min").value>=0){
	    var el = document.getElementById("campoDate");
	    el.setAttribute("value",stringDate);
            return true;
        }
        else{
            alert("inserisci un'ora realistica");
            document.getElementById("ora").value="";
            document.getElementById("min").value="";
            return false;
        }

    }

    function closed(cal) {

      // here we'll write the output; this is only for example.  You
      // will normally fill an input field or something with the dates.
      var el = document.getElementById("output");

      // reset initial content.
      el.innerHTML = "<h3>Date selezionate</h3><ul>";

      // Reset the "MA", in case one triggers the calendar again.
      // CAREFUL!  You don't want to do "MA = [];".  We need to modify
      // the value of the current array, instead of creating a new one.
      // Calendar.setup is called only once! :-)  So be careful.
      MA.length = 0;

      // walk the calendar's multiple dates selection hash

      stringDate="";
      for (var i in cal.multiple) {
        var d = cal.multiple[i];
        // sometimes the date is not actually selected, that's why we need to check.
        if (d) {
          // OK, selected.  Fill an input field.  Or something.  Just for example,
          // we will display all selected dates in the element having the id "output".
          var stringa=d.print("%A %d %B %Y");
          el.innerHTML += ("<li><span>"+stringa + "</span></li>");
          stringDate=stringDate+stringa+";";
          // and push it in the "MA", in case one triggers the calendar again.
          MA[MA.length] = d;
        }
      }
      el.innerHTML += "</ul>";
      cal.hide();
      return true;
    };

    Calendar.setup({
      align      : "BR",
      showOthers : true,
      multiple   : MA, // pass the initial or computed array of multiple dates to be initially selected
      onClose    : closed,
      button     : "trigger"
    });
  //]]>
  </script>
    </body>
</html>
