package br.edu.ifpb.main;

import br.edu.ifpb.domain.joined.Aluno;
import br.edu.ifpb.domain.joined.Pessoa;
import br.edu.ifpb.domain.joined.Professor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 04/02/2019, 11:12:19
 */
public class MainJoined {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploPU");
        EntityManager em = emf.createEntityManager();

        Pessoa pessoa = new Pessoa(
                "123", "Chaves"
        );

        Aluno aluno = new Aluno(
                "123", "124", "Godiles"
        );

        Professor professor = new Professor(
                "125", "125", "Girafales"
        );

        em.getTransaction().begin();
        em.persist(pessoa);
        em.persist(aluno);
        em.persist(professor);
        em.getTransaction().commit();

        em.createQuery("FROM Professor p", Professor.class)
                .getResultList().forEach(
                        p -> System.out.println(p.getNome())
                );

    }

}
