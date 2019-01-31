package br.edu.ifpb.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

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
    @Embedded
    private Endereco endereco;
//    @Convert(converter = ConvertLocalDate.class)
    @Convert(converter = ConvertLocalDateToLong.class)
    private LocalDate dataDeNascimento;

    @Version
    private int versao;

//    @Transient
//    private int idade;
    public Aluno() {
    }

    private Aluno(String nome, double cre, Endereco end) {
        this.nome = nome;
        this.cre = cre;
        this.endereco = end;
        this.dataDeNascimento = LocalDate.now();
    }

    public static Aluno of(String nome, double cre) {
        return of(nome, cre, Endereco.empty());
    }

    public static Aluno of(String nome, double cre, Endereco end) {
        return new Aluno(nome, cre, end);
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public int getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

}
