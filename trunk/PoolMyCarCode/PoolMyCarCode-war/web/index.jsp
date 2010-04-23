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

            <div id="headerPan">
                <h1>Welcome to PoolMyCar, netSurfer!</h1>


                <ul class="botton">

                    <li class="home"><form name="login" action="ServletController" method="POST">
                            <table border=0>
                                <tr><td>Username:</td><td> <input name="login" type="text" value="" size=10/> </td></tr>
                                <tr><td>Password:</td><td> <input name="password" type="password" value="" size=10/> </td></tr>
                                <tr><td align="center"><input type="submit" name="operation" value="login"/></td><td><input type="submit" name="operation" value="registrati"/></td></tr>

                            </table>
                        </form>
                </ul>


            </div>
            <div id="bodyPan">
                <div id="leftPan">

                    <ul>
                        <li><a href="index.jsp"><span>Home</span></a></li>
                        <li><a href="RicercaViaggio.jsp"><span>Cerca viaggio</span></a></li>
                        <li><a href=""><span>Inserisci viaggio</span></a></li>
                        <li><a href=""><span>Partner</span></a></li>
                        <li><a href=""><span>Chi siamo</span></a></li>
                        <li><a href=""><span>Contatti</span></a></li>
                    </ul>


                </div>
                <div id="rightPan">
                    <p>Aquatic is a free, tableless, W3C-compliant web design layout by Template World. This template has been tested and proven compatible with all major browser environments and operating systems. You are free to modify the design to suit your tastes in any way you like.</p>
                    <p>These templates are licensed under a Creative Commons Attribution 2.5 License. This means that you are free to modify the design to suit your tastes in any way you like, but you must include the provided link back to Template World.</p>

                    <p>If you are interested in seeing more of our free web template designs feel free to visit our website, Template World. We intend to add at least 25 new free templates in the coming month.</p>
                    <p class="more"><a href="http://www.free-css.com/">read more</a></p>
                    <h2>service overview</h2>
                    <ul class="services">
                        <li class="captionone">Quis magna vel</li>
                        <li><a href="http://www.free-css.com/">Amet, consectetuer</a></li>

                        <li><a href="http://www.free-css.com/">Adipiscing elit.</a></li>
                        <li><a href="http://www.free-css.com/">Praesent scing ips</a></li>
                        <li><a href="http://www.free-css.com/">Um Id nislpo.</a></li>
                    </ul>
                    <ul class="servicestwo">
                        <li class="captiontwo">Quis magna vel</li>
                        <li><a href="http://www.free-css.com/">Amet, consectetuer</a></li>

                        <li><a href="http://www.free-css.com/">Adipiscing elit.</a></li>
                        <li><a href="http://www.free-css.com/">Praesent scing ips</a></li>
                        <li><a href="http://www.free-css.com/">Um Id nislpo.</a></li>
                    </ul>
                    <p class="more"><a href="http://www.free-css.com/">read more</a></p>
                    <h4>current events</h4>

                    <ul class="events">
                        <li class="captionthree">24.07.06</li>
                        <li><a href="http://www.free-css.com/">Amet, consectetuer Adipiscing elit. Praesent </a></li>
                        <li><a href="http://www.free-css.com/">Scing ipconvallis, leo pede eleifend orci, sed</a></li>
                        <li><a href="http://www.free-css.com/">lobortis orci tortor id erat. Etiam facilisis. Etiam</a></li>
                        <li><a href="http://www.free-css.com/">Rutrum nuncs</a></li>

                    </ul>
                    <p class="more"><a href="http://www.free-css.com/">read more</a></p>
                </div>
            </div>
        </div>
        <div id="footermainPan">
            <div id="footerPan">
                <ul>
                    <li><a href="http://www.free-css.com/">Home</a>| </li>

                    <li><a href="http://www.free-css.com/">About</a>| </li>
                    <li><a href="http://www.free-css.com/">Solution</a>| </li>
                    <li><a href="http://www.free-css.com/">Support</a>| </li>
                    <li><a href="http://www.free-css.com/">Chat</a>| </li>
                    <li><a href="http://www.free-css.com/">Blog</a>| </li>

                    <li><a href="http://www.free-css.com/">Forum</a>| </li>
                    <li><a href="http://www.free-css.com/">Clients</a>| </li>
                    <li><a href="http://www.free-css.com/">Contact</a></li>
                </ul>
                <p class="copyright">©aquatic. All right reserved.</p>
                <div id="footerPanhtml"><a href="http://validator.w3.org/check?uri=referer" target="_blank">XHTML</a></div>

                <div id="footerPancss"><a href="http://jigsaw.w3.org/css-validator/check/referer" target="_blank">css</a></div>
                <ul class="templateworld">
                    <li>design by:</li>
                    <li><a href="http://www.templateworld.com" target="_blank">Template World</a></li>
                </ul>
            </div>
        </div>


</html>
