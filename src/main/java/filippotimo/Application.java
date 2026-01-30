package filippotimo;

import filippotimo.dao.*;
import filippotimo.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("bw1backendpu");
        }
        return entityManagerFactory;
    }

    public static void main(String[] args) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        Scanner scanner = new Scanner(System.in);


        //    *************************************** CREAZIONE OGGETTI DAO ***************************************

        MezzoPubblicoDAO md = new MezzoPubblicoDAO(em);
        ProdottoDAO pd = new ProdottoDAO(em);
        RivenditoriDAO rd = new RivenditoriDAO();
        TrattaDAO td = new TrattaDAO(em);
        UtentiDAO ud = new UtentiDAO(em);


        //    *************************************** CREAZIONE RIVENDITORI ***************************************

        Rivenditore primoRivenditore = new RivenditoreAutorizzato("Filippo");
        Rivenditore secondoRivenditore = new RivenditoreAutomatico("BOT", true);

        // rd.save(primoRivenditore);
        // rd.save(secondoRivenditore);


        //    *************************************** CREAZIONE MEZZO ***************************************

        MezzoPubblico primoMezzo = new MezzoPubblico("Iveco", tipoMezzo.AUTOBUS);
        MezzoPubblico secondoMezzo = new MezzoPubblico("BOH", tipoMezzo.TRAM);

        // md.saveMezzoPubblico(primoMezzo);
        // md.saveMezzoPubblico(secondoMezzo);


        //    *************************************** CREAZIONE UTENTI E TESSERE ***************************************

        Utenti primoUtente = new Utenti("Marcello", "Lippi");
        Utenti secondoUtente = new Utenti("Roberto", "Mancini");

        // ud.save(primoUtente);
        // ud.save(secondoUtente);


        // Per le Tessere abbiamo un metodo che esegue la creazione e il salvataggio nel DB in un unico passaggio
        Utenti primoUtenteFromDB = ud.findUtenteById(1);
        Utenti secondoUtenteFromDB = ud.findUtenteById(2);

        ud.createAndSaveTessera(primoUtenteFromDB, LocalDate.of(2025, 12, 6));
        ud.createAndSaveTessera(secondoUtenteFromDB, LocalDate.of(2026, 1, 10));


        //    *************************************** CREAZIONE BIGLIETTO ***************************************

        // RIPRENDO I RIVENDITORI DAL DB
        Rivenditore primoRivenditoreDB = rd.findById(1);
        Rivenditore secondoRivenditoreDB = rd.findById(2);

        // RIPRENDO I MEZZI DAL DB
        MezzoPubblico primoMezzoDB = md.findMezzoById(1);
        MezzoPubblico secondoMezzoDB = md.findMezzoById(2);

        // CREO EFFETTIVAMENTE I BIGLIETTI
        Biglietto biglietto1 = new Biglietto(LocalDate.of(2026, 1, 12), primoRivenditoreDB, tipoMezzo.AUTOBUS);
        Biglietto biglietto2 = new Biglietto(LocalDate.of(2026, 1, 29), secondoRivenditoreDB, tipoMezzo.TRAM);

        // pd.save(biglietto1);
        // pd.save(biglietto2);


        //    *************************************** CREAZIONE ABBONAMENTO ***************************************

        Tessera primaTesseraFromDB = ud.findTesseraByNumero(1);

        // Per l'abbonamento abbiamo un metodo che esegue la creazione e il salvataggio nel DB in un unico passaggio
        pd.createAndSaveAbbonamento(LocalDate.now(), primoRivenditoreDB, durataAbbonamento.MENSILE, primaTesseraFromDB);


        //    *************************************** CREAZIONE TRATTA ***************************************

        // Per le tratte abbiamo un metodo che esegue la creazione e il salvataggio nel DB in un unico passaggio
        td.createAndSaveTratta("Linea A", "Pomezia", "Tor San Lorenzo", 25);
        td.createAndSaveTratta("Linea B", "Torvaianica", "Tor San Lorenzo", 25);


        //    *************************************** CREAZIONE PERCORRENZA ***************************************

        Tratta tratta1DB = td.findTrattaById(1);
        Tratta tratta2DB = td.findTrattaById(2);

        td.createAndSavePercorrenza(18, tratta1DB, primoMezzoDB);
        td.createAndSavePercorrenza(18, tratta2DB, secondoMezzoDB);


        //    *************************************** CREAZIONE IN MANUTENZIONE ***************************************

        // Per la manutenzione abbiamo un metodo che esegue la creazione e il salvataggio nel DB in un unico passaggio
        // md.mandaInManutenzione(1);
        // md.mandaInManutenzione(2);


        //    *************************************** METODO PER SETTARE LA DATA DI FINE MANUTENZIONE ***************************************

        // md.setDataFineManutenzione(1,LocalDate.of(2025,1,30));
        // md.setDataFineManutenzione(2,LocalDate.of(2025,1,31));


        /* ----------------------------------------------------------------------------------------------------------------------
        ------------------------------------------------ METODI AVANZATI UTENTE ------------------------------------------------
        ---------------------------------------------------------------------------------------------------------------------- */

        //    *************************************** METODO PER VIDIMARE UN BIGLIETTO ***************************************

        pd.validateATicketBySetter(1, 1);


        /* ----------------------------------------------------------------------------------------------------------------------
        -------------------------------------------- METODI AVANZATI AMMINISTRATORE --------------------------------------------
        ---------------------------------------------------------------------------------------------------------------------- */


        //    ******************************* VEDERE QUANTI BIGLIETTI E ABBONAMENTI EMESSI PER RIVENDITORE IN UN LASSO DI TEMPO *******************************

        // Questo metodo restituisce il numero di prodotti
        pd.countAllProductsInAPeriodOfTime(1, LocalDate.of(2025, 1, 15), LocalDate.now());

        // Questo metodo restituisce una lista di prodotti
        List<Prodotto> listaProdottiEmessi = pd.findAllInAPeriodOfTime(1, LocalDate.of(2025, 1, 15), LocalDate.now());
        if (listaProdottiEmessi.isEmpty()) {
            System.out.println("Nessun prodotto trovato");
        } else {
            listaProdottiEmessi.forEach(System.out::println);
        }

        //    ******************************* IMPOSTARE UN RIVENDITORE AUTOMATICO COME IN SERVIZIO / FUORI SERVIZIO *******************************

        rd.setInServizioRivenditoreAutomatico(2, false);


        //    ************************************ VERIFICARE VALIDITÀ ABBONAMENTO IN BASE AL NUMERO DI TESSERA  ************************************

        List<Abbonamento> abbonamentoVerificato = ud.verifyAbbonamentoByTessera(2);
        if (abbonamentoVerificato.isEmpty()) {
            System.out.println("Nessun abbonamento trovato");
        } else {
            abbonamentoVerificato.forEach(System.out::println);
        }

        //    ************************************ TRACCIARE LE MANUTENZIONI DI UN MEZZO  ************************************

        List<InManutenzione> listaManutenzioniDiUnMezzo = md.getListaManutenzioniPerMezzoById(1);

        listaManutenzioniDiUnMezzo.forEach(System.out::println);


        //    ************************************ NUMERO DI BIGLIETTI VIDIMATI SU UN MEZZO IN UN PERIODO DI TEMPO ************************************

        // Ritorna il NUMERO di biglietti vidimati in un determinato lasso di tempo per un determinato mezzo specificato tramite ID
        long VidimazioniPerMezzoInUnLassoDiTempo = md.getNumeroBigliettiVidimatiPerMezzo(1, LocalDate.of(2025, 1, 5), LocalDate.of(2026, 1, 30));
        System.out.println(VidimazioniPerMezzoInUnLassoDiTempo);


        //    *************************************** COUNT / FIND NUMERO DI BIGLIETTI VIDIMATI IN UN DETERMINATO LASSO DI TEMPO ***************************************

        // Ritorna il NUMERO di biglietti vidimati in un determinato lasso di tempo
        long numeroBigliettiVidimatiPerTempo = pd.countAllValidatesInAPeriodOfTime(LocalDate.of(2025, 1, 1), LocalDate.of(2026, 12, 31));
        System.out.println(numeroBigliettiVidimatiPerTempo);

        // Ritorna la LISTA di biglietti vidimati in un determinato lasso di tempo
        List<Biglietto> listaBigliettiVidimatiPerTempo = pd.findAllValidatesInAPeriodOfTime(LocalDate.of(2025, 1, 1), LocalDate.of(2026, 12, 31));
        if (listaBigliettiVidimatiPerTempo.isEmpty()) {
            System.out.println("Nessun biglietto trovato");
        } else {
            listaBigliettiVidimatiPerTempo.forEach(System.out::println);
        }


        //    ************************************ NUMERO DI PERCORRENZE PER MEZZO E TRATTA ************************************

        long numeroPercorrenzePerMezzoETratta = td.numeroPercorrenzePerMezzoETratta(1, 1);

        System.out.println(numeroPercorrenzePerMezzoETratta);


        //    ************************************ LISTA DEI TEMPI DI PERCORRENZA EFFETTIVI PER MEZZO E TRATTA ************************************

        List<Percorrenza> listaTempiEffettivi = td.getListaTempiPercorrenzaEffettivi(1, 1);
        if (listaTempiEffettivi.isEmpty()) {
            System.out.println("Nessuna lista con tempi di percorrenza trovata");
        } else {
            listaTempiEffettivi.forEach(System.out::println);
        }


        //    ************************************ TEMPO MEDIO DI PERCORRENZA EFFETTIVO DI UN MEZZO SU UNA TRATTA ************************************

        double tempoMedioEffettivo = td.tempoMedioPerMezzoETratta(1, 1);

        System.out.println(tempoMedioEffettivo);


        //    *************************************** Esempio di menù strutturato come avevamp pensato ***************************************

        // questa variabile mi serve pr il ciclo while
        /*
        boolean continua = true;

        while (continua) {
            System.out.println("Sei un utente (scrivi 1) o un amministratore (scrivi 2)? Scrivi 0 per terminare il programma");
            int casoDaEseguire = Integer.parseInt(scanner.nextLine());

            switch (casoDaEseguire) {
                case 0:
                    System.out.println("Hai scelto di terminare il programma");
                    continua = false;
                    scanner.close();
                    break;

                case 1:
                    System.out.println("Hai selezionato Utente");
                    System.out.println("Seleziona il tipo di operazione che vuoi eseguire:");
                    System.out.println("1) Acquistare un prodotto");
                    System.out.println("2) Creare una tessera");
                    System.out.println("3) Vidimare un biglietto");
                    System.out.println("0) Esci");
                    int sceltaUser = Integer.parseInt(scanner.nextLine());
                    switch (sceltaUser) {
                        case 1:
                            System.out.println("Che tipo di prodotto vuoi acquistare?");
                            System.out.println("1) Biglietto");
                            System.out.println("2) Abbonamento");
                            System.out.println("0 Esci) Abbonamento");
                            int scaltaRivenditore = Integer.parseInt(scanner.nextLine());
                            switch (scaltaRivenditore) {
                                case 1:
                                    System.out.println("");
                            }
                        case 0:
                            System.out.println("Programma terminato, arrivederci!");
                            break;
                    }

                    break;

                case 2:
                    System.out.println("Hai selezionato Amministratore");

                    break;


                default:
                    System.out.println("Il numero selezionato non è valido");
            }
        }
        */

        em.close();
        entityManagerFactory.close();
    }
}
