package br.edu.ifpb.domain;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 07/02/2019, 10:01:24
 */
public class Gerencia {

    private String departamento;
    private Gerente gerente;
    private int id;

    public Gerencia() {
    }

    public Gerencia(Gerente gerente, String departamento, int id) {
        this.departamento = departamento;
        this.gerente = gerente;
        this.id=id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Gerencia{" + "departamento=" + departamento + ", gerente=" + gerente + ", id=" + id + '}';
    }
    
    

}
