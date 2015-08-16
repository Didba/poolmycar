# Introduction #

Un primo prototipo vedrà implementate le due principali funzionalità di inserimento e ricerca di un viaggio.


# Inserimento viaggio #
All'atto dell'inserimento di un viaggio verrà visualizzato un form per specificare gli indirizzi di arrivo e partenza. Sarà resa disponibile un'opzione per l'aggiunta di eventuali tappe intermedie e l'intero percorso sarà visualizzato su mappa.
Sui due indirizzi verrà calcolata una funzione di geocoding per ottenere le rispettive coordinate geografiche. Queste coordinate serviranno come parametri per richiamare una funzione javascript che utilizza le API di GoogleMaps per visualizzare un percorso. Le tappe intermedie saranno aggiunte in un secondo momento come oggetti Markers (**da vedere**).

# Viaggi e Pacchetti #
Il pacchetto raggruppa viaggi di uno stesso utente con stesse tappe di arrivo e partenza. I viaggi si distinguono per la data. Quando l'autista inserisce i dati del viaggio ne specifica le date con o senza ricorrenza.
Il pacchetto definisce il percorso di defautl con le tappe intermedie, e i viaggi vengono istanziati con la stessa lista di tappe. La lista delle tappe di un viaggio viene modificata solo attraverso l'aggiunta di nuove tappe per lo scarico e carico di passeggeri.

_Nell'inserimento viaggio è disponibile un form con due textbox per insierire i campi di partenza e arrivo e una mappa a fianco inizialmente centrata su un punto (pier della francesca). Inseriti i primi due campi e cliccato ok verrà visualizzato il percorso su mappa, comparirà un'opzione "aggiungi destinazione" che con javascript aggiunge un campo del form e comparirà un bottone salva viaggio che riporta il percorso fino ad ora creato alla servlet che lo salverà su DB_


# Inserimento e modifica tappe #
Alla creazione del pacchetto è possibile aggiungere tappe intermedie nel percorso. In seguito non è possibile modificare il pacchetto. Le modifiche alle tappe possono essere fatte solo sui singoli viaggi. Ad esempio un oggetto Richiesta suggerisce all'Autista fino a due tappe da aggiungere al viaggio: la partenza e la destinazione del passeggero (se differenti da quelle speficate nel pacchetto).
Accanto alla mappa del percorso viene visualizzata la lista degli attuali punti di passaggio del percorso. Ogni tappa potrà essere rimossa o spostata d'ordine. Per ognuna di queste operazioni la mappa visualizzata proporrà lo stato attuale del viaggio secondo le modifiche. Una volta completate le operazioni, con un tasto salva verranno rese persistenti le modifiche.
L'ordinamento delle tappe è comunque affidato all'autista.

# Ricerca viaggio #
E` possibile ricercare i viaggi presenti nel sistema inserendo la data e:
  * indirizzo di partenza e il raggio chilometrico del suo intorno
  * indirizzo di arrivo e il raggio chilometrico del suo intorno
  * indirizzo di partenza e di arrivo e i relativi raggi chilometrici degli intorni
In particolare la prima e la seconda opzione consentono ai viaggiatori di poter trovare un viaggio che parta o termini all'indirizzo specificato senza vincolare la tratta coperta dal viaggio.
I viaggi che rispettano i criteri di ricerca vengono elencati in base ai seguenti parametri selezionabili dall'utente:
  * lunghezza percorso: crescente (default) o  decrescente
  * citta` d'arrivo in ordine alfabetico
  * città di partenza in ordine alfabetico
  * data di creazione
  * data di validita`
Del viaggio selezionato tra quelli elencati verra` visualizzata la mappa con il percorso.
I dati dei viaggi vengono recupertati dal database e memorizzati in un bean stateless. Quando si seleziona un viaggio e si vuole visualizzare mappa e percorso la funzione javascript "setDirections" prendera' come parametri i valori memorizzati nel bean.