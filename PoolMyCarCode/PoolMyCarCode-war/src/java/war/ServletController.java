/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package war;

import ejb.GestoreViaggiBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utenti.Autista;
import utenti.Viaggiatore;
import viaggi.Tappa;

/**
 *
 * @author berto
 */
public class ServletController extends HttpServlet {
    @EJB
    private GestoreViaggiBeanLocal gestoreViaggiBeanBean;
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String action = request.getParameter("action");

            if(action.equals("login")){
                
            }
            
            /////////////////////////////////solo cose da loggato////////////////////
            HttpSession session = request.getSession();
            
            if(action.equals("inserisciViaggio")){
                if(session==null){
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("./SessionNull.jsp");
                    rd.forward(request, response);
                }

                Viaggiatore viaggiatore=null;
                //TO-DO: carica oggetto Viaggiatore chiamando GestoreUtentiBean

                Autista autista=null;
                try{
                    autista=(Autista) viaggiatore;
                }
                catch(ClassCastException e){
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("./NotPermitted.jsp");
                    rd.forward(request, response);
                }
                session.setAttribute("autista", autista);

                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("./InserisciLuoghiViaggio.jsp");
                rd.forward(request, response);
            }
            
            if(action.equals("inserisciTappe")){
                if(session==null){
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("./SessionNull.jsp");
                    rd.forward(request, response);
                }
                if(session.getAttribute("autista")==null){
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("./NotPermitted.jsp");
                    rd.forward(request, response);
                }

                List<Tappa> tappe = new LinkedList<Tappa>();
                String indirizzo=null;
                //poniamo che le tappe si chiamino tappa0, tappa1...
                int i=0;
                while(request.getParameter(new String("tappa"+i))!=null){
                    indirizzo=request.getParameter(new String("tappa"+i));
                    Tappa tappa=gestoreViaggiBeanBean.geocoding(indirizzo);
                    if(tappa==null){
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("./InserisciViaggio?errore=tappaerrata.jsp");
                        rd.forward(request, response);
                    }
                    else{
                        tappe.add(tappa);
                    }

                }

                session.setAttribute("tappe", tappe);

                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("./InserisciDateViaggio.jsp");
                rd.forward(request, response);

            }
            if(action.equals("inserisciDate")){
                if(session==null){
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("./SessionNull.jsp");
                    rd.forward(request, response);
                }
                if(session.getAttribute("autista")==null || session.getAttribute("tappe")==null){
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("./NotPermitted.jsp");
                    rd.forward(request, response);
                }

                List<Date> date = new LinkedList<Date>();
                //TO-DO: leggi date da calendario e trasformale in oggetti Date

                session.setAttribute("date", date);

                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("./ConfermaViaggio.jsp");
                rd.forward(request, response);
            }
            if(action.equals("viaggioConfermato")){
                if(session==null){
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("./SessionNull.jsp");
                    rd.forward(request, response);
                }
                if(session.getAttribute("autista")==null || session.getAttribute("tappe")==null || session.getAttribute("date")==null){
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("./NotPermitted.jsp");
                    rd.forward(request, response);
                }

                String nota = request.getParameter("nota");
                boolean richiestaContributo=false;  //TO-DO: controllare è null se non checckato
                if(request.getParameter("richiestaContributo")!=null)
                    richiestaContributo=true;

                gestoreViaggiBeanBean.inserisciPacchetto((List<Tappa>) session.getAttribute("tappe"), (List<Date>) session.getAttribute("date"), (Autista) session.getAttribute("autista"), nota, richiestaContributo, null);  //bacheca va istanziato

                //TO-DO: caricare indice del viaggio per forward "paginaviaggio.jsp"
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher("./PaginaViaggio.jsp");
                rd.forward(request, response);
            }

        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
