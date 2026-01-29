package filippotimo.dao;

import filippotimo.entities.Tessera;
import filippotimo.entities.Utenti;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UtentiDAO {

    private final EntityManager em;

    public UtentiDAO(EntityManager em) {
        this.em = em;
    }


    // creazione utente
    public Utenti creaUtente(String nome, String cognome) {
        Utenti utente = new Utenti(nome, cognome);

        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();

        return utente;
    }

    // ricerca utente by id
    public Optional<Utenti> trovaUtenteById(Long id) {
        return Optional.ofNullable(em.find(Utenti.class, id));
    }

    // rimozione utente (rimuove anche le tessere grazie a cascade)
    public boolean rimuoviUtente(Long id) {
        Utenti utente = em.find(Utenti.class, id);
        if (utente == null) return false;

        em.getTransaction().begin();
        em.remove(utente);
        em.getTransaction().commit();

        return true;
    }

    // tutti gli utenti
    public List<Utenti> trovaTuttiUtenti() {
        return em.createQuery("SELECT u FROM Utenti u", Utenti.class)
                .getResultList();
    }



    // creazione tessera
    public Tessera creaTessera(Long idUtente, LocalDate emissione, LocalDate scadenza) {
        Utenti utente = em.find(Utenti.class, idUtente);
        if (utente == null)
            throw new IllegalArgumentException("Utente non trovato");

        Tessera tessera = new Tessera(utente, emissione, scadenza);

        em.getTransaction().begin();
        em.persist(tessera);
        em.getTransaction().commit();

        return tessera;
    }

    // ricerca tessera by id
    public Optional<Tessera> trovaTesseraById(Long numeroTessera) {
        return Optional.ofNullable(em.find(Tessera.class, numeroTessera));
    }

    // rimozione tessera
    public boolean rimuoviTessera(Long numeroTessera) {
        Tessera tessera = em.find(Tessera.class, numeroTessera);
        if (tessera == null) return false;

        em.getTransaction().begin();
        em.remove(tessera);
        em.getTransaction().commit();

        return true;
    }

    // tutte le tessere di un utente
    public List<Tessera> trovaTesserePerUtente(Long idUtente) {
        TypedQuery<Tessera> q = em.createQuery(
                "SELECT t FROM Tessera t WHERE t.utente.id = :id",
                Tessera.class
        );
        q.setParameter("id", idUtente);
        return q.getResultList();
    }



    // verifica di un abbonamento in base al numero di tessera
    public boolean verificaAbbonamentoAttivo(Long numeroTessera) {
        TypedQuery<Long> q = em.createQuery(
                "SELECT COUNT(t) FROM Tessera t " +
                        "WHERE t.numeroTessera = :num " +
                        "AND t.dataScadenza >= :oggi",
                Long.class
        );

        q.setParameter("num", numeroTessera);
        q.setParameter("oggi", LocalDate.now());

        return q.getSingleResult() > 0;
    }
}
