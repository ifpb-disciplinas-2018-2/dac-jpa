package br.edu.ifpb.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/12/2018, 09:46:26
 */
@Entity
@Table(name = "contatotelefonico")
public class Contato implements Serializable {

    @Id
    private String email;
//    @Column(name = "numero")
    private String nome;

    public Contato() {
    }

    private Contato(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }

    public static Contato of(String email, String nome) {
        // validar se o email está nulo
        return new Contato(email, nome);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
