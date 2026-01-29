package filippotimo.dao;


import filippotimo.entities.Tratta;
import filippotimo.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TrattaDAO {
    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }


    public Tratta findTrattById(long id) {
        Tratta trattaTrovata = em.find(Tratta.class, id);
        if (trattaTrovata == null) throw new NotFoundException("La tratta con id " + id + " non è stata trovata!");
        return trattaTrovata;
    }


    public void createAndSaveTratta(String nomeTratta, String luogoPartenza, String capolinea, Integer tempoPercPrev) {

        Tratta newTratta = new Tratta(nomeTratta, luogoPartenza, capolinea, tempoPercPrev);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newTratta);
        transaction.commit();
        System.out.println("La tratta " + newTratta + " è stata salvata correttamente in DB!");


    }

    public void removeTratta(long id) {
        Tratta trattaTrovata = findTrattById(id);
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.remove(trattaTrovata);
        tr.commit();
        System.out.println("La  Tratta: " + trattaTrovata + " è stata rimossa correttamente dal DB!");
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


}