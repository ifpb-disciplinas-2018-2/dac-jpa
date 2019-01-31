package br.edu.ifpb.main;

import br.edu.ifpb.domain.Departamento;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Endereco;
import br.edu.ifpb.domain.Funcionario;
import br.edu.ifpb.domain.Projeto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/01/2019, 07:55:38
 */
public class MainUnidirecional {

    public static void main(String[] args) {

        Endereco meuEndereco = new Endereco(
                "Minha rua", "Meu bairro", "Minha cidade"
        );

        Funcionario madruga = new Funcionario(
                "Madruga", "123", meuEndereco
        );
        Funcionario florinda = new Funcionario(
                "Florinda", "124", meuEndereco
        );

        Dependente chiquinha = new Dependente(
                "Chiquinha"
        );
        Dependente chaves = new Dependente(
                "Chaves"
        );

        Projeto dac = new Projeto(
                "DAC"
        );
        Projeto pwi = new Projeto(
                "PWI"
        );
        Departamento uninfo = new Departamento(
                "UNINFO"
        );
        
        madruga.adicionar(chaves);
        madruga.adicionar(chiquinha);
        madruga.adicionar(pwi);
        madruga.adicionar(dac);
        madruga.setDepartamento(uninfo);
        florinda.setDepartamento(uninfo);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExemploPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(meuEndereco);
        em.persist(chiquinha);
        em.persist(chaves);
        em.persist(dac);
        em.persist(pwi);
        em.persist(uninfo);
        em.persist(madruga);
        em.persist(florinda);
        em.getTransaction().commit();

    }
}
