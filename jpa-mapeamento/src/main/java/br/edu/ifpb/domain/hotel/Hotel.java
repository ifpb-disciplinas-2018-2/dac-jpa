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
public class Hotel implements Serializable{

    @Id
    @GeneratedValue
    private int id;
    private String abreviacao;

//    @ManyToMany
//    @JoinTable(name = "Hospedagem")
    @OneToMany(mappedBy = "hotel", targetEntity = Hospedagem.class)
    @Basic
    private List<Hospedagem> hospedes;
    
    public Hotel() {
    }

    public Hotel( String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }
    
    
}
