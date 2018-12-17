package br.edu.ifpb.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/12/2018, 10:25:29
 */
@Entity
@SequenceGenerator(
        name = "seq_name",
        allocationSize = 1,
        initialValue = 1,
        sequenceName = "minha_seq"
)
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(generator = "seq_name", strategy = GenerationType.SEQUENCE)
    private int id;
    private String nome;
    private double cre;
//    @Embedded
//    private Endereco endereco;
//    @Convert(converter = LocalDateConverter.class)
//    private LocalDate dataDeNascimento;

    public Aluno() {
    }

    private Aluno(String nome, double cre) {
        this.nome = nome;
        this.cre = cre;
    }

    public static Aluno of(String nome, double cre) {
        return new Aluno(nome, cre);
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

    public double getCre() {
        return cre;
    }

    public void setCre(double cre) {
        this.cre = cre;
    }

}
