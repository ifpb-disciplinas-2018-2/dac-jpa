package br.edu.ifpb.main;

import br.edu.ifpb.domain.single.Animal;
import br.edu.ifpb.domain.single.Cachorro;
import br.edu.ifpb.domain.single.Gato;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 10:17:31
 */
public class MainSingleTable {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploPU");
        EntityManager em = emf.createEntityManager();

        Animal animal = new Animal(
                "passaro", "pardal"
        );

        Cachorro rex = new Cachorro(
                "vira-lata", "Rex", "vira lata"
        );

        Gato tom = new Gato(
                "certeza", "Tom", "não sei informar"
        );

//        Gato tomcat = new Gato(
//                null, null, null
//        );
        em.getTransaction().begin();
        em.persist(tom);
        em.persist(rex);
        em.persist(animal);
//        em.persist(tomcat);
        em.getTransaction().commit();

        em.createQuery("FROM Animal c", Animal.class).
                getResultList().forEach(
                        c -> System.out.println(c.getNome())
                );

    }

}
