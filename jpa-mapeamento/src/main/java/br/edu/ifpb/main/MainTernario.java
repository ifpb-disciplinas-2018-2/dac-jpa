package br.edu.ifpb.main;

import br.edu.ifpb.domain.hotel.Hospedagem;
import br.edu.ifpb.domain.hotel.Hospede;
import br.edu.ifpb.domain.hotel.Hotel;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 31/01/2019, 08:09:53
 */
public class MainTernario {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploPU");
        EntityManager em = emf.createEntityManager();

        Hotel hotel = new Hotel(
                "Hill"
        );
        Hospede chaves = new Hospede(
                "Chaves"
        );
        Hospedagem hospedagem = new Hospedagem(
                hotel, chaves, new Date()
        );

        em.getTransaction().begin();
        em.persist(hotel);
        em.persist(chaves);
        em.persist(hospedagem);
        em.getTransaction().commit();

    }
}
