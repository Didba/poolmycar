<%-- 
    Document   : PaginaViaggio
    Created on : 12-apr-2010, 16.55.33
    Author     : berto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="viaggi.Pacchetto" %>
<%@page import="viaggi.Tappa" %>
<%@page import="utenti.Indirizzo" %>
<%@page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="pacchetto" scope="session" class="viaggi.Pacchetto" />
        <h1>Pagina viaggio</h1>
        <%  String partenza=pacchetto.getPartenza().getIndirizzo().getCitta();
            String arrivo=pacchetto.getArrivo().getIndirizzo().getCitta();
            List<String> tappe= new LinkedList<String>();
            for(Tappa t:pacchetto.getTappeIntermedie())
                tappe.add(t.getIndirizzo().getCitta());
        %>
        partenza:<%=partenza%>
        arrivo:<%=arrivo%>
        <%
        int i=0;
        for(i=0; i<tappe.size(); i++) { %>
            tappa=<%=tappe.get(i)%>
        <% } %>
        ____________________________________________________

        <br>
        <%
            Tappa t=new Tappa();
            Indirizzo indi=new Indirizzo();
            indi.setCitta("Zuzzurellone");
            t.setIndirizzo(indi);
            pacchetto.setPartenza(t);

            List<Tappa> intermedie=pacchetto.getTappeIntermedie();
            Iterator<Tappa> it =intermedie.iterator();
            int j=0;
            Tappa tappaTmp=null;

            while(it.hasNext()){
                if(j==2){
                    tappaTmp=it.next();
                    it.remove();
                }
                else
                    it.next();
                j++;
            }
            
            intermedie.add(tappaTmp);
            pacchetto.setTappeIntermedie(intermedie);
        %>
        <a href="ServletController?operation=modificaViaggio">clicca</a>
    </body>
</html>
