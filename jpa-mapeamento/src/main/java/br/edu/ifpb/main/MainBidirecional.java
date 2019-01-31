package br.edu.ifpb.main;

import br.edu.ifpb.domain.Departamento;
import br.edu.ifpb.domain.Endereco;
import br.edu.ifpb.domain.Funcionario;
import br.edu.ifpb.domain.Gerente;
import br.edu.ifpb.domain.Projeto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 28/01/2019, 09:46:24
 */
public class MainBidirecional {

    public static void main(String[] args) {
        Gerente gerente = new Gerente(
                "Jo", "123"
        );
        Departamento uninfo = new Departamento(
                "UNINFO"
        );

        Projeto dac = new Projeto(
                "DAC"
        );
        Projeto sd = new Projeto(
                "SD"
        );

        Endereco primeiro = new Endereco(
                "rua", "bairro", "cidade"
        );
        Endereco segundo = new Endereco(
                "rua 1", "bairro", "cidade"
        );
        Funcionario chaves = new Funcionario(
                "CHaves", "134", primeiro
        );
        Funcionario kiko = new Funcionario(
                "Kiko", "1354", segundo
        );
        //bidirecional
        chaves.adicionar(dac);
        chaves.adicionar(sd);
        dac.adicinar(chaves);
        sd.adicinar(chaves);

        kiko.adicionar(dac);
        dac.adicinar(kiko);

        // bidirecional
        gerente.adicionar(sd);
        gerente.adicionar(dac);
        dac.setGerente(gerente);
        sd.setGerente(gerente);

        // bidirecional
        gerente.setDep(uninfo);
        uninfo.setGerente(gerente);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(primeiro);
        em.persist(segundo);
        em.persist(chaves);
        em.persist(kiko);
        em.persist(dac);
        em.persist(sd);
        em.persist(uninfo);
        em.persist(gerente);
        em.getTransaction().commit();
    }

}
