package filippotimo.dao;

import filippotimo.entities.Biglietto;
import filippotimo.entities.InManutenzione;
import filippotimo.entities.MezzoPubblico;
import filippotimo.entities.tipoMezzo;
import filippotimo.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class MezzoPubblicoDAO {
    private final EntityManager em;

    public MezzoPubblicoDAO(EntityManager em){
        this.em = em;
    }

//    metodo creazione e salvataggio
    public void createAndSaveMezzoPubblico(String nomeMezzo, tipoMezzo tipoMezzo) {
//        invoco il costruttore per creare un nuovo mezzo
        MezzoPubblico newMezzo = new MezzoPubblico(nomeMezzo, tipoMezzo);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newMezzo);
        transaction.commit();
        System.out.println("Il mezzo pubblico " + newMezzo + " è stato creato e salvato correttamente in DB");
    }
//   metodo salvataggio semplice
    public void saveMezzoPubblico(MezzoPubblico newMezzo) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newMezzo);
        transaction.commit();
        System.out.println("Il mezzo pubblico " + newMezzo + " è stato salvato correttamente in DB!");
    }
//    metodo ricercaById
    public MezzoPubblico findMezzoById(long idMezzo) {
        MezzoPubblico mezzoTrovato = em.find(MezzoPubblico.class, idMezzo);
        if(mezzoTrovato == null) throw new NotFoundException("Mezzo Pubblico con id " + idMezzo + " non trovato!" );
        return mezzoTrovato;
    }
//    metodo rimuovi mezzo
    public void removeMezzoById(long idMezzo) {
        MezzoPubblico mezzoTrovato = findMezzoById(idMezzo);
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.remove(mezzoTrovato);
        tr.commit();
        System.out.println("Il mezzo: " + mezzoTrovato + " è stato rimosso dal DB correttamente!");
    }
//    metodo crea e salva nuova manutenzione
    public void mandaInManutenzione(long idMezzo) {
        MezzoPubblico mezzoTrovato = findMezzoById(idMezzo);
        InManutenzione nuovaManutenzione = new InManutenzione(LocalDate.now(),mezzoTrovato);
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.persist(nuovaManutenzione);
        tr.commit();
        System.out.println("Il mezzo: " + mezzoTrovato + " è stato mandato in manutenzione!");
        System.out.println(nuovaManutenzione);
    }
//      ricerca manutenzione
    public InManutenzione findManutenzioneById(long idManutenzione) {
        InManutenzione manutenzioneTrovata = em.find(InManutenzione.class, idManutenzione);
        if(manutenzioneTrovata == null) throw new NotFoundException("Manutenzione con id " + idManutenzione + " non trovata!");
        return manutenzioneTrovata;
    }
//      rimuovi manutenzione
    public void removeManutenzione(long idManutenzione) {
        InManutenzione manutenzioneTrovata = findManutenzioneById(idManutenzione);
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.remove(manutenzioneTrovata);
        tr.commit();
        System.out.println("La manutenzione: " + manutenzioneTrovata + " è stata rimossa correttamente dal DB!");
    }

    public void setDataFineManutenzione(long idManutenzione, LocalDate dataFine) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        InManutenzione manutenzioneDaSettare = findManutenzioneById(idManutenzione);
        manutenzioneDaSettare.setDataFine(dataFine);
        tr.commit();
        System.out.println("La manutenzione " + manutenzioneDaSettare + " è stata aggiornata con la data " + dataFine);
    }

    public List<InManutenzione> getListaManutenzioniPerMezzoById(long idMezzo) {
        TypedQuery<InManutenzione> query = em.createQuery("SELECT m FROM InManutenzione m WHERE m.idMezzoPubblico.idMezzoPubblico = :idMezzo", InManutenzione.class);
        query.setParameter("idMezzo", idMezzo);
        List<InManutenzione> listaManutenzioni = query.getResultList();
        if(listaManutenzioni.isEmpty()) System.out.println("Nessuna manutenzione trovata per il mezzo con ID: "+ idMezzo);
        return listaManutenzioni;
    }

    public Long getNumeroBigliettiVidimatiPerMezzo(long idMezzo, LocalDate dataVidimazioneI, LocalDate dataVidimazioneF) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT (b) FROM Biglietto b" +
                " WHERE b.idMezzo.idMezzoPubblico = :idMezzo" +
                " AND b.dataVidimazione IS NOT NULL" +
                " AND b.dataVidimazione >= :dataVidimazioneI" +
                " AND b.dataVidimazione <= :dataVidimazioneF ", Long.class);
                query.setParameter("idMezzo", idMezzo);
                query.setParameter("dataVidimazioneI", dataVidimazioneI);
                query.setParameter("dataVidimazioneF", dataVidimazioneF);
        return query.getSingleResult();
    }

}
