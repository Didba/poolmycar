<%-- 
    Document   : index
    Created on : 13-dic-2009, 17.18.48
    Author     : Erica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link rel=stylesheet href="style.css" type="text/css">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                        <li><a href="ServletController"><span>Inserisci Viaggio</span></a></li>
                         <li><a href="ServletController"><span>Cerca Autista</span></a></li>
                        <li><a href=""><span>Partner</span></a></li>
                        <li><a href=""><span>Chi siamo</span></a></li>
                        <li><a href=""><span>Contatti</span></a></li>
                    </ul>


                </div>
                <div id="rightPan">

                    
                    <h1>Hai bisogno di un passaggio?</h1>
                    <p>Contatta e incontra persone per i tuoi trasferimenti, piccole tratte, brevi spostamenti. Un nostro associato potrebbe accompagnarti. Ecco cosa fare:
                    </p>
                    <img width="530" height=680" align="center" src="images/index.jpg" alt=""/>
                    
                    <p>Per avere più informazioni, cliccare sul pulsante. </p>
                    <p class="more"><a href="">Read more</a></p>
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


</html>
