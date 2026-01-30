package filippotimo.dao;

import filippotimo.entities.*;
import filippotimo.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class ProdottoDAO {

    private EntityManager em;

    public ProdottoDAO(EntityManager em) {
        this.em = em;
    }

    //    *************************************** SAVE ***************************************

    public void save(Prodotto newProdotto) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newProdotto);

        transaction.commit();

        System.out.println("Il prodotto con id = " + newProdotto.getIdProdotto() + " è stato salvato correttamente!");
    }

    //    *************************************** CREATE AND SAVE (biglietto) ***************************************

    public void createAndSaveBiglietto(LocalDate dataEmissione, Rivenditore idRivenditore, tipoMezzo tipoMezzo, MezzoPubblico idMezzo) {

        Biglietto newBiglietto = new Biglietto(dataEmissione, idRivenditore, tipoMezzo);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newBiglietto);

        transaction.commit();

        System.out.println("Il biglietto con id = " + newBiglietto.getIdProdotto() + " è stato salvato correttamente!");

    }

    //    *************************************** CREATE AND SAVE (abbonamento) ***************************************

    public void createAndSaveAbbonamento(LocalDate dataEmissione, Rivenditore idRivenditore, durataAbbonamento durataAbbonamento, Tessera idTessera) {

        Abbonamento newAbbonamento = new Abbonamento(dataEmissione, idRivenditore, durataAbbonamento, idTessera);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newAbbonamento);

        transaction.commit();

        System.out.println("Il biglietto con id = " + newAbbonamento.getIdProdotto() + " è stato salvato correttamente!");

    }

    //    *************************************** FIND BY ID ***************************************

    public Prodotto findById(long idProdotto) {
        Prodotto found = em.find(Prodotto.class, idProdotto);
        if (found == null)
            throw new IdNotFoundException(idProdotto);
        return found;
    }

    //    *************************************** FIND BY ID AND DELETE ***************************************

    public void findByIdAndDelete(long idProdotto) {

        Prodotto found = this.findById(idProdotto);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("Il prodotto con id = " + idProdotto + " è stato eliminato correttamente");
    }

    //    *************************************** FIND ALL ABBONAMENTI E BIGLIETTI IN UN DETERMINATO LASSO DI TEMPO PER RIVENDITORE ***************************************

    public List<Prodotto> findAllInAPeriodOfTime(long idRivenditore, LocalDate dataDiPartenza, LocalDate dataDiFine) {

        TypedQuery<Prodotto> result = em.createQuery("SELECT p FROM Prodotto p WHERE p.idRivenditore.id = :idRivenditore AND p.dataEmissione >= :dataDiPartenza  AND p.dataEmissione <= :dataDiFine", Prodotto.class);
        result.setParameter("idRivenditore", idRivenditore);
        result.setParameter("dataDiPartenza", dataDiPartenza);
        result.setParameter("dataDiFine", dataDiFine);


        return result.getResultList();
    }

    //    *************************************** COUNT ABBONAMENTI E BIGLIETTI IN UN DETERMINATO LASSO DI TEMPO PER RIVENDITORE ***************************************

    public long countAllProductsInAPeriodOfTime(long idRivenditore, LocalDate dataDiPartenza, LocalDate dataDiFine) {

        TypedQuery<Long> result = em.createQuery("SELECT COUNT(p) FROM Prodotto p WHERE p.idRivenditore.id = :idRivenditore AND p.dataEmissione >= :dataDiPartenza  AND p.dataEmissione <= :dataDiFine", Long.class);
        result.setParameter("idRivenditore", idRivenditore);
        result.setParameter("dataDiPartenza", dataDiPartenza);
        result.setParameter("dataDiFine", dataDiFine);

        return result.getSingleResult();
    }

    //    *************************************** METODO PER VIDIMARE UN BIGLIETTO ***************************************

    public void validateATicketBySetter(long idBiglietto, long idMezzo) {
        EntityTransaction tr = em.getTransaction();
        MezzoPubblicoDAO md = new MezzoPubblicoDAO(em);
        LocalDate today = LocalDate.now();
        tr.begin();
        Biglietto bigliettoDaVidimare = (Biglietto) findById(idBiglietto);
        bigliettoDaVidimare.setDataVidimazione(today);
        MezzoPubblico mezzoTrovato = md.findMezzoById(idMezzo);
        bigliettoDaVidimare.setIdMezzo(mezzoTrovato);
        tr.commit();
        System.out.println("La vidimazione del biglietto " + bigliettoDaVidimare + " è avvenuta in data " + today.toString() + " sul mezzo: " + mezzoTrovato);
    }

    //    *************************************** COUNT IL NUMERO DI BIGLIETTI VIDIMATI IN UN DETERMINATO LASSO DI TEMPO ***************************************

    public long countAllValidatesInAPeriodOfTime(LocalDate dataDiPartenza, LocalDate dataDiFine) {

        TypedQuery<Long> result = em.createQuery("SELECT COUNT(b) FROM Biglietto b " +
                "WHERE b.dataVidimazione IS NOT NULL " +
                "AND b.dataVidimazione BETWEEN :dataDiPartenza  AND :dataDiFine", Long.class);
        result.setParameter("dataDiPartenza", dataDiPartenza);
        result.setParameter("dataDiFine", dataDiFine);

        return result.getSingleResult();
    }

    //    *************************************** FIND ALL BIGLIETTI VIDIMATI IN UN DETERMINATO LASSO DI TEMPO ***************************************

    public List<Biglietto> findAllValidatesInAPeriodOfTime(LocalDate dataDiPartenza, LocalDate dataDiFine) {

        TypedQuery<Biglietto> result = em.createQuery("SELECT b FROM Biglietto b " +
                "WHERE b.dataVidimazione IS NOT NULL " +
                "AND b.dataVidimazione BETWEEN :dataDiPartenza  AND :dataDiFine", Biglietto.class);
        result.setParameter("dataDiPartenza", dataDiPartenza);
        result.setParameter("dataDiFine", dataDiFine);

        return result.getResultList();
    }

}
