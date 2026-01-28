package filippotimo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("abbonamenti")
public class Abbonamento extends Prodotto {

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @Column(name = "durata")
    private durataAbbonamento durataAbbonamento;


    @ManyToOne
    @JoinColumn(name = "numero_tessera")
    private Tessera idTessera;


    public Abbonamento() {
    }

    public Abbonamento(LocalDate dataEmissione, Rivenditore idRivenditore, LocalDate dataScadenza, durataAbbonamento durataAbbonamento, Tessera idTessera) {
        super(dataEmissione, idRivenditore);
        this.dataScadenza = dataScadenza;
        this.durataAbbonamento = durataAbbonamento;
        this.idTessera = idTessera;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public durataAbbonamento getDurataAbbonamento() {
        return durataAbbonamento;
    }

    public void setDurataAbbonamento(durataAbbonamento durataAbbonamento) {
        this.durataAbbonamento = durataAbbonamento;
    }

    public Tessera getIdTessera() {
        return idTessera;
    }

    public void setIdTessera(Tessera idTessera) {
        this.idTessera = idTessera;
    }

    @Override
    public String toString() {
        return "Abbonamento {" +
                "dataScadenza = " + dataScadenza +
                ", durataAbbonamento = " + durataAbbonamento +
                ", idTessera = " + idTessera +
                '}' + super.toString();
    }
}
