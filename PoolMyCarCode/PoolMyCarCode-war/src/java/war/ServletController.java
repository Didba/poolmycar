/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import ejb.GestoreUtentiLocal;
import ejb.GestoreViaggiBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private GestoreViaggiBeanLocal gestoreViaggiBean;
    @EJB
    private GestoreUtentiLocal gestoreUtentiBean;

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

            /*gestoreUtentiBean.registraUtente("aaa", "bbb", true);
            Autista a=(Autista) gestoreUtentiBean.doLogin("aaa","bbb");

            Tappa t=gestoreViaggiBeanBean.geocoding("torino");
            Tappa t2=gestoreViaggiBeanBean.geocoding("milano");
            List<Tappa> l =new LinkedList<Tappa>();
            l.add(t);
            l.add(t2);
            Calendar d=new GregorianCalendar();
            List<Calendar> ld = new LinkedList<Calendar>();
            ld.add(d);

            gestoreViaggiBeanBean.inserisciPacchetto(l, ld, a, null, true);*/


            String action = request.getParameter("operation");
            if (action == null) {
                out.println("<html><body>action=null</body></html>");
            } else {
                if (action.equals("login")) {
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    HttpSession session = request.getSession();

                    Viaggiatore viaggiatore = gestoreUtentiBean.doLogin(login, password);

                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = null;

                    if (viaggiatore != null) {
                        session.setAttribute("utente", viaggiatore);
                        rd = sc.getRequestDispatcher("/Profilo.jsp");
                    } else {
                        rd = sc.getRequestDispatcher("/index.jsp"); //<-- controllare
                    }
                    rd.forward(request, response);
                }
                if (action.equals("registrati")) {
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/Registrazione.jsp");
                    rd.forward(request, response);
                }
                if (action.equals("registrazione")) {

                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    boolean autista = (request.getParameter("autista")) != null;

                    if (gestoreUtentiBean.registraUtente(login, password, autista)) {

                        Viaggiatore viaggiatore = gestoreUtentiBean.doLogin(login, password);
                        HttpSession session = request.getSession();
                        session.setAttribute("utente", viaggiatore);


                        try {

                            Autista p = (Autista) viaggiatore;
                        } catch (ClassCastException e) {
                            ServletContext sc = getServletContext();
                            RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                            rd.forward(request, response);
                        }

                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/Profilo.jsp");
                        rd.forward(request, response);
                    } else {
                        // login esistente, ripresentare la pagina chiedendo un nuovo login
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/SessionNull.jsp");
                        rd.forward(request, response);
                    }
                }



                /////////////////////////////////solo cose da loggato////////////////////
                HttpSession session = request.getSession();

                if (action.equals("inserisciViaggio")) {
                    if (session == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/SessionNull.jsp");
                        rd.forward(request, response);
                    }

                    Viaggiatore viaggiatore = (Viaggiatore) session.getAttribute("utente");

                    Autista autista = null;
                    try {
                        autista = (Autista) viaggiatore;
                    } catch (ClassCastException e) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }
                    session.setAttribute("utente", autista);

                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/InserisciLuoghiViaggio.jsp");
                    rd.forward(request, response);
                }

                if (action.equals("inserisciTappe")) {
                    if (session == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/SessionNull.jsp");
                        rd.forward(request, response);
                    }
                    if (session.getAttribute("utente") == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }

                    List<Tappa> tappe = new LinkedList<Tappa>();
                    String indirizzo = null;
                    //poniamo che le tappe si chiamino tappa0, tappa1...

                    int i = 0;
                    while (request.getParameter(new String("tappa" + i)) != null) {
                        indirizzo = request.getParameter(new String("tappa" + i));
                        Tappa tappa = gestoreViaggiBean.geocoding(indirizzo);
                        if (tappa == null) {  //geocoding fallito
                            ServletContext sc = getServletContext();
                            RequestDispatcher rd = sc.getRequestDispatcher("/InserisciViaggio.jsp");
                            //TO-DO: segnalare geocoding fallito
                            rd.forward(request, response);
                        } else {
                            tappe.add(tappa);
                            i++;
                        }

                    }

                    session.setAttribute("tappe", tappe);

                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/InserisciDateViaggio.jsp");
                    rd.forward(request, response);

                }
                if (action.equals("inserisciDate")) {
                    if (session == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/SessionNull.jsp");
                        rd.forward(request, response);
                    }
                    if (session.getAttribute("utente") == null || session.getAttribute("tappe") == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }

                    List<Calendar> date = new LinkedList<Calendar>();
                    String stringaDate = request.getParameter("date");
                    String[] arrayDate = stringaDate.split(";");

                    String s;
                    for (int i = 0; i < arrayDate.length; i++) {
                        s = arrayDate[i];
                        String[] arrString = s.split(" ");
                        Calendar c = new GregorianCalendar(new Integer(arrString[3]), getMese(arrString[2]), new Integer(arrString[1]));
                        date.add(c);
                        //TO-DO: leggi date da calendario e trasformale in oggetti Date

                    }

                    session.setAttribute("date", date);

                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/ConfermaViaggio.jsp");
                    rd.forward(request, response);
                }
                if (action.equals("viaggioConfermato")) {
                    if (session == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/SessionNull.jsp");
                        rd.forward(request, response);
                    }
                    if (session.getAttribute("utente") == null || session.getAttribute("tappe") == null || session.getAttribute("date") == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }

                    String nota = request.getParameter("nota");
                    boolean richiestaContributo = false;
                    if (request.getParameter("richiestaContributo") != null) {
                        richiestaContributo = true;
                    }

                    gestoreViaggiBean.inserisciPacchetto((List<Tappa>) session.getAttribute("tappe"), (List<Calendar>) session.getAttribute("date"), (Autista) session.getAttribute("utente"), nota, richiestaContributo);

                    //TO-DO: caricare indice del viaggio per forward "paginaviaggio.jsp"
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/PaginaViaggio.jsp");
                    rd.forward(request, response);
                } ///RICERCA----------------------------------
                if (action.equals("cerca")) {
                    
                    // salva i parametri di ricerca
                    String partenza = request.getParameter("partenza");
                    
                    
                    session.setAttribute("partenza", partenza);
                    String arrivo =  request.getParameter("arrivo");
                    session.setAttribute("arrivo", arrivo);
                    boolean intervallo = request.getParameter("opIntervalloDate").equals("Date");
                    session.setAttribute("intervallo", intervallo);
                    Date data1=null;
                    Date data2=null;
                    if(intervallo)
                    {
                    //Formato per la data
                    SimpleDateFormat df=new SimpleDateFormat("dd/MM/YYYY");
                    df.setLenient(true);

                    data1 = df.parse(request.getParameter("data1"));
                    session.setAttribute("data1", data1);

                    System.out.println("------------------------ DATA:  " + data1);
                    data2 = DateFormat.getInstance().parse(request.getParameter("data2"));
                    session.setAttribute("data2", data2);
                    }
                    else
                    {
                    String dataPartenza = request.getParameter("dataPartenza");
                    session.setAttribute("dataPartenza", dataPartenza);
                    String ora = request.getParameter("ora");
                    session.setAttribute("ora", ora);

                    }

                    // richiede la creazione del bean con i risultati e lo salva in sessione
                    // RisultatiRicercaViaggi ris = gestoreViaggiBean.ricercaViaggi(action, action, intervallo, null, null, null)
                    //forward alla pagina di visualizzazione
                      out.println("<html><body> ciaooooooooooooo</body></html>");
                     
                }

            }

        } catch (Exception e) {
            out.println("<html><body>");
            e.printStackTrace(out);
            out.println("</body></html>");

        } finally {
            out.close();

        }
    }

    private int getMese(String s) {
        if (s.equals("January")) {
            return Calendar.JANUARY;
        }
        if (s.equals("February")) {
            return Calendar.FEBRUARY;
        }
        if (s.equals("March")) {
            return Calendar.MARCH;
        }
        if (s.equals("April")) {
            return Calendar.APRIL;
        }
        if (s.equals("May")) {
            return Calendar.MAY;
        }
        if (s.equals("June")) {
            return Calendar.JUNE;
        }
        if (s.equals("July")) {
            return Calendar.JULY;
        }
        if (s.equals("August")) {
            return Calendar.AUGUST;
        }
        if (s.equals("September")) {
            return Calendar.SEPTEMBER;
        }
        if (s.equals("October")) {
            return Calendar.OCTOBER;
        }
        if (s.equals("November")) {
            return Calendar.NOVEMBER;
        }
        if (s.equals("December")) {
            return Calendar.DECEMBER;
        }
        return 0;
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
