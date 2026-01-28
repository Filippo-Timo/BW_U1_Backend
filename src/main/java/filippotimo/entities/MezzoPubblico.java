package filippotimo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "mezzi_pubblici")
public class MezzoPubblico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mezzo_pubblico")
    private long idMezzoPubblico;
    @Column(name = "nome_mezzo", nullable = false)
    private String nomeMezzo;
    @Column(name = "posti", nullable = false)
    private int posti;
    @Column(name = "tipo_mezzo", nullable = false)
    @Enumerated(EnumType.STRING)
    private tipoMezzo tipoMezzo;

    //    costruttore vuoto per JPA
    public MezzoPubblico() {
    }

    public MezzoPubblico(String nomeMezzo, tipoMezzo tipoMezzo) {
        this.nomeMezzo = nomeMezzo;
        this.tipoMezzo = tipoMezzo;
        if (tipoMezzo == filippotimo.entities.tipoMezzo.AUTOBUS) {
            this.posti = 100;
        } else {
            this.posti = 220;
        }
    }

//    getter e setter


    public long getIdMezzoPubblico() {
        return idMezzoPubblico;
    }

    public String getNomeMezzo() {
        return nomeMezzo;
    }

    public void setNomeMezzo(String nomeMezzo) {
        this.nomeMezzo = nomeMezzo;
    }

    public int getPosti() {
        return posti;
    }

    public void setPosti(int posti) {
        this.posti = posti;
    }

    public tipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(tipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    @Override
    public String toString() {
        return "MezzoPubblico{" +
                "idMezzoPubblico=" + idMezzoPubblico +
                ", nomeMezzo='" + nomeMezzo + '\'' +
                ", posti=" + posti +
                ", tipoMezzo=" + tipoMezzo +
                '}';
    }
}
