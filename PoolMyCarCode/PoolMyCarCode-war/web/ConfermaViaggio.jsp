<%-- 
    Document   : ConfermaViaggio
    Created on : 12-apr-2010, 14.52.06
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Conferma viaggio</h1>
        <form action="ServletController" method="POST">
            contributi?<input name="contributi" type="checkbox"/>
            nota:<input name="nota" type="text"/>
            
            <input type='hidden' name='operation' value='viaggioConfermato'/>
            <input type='submit' value='Conferma viaggio'/>
        </form>
    </body>
</html>
