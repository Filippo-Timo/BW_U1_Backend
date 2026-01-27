package filippotimo.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Tessera")
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroTessera;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private Utenti utente;

    @Column(nullable = false)
    private LocalDate dataEmissione;

    @Column(nullable = false)
    private LocalDate dataScadenza;

    // costruttori
    public Tessera() {}

    public Tessera(Utenti utente, LocalDate dataEmissione, LocalDate dataScadenza) {
        this.utente = utente;
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataScadenza;
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
}