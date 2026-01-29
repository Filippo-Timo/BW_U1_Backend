package filippotimo;

import filippotimo.dao.*;
import filippotimo.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

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

        Rivenditore primoRivenditore = new RivenditoreAutomatico("Filippo", false);
//        rd.save(primoRivenditore);
        Rivenditore rivPerAbbonamento = rd.findById(2);

        MezzoPubblico primoMezzo = new MezzoPubblico("Iveco", tipoMezzo.AUTOBUS);
//        md.saveMezzoPubblico(primoMezzo);

        Biglietto biglietto1 = new Biglietto(LocalDate.of(2026, 1, 12), primoRivenditore, tipoMezzo.AUTOBUS, primoMezzo);
//        pd.save(biglietto1);


        Utenti primoUtente = new Utenti("Marcello", "Lippi");
//        ud.save(primoUtente);
        Tessera tesseraPerAbbonamento = ud.findTesseraByNumero(2);
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
