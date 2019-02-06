package br.edu.ifpb.domain.mapped;

import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 11:28:27
 */
@Entity
public class TAE extends Tecnico{

    private String matricula;

    public TAE() {
    }

    public TAE(String matricula, String cpf, String nome) {
        super(cpf, nome);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
}
