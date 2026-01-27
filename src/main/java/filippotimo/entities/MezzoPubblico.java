package filippotimo.entities;

import jakarta.persistence.*;

public class MezzoPubblico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMezzoPubblico;
    @Column(name = "nome_mezzo", nullable = false)
    private String nomeMezzo;
    @Column(name = "posti_a_sedere", nullable = false)
    private int postiASedere;
    @Column(name = "tipo_mezzo", nullable = false)
    @Enumerated(EnumType.STRING)
    private tipoMezzo tipoMezzo;

//    costruttore vuoto per JPA
    public MezzoPubblico(){}
    public MezzoPubblico(String nomeMezzo, tipoMezzo tipoMezzo){

    }
}
