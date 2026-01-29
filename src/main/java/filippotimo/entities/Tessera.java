package filippotimo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Tessera")
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_tessera")
    private Long numeroTessera;

    @OneToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utenti utente;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza", nullable = false)
    private LocalDate dataScadenza;

    // costruttori
    public Tessera() {
    }

    public Tessera(Utenti utente, LocalDate dataEmissione) {
        this.utente = utente;
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataEmissione.plusYears(1);
    }

    // getter e setter
    public Long getNumeroTessera() {
        return numeroTessera;
    }

    public Utenti getUtente() {
        return utente;
    }

    public void setUtente(Utenti utente) {
        this.utente = utente;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    @Override
    public String toString() {
        return "Tessera { " +
                "numeroTessera = " + numeroTessera +
                ", utente = " + utente +
                ", dataEmissione = " + dataEmissione +
                ", dataScadenza = " + dataScadenza +
                '}';
    }
}