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
        <!-- calendar stylesheet -->
        <link rel=stylesheet href="style.css" type="text/css">
        <link rel="stylesheet" type="text/css" media="all" href="calendarSingleDate/calendarSingleDate.css"/>

        <!-- main calendar program -->
        <script type="text/javascript" src="calendarSingleDate/calendarSingleDate.js"></script>

        <script language="javascript" type="text/javascript" src="Autocompletamento/autocomplete.js"></script>
        <script type="text/javascript" src="ajaxjs.js"></script>
        <script language="JavaScript" type="text/javascript">
            
   

  function invia(){
                   ds = document.getElementById("ds").value;
                   ora = document.getElementById("ora").value;
                   min = document.getElementById("min").value;
                   data1 = document.getElementById("data1").value;
                   data2 = document.getElementById("data2").value;
                   if((ds!="" && ora!="" && min!="") || (data1!="" && data2!="")) {
                       document.getElementById("form").submit();
                   }
                   else{
                       alert("Inserisci l'intervallo di date o una data singola con l'ora");
                   }
            }


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
            function checkDate(){
                if(document.getElementById("ora").value<=23 && document.getElementById("ora").value>=0 && document.getElementById("min").value<=59 && document.getElementById("min").value>=0){
                    return true;
                }
                else{
                    alert("inserisci un'ora realistica");
                    document.getElementById("ora").value="";
                    document.getElementById("min").value="";
                    return false;
                }

            }
        </script>



        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ricerca Viaggio</title>
    </head>
    <body onload="loadContent(new Array('partenza','arrivo'));">
        <div id="mainPan">
            <jsp:include page="/header.jsp"/>
            <div id="bodyPan">
                <jsp:include page="/leftpanel.jsp"/>
                <div id="rightPan">


                    <!-- Contenuto principale della pagina -->

                    <center>
                        <h1>Inserisci i dati del viaggio</h1>


                        <form id ="form" action="ServletController" method="POST" onSubmit="return(checkDate());">
                            <div>
                                Partenza <input id="partenza" name="partenza" type="text"/>
                                Arrivo <input id="arrivo" name="arrivo" type="text"/>
                                <!-- Partenza <input id="partenza" name="partenza" type="text" onkeyup="if(this.value!=''){loadContent(this.value);}else{document.getElementById('prtCnt').innerHTML=''}"/>
                                 Arrivo <input id="arrivo" name="arrivo" type="text" onkeyup="if(this.value!=''){loadContent(this.value);}else{document.getElementById('prtCnt').innerHTML=''}"/>-->
                            </div>

                            <div id="prtCnt"></div>

                            <div>
                                <input name="opIntervalloDate" type="radio" onclick="abilitaIntervallo('Date'); " value="Date"/> vuoi intervallo date
                                <input name="opIntervalloDate" type="radio" onclick="abilitaIntervallo('DataSingola');" value="DataSingola"/> vuoi data singola
                            </div>
                            <table><tr><td>
                                        <div id="intervalloDate" style="visibility: hidden;">
                                            Intervallo Date <br>
                                            <table>
                                                <tr>
                                                    <td>Dal</td>
                                                    <td><input id="data1" name="data1" type="text" maxlength="10" size="10" readonly onclick="displayDatePicker('data1');"/>
                                                        <button type="button" value="" style="width: 18px; height: 18px;border-right-style: none; border-left-style: none;border-bottom-style: none;border-top-style: none;" onclick="displayDatePicker('data1');"><img src="calendarSingleDate/calendar_ico.gif" alt=""></button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Al</td>
                                                    <td><input id="data2" name="data2" type="text" maxlength="10" readonly size="10" onclick="displayDatePicker('data2');"/>
                                                        <button type="button" value="" style="width: 18px; height: 18px;border-right-style: none; border-left-style: none;border-bottom-style: none;border-top-style: none;" onclick="displayDatePicker('data2');"><img src="calendarSingleDate/calendar_ico.gif" alt=""></button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td><td>

                                        <div id="dataSingola" style="visibility: hidden;">
                                            Data Singola<br>
                                            <table>
                                                <tr>
                                                    <td> Data</td>
                                                    <td><input type="text" name="dataSingola" id="ds" readonly maxlength="10" size="10" onclick="displayDatePicker('dataSingola');"/>
                                                        <button type="button" value="" style="width: 18px; height: 18px;border-right-style: none; border-left-style: none;border-bottom-style: none;border-top-style: none;" onclick="displayDatePicker('dataSingola');"><img src="calendarSingleDate/calendar_ico.gif" alt=""></button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Ora </td>
                                                    <td><input id="ora" name="ora" type="text" maxlength="2"  size="2" />:<input id="min" name="min" type="text" maxlength="2" size="2"/></td>
                                                </tr>
                                            </table>
                                        </div>

                                    </td>
                                </tr>
                            </table>
                            <input type="hidden" name="operation" value="cerca"
                            <input type="button"  name="operation1" value="cerca" onclick="invia()"/>
                        </form>
                    </center>



                </div>
            </div>
        </div>
    <jsp:include page="/foot.jsp"/>

</body>
</html>
