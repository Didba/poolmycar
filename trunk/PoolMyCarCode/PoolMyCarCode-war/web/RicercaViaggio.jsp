<%-- 
    Document   : RicercaViaggio
    Created on : 14-apr-2010, 10.02.45
    Author     : Erica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel=stylesheet href="style.css" type="text/css">

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


        <div id="mainPan">
            <div id="topPan"></div>
            <div id="headerPan">
                <h1>Welcome to PoolMyCar, netSurfer!</h1>


                <ul class="botton">

                    <li class="home">
                        <form name="login" action="ServletController" method="POST">
                            <table border=0>
                                <tr><td>Username:</td><td> <input name="login" type="text" value="" size=10/> </td></tr>
                                <tr><td>Password:</td><td> <input name="password" type="password" value="" size=10/> </td></tr>
                                <tr><td align="center"><input type="submit" name="operation" value="login"/></td><td><input type="submit" name="operation" value="registrati"/></td></tr>
                            </table>
                        </form>
                    </li>
                </ul>


            </div>
            <div id="bodyPan">
                <div id="leftPan">
                    <ul>
                        <li><a href="index.jsp"><span>Home</span></a></li>
                        <li><a href="RicercaViaggio.jsp"><span>Cerca Viaggio</span></a></li>
                        <li><a href="ServletController"><span>Cerca Autista</span></a></li>
                        <li><a href=""><span>Partner</span></a></li>
                        <li><a href=""><span>Chi siamo</span></a></li>
                        <li><a href=""><span>Contatti</span></a></li>
                    </ul>


                </div>
                <div id="rightPan">

                    <center>
                    <h1>Inserisci i dati del viaggio</h1>


                    <form action="ServletController" method="POST" onSubmit="return(checkDate());">
                        <div>
                            Partenza <input name="partenza" type="text" onkeyup="if(this.value!=''){loadContent(this.value);}else{document.getElementById('prtCnt').innerHTML=''}"/>
                            Arrivo <input name="arrivo" type="text" onkeyup="if(this.value!=''){loadContent(this.value);}else{document.getElementById('prtCnt').innerHTML=''}"/>
                        </div>

                        <div id="prtCnt"></div>

                        <div>
                            <input name="opIntervalloDate" type="radio" onclick="abilitaIntervallo('Date');" value="Date"/> vuoi intervallo date
                            <input name="opIntervalloDate" type="radio" onclick="abilitaIntervallo('DataSingola');" value="DataSingola"/> vuoi data singola
                        </div>
                        <table><tr><td>
                        <div id="intervalloDate" style="visibility: hidden;">
                            Intervallo Date <br>
                            <table>
                                <tr>
                                    <td>Dal</td>
                                    <td><input name="data1" type="text" maxlength="10" size="10" readonly onclick="displayDatePicker('data1');"/>
                                        <button type="button" value="" style="width: 18px; height: 18px;border-right-style: none; border-left-style: none;border-bottom-style: none;border-top-style: none;" onclick="displayDatePicker('data1');"><img src="calendarSingleDate/calendar_ico.gif" alt=""></button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Al</td>
                                    <td><input name="data2" type="text" maxlength="10" readonly size="10" onclick="displayDatePicker('data2');"/>
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
                                    <td><input type="text" name="dataSingola" id="dataSingola" readonly maxlength="10" size="10" onclick="displayDatePicker('dataSingola');"/>
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
                        <input type="submit" name="operation" value="cerca"/>
                    </form>
                    </center>



                </div>
            </div>
        </div>
        <div id="footermainPan">
            <div id="footerPan">
                <ul>
                    <li><a href="">About</a>| </li>
                    <li><a href="">Solution</a>| </li>
                    <li><a href="">Support</a>| </li>
                    <li><a href="">Chat</a>| </li>
                    <li><a href="">Blog</a>| </li>

                    <li><a href="">Forum</a>| </li>
                    <li><a href="">Clients</a>| </li>
                </ul>
                <p class="copyright">©PoolMyCarGroup. All right reserved.</p>
                <div id="footerPanhtml"><a href="" target="_blank">XHTML</a></div>

                <div id="footerPancss"><a href="" target="_blank">css</a></div>
                <ul class="templateworld">
                    <li>design by:</li>
                    <li><a href="" target="_blank">PoolMyCarGroup</a></li>
                </ul>
            </div>
        </div>

















    </body>
</html>
