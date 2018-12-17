package br.edu.ifpb.domain;

import java.io.Serializable;
import javax.persistence.Entity;
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
//    private List<String> telefones = new ArrayList<>();
//    private Sexo sexo;
//    private CPF cpf;

    public Professor() {
    }

    private Professor(double salario, String matricula, int idade) {
        this.salario = salario;
        this.matricula = matricula;
        this.idade = idade;
    }

    public static Professor of(double salario, String matricula, int idade) {
        return new Professor(salario, matricula, idade);
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

}
