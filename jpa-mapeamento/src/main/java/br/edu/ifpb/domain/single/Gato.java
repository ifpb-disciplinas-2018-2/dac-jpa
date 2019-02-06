package br.edu.ifpb.domain.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 10:15:41
 */
@Entity
//@DiscriminatorValue("G")
@DiscriminatorValue("2")
public class Gato extends Animal {

    private String manha;

    public Gato() {
    }

    public Gato(String manha, String nome, String raca) {
        super(nome, raca);
        this.manha = manha;
    }

    public String getManha() {
        return manha;
    }

    public void setManha(String manha) {
        this.manha = manha;
    }
    
}
