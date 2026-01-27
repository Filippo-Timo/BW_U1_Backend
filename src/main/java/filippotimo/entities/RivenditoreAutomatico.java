package filippotimo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RIVENDITORE_AUTOMATICO")
public class RivenditoreAutomatico extends Rivenditore {

    @Column(name = "in_servizio", nullable = false)
    private boolean inServizio;

    public RivenditoreAutomatico() {
        super();
    }

    public RivenditoreAutomatico(String nome, boolean inServizio) {
        super(nome);
        this.inServizio = inServizio;
    }

    public boolean isInServizio() {
        return inServizio;
    }

    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }
}
