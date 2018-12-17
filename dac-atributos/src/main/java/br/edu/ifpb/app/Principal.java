package br.edu.ifpb.app;

import br.edu.ifpb.domain.Aluno;
import br.edu.ifpb.domain.Contato;
import br.edu.ifpb.domain.Perfil;
import br.edu.ifpb.domain.Professor;
import br.edu.ifpb.domain.Tecnico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/12/2018, 09:57:00
 */
public class Principal {

    private static EntityManager em = Persistence
            .createEntityManagerFactory("DACPU")
            .createEntityManager();

    public static void main(String[] args) {
        salvarTecnico();

//        criarUmPerfil();
//        salvarAluno();
//        salvarProfessor();
//        salvarUmContato();
//        listarTodosOsContatos();
    }

    private static void salvarUmContato() {

        EntityTransaction transaction = em.getTransaction();
        Contato chaves = Contato.of("florind2a@chaves.com", "Florind2a");

        transaction.begin();
        em.persist(chaves);
        transaction.commit();
    }

    private static void listarTodosOsContatos() {
        // select * from contatotelefonico
        List<Contato> resultList = em.createQuery("SELECT c FROM Contato c", Contato.class).getResultList();

        resultList.forEach(c -> System.out.println(c.getEmail()));
    }

    private static void salvarProfessor() {
        Professor job = Professor.of(1500.09, "1234567", 28);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(job);
        transaction.commit();
    }

    private static void salvarAluno() {
        Aluno rogerio = Aluno.of("Rogerio", 8.54);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(rogerio);
        transaction.commit();
    }

    private static void criarUmPerfil() {
        Perfil chaves = Perfil.of("chaves.gatinho");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(chaves);
        transaction.commit();
    }

    private static void salvarTecnico() {
        Tecnico madruga = Tecnico.of("Florinda", "124", null);
        EntityTransaction transaction = em.getTransaction();

        if (madruga.valido()) {
            transaction.begin();
            em.persist(madruga);
            transaction.commit();
        }
    }
}
