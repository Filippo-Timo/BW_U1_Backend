package filippotimo.entities;

import jakarta.persistence.*;

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

    // costruttori
    public Utenti() {
    }

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

    @Override
    public String toString() {
        return "Utenti { " +
                "id = " + id +
                ", nome = " + nome + '\'' +
                ", cognome = " + cognome + '\'' +
                '}';
    }
}





