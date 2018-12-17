package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.service.ServiceDeProduto;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 13/12/2018, 09:53:31
 */
@Named
@RequestScoped
public class ControladorDeProduto {

    private Produto produto = new Produto();

//    @EJB
    @Inject
    private ServiceDeProduto service;

    public List<Produto> todosOsProdutos(){
        return this.service.todos();
    }
    public String salvar() {
        this.service.salvar(produto);
        this.produto = new Produto();
        return null;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
