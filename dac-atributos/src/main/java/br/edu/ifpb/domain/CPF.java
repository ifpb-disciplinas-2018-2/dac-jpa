package br.edu.ifpb.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/01/2019, 10:01:55
 */
@Embeddable
public class CPF implements Serializable {

    @Column(name = "cpf", length = 11)
    private String valor;

    public CPF() {
    }

    public CPF(String valor) {
        this.valor = valor;
    }

    public String simples() {
        return valor; //12345678910
    }

    public String formatado() {
        return valor; //123.456.789-10
    }

    public boolean valido() {
        if (valor == null) {
            return false;
        }
        return this.valor.trim().length() <= 11;
    }
}
