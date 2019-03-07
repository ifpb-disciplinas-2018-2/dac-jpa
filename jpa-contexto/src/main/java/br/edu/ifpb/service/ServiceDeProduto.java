package br.edu.ifpb.service;

import br.edu.ifpb.domain.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 13/12/2018, 09:55:07
 */
@Stateless
public class ServiceDeProduto {

    @PersistenceContext
    private EntityManager em;

    public void salvar(Produto produto) {
        em.persist(produto);
    }

    public List<Produto> todos() {
        return em.createQuery(
                "FROM Produto p", Produto.class
        ).getResultList();
    }

    public void atualizar(Produto arroz,String descricao) {
        Produto produto = em.find(Produto.class,arroz.getCodigo());
        produto.setDescricao(descricao);
    }
}
