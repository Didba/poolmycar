<%@page import="utenti.*" %>
<div id="topPan"></div>
<div id="headerPan">
    <h1>Welcome to PoolMyCar, netSurfer!</h1>


    <ul class="botton">

        <li class="home">
            <form name="login" action="ServletController" method="POST">
                <%
                            Viaggiatore v = (Viaggiatore) session.getAttribute("utente");
                            if (v != null) {
                %>
                Benvenuto <%= v.getNome()%>
                <%
                                            } else {
                %>
                <table border=0>
                    <tr><td>Username:</td><td> <input name="login" type="text" value="" size=10/> </td></tr>
                    <tr><td>Password:</td><td> <input name="password" type="password" value="" size=10/> </td></tr>
                    <tr><td align="center"><input type="submit" name="operation" value="login"/></td><td><input type="submit" name="operation" value="registrati"/></td></tr>
                </table>
                <%}%>
            </form>
        </li>
    </ul>


</div>