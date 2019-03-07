package br.edu.ifpb.main;

import br.edu.ifpb.domain.Dependente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 28/02/2019, 09:32:23
 */
public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploPU");
        EntityManager em = emf.createEntityManager();
        new IniciadorBancoDeDados(em).dadosIniciais();
//        em.createQuery("SELECT d FROM Dependente d",Dependente.class)
//            .getResultList().forEach(System.out::println);

        Dependente jose = em.find(Dependente.class,17);

        em.getTransaction().begin();
        jose.setNome("Maria");
//        em.merge(jose);
        em.getTransaction().commit();

        em.createQuery("SELECT d FROM Dependente d",Dependente.class)
            .getResultList().forEach(System.out::println);

    }

}
