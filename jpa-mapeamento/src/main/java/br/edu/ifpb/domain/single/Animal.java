package br.edu.ifpb.domain.single;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 10:08:32
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
//        name = "Tipo_do_objeto",discriminatorType = DiscriminatorType.CHAR, length = 1
        name = "Tipo_do_objeto",discriminatorType = DiscriminatorType.INTEGER
)
//@DiscriminatorValue("A")
@DiscriminatorValue("1")
public class Animal implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String raca;

    public Animal() {
    }

    public Animal(String nome, String raca) {
        this.nome = nome;
        this.raca = raca;
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
    
}

/**
 * Tabela: Animal  
 * id | nome | raca | manha | pedigree
 *
 */
