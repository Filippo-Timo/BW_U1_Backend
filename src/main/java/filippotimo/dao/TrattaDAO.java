package filippotimo.dao;


import filippotimo.entities.MezzoPubblico;
import filippotimo.entities.Percorrenza;
import filippotimo.entities.Tratta;
import filippotimo.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TrattaDAO {
    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }


    public void saveTratta(Tratta trattaDaSalvare) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(trattaDaSalvare);
        transaction.commit();
        System.out.println("La tratta " + trattaDaSalvare + " è stata salvata correttamente in DB!");
    }

    public void savePercorrenza(Percorrenza percorrenzaDaSalvare) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(percorrenzaDaSalvare);
        transaction.commit();
        System.out.println("La Percorrenza " + percorrenzaDaSalvare + " è stata salvata correttamente in DB!");
    }

    public Tratta findTrattaById(long id) {
        Tratta trattaTrovata = em.find(Tratta.class, id);
        if (trattaTrovata == null) throw new NotFoundException("La tratta con id " + id + " non è stata trovata!");
        return trattaTrovata;
    }

    public Percorrenza findPercorrenzaById(long id) {
        Percorrenza percorrenzaTrovata = em.find(Percorrenza.class, id);
        if (percorrenzaTrovata == null) throw new NotFoundException("La tratta con id " + id + " non è stata trovata!");
        return percorrenzaTrovata;
    }


    public void createAndSaveTratta(String nomeTratta, String luogoPartenza, String capolinea, Integer tempoPercPrev) {

        Tratta newTratta = new Tratta(nomeTratta, luogoPartenza, capolinea, tempoPercPrev);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newTratta);
        transaction.commit();
        System.out.println("La tratta " + newTratta + " è stata salvata correttamente in DB!");


    }

    public void createAndSavePercorrenza(int tempoPercorrenzaEffettivo, Tratta tratta, MezzoPubblico mezzo) {

        Percorrenza newPercorrenza = new Percorrenza(tempoPercorrenzaEffettivo, tratta, mezzo);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newPercorrenza);
        transaction.commit();
        System.out.println("La Percorrenza " + newPercorrenza + " è stata salvata correttamente in DB!");


    }


    public void removeTratta(long id) {
        Tratta trattaTrovata = findTrattaById(id);
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.remove(trattaTrovata);
        tr.commit();
        System.out.println("La  Tratta: " + trattaTrovata + " è stata rimossa correttamente dal DB!");
    }

    public void removePercorrenza(long id) {
        Percorrenza percorrenzaTrovata = findPercorrenzaById(id);
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.remove(percorrenzaTrovata);
        tr.commit();
        System.out.println("La  Percorrenza: " + percorrenzaTrovata + " è stata rimossa correttamente dal DB!");
    }


    public Double tempoMedioPerMezzoETratta(long mezzoId, long trattaId) {
        return em.createQuery(
                        "SELECT AVG(p.tempoPercorrenzaEffettivo) " +
                                "FROM Percorrenza p " +
                                "WHERE p.tratta.id = :trattaId " +
                                "AND p.idMezzo.idMezzoPubblico = :mezzoId",
                        Double.class
                )
                .setParameter("trattaId", trattaId)
                .setParameter("mezzoId", mezzoId)
                .getSingleResult();
    }


    public Long numeroPercorrenzePerMezzoETratta(long mezzoId, long trattaId) {
        return em.createQuery(
                        "SELECT COUNT(p) " +
                                "FROM Percorrenza p " +
                                "WHERE p.tratta.id = :trattaId " +
                                "AND p.idMezzo.idMezzoPubblico = :mezzoId",
                        Long.class
                )
                .setParameter("trattaId", trattaId)
                .setParameter("mezzoId", mezzoId)
                .getSingleResult();
    }

    public List<Percorrenza> getListaTempiPercorrenzaEffettivi(long mezzoId, long trattaId) {
        TypedQuery<Percorrenza> query = em.createQuery(
                "SELECT p" +
                " FROM Percorrenza p" +
                " WHERE p.tratta.id = :trattaId" +
                " AND p.idMezzo.idMezzoPubblico = :mezzoId", Percorrenza.class);
        query.setParameter("mezzoId", mezzoId);
        query.setParameter("trattaId", trattaId);
        return query.getResultList();
    }
    }

