package br.edu.ifpb.domain.hotel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 31/01/2019, 08:06:12
 */
@Entity
@IdClass(HospedagemChave.class)
public class Hospedagem implements Serializable {

    @Id
    @Column(name = "hospedes_id", insertable = false, updatable = false)
    private int hospedes; //read-only
    @Id
    @Column(name = "hoteis_id", insertable = false, updatable = false)
    private int hoteis; // read-only
    @Temporal(TemporalType.DATE)
    private Date dataHospedagem;

    @ManyToOne
    @JoinColumn(name = "hoteis_id")
    private Hotel hotel;
    @ManyToOne
    @JoinColumn(name = "hospedes_id")
    private Hospede hospede;

    public Hospedagem() {
    }
    

    public Hospedagem(Hotel hotel, Hospede hospede, Date dataHospedagem) {
        this.dataHospedagem = dataHospedagem;
        this.hotel = hotel;
        this.hospede = hospede;
    }

    public int getHospedes() {
        return hospedes;
    }

    public void setHospedes(int hospedes) {
        this.hospedes = hospedes;
    }

    public int getHoteis() {
        return hoteis;
    }

    public void setHoteis(int hoteis) {
        this.hoteis = hoteis;
    }

    public Date getDataHospedagem() {
        return dataHospedagem;
    }

    public void setDataHospedagem(Date dataHospedagem) {
        this.dataHospedagem = dataHospedagem;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

}
