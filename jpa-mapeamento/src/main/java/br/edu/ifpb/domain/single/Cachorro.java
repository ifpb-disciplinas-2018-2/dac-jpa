package br.edu.ifpb.domain.single;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 10:15:57
 */
@Entity
//@DiscriminatorValue("C")
@DiscriminatorValue("3")
public class Cachorro extends Animal {

//    @Column(nullable = false) // Nessa estrategia não podemos ter atributos Não-nulos.
    private String pedigree;

    public Cachorro() {
    }

    public Cachorro(String pedigree, String nome, String raca) {
        super(nome, raca);
        this.pedigree = pedigree;
    }

    public String getPedigree() {
        return pedigree;
    }

    public void setPedigree(String pedigree) {
        this.pedigree = pedigree;
    }

}
