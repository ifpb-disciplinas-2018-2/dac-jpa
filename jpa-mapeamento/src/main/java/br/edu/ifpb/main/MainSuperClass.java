package br.edu.ifpb.main;

import br.edu.ifpb.domain.mapped.Analista;
import br.edu.ifpb.domain.mapped.TAE;
import br.edu.ifpb.domain.mapped.Tecnico;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 11:32:04
 */
public class MainSuperClass {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploPU");
        EntityManager em = emf.createEntityManager();

        Tecnico tecnico = new Tecnico(
                "128", "Parreira"
        );
        TAE tae = new TAE(
                "123", "123", "Job"
        );

        Analista analista = new Analista(
                "124", "124", "Chaves"
        );

        em.getTransaction().begin();
        em.persist(analista);
        em.persist(tae);
//        em.persist(tecnico); // Não é uma entidade
        em.getTransaction().commit();

        em.createQuery("FROM Tecnico a", Tecnico.class)
                .getResultList().forEach(
                        a -> System.out.println(a.getNome())
                );

    }

}
