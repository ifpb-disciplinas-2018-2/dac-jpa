package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 28/02/2019, 10:10:27
 */
@Entity
public class Sacola implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    @OneToMany
    private List<Produto> produtos = new ArrayList<>();

    public Sacola() {
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Sacola{" + "id=" + id + ", nome=" + nome + ", produtos=" + produtos + '}';
    }
    
}
