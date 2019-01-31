package br.edu.ifpb.app;

import br.edu.ifpb.domain.Produto;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 13/12/2018, 09:36:03
 */
public class Principal {
    public static void main(String[] args) {
        Produto tv = new Produto("TV", 2500.0f, 1);
        EntityManager em = Persistence.createEntityManagerFactory(
                "ProdutoPU"
        ).createEntityManager();
        
        EntityTransaction transaction = em.getTransaction();
        
        transaction.begin();
        em.persist(tv);
        transaction.commit();
    }
}
