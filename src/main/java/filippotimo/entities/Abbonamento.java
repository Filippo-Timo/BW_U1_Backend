package filippotimo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("abbonamenti")
public class Abbonamento extends Prodotto {

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @Column(name = "durata")
    @Enumerated(EnumType.STRING)
    private durataAbbonamento durataAbbonamento;


    @ManyToOne
    @JoinColumn(name = "numero_tessera")
    private Tessera numeroTessera;


    public Abbonamento() {
    }

    public Abbonamento(LocalDate dataEmissione, Rivenditore idRivenditore, durataAbbonamento durataAbbonamento, Tessera idTessera) {
        super(dataEmissione, idRivenditore);
        this.durataAbbonamento = durataAbbonamento;
        if (this.durataAbbonamento == filippotimo.entities.durataAbbonamento.SETTIMANALE) {
            this.dataScadenza = dataEmissione.plusDays(7);
        } else {
            dataScadenza = dataEmissione.plusMonths(1);
        }
        ;

        this.numeroTessera = idTessera;
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

    public Tessera getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(Tessera numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    @Override
    public String toString() {
        return "Abbonamento {" +
                "dataScadenza = " + dataScadenza +
                ", durataAbbonamento = " + durataAbbonamento +
                ", idTessera = " + numeroTessera +
                '}' + super.toString();
    }
}
