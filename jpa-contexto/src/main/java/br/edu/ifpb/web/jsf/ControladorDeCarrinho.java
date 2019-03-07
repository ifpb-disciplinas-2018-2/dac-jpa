package br.edu.ifpb.web.jsf;

import br.edu.ifpb.domain.Produto;
import br.edu.ifpb.service.CarrinhoDeProduto;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 28/02/2019, 10:05:28
 */
@Named
@SessionScoped
public class ControladorDeCarrinho implements Serializable {

    @EJB
    private CarrinhoDeProduto carrinho;
    private Produto produto;

    @PostConstruct
    public void init() {
        this.produto = carrinho.carregarProduto();
    }

    public String atualizar() {
        carrinho.atualizar(produto,"arroz, de novo");
        return null;
    }
    public String adicionar() {
        carrinho.adicionar(produto);
        return null;
    }
    public String finalizar() {
        carrinho.finalizar();
        return null;
    }

}
