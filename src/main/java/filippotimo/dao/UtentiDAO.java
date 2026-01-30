package filippotimo.dao;

import filippotimo.entities.Abbonamento;
import filippotimo.entities.Tessera;
import filippotimo.entities.Utenti;
import filippotimo.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class UtentiDAO {

    private final EntityManager em;

    public UtentiDAO(EntityManager em) {
        this.em = em;
    }

    //    *************************************** SAVE ***************************************

    public void save(Utenti newUtenti) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newUtenti);

        transaction.commit();

        System.out.println("L'utente con id = " + newUtenti.getId() + " è stato salvato correttamente!");
    }

    //    *************************************** CREATE AND SAVE (Utente) ***************************************

    public void createAndSaveUtente(String nome, String cognome) {

        Utenti newUtenti = new Utenti(nome, cognome);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newUtenti);

        transaction.commit();

        System.out.println("L'utente con id = " + newUtenti.getId() + " è stato salvato correttamente!");

    }

    //    *************************************** CREATE AND SAVE (Tessera) ***************************************

    public void createAndSaveTessera(Utenti utente, LocalDate dataEmissione) {

        Tessera newTessera = new Tessera(utente, dataEmissione);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(newTessera);

        transaction.commit();

        System.out.println("La tessera con id = " + newTessera.getNumeroTessera() + " è stato salvato correttamente!");

    }

    //    *************************************** FIND UTENTE BY ID ***************************************

    public Utenti findUtenteById(long idUtenti) {
        Utenti found = em.find(Utenti.class, idUtenti);
        if (found == null)
            throw new IdNotFoundException(idUtenti);
        return found;
    }

    //    *************************************** FIND TESSERA BY NUMERO ***************************************

    public Tessera findTesseraByNumero(long numeroTessera) {
        Tessera found = em.find(Tessera.class, numeroTessera);
        if (found == null)
            throw new IdNotFoundException(numeroTessera);
        return found;
    }

    //    *************************************** FIND UTENTE BY ID AND DELETE ***************************************

    public void findUtenteByIdAndDelete(long idUtente) {

        Utenti found = this.findUtenteById(idUtente);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("L'utente con id = " + idUtente + " è stato eliminato correttamente");
    }

    //    *************************************** FIND TESSERA BY NUMBER AND DELETE ***************************************

    public void findTesseraByNumberAndDelete(long numeroTessera) {

        Tessera found = this.findTesseraByNumero(numeroTessera);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("La tessera con numero = " + numeroTessera + " è stato eliminato correttamente");
    }

    //    *************************************** VERIFY Abbonamento BY Tessera ***************************************

    public List<Abbonamento> verifyAbbonamentoByTessera(long numeroTessera) {
        TypedQuery<Abbonamento> a = em.createQuery("SELECT a FROM Abbonamento a WHERE a.numeroTessera.numeroTessera = :numeroTessera", Abbonamento.class);

        a.setParameter("numeroTessera", numeroTessera);

        return a.getResultList();
    }

}
