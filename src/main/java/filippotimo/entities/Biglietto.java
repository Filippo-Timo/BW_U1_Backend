package filippotimo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("biglietti")
public class Biglietto extends Prodotto {

    @Column(name = "tipo_mezzo", nullable = false)
    @Enumerated(EnumType.STRING)
    private tipoMezzo tipoMezzo;

    @Column(name = "data_vidimazione")
    private LocalDate dataVidimazione;


    @ManyToOne
    @JoinColumn(name = "id_mezzo_pubblico")
    private MezzoPubblico idMezzo;


    public Biglietto() {
    }

    public Biglietto(LocalDate dataEmissione, Rivenditore idRivenditore, tipoMezzo tipoMezzo, MezzoPubblico idMezzo) {
        super(dataEmissione, idRivenditore);
        this.tipoMezzo = tipoMezzo;
        this.dataVidimazione = null;
        this.idMezzo = idMezzo;
    }

    public tipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(tipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    public LocalDate getDataVidimazione() {
        return dataVidimazione;
    }

    public void setDataVidimazione(LocalDate dataVidimazione) {
        this.dataVidimazione = dataVidimazione;
    }

    public MezzoPubblico getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(MezzoPubblico idMezzo) {
        this.idMezzo = idMezzo;
    }

    @Override
    public String toString() {
        return "Biglietto {" +
                "tipoMezzo = " + tipoMezzo +
                ", dataVidimazione = " + dataVidimazione +
                ", idMezzo = " + idMezzo +
                '}' + super.toString();
    }
}
