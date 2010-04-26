/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package war;

import ejb.CarrelloInserimentoViaggioLocal;
import ejb.GestoreUtentiLocal;
import ejb.GestoreViaggiBeanLocal;
import ejb.RiempiDBLocal;
import ejb.RisultatiRicercaViaggi;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utenti.Indirizzo;
import utenti.Viaggiatore;
import viaggi.Pacchetto;
import viaggi.Tappa;

/**
 *
 * @author berto
 */
public class ServletController extends HttpServlet {

    @EJB
    private RiempiDBLocal riempiDB;
    @EJB
    private CarrelloInserimentoViaggioLocal creazioneViaggiBean;
    @EJB
    private GestoreViaggiBeanLocal gestoreViaggiBean;
    @EJB
    private GestoreUtentiLocal gestoreUtentiBean;

    @Override
    public void init() throws ServletException {
        try {
            riempiDB.riempi();
        } catch (ParseException ex) {
            Logger.getLogger(ServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.init();


    }

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
                    String nome = request.getParameter("nome");
                    String cf = request.getParameter("cf");
                    String telefono = request.getParameter("telefono");
                    String via = request.getParameter("via");
                    String provincia = request.getParameter("provincia");
                    String numero_civico = request.getParameter("numero");
                    String citta = request.getParameter("citta");
                    String cap = request.getParameter("cap");
                    String stato = request.getParameter("stato");
                    String nota = request.getParameter("nota");
                    boolean fumatore = (request.getParameter("fumatore")) != null;

                    if (gestoreUtentiBean.registraUtente(login, password)) {

                        Viaggiatore viaggiatore = gestoreUtentiBean.doLogin(login, password);
                        viaggiatore.setCf(cf);
                        viaggiatore.setNome(nome);
                        viaggiatore.setTelefono(telefono);
                        viaggiatore.setNote(nota);
                        viaggiatore.setFumatore(fumatore);
                        Indirizzo ind = new Indirizzo();
                        ind.setCitta(citta);
                        ind.setProvincia(provincia);
                        ind.setVia(via);
                        ind.setNumerocivico(numero_civico);
                        ind.setStato(stato);
                        ind.setCap(cap);
                        viaggiatore.setIndirizzo(ind);


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
                if (action.equals("autoCompletamento")) {
                    String ricerca = request.getParameter("q");

                    List<String> risposta = gestoreViaggiBean.getCitta(ricerca);

                    String buffer = "";
                    for (String s : risposta) {
                        buffer = buffer + s + "<br>";
                    }
                    out.print(buffer);
                }
                ///RICERCA----------------------------------
                if (action.equals("cerca")) {
                    HttpSession session = request.getSession();
                    // salva i parametri di ricerca
                    String partenza = request.getParameter("partenza");


                    session.setAttribute("partenza", partenza);
                    String arrivo = request.getParameter("arrivo");
                    session.setAttribute("arrivo", arrivo);
                    boolean intervallo = request.getParameter("opIntervalloDate").equals("Date");
                    session.setAttribute("intervallo", intervallo);
                    Calendar data1 = new GregorianCalendar();
                    Calendar data2 = new GregorianCalendar();
                    Calendar dataOraPartenza = new GregorianCalendar();
                    if (intervallo) {
                        //Formato per la data
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Date d1 = df.parse(request.getParameter("data1").trim());
                        data1.setTime(d1);
                        session.setAttribute("data1", data1);
                        Date d2 = df.parse(request.getParameter("data2"));
                        data2.setTime(d2);
                        session.setAttribute("data2", data2);
                    } else {
                        String d = request.getParameter("dataSingola");
                        String ora = request.getParameter("ora");
                        String min = request.getParameter("min");

                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy H.mm");
                        Date dOP = df.parse(d.trim() + " " + ora.trim() + "." + min.trim());
                        dataOraPartenza.setTime(dOP);
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

                    if (viaggiatore.isAutista()) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }

                    String tipoMezzo = request.getParameter("tipoMezzo");
                    String patente = request.getParameter("patente");
                    gestoreUtentiBean.diventaAutista(viaggiatore, patente, tipoMezzo);

                    session.setAttribute("utente", viaggiatore);
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


                    if (!viaggiatore.isAutista()) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }

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

                    Viaggiatore autista = (Viaggiatore) session.getAttribute("utente");
                    if (!autista.isAutista()) {
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
                        indirizzo = request.getParameter(new String("tappa" + i));
                        System.out.println("indirizzo=" + indirizzo);
                        if (!indirizzo.equals("")) {
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
                    if (session.getAttribute("utente") == null || creazioneViaggiBean == null || creazioneViaggiBean.getTappe() == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }

                    Viaggiatore autista = (Viaggiatore) session.getAttribute("utente");
                    if (!autista.isAutista()) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }

                    int ora = new Integer(request.getParameter("ora"));
                    int minuti = new Integer(request.getParameter("min"));
                    System.out.println("ora " + ora + ":" + minuti);

                    List<Calendar> date = new LinkedList<Calendar>();
                    String stringaDate = request.getParameter("date");
                    String[] arrayDate = stringaDate.split(";");

                    String s;
                    for (int i = 0; i < arrayDate.length; i++) {
                        s = arrayDate[i];
                        String[] arrString = s.split(" ");
                        Calendar c = new GregorianCalendar(new Integer(arrString[3]), getMese(arrString[2]), new Integer(arrString[1]), ora, minuti);
                        //Calendar c = new GregorianCalendar(new Integer(arrString[3]), getMese(arrString[2]), new Integer(arrString[1]));
                        date.add(c);
                    }


                    //ordina le date nella lista
                    int i = 0;
                    CalendarSortable[] dateArray = new CalendarSortable[date.size()];
                    for (Calendar c : date) {
                        dateArray[i] = new CalendarSortable(c);
                        i++;
                    }
                    Arrays.sort(dateArray);
                    date = new LinkedList<Calendar>();
                    for (int j = 0; j < dateArray.length; j++) {
                        date.add(dateArray[j].getCalendar());
                    }
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
                    if (session.getAttribute("utente") == null || creazioneViaggiBean == null || creazioneViaggiBean.getTappe() == null || creazioneViaggiBean.getDate() == null) {
                        ServletContext sc = getServletContext();
                        RequestDispatcher rd = sc.getRequestDispatcher("/NonPermesso.jsp");
                        rd.forward(request, response);
                    }


                    Viaggiatore autista = (Viaggiatore) session.getAttribute("utente");
                    if (!autista.isAutista()) {
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

                    Pacchetto p = gestoreViaggiBean.inserisciPacchetto(creazioneViaggiBean.getTappe(), creazioneViaggiBean.getDate(), autista, creazioneViaggiBean.getNota(), creazioneViaggiBean.getRichiestaContributi(), (String) session.getAttribute("distanza"));

                    System.out.println("partenza: " + p.getPartenza().getIndirizzo().getCitta() + "    arrivo: " + p.getArrivo().getIndirizzo().getCitta());

                    session.setAttribute("pacchetto", p);

                    ServletContext sc = getServletContext();
                    RequestDispatcher rd = sc.getRequestDispatcher("/PaginaViaggio.jsp");
                    rd.forward(request, response);
                }
                if (action.equals("modificaViaggio")) {
                    Pacchetto p = gestoreViaggiBean.aggiornaPacchetto((Pacchetto) session.getAttribute("pacchetto"));
                    session.setAttribute("pacchetto", p);
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
    private class CalendarSortable implements Comparable {

        private Calendar calendar;
        private long currenTime;

        public CalendarSortable(Calendar c) {
            calendar = c;
            currenTime = c.getTimeInMillis();
        }

        public int compareTo(Object o) {
            CalendarSortable c = (CalendarSortable) o;
            if (currenTime < c.getTime()) {
                return -1;
            } else if (currenTime == c.getTime()) {
                return 0;
            } else {
                return 1;
            }
        }

        public long getTime() {
            return currenTime;
        }

        public Calendar getCalendar() {
            return calendar;
        }
    }
}
