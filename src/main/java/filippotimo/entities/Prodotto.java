package filippotimo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prodotti")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prodotto")
    private Long idProdotto;

    @Column(name = "data_emissione", nullable = false)
    private LocalDate dataEmissione;


    @ManyToOne
    @JoinColumn(name = "id_rivenditore")
    private Rivenditore idRivenditore;

    public Prodotto() {
    }

    public Prodotto(LocalDate dataEmissione, Rivenditore idRivenditore) {
        this.dataEmissione = dataEmissione;
        this.idRivenditore = idRivenditore;
    }

    public Long getIdProdotto() {
        return idProdotto;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public Rivenditore getIdRivenditore() {
        return idRivenditore;
    }

    public void setIdRivenditore(Rivenditore idRivenditore) {
        this.idRivenditore = idRivenditore;
    }

    @Override
    public String toString() {
        return "Prodotto {" +
                "idProdotto = " + idProdotto +
                ", dataEmissione = " + dataEmissione +
                ", idRivenditore = " + idRivenditore +
                '}';
    }
}
