package filippotimo;

import filippotimo.dao.MezzoPubblicoDAO;
import filippotimo.dao.ProdottoDAO;
import filippotimo.entities.MezzoPubblico;
import filippotimo.entities.tipoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw1backendpu");


    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        MezzoPubblicoDAO md = new MezzoPubblicoDAO(em);
        ProdottoDAO pd = new ProdottoDAO(em);

//        Rivenditore riv1 = new RivenditoreAutorizzato("Geppetto");
//
//        MezzoPubblico mez1 = new MezzoPubblico("carmelo", tipoMezzo.AUTOBUS);
//
//        Biglietto biglietto1 = new Biglietto(LocalDate.of(2026, 1, 12), riv1, tipoMezzo.AUTOBUS, null, mez1);
//
//        System.out.println(biglietto1);

        MezzoPubblico primoMezzo = new MezzoPubblico("AA 123 BB", tipoMezzo.AUTOBUS);
//        md.saveMezzoPubblico(primoMezzo);

//        md.createAndSaveMezzoPubblico("CC 456 DD", tipoMezzo.TRAM);
        

    }
}
