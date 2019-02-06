package br.edu.ifpb.main;

import br.edu.ifpb.domain.table.Carro;
import br.edu.ifpb.domain.table.Celta;
import br.edu.ifpb.domain.table.Fusca;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 10:53:44
 */
public class MainTablePerClass {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploPU");
        EntityManager em = emf.createEntityManager();

        Carro carro = new Carro(
                "Olodum", "carro de mão"
        );
        Fusca fusca = new Fusca(
                89, "Meu fusca", "Fusca 86"
        );
        Celta celta = new Celta(
                2012, "rebaixado no grave", "life"
        );

        em.getTransaction().begin();
        em.persist(celta);
        em.persist(fusca);
        em.persist(carro);
        em.getTransaction().commit();
        
        em.createQuery("FROM Fusca c", Fusca.class).
                getResultList().forEach(
                        c-> System.out.println(c.getNome())
                );
    }
}
