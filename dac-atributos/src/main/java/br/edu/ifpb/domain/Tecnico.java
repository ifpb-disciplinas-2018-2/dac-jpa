package br.edu.ifpb.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/12/2018, 11:15:36
 */
@Entity
public class Tecnico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column(length = 80, nullable = false)
    private String nome;
    @Column(length = 25)
    private String sigepe;
    @Column(length = 11, nullable = false)
    private String cpf;

    public Tecnico() {
    }

    private Tecnico(String nome, String sigepe, String cpf) {
        this.nome = nome;
        this.sigepe = sigepe;
        this.cpf = cpf;
    }

    public static Tecnico of(String nome, String sigepe, String cpf) {
        return new Tecnico(nome, sigepe, cpf);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigepe() {
        return sigepe;
    }

    public void setSigepe(String sigepe) {
        this.sigepe = sigepe;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean valido() {
        if(cpf==null) return false;
        return this.cpf.trim().length() <= 11;
    }

}
