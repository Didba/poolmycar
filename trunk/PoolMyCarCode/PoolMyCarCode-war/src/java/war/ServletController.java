/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import ejb.CarrelloInserimentoViaggioLocal;
import ejb.GestoreUtentiLocal;
import ejb.GestoreViaggiBeanLocal;
import ejb.RisultatiRicercaViaggi;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import viaggi.Pacchetto;
import viaggi.Tappa;

/**
 *
 * @author berto
 */
public class ServletController extends HttpServlet {
    @EJB
    private CarrelloInserimentoViaggioLocal creazioneViaggiBean;
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
                    //boolean autista = (request.getParameter("autista")) != null;

                    if (gestoreUtentiBean.registraUtente(login, password)) {

                        Viaggiatore viaggiatore = gestoreUtentiBean.doLogin(login, password);
                        HttpSession session = request.getSession();
                        session.setAttribute("utente", viaggiatore);

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

                 if (action.equals("diventaAutista")) {
                    if (session == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/SessionNull.jsp");
                        rd.forward(request, response);
                    }

                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/RegistrazioneAutista.jsp");
                    rd.forward(request, response);
                }

                if (action.equals("registrazioneAutista")) {
                    if (session == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/SessionNull.jsp");
                        rd.forward(request, response);
                    }
                    Viaggiatore viaggiatore = (Viaggiatore) session.getAttribute("utente");
                    //Se è già un autista lo rimando a notpermitted
                    try {
                        Autista autista= (Autista) viaggiatore;
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    } catch (ClassCastException e) {} //eccezione catturata:va tutto bene
                    String tipoMezzo=request.getParameter("tipoMezzo");
                    String patente=request.getParameter("patente");
                    gestoreUtentiBean.diventaAutista(viaggiatore, patente, tipoMezzo);

                    session.setAttribute("utente",viaggiatore);
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/Profilo.jsp");
                    rd.forward(request, response);
                }

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
                    try{
                        Autista autista=(Autista) session.getAttribute("utente");
                    }
                    catch(ClassCastException e){
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }


                    session.setAttribute("distanza", request.getParameter("distanza"));
                    List<Tappa> tappe = new LinkedList<Tappa>();
                    String indirizzo = null;
                    //poniamo che le tappe si chiamino tappa0, tappa1...

                    int i = 0;
                    while (request.getParameter(new String("tappa" + i)) != null) {
                        indirizzo=request.getParameter(new String("tappa" + i));
                        System.out.println("indirizzo="+indirizzo);
                        if(!indirizzo.equals("")){
                            Tappa tappa = gestoreViaggiBean.geocoding(indirizzo);
                            if (tappa == null) {  //geocoding fallito
                                ServletContext sc = getServletContext();
                                RequestDispatcher rd = sc.getRequestDispatcher("/InserisciViaggio.jsp");
                                rd.forward(request, response);
                            } else {
                                tappe.add(tappa);
                            }
                       }
                       i++;

                    }

                    
                    //-------istanziazione del session bean------------
                    /*CarrelloInserimentoViaggioLocal viaggio = new CarrelloInserimentoViaggioBean();
                    viaggio.setTappe(tappe);*/
                    creazioneViaggiBean.setTappe(tappe);
                    session.setAttribute("creazioneViaggiBean", creazioneViaggiBean);

                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/InserisciDateViaggio.jsp");
                    rd.forward(request, response);

                }
                if (action.equals("inserisciDate")) {
                    creazioneViaggiBean = (CarrelloInserimentoViaggioLocal) session.getAttribute("creazioneViaggiBean");
                    if (session == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/SessionNull.jsp");
                        rd.forward(request, response);
                    }
                    if (session.getAttribute("utente") == null || creazioneViaggiBean==null || creazioneViaggiBean.getTappe() == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }
                    try{
                        Autista autista=(Autista) session.getAttribute("utente");
                    }
                    catch(ClassCastException e){
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
                    }

                    
                    //ordina le date nella lista
                    int i=0;
                    CalendarSortable[] dateArray=new CalendarSortable[date.size()];
                    for(Calendar c: date){
                        dateArray[i]=new CalendarSortable(c);
                        i++;
                    }
                    Arrays.sort(dateArray);
                    date = new LinkedList<Calendar>();
                    for(int j=0; j<dateArray.length; j++)
                        date.add(dateArray[j].getCalendar());
                    ////////////////////////////////

                    creazioneViaggiBean.setDate(date);

                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/ConfermaViaggio.jsp");
                    rd.forward(request, response);
                }
                if (action.equals("viaggioConfermato")) {
                    creazioneViaggiBean = (CarrelloInserimentoViaggioLocal) session.getAttribute("creazioneViaggiBean");
                    if (session == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/SessionNull.jsp");
                        rd.forward(request, response);
                    }
                    if (session.getAttribute("utente") == null || creazioneViaggiBean==null || creazioneViaggiBean.getTappe() == null || creazioneViaggiBean.getDate() == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }
                    Autista autista=null;
                    try{
                        autista=(Autista) session.getAttribute("utente");
                    }
                    catch(ClassCastException e){
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }

                    String nota = request.getParameter("nota");
                    boolean richiestaContributo = false;
                    if (request.getParameter("richiestaContributo") != null) {
                        richiestaContributo = true;
                    }

                    creazioneViaggiBean.setNota(nota);
                    creazioneViaggiBean.setRichiestaContributi(richiestaContributo);

                    Pacchetto p=gestoreViaggiBean.inserisciPacchetto(creazioneViaggiBean.getTappe(), creazioneViaggiBean.getDate(), autista, creazioneViaggiBean.getNota(), creazioneViaggiBean.getRichiestaContributi(),(String)session.getAttribute("distanza"));
                    session.setAttribute("pacchetto",p);

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
                    Date dataOraPartenza = null;
                    if(intervallo)
                    {
                    //Formato per la data
                    SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
                    data1 = df.parse(request.getParameter("data1").trim());
                    session.setAttribute("data1", data1);
                  
                    data2 = df.parse(request.getParameter("data2"));
                    session.setAttribute("data2", data2);
                    }
                    else
                    {
                    String d = request.getParameter("dataPartenza");
                    String ora = request.getParameter("ora");

                    SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy H.mm");
                    dataOraPartenza = df.parse(d.trim() +" "+ ora.trim());
                    session.setAttribute("dataOraPartenza", dataOraPartenza);
                    

                    }

                    // richiede la creazione del bean con i risultati e lo salva in sessione
                    RisultatiRicercaViaggi ris = gestoreViaggiBean.ricercaViaggi(partenza, arrivo, intervallo, data1, data2, dataOraPartenza);
                    session.setAttribute("risulatatoRicerca", ris);
                    //forward alla pagina di visualizzazione
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/RisultatiRicerca.jsp");
                    rd.forward(request, response);
                     
                }
                //-------------------------------
                if (action.equals("modificaViaggio")) {
                    Pacchetto p=gestoreViaggiBean.aggiornaPacchetto((Pacchetto) session.getAttribute("pacchetto"));
                    session.setAttribute("pacchetto",p);
                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/PaginaViaggio.jsp");
                    rd.forward(request, response);
                }

                //--------------------------
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

    //classe privata per il sorting delle date
    private class CalendarSortable implements Comparable{
        private Calendar calendar;
        private long currenTime;

        public CalendarSortable(Calendar c){
            calendar=c;
            currenTime=c.getTimeInMillis();
        }

        public int compareTo(Object o) {
            CalendarSortable c=(CalendarSortable) o;
            if(currenTime<c.getTime())
                return -1;
            else if(currenTime==c.getTime())
                return 0;
            else
                return 1;
        }

        public long getTime() {
            return currenTime;
        }

        public Calendar getCalendar(){
            return calendar;
        }



    }
}
