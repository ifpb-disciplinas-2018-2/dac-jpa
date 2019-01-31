package br.edu.ifpb.domain.chave;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/01/2019, 10:46:48
 */
@Entity
//@IdClass(TelefoneChaveComposta.class)
public class Telefone implements Serializable {

//    @Id
//    private String ddd; // 083
//    @Id
//    private String numero; // 35324100
    private String pais; // +55
    
    @EmbeddedId
// TODO: Não fazer!!!!!!!!!
//    @Embedded
//    @Id
    private TelefonePK chave;

    public Telefone() {
    }

    public Telefone(String ddd, String numero, String pais) {
//        this.ddd = ddd;
//        this.numero = numero;
        this.pais = pais;
        this.chave = new TelefonePK(ddd, numero);
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
//    public String getDdd() {
//        return ddd;
//    }
//
//    public void setDdd(String ddd) {
//        this.ddd = ddd;
//    }
//
//    public String getNumero() {
//        return numero;
//    }
//
//    public void setNumero(String numero) {
//        this.numero = numero;
//    }

    public TelefonePK getChave() {
        return chave;
    }

    public void setChave(TelefonePK chave) {
        this.chave = chave;
    }

}
