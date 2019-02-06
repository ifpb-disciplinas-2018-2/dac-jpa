package br.edu.ifpb.domain.mapped;

import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 11:28:39
 */
@Entity
public class Analista extends Tecnico {

    private String siape;

    public Analista() {
    }

    public Analista(String siape, String cpf, String nome) {
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
