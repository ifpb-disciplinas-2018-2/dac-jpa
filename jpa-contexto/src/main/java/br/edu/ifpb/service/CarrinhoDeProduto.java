package br.edu.ifpb.service;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.domain.Sacola;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 13/12/2018, 09:55:07
 */
@Stateful
public class CarrinhoDeProduto {

    @PersistenceContext(type = PersistenceContextType.EXTENDED,
                        synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    private Sacola sacola = new Sacola();

    public Produto carregarProduto() {
        this.sacola.setNome("primeira");
        return em.find(Produto.class,2);
    }

    public void atualizar(Produto arroz,String descricao) {
        arroz.setDescricao(descricao);
    }

    public void adicionar(Produto produto) {
        this.sacola.getProdutos().add(produto);
    }

    @Remove
    public void finalizar() {
        em.persist(this.sacola);
        em.joinTransaction();
        System.out.println(sacola);
    }
}
