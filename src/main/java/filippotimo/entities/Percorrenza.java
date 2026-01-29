package filippotimo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="percorrenza")

public class Percorrenza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tempo_percorrenze", nullable = false)
    private int tempoPercorrenzaEffettivo;

   @ManyToOne
   @JoinColumn(name="tratta_id",nullable = false)
    private Tratta tratta;

   @ManyToOne
   @JoinColumn(name = "id_mezzo_pubblico", nullable = false)
   private MezzoPubblico idMezzo;


   public Percorrenza(){}


    public Percorrenza(int tempoPercorrenzaEffettivo, Tratta tratta, MezzoPubblico idMezzo){
       this.tempoPercorrenzaEffettivo=tempoPercorrenzaEffettivo;
       this.tratta=tratta;
       this.idMezzo=idMezzo;
    }

    public int getTempoPercorrenzaEffettivo() {
        return tempoPercorrenzaEffettivo;
    }

    public void setTempoPercorrenzaEffettivo(int tempoPercorrenzaEffettivo) {
        this.tempoPercorrenzaEffettivo = tempoPercorrenzaEffettivo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    @Override
    public String toString() {
        return "Percorrenza{" +
                "id=" + id +
                ", tempoPercorrenzaEffettivo=" + tempoPercorrenzaEffettivo +
                ", tratta=" + tratta +
                '}';
    }
}
