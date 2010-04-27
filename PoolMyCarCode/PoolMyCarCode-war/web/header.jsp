

<%@page import="utenti.*" %>
<div id="topPan"><img src="images/LogoPMC.gif" alt="PoolMyCar" width="200" height="31" border="0"  class="logo" title="PoolMyCar"/>
</div>
<div id="headerPan">

    <!-- <ul class="botton">

         <li class="home">-->
    <div align="right">
        <%
            Viaggiatore v = (Viaggiatore) session.getAttribute("utente");
            if (v != null) {
        %>
        <h1>Benvenuto <%= v.getNome()%></h1>
        <form name="logout" action="ServletController" method="POST">
            <input name="operation" type="submit" value="logout"/>
        </form>

        <%
                    } else {
        %>
        <form name="login" action="ServletController" method="POST">
            <table border=0>
                <tr><td>Username:</td><td> <input name="login" type="text" value="" size=10/> </td></tr>
                <tr><td>Password:</td><td> <input name="password" type="password" value="" size=10/> </td></tr>
                <tr><td align="center"><input type="submit" name="operation" value="login"/></td><td><input type="submit" name="operation" value="registrati"/></td></tr>
            </table>
        </form>


        <%}%>
        <!-- </li>
        </ul>-->
    </div>

</div>