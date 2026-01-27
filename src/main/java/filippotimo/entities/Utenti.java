package filippotimo.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utenti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Tessera> tessere;

    // costruttori
    public Utenti() {}

    public Utenti(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    // getter e setter
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public List<Tessera> getTessere() {
        return tessere;
    }

    public void setTessere(List<Tessera> tessere) {
        this.tessere = tessere;
    }
}





