package filippotimo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rivenditori")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipo_venditore",
        discriminatorType = DiscriminatorType.STRING,
        length = 50 // 16 e' abbastanza capiente per "automatico" (10) e "autorizzato" (10)
)

public abstract class Rivenditore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rivenditore")
    private Long id;

    @Column(nullable = false)
    private String nome;

    protected Rivenditore() {
    }

    protected Rivenditore(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Rivenditore { id = " + id + ", nome = '" + nome + "' }";
    }

    public String getTipoLabel() {
        if (this instanceof RivenditoreAutomatico) {
            return "automatico";
        }
        if (this instanceof RivenditoreAutorizzato) {
            return "autorizzato";
        }
        throw new IllegalStateException("Tipo rivenditore non supportato: " + getClass().getName());
    }
}
