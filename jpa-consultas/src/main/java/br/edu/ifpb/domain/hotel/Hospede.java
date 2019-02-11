package br.edu.ifpb.domain.hotel;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 31/01/2019, 08:06:12
 */
@Entity
public class Hospede implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;

//    @ManyToMany(mappedBy = "hospedes")
    @OneToMany(mappedBy = "hospede", targetEntity = Hospedagem.class)
    @Basic
    private List<Hospedagem> hoteis;

    public Hospede() {
    }

    public Hospede(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
