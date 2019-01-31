package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto; // BLOB
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String descricao; //CLOB
    @Temporal(TemporalType.TIME)
    private Date criadoEm; //java.util.Calendar
    @Transient
    private ImageFromFile file;

    public Perfil() {
        this.criadoEm = new Date();
    }

    private Perfil(String profile) {
        this(profile, "");
    }

    private Perfil(String profile, String path) {
        this();
        this.profile = profile;
        this.file = new ImageFromFile(path);
        this.foto = file.toBytes();

    }

    public static Perfil of(String profile) {
        return new Perfil(profile);
    }

    public static Perfil of(String profile, String path) {
        return new Perfil(profile, path);
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

}
