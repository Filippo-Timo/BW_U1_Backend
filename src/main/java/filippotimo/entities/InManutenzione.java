package filippotimo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "in_manutenzione")
public class InManutenzione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_manutenzione")
    private long idManutenzione;
    @Column(name = "data_inizio",nullable = false)
    private LocalDate dataInizio;
    @Column(name = "data_fine")
    private LocalDate dataFine;
    @OneToOne
    @JoinColumn(name = "id_mezzo_pubblico", nullable = false)
    private MezzoPubblico idMezzoPubblico;
//    classe vuota per JPA
public InManutenzione(){}
//    2 classi custom creazione
public InManutenzione(LocalDate data_inizio, MezzoPubblico idMezzo){
    this.dataInizio = data_inizio;
    this.idMezzoPubblico = idMezzo;
    this.dataFine = null;
}
public InManutenzione(LocalDate data_inizio ,LocalDate data_fine, MezzoPubblico idMezzo){
this.dataInizio = data_inizio;
this.dataFine = data_fine;
this.idMezzoPubblico = idMezzo;
}

//getter e setter


    public long getIdManutenzione() {
        return idManutenzione;
    }

    public void setIdManutenzione(long idManutenzione) {
        this.idManutenzione = idManutenzione;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public MezzoPubblico getIdMezzoPubblico() {
        return idMezzoPubblico;
    }

    public void setIdMezzoPubblico(MezzoPubblico idMezzoPubblico) {
        this.idMezzoPubblico = idMezzoPubblico;
    }
}

