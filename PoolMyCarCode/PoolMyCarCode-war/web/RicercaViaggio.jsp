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
        <link rel="stylesheet" type="text/css" media="all" href="calendarSingleDate/calendarSingleDate.css"/>

        <!-- main calendar program -->
        <script type="text/javascript" src="calendarSingleDate/calendarSingleDate.js"></script>






        <script type="text/javascript" src="ajaxjs.js"></script>
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
    <body>

        <form action="ServletController" method="POST" onSubmit="return(checkDate());">
            PERCORSO
            <div>
                PARTENZA <input name="partenza" type="text" onkeyup="if(this.value!=''){loadContent(this.value);}else{document.getElementById('prtCnt').innerHTML=''}"/>
                ARRIVO <input name="arrivo" type="text" onkeyup="if(this.value!=''){loadContent(this.value);}else{document.getElementById('prtCnt').innerHTML=''}"/>
            </div>
            <div id="prtCnt"></div>
            <input name="opIntervalloDate" type="radio" onclick="abilitaIntervallo('Date');" value="Date"/> vuoi intervallo date <br>
            <input name="opIntervalloDate" type="radio" onclick="abilitaIntervallo('DataSingola');" value="DataSingola"/> vuoi data singola <br>

            <div id="intervalloDate" style="visibility: hidden;">
                INTERVALLO DATE <br>
                <table>
                    <tr>
                        <td>Dal</td>
                        <td><input name="data1" type="text" size="10" disabled/>
                            <button type="button" value="" style="width: 18px; height: 18px;border-right-style: none; border-left-style: none;border-bottom-style: none;border-top-style: none;" onclick="displayDatePicker('data1');"><img src="calendarSingleDate/calendar_ico.gif"></button>
                        </td>
                    </tr>
                    <tr>
                        <td>Al</td>
                        <td><input name="data2" type="text" size="10" disabled/>
                            <button type="button" value="" style="width: 18px; height: 18px;border-right-style: none; border-left-style: none;border-bottom-style: none;border-top-style: none;" onclick="displayDatePicker('data2');"><img src="calendarSingleDate/calendar_ico.gif"></button>
                        </td>
                    </tr>
                </table>
            </div>


            <div id="dataSingola" style="visibility: hidden;">
                DATA Singola<br>
                <table>
                    <tr>
                        <td> Data</td>
                        <td><input type="text" name="dataSingola" id="dataSingola" disabled size="10"/>
                            <button type="button" value="" style="width: 18px; height: 18px;border-right-style: none; border-left-style: none;border-bottom-style: none;border-top-style: none;" onclick="displayDatePicker('dataSingola');"><img src="calendarSingleDate/calendar_ico.gif"></button>
                        </td>
                    </tr>
                    <tr>
                        <td>Ora </td>
                        <td><input id="ora" name="ora" type="text" size="2" />:<input id="min" name="min" type="text" size="2" /></td>
                    </tr>
                </table>
            </div>
            <input type="submit" name="operation" value="cerca"/>
        </form>
    </body>
</html>
