package filippotimo.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RIVENDITORE_AUTORIZZATO")
public class RivenditoreAutorizzato extends Rivenditore {

    public RivenditoreAutorizzato() {
        super();
    }

    public RivenditoreAutorizzato(String nome) {
        super(nome);
    }
}
