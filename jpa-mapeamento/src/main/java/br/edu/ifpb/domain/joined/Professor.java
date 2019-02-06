package br.edu.ifpb.domain.joined;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 11:09:35
 */
@Entity
@DiscriminatorValue("P")
public class Professor extends Pessoa{

    private String siape;

    public Professor() {
    }

    public Professor(String siape, String cpf, String nome) {
        super(cpf, nome);
        this.siape = siape;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }
    
}
