package filippotimo;

import filippotimo.dao.*;
import filippotimo.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Application {

    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("bw1backendpu");
        }
        return entityManagerFactory;
    }

    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
    }

    public static void main(String[] args) {
        EntityManager em = getEntityManagerFactory().createEntityManager();

        MezzoPubblicoDAO md = new MezzoPubblicoDAO(em);
        ProdottoDAO pd = new ProdottoDAO(em);
        RivenditoriDAO rd = new RivenditoriDAO();
        TrattaDAO td = new TrattaDAO(em);
        UtentiDAO ud = new UtentiDAO(em);

        Rivenditore primoRivenditore = new RivenditoreAutorizzato("Filippo");
        Rivenditore secondoRivenditore = new RivenditoreAutomatico("BOT",true);
//        rd.save(primoRivenditore);
//        rd.save(secondoRivenditore);
//        Rivenditore rivPerAbbonamento = rd.findById(2);

        MezzoPubblico primoMezzo = new MezzoPubblico("Iveco", tipoMezzo.AUTOBUS);
        MezzoPubblico secondoMezzo = new MezzoPubblico("BOH", tipoMezzo.TRAM);
//        md.saveMezzoPubblico(primoMezzo);
//        md.saveMezzoPubblico(secondoMezzo);
        Rivenditore primoRivenditoreDB = rd.findById(1);
        Rivenditore secondoRivenditoreDB = rd.findById(2);
        MezzoPubblico mezzo1DB = md.findMezzoById(3);
        MezzoPubblico mezzo2DB = md.findMezzoById(4);
        Biglietto biglietto1 = new Biglietto(LocalDate.of(2026, 1, 12), primoRivenditoreDB, tipoMezzo.AUTOBUS);
        Biglietto biglietto2 = new Biglietto(LocalDate.of(2026, 1, 29), secondoRivenditoreDB, tipoMezzo.TRAM);
//        pd.save(biglietto1);
//        pd.save(biglietto2);


        Utenti primoUtente = new Utenti("Marcello", "Lippi");
        Utenti secondoUtente = new Utenti("Roberto", "Mancini");
//        pd.validateATicketBySetter(2,3);
//        long result = md.getNumeroBigliettiVidimatiPerMezzo(2,LocalDate.of(2026,1,28),LocalDate.of(2026,1,30));
//        System.out.println(result);
        Tratta tratta1 = new Tratta("Linea A","Pomezia","Tor San Lorenzo",25);
//        td.saveTratta(tratta1);
//        td.createAndSaveTratta("Linea C","Torvajanica","Tor San Lorenzo",20);
        Tratta tratta1DB = td.findTrattaById(1);
        Tratta tratta2DB = td.findTrattaById(2);
//        td.removeTratta(3);
//        td.createAndSavePercorrenza(18, tratta1DB,mezzo1DB);
//        td.createAndSavePercorrenza(18, tratta2DB,mezzo2DB);
        System.out.println(td.tempoMedioPerMezzoETratta(3,1));
//        System.out.println(td.numeroPercorrenzePerMezzoETratta(1,1));
        List<Percorrenza> listaTempiEffett = td.getListaTempiPercorrenzaEffettivi(3,1);
        listaTempiEffett.forEach(percorrenza -> System.out.println(percorrenza));

//        ud.save(primoUtente);
//        ud.save(secondoUtente);
//        rd.removeById(3);
//        rd.setInServizioRivenditoreAutomatico(4,false);
//        List<Rivenditore> lista = rd.findAll();
//        lista.forEach(r-> System.out.println(r));
//        md.mandaInManutenzione(3);
//        md.mandaInManutenzione(4);
//        md.setDataFineManutenzione(1,LocalDate.of(2025,1,30));
//        md.setDataFineManutenzione(3,LocalDate.of(2025,1,31));
//        List<InManutenzione> listaManutenzioniVeicolo = md.getListaManutenzioniPerMezzoById(3);
//        listaManutenzioniVeicolo.forEach(m-> System.out.println(m));

//        Tessera tesseraPerAbbonamento = ud.findTesseraByNumero(2);
//        ud.createAndSaveTessera(primoUtente, LocalDate.of(2025, 12, 6));
//        Utenti sirLippi = ud.findUtenteById(3);
//        ud.createAndSaveTessera(sirLippi, LocalDate.of(2026, 1, 12));
//        ud.findUtenteByIdAndDelete(2);
//        Abbonamento primoAbbonamento = new Abbonamento(LocalDate.of(2026, 1, 10), rivPerAbbonamento, durataAbbonamento.MENSILE, tesseraPerAbbonamento);
//        pd.save(primoAbbonamento);
//        Prodotto diClaudione = pd.findById(3);

//        List<Prodotto> test1 = pd.findAllInAPeriodOfTime(2, LocalDate.of(2024, 12, 3), LocalDate.of(2029, 2, 23));
//        if (test1.isEmpty()) {
//            System.out.println("Nessun prodotto");
//        } else {
//            test1.forEach(System.out::println);
//        }

//        long test1 = pd.countAllProductsInAPeriodOfTime(2, LocalDate.of(2029, 12, 3), LocalDate.of(2029, 2, 23));
//        if (test1 == 0) {
//            System.out.println("Nessun prodotto");
//        } else {
//            System.out.println(test1);
//            ;
//        }

//        Biglietto bigliettoDaValidare = (Biglietto) pd.findById(1);
//        pd.validateATicketBySetter(1);

//        long testNumeroBigliettiVidimati = pd.countAllValidatesInAPeriodOfTime(LocalDate.of(2023, 12, 3), LocalDate.of(2029, 2, 23));
//        if (testNumeroBigliettiVidimati == 0) {
//            System.out.println("Nessun prodotto trovato");
//        } else {
//            System.out.println("Il numero di biglietti vidimati nel lasso di tempo inserito è: " + testNumeroBigliettiVidimati);
//            ;
//        }

//        List<Biglietto> testListaBigliettiVidimati = pd.findAllValidatesInAPeriodOfTime(LocalDate.of(2024, 12, 3), LocalDate.of(2029, 2, 23));
//        if (testListaBigliettiVidimati.isEmpty()) {
//            System.out.println("Nessun prodotto");
//        } else {
//            testListaBigliettiVidimati.forEach(System.out::println);
//        }

//        System.out.println(diClaudione);
//        Abbonamento ciaone = ud.verifyAbbonamentoByTessera(2);
//        System.out.println(ciaone);


//        MezzoPubblico primoMezzo = new MezzoPubblico("AA 123 BB", tipoMezzo.AUTOBUS);
//        md.saveMezzoPubblico(primoMezzo);

//        md.createAndSaveMezzoPubblico("CC 456 DD", tipoMezzo.TRAM);

        em.close();
        entityManagerFactory.close();
    }
}
