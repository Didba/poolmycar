<%-- 
    Document   : InserisciLuoghiViaggio
    Created on : 6-apr-2010, 16.45.00
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script language="JavaScript" type="text/javascript">
        <!--
        var num=2;
        function accoda(){
                if(document.createElement && document.getElementById && document.getElementsByTagName) {
                        // crea elementi
                        var oTr=document.createElement("TR");
                        var oTd1=document.createElement("TD");
                        //var oTd2=document.createElement("TD");
                        var oField=document.createElement("INPUT");
                        //var oButt=document.createElement("INPUT");

                        // setta attributi
                        oField.setAttribute("type","text");
                        oField.setAttribute("name","tappa"+num);
                        //oButt.setAttribute("type","button");
                        //oButt.setAttribute("value","rimuovi");

                        // setta gestore evento
                        //if(oButt.attachEvent) oButt.attachEvent('onclick',function(e){rimuovi(e);})
                        //else if(oButt.addEventListener) oButt.addEventListener('click',function(e){rimuovi(e);},false)

                        // appendi al relativo padre
                        oTd1.appendChild(oField);
                        //oTd2.appendChild(oButt);
                        oTr.appendChild(oTd1);
                        //oTr.appendChild(oTd2);
                        document.getElementById('tabella').getElementsByTagName('TBODY')[0].appendChild(oTr);

                        // incrementa variabile globale
                        num++
                }
        }


        function rimuovi(e){
                if(document.removeChild && document.getElementById && document.getElementsByTagName) {
                        if(!e) e=window.event;
                        var srg=(e.target)?e.target:e.srcElement;

                        // risali al tr del td che contiene l' elemento che ha scatenato l' evento
                        while(srg.tagName!="TR"){srg=(srg.parentNode)?srg.parentNode:srg.parentElement}

                        // riferimento al tbody
                        var tb=document.getElementById('tabella').getElementsByTagName('TBODY')[0];

                        // rimuovi
                        tb.removeChild(srg);
                }
        }
        //-->
        </script>
    </head>
    <body>
        <form name="modulo" action="ServletController" method="POST">
        <input type="button" value="accoda" onclick="accoda()" />
        <table border="1" id="tabella">
        <tbody>
        <tr>
        <td><input type="text" name="tappa0" /></td><!--<td><input type="button" disabled="disabled" value="rimuovi" /></td>-->
        </tr>
        <tr>
        <td><input type="text" name="tappa1" /></td><!--<td><input type="button" disabled="disabled" value="rimuovi" /></td>-->
        </tr>
        </tbody>
        </table>
        <input type="submit" name="operation" value="inserisciTappe"/>
        </form>
    </body>
</html>