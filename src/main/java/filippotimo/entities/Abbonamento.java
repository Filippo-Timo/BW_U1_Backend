package filippotimo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("abbonamenti")
public class Abbonamento extends Prodotto {

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @Column(name = "durata")
    private durataAbbonamento durataAbbonamento;

    // ********************* COLLEGARE ID TESSERA *********************
    //    private Tessera idTessera;

    public Abbonamento() {
    }

    public Abbonamento(LocalDate dataEmissione, long idRivenditore, LocalDate dataScadenza, durataAbbonamento durataAbbonamento, long idTessera) {
        super(dataEmissione);
        this.dataScadenza = dataScadenza;
        this.durataAbbonamento = durataAbbonamento;
//        this.idTessera = idTessera;
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

//    public Tessera getIdTessera() {
//        return idTessera;
//    }

    @Override
    public String toString() {
        return "Abbonamento {" +
                "dataScadenza = " + dataScadenza +
                ", durataAbbonamento = " + durataAbbonamento +
//                ", idTessera = " + idTessera +
                '}' + super.toString();
    }
}
