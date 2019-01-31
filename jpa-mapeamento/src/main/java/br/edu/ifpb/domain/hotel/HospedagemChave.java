package br.edu.ifpb.domain.hotel;

import java.io.Serializable;
import javax.persistence.Column;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 31/01/2019, 08:15:16
 */
public class HospedagemChave implements Serializable{

    private int hospedes;
    private int hoteis;

    public HospedagemChave() {
    }

    public HospedagemChave(int hospede, int hotel) {
        this.hospedes = hospede;
        this.hoteis = hotel;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.hospedes;
        hash = 89 * hash + this.hoteis;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HospedagemChave other = (HospedagemChave) obj;
        if (this.hospedes != other.hospedes) {
            return false;
        }
        if (this.hoteis != other.hoteis) {
            return false;
        }
        return true;
    }
    
    
}
