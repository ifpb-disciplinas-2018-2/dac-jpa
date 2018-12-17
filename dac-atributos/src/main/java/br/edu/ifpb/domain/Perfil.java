package br.edu.ifpb.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/12/2018, 10:59:15
 */
@Entity
@TableGenerator(
        name = "table_name",
        table = "sequence",
//        table = "minhatabela",
        initialValue = 5,
        allocationSize = 3,
        pkColumnValue = "seq_minha_tabela"
)
public class Perfil implements Serializable {
    @Id
    @GeneratedValue(generator = "table_name", strategy = GenerationType.TABLE)
    private int codigo;
    private String profile;
//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    private byte[] foto; // BLOB
//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    private String descricao; //CLOB
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date criadoEm;

    public Perfil() {
    }

    private Perfil(String profile) {
        this.profile = profile;
    }
    public static Perfil of(String profile) {
        return new Perfil(profile);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    
}