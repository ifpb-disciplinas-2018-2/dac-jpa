package br.edu.ifpb.domain.joined;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 11:09:08
 */
@Entity
@DiscriminatorValue("A")
public class Aluno extends Pessoa {

    private String matricula;

    public Aluno() {
    }

    public Aluno(String matricula, String cpf, String nome) {
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
