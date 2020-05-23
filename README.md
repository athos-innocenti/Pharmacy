# Pharmacy
Simple Java pharmacy manager

L'obiettivo di questo progetto è realizzare un software in Java per la gestione della vendita di medicine in una farmacia.

Il cliente, interagendo con la farmacia, comunica il nome della medicina che vuole acquistare e se desidera la versione originale o generica. A questo punto viene controllata la disponibilità di tale medicina nel magazzino: se la medicina richiesta è disponibile si prosegue con il pagamento altrimenti viene creata una prenotazione a nome del cliente contenete i suoi dati e le informazioni relative alla medicina prenotata. Nella prima fase del pagamento viene chiesto al cliente se desidera pagare la medicina a prezzo pieno o con una riduzione: se desidera acquistare a prezzo pieno al costo della medicina viene aggiunta l'iva e un tasso di guadagno per la farmacia, se invece desidera acquistare a prezzo ridotto viene richiesto l'ISEE del cliente, si calcola la fascia di redditto di appartenenza e in base ad essa viene applicato uno sconto. Una volta acquistata o prenotata una medicina viene chiesto al cliente se desidera acquistarne un'altra. Conclusa l'interazione viene mostrato lo scontrino contenente un riassunto di tutte le medicine acquistate con il costo totale di esse e si passa al cliente successivo, se presente, altrimenti la farmacia chiude e viene fatto un resoconto sul guadagno totale.

Qualora la medicina richiesta non fosse disponibile, una funzione ne simula la richiesta alla casa farmaceutica e la conseguente acquisizione tramite la creazione di nuovi oggetti di tipo medicina a partire dalla lista di tutte le medicine richieste.

Per rappresentare una situazione più realistica possono essere create medicine richieste solo da clienti passati e non dal cliente attuale.

La medicina che il cliente vuole acquistare, la sua tipologia e la medicina richiesta che verrà creata sono tutte scelte casualmente.

Nella fase iniziale di progettazione sono stati individuati i vari casi d'uso che sono stati riportati all'interno dello use case diagram.

Insieme ai casi d'uso è stata definita una logica di dominio in prospettiva di implementazione che è stata rappresentata attraverso un diagramma UML.

Per la realizzazione del progetto sono stati utilizzati tre design patterns: il design pattern creazionale Singleton applicato alla classe Pharmacy rappresentante la farmacia; il design pattern comportamentale Observer tra la classe Pharmacy che riveste il ruolo di observer e la classe Warehouse, rappresentante il magazzino della farmacia, che riveste invece il ruolo di subject e il design pattern strutturale Proxy per il pagamento della medicina che il cliente desidera acquistare a seconda che quest'ultimo desideri pagare a prezzo pieno o ridotto.

Si è venuta quindi a creare una rappresentazione dell'interazione tra cliente e farmacia dove i vari attributi relativi al cliente, alla farmacia, ai farmacisti e la scelta del metodo di pagamento e la volontà di acquistare altre medicine sono inseriti da linea di comando mediante l'impiego di oggetti Scanner.
