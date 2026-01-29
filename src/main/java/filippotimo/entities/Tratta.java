package filippotimo.entities;


import jakarta.persistence.*;

@Entity
@Table(name="tratta")

public class Tratta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

     @Column(name="nome_tratta",nullable = false)
    private String nomeTratta;
     @Column(name="luogo_partenza", nullable = false)
    private String luogoPartenza;
     @Column(name = "capolinea", nullable = false)
    private String capolinea;
     @Column(name="Tempo_perc_prev")
    private int tempoPercPrev;






     protected Tratta(){}


    public Tratta(String nomeTratta,String luogoPartenza,String capolinea, int tempoPercPrev){
         this.nomeTratta= nomeTratta;

         this.luogoPartenza=luogoPartenza;
         this.capolinea= capolinea;
         this.tempoPercPrev = tempoPercPrev;
    }

    public long getId() {
        return id;
    }

    public String getNomeTratta() {
        return nomeTratta;
    }

    public void setNomeTratta(String nomeTratta) {
        this.nomeTratta = nomeTratta;
    }

    public String getLuogoPartenza() {
        return luogoPartenza;
    }

    public void setLuogoPartenza(String luogoPartenza) {
        this.luogoPartenza = luogoPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public long getTempoPercPrev() {
        return tempoPercPrev;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", nomeTratta='" + nomeTratta + '\'' +
                ", luogoPartenza='" + luogoPartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", TempoPercPrev=" + tempoPercPrev +
                '}';
    }
}
