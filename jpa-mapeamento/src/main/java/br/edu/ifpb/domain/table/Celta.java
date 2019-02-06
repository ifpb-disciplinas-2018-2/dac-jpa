package br.edu.ifpb.domain.table;

import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 10:51:12
 */
@Entity
public class Celta extends Carro {

    private int ano;

    public Celta(int ano, String nome, String modelo) {
        super(nome, modelo);
        this.ano = ano;
    }

    public Celta() {
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

}
