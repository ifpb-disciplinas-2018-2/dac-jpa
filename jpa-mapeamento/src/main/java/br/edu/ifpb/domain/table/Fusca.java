package br.edu.ifpb.domain.table;

import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 10:51:07
 */
@Entity
public class Fusca extends Carro{

    private int nivelDeBeleza;

    public Fusca() {
    }

    public Fusca(int nivelDeBeleza, String nome, String modelo) {
        super(nome, modelo);
        this.nivelDeBeleza = nivelDeBeleza;
    }

    public int getNivelDeBeleza() {
        return nivelDeBeleza;
    }

    public void setNivelDeBeleza(int nivelDeBeleza) {
        this.nivelDeBeleza = nivelDeBeleza;
    }
    
    
}
