package br.edu.ifpb.main.stored;

import br.edu.ifpb.main.IniciadorBancoDeDados;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 25/02/2019, 14:57:25
 */
public class MainStoredProcedureQuery {

    public static void main(String[] args) {
        EntityManager em = Persistence
            .createEntityManagerFactory("ExemploPU")
            .createEntityManager();

        new IniciadorBancoDeDados(em).dadosIniciais();

//        somar(em);
    }

    public static void somar(EntityManager em) {
        StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery(
            "somar"
        );
        storedProcedure.registerStoredProcedureParameter(
            "primeiro",Double.class,ParameterMode.IN
        );
        storedProcedure.registerStoredProcedureParameter(
            "segundo",Double.class,ParameterMode.IN
        );
        storedProcedure.registerStoredProcedureParameter(
            "resposta",Double.class,ParameterMode.OUT
        );

        storedProcedure.setParameter("primeiro",3.6);
        storedProcedure.setParameter("segundo",6.9);

        storedProcedure.execute();

        Double resposta = (Double) storedProcedure.getOutputParameterValue(
            "resposta"
        );
        System.out.println("A soma foi: " + resposta);
    }

}

    