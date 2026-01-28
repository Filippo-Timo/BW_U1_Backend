package filippotimo.dao;

import filippotimo.entities.*;
import filippotimo.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;

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

    public void createAndSaveBiglietto(LocalDate dataEmissione, Rivenditore idRivenditore, tipoMezzo tipoMezzo, LocalDate dataVidimazione, MezzoPubblico idMezzo) {

        Biglietto newBiglietto = new Biglietto(dataEmissione, idRivenditore, tipoMezzo, dataVidimazione, idMezzo);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newBiglietto);

        transaction.commit();

        System.out.println("Il biglietto con id = " + newBiglietto.getIdProdotto() + " è stato salvato correttamente!");

    }

    //    *************************************** CREATE AND SAVE (abbonamento) ***************************************

    public void createAndSaveAbbonamento(LocalDate dataEmissione, Rivenditore idRivenditore, LocalDate dataScadenza, durataAbbonamento durataAbbonamento, Tessera idTessera) {

        Abbonamento newAbbonamento = new Abbonamento(dataEmissione, idRivenditore, dataScadenza, durataAbbonamento, idTessera);

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

    public void findByIdAndDeleted(long idProdotto) {

        Prodotto found = this.findById(idProdotto);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("Il prodotto con id = " + idProdotto + " è stato eliminato correttamente");
    }

}
