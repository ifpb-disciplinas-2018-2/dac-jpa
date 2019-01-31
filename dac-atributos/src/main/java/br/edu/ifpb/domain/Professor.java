package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/12/2018, 09:40:23
 */
@Entity
public class Professor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue
    private int codigo;
    private double salario;
    private String matricula;
    private int idade;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Telefones")
    private List<String> telefones = new ArrayList<>();
    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;

    public Professor() {
    }

    private Professor(double salario, String matricula, int idade, Sexo sexo) {
        this.salario = salario;
        this.matricula = matricula;
        this.idade = idade;
        this.sexo = sexo;
    }

    public static Professor of(double salario, String matricula, int idade, Sexo sexo) {
        return new Professor(salario, matricula, idade, sexo);
    }
    
    public void novoTel(String tel){
        this.telefones.add(tel);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }
    

}
