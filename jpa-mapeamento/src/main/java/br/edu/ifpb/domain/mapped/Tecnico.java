package br.edu.ifpb.domain.mapped;

import br.edu.ifpb.domain.Endereco;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 11:27:43
 */
@MappedSuperclass
public class Tecnico implements Serializable {

    @Id
    private String cpf;
    private String nome;

    @OneToOne
    private Endereco endereco;

    public Tecnico() {
    }

    public Tecnico(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
