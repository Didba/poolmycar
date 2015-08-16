# Introduction #

Add your content here.


# Details #

**Gestione utenti**

La gestione degli utenti viene fatta attraverso un database interno. Non si è reso necessario l'utilizzo di servizi di autenticazione esterni (come OpenID) in quanto l'unico web service offerto è la ricerca (effettuabile senza autenticazione).

_Viaggiatore_ Tutti gli utenti registrati appartengono alla categoria _Viaggiatore_, ovvero hanno la possibilità di usufruire di uno dei viaggi messi a disposizione dagli autisti. I dati richiesti sono Nome, Cognome, Sesso (M/F), data di nascita, email (indispensabile per confermare la registrazione), un codice di verifica per evitare registrazioni automatiche (**spike**) e alcuni dati facoltativi specificati più avanti.

_Autista_ Successivamente all'iscrizione, un utente ha la possibilità di inserire i dati della propria patente di guida e assumere così anche il ruolo di _Autista_. Questo comporta la possibilità di inserire dei nuovi viaggi all'interno del sistema. Durante quest'assunzione di ruolo è obbligatorio anche fornire i dati di almeno una tipologia di mezzo che l'autista intende utilizzare per i propri viaggi.

_Dati profilo_ All'interno del profilo è possibile inserire e modificare alcune delle proprie informazioni personali. E' buona norma compilare questi campi per aiutare gli utenti nella scelta dei compagni di viaggio. Nel caso l'utente sia anche _Autista_ dispone di un'insieme di campi più ampio rispetto a un semplice _Viaggiatore_. Ad un utente _Viaggiatore_ verrà chiesto:
  * numeri di telefono (fissi e/o mobili)
  * residenza (via, città, stato)
  * se fumatore o non fumatore
  * se disposto o meno a viaggiare con animali
  * ...
ad un _Autista_ verrà ulteriormente chiesto:
  * ...


_Feedback_ Ad ogni utente registrato viene associato un feedback in qualità di viaggiatore. Se l'utente è anche _Autista_ avrà anche un secondo feedback in qualità di conducente. I feedback sono il sunto dei commenti rilasciati da altri utenti che hanno partecipato ad uno stesso viaggio (uno in qualità di _Autista_, uno in qualità di _Viaggiatore_).

_Commento_ A viaggio concluso il sistema invia alla Inbox di ogni utente che ha partecipato al viaggio una _Richiesta di Commento_: attraverso i link di questo messaggio è possibile rilasciare un commento (facoltativo) per l'_Autista_ (se l'utente è un _Viaggiatore_) o per i _Viaggiatori_ (se viceversa è un _Autista_) entro due giorni dalla fine del pacchetto.
Il sistema controlla che sia possibile per un utente rilasciare un commento su di un altro utente solo una volta per ogni _Pacchetto Viaggi_ a cui hanno partecipato entrambi. Nel caso si partecipi ad un _Pacchetto Viaggi_ che comprende più di un viaggio, è possibile rilasciare un commento subito dopo il termine del primo viaggio del pacchetto; ma questo commento potrà essere modificato nel tempo dall'utente stesso.
I commenti sono suddivisi per aspetti a cui assegnare una valutazione da 1 a 5, oltre ad un opzionale commento testuale. Per un _Viaggiatore_ gli aspetti da valutare sono:
  * Puntualità
  * Flessibilità
  * Cordialità e Correttezza.
In più un _Autista_ è valutabile anche secondo un parametro "Guida e Comfort".
Attraverso una media di questi parametri si genenera in automatico un Feedback _Viaggiatore_ e un eventuale feedback _Autista_ per ogni utente.

_Funzionalità offerte agli ospiti del sito_ Per chi accede al sito senza volersi registrare è resa disponibile la ricerca dei viaggi attualmente offerti da PoolMyCar, ma senza alcuna possibilità di visualizzazione profili e feedback.


**Gestione viaggio**

_Tipi di Viaggio_ Quando un _Autista_ mette a disposizione un nuovo viaggio, innanzitutto deve creare un nuovo _Pacchetto Viaggi_. Questo è un insieme di uno o più viaggi messi a disposizione dall'autista. Questa modalità viene utilizzata per consentire all'_Autista_ di mettere a disposizione in un unico pacchetto viaggi che si ripetono con una cadenza periodica. E' in particolar modo utile per viaggiatori pendolari che desiderano usufruire del trasporto con una certa regolarità.

_Contributo spese_ La gestione dei pagamenti non rientra nelle funzionalità offerte dal sistema. Alla creazione di un nuovo _Pacchetto Viaggi_ l'_Autista_ può segnalare se richiede o meno un contributo per le spese del viaggio; nel caso saranno gli utenti stessi a concordare l'ammontare del contributo e le modalità di pagamento attraverso il sistema di messaggistica disponibile sul sistema o nella maniera a loro più congeniale.

_Bacheca del Viaggio_ Ad ogni _Pacchetto Viaggi_ è associata una _Bacheca_ in cui gli utenti registrati possono postare dei messaggi relativi al viaggio (o i viaggi) in questione. Tale _Bacheca_ è implementata per favorire una comunicazione efficace riguardo a tutti quegli aspetti relativi al _Viaggio_ per i _Viaggiatori_ interessati a parteciparvi. Il sistema mantiene in memoria lo storico dei messaggi inseriti in ogni _Bacheca_.

_Inserire un nuovo Pacchetto Viaggi_ Ovviamente solo un _Autista_ può inserire un nuovo _Pacchetto Viaggi_. I dati richiesti alla creazione sono:
  * Indirizzo di Partenza (nella forma: via, città, provincia, nazione)
  * Indirizzo di Arrivo (nella forma: via, città, provincia, nazione)
  * Mezzo (scelto tra quelli registrati sul sistema dall'_Autista_)
  * Eventuale Contributo Spese
  * Data/e o Periodicità (**da rivedere**)
  * Note

_Ricerca di un Viaggio_ Un qualunque visitatore può utilizzare il sistema di ricerca viaggio presente sul sito. Si può ricercare un viaggio inserendo l'indirizzo di partenza e quello di arrivo (in assenza di una via specifica si assume il centro città) oppure visionando una mappa presente sul sito che riporta graficamente i vari viaggi offerti. Se si utlizza la prima modalità si può specificare un intervallo kilometrico dentro il quale vengano rilevate tutti i viaggi con partenza (o, in maniera speculare, con arrivo) al suo interno. In ogni caso è possibile restringere il campo della ricerca ad un determinato intervallo temporale.

_Richiesta di Partecipazione_ Se il _Viaggiatore_ ha trovato un viaggio a cui intende partecipare, può inviare in autamatico una _Richiesta di Partecipazione_ all'_Autista_. Questa è uno speciale messaggio che contiene due parametri obbligatori: un punto di ritrovo (proposto dal _Viaggiatore_) e una destinazione alla quale il _Viaggiatore_ intende arrivare. Inoltre è disponibile una sezione dove è possibile inserire una nota.
Un viaggiatore compilando questi campi crea una richiesta che viene inviata a chi ha proposto il viaggio. L'autista può:
> - accettare la richiesta così com'è (le tappe verranno aggiunte in automatico)
> - modificare la richiesta e rimandarla al mittente con un nuovo commento
> - rifiutare la richiesta con un'eventuale motivazione.
Il viaggiatore che ha creato la richiesta e ha ricevuto una proposta di modifica può accettarla, proporre una nuova modifica o annullare la richiesta.
La richiesta viene fatta sul pacchetto viaggio, selezionando le date in cui si vuole partecipare (nel caso non siano tutte)

_Modifiche, Rinunce e Cancellazioni_ Entro 3 giorni dalla partenza è ancora possibile modificare i dati del viaggio (come indirizzo di partenza, ora, data, etc..). La modifica verrà notificata ai partecipanti e richiedenti del viaggio tramite un messaggio sulla Inbox; per i passeggeri già accettati, in particolare, viene considerata ancora valida la loro partecipazione e viene data la possibilità di rifiutare il nuovo viaggio modificato.
Se il viaggio viene cancellato, sempre entro 3 giorni dalla partenza, il fatto viene notificato ai passeggeri.
I passeggeri, entro lo stesso limite di tempo, possono rinunciare al viaggio.

_Gestione Tappe e Percorsi_
L'inserimento di tappe intermedie nel percorso può essere effettuato solo dall'organizzatore del viaggio su indicazione dei potenziali partecipanti attraverso la bacheca disponibile.
L'autista avrà la possibilità di modificare l'ordine delle tappe.


**Web services utilizzati**
  * Google Maps
  * Google GeoCoder


**Web services offerti**
  * La ricerca dei viaggi


**Future Work**
  * Aiuto dinamico nella compilazione campi indirizzi
  * Notifiche via SMS
  * Possibilità di rilasciare un feedback ai compagni di viaggio. (e relativi problemi: pacchetti viaggio con persone diverse, persone che partecipano allo stesso viaggio ma in una tratta differente...)
  * Ricerca viaggio per località diverse da tappe già definite
  * Definizione di un evento. Possibilità di tag dei viaggi riferendoli ad eventi comuni