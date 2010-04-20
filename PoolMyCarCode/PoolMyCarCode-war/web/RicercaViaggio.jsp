<%-- 
    Document   : RicercaViaggio
    Created on : 14-apr-2010, 10.02.45
    Author     : Erica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <script language="JavaScript" type="text/javascript">
        function abilitaIntervallo(n){
            var divInt = document.getElementById("intervalloDate");
            var divData = document.getElementById("dataSingola");
            if(n == "Date"){
                divData.style.visibility = "hidden";
                divInt.style.visibility = "visible";
            }
            else{
                divData.style.visibility = "visible";
                divInt.style.visibility = "hidden";
            }
        }
    </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ricerca Viaggio</title>
    </head>
    <body>
        <form action="ServletController" method="POST">
            PERCORSO
            <div>
                PARTENZA <input name="partenza" type="text" />
                ARRIVO <input name="arrivo" type="text" />
            </div>
            <input name="opIntervalloDate" type="radio" onclick="abilitaIntervallo('Date');" value="Date"/> vuoi intervallo date <br>
            <input name="opIntervalloDate" type="radio" onclick="abilitaIntervallo('DataSingola');" value="DataSingola"/> vuoi data singola <br>

            <div id="intervalloDate" style="visibility: hidden;">
                INTERVALLO DATE <br>
                Dal <input name="data1" type="text" />
                al <input name="data2" type="text" />
            </div>


            <div id="dataSingola" style="visibility: hidden;">
                DATA SINGOLA <br>
                Data <input name="dataPartenza" type="text" />
                ora <input name="ora" type="text" />
            </div>
            <input type="submit" name="operation" value="cerca"/>
        </form>
    </body>
</html>
