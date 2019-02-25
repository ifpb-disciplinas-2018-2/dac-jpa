package br.edu.ifpb.main;

import br.edu.ifpb.domain.Departamento;
import br.edu.ifpb.domain.Departamento_;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Dependente_;
import br.edu.ifpb.domain.Funcionario;
import br.edu.ifpb.domain.Funcionario_;
import br.edu.ifpb.domain.Gerencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/02/2018, 22:51:20
 */
public class MainConsultasCriteria {

    public static void main(String[] args) {
        EntityManager em = Persistence
            .createEntityManagerFactory("ExemploPU")
            .createEntityManager();
        new IniciadorBancoDeDados(em).dadosIniciais();
//        consultarTodosOsFuncionarios(em);
//        consultarNomeDoDependentes(em);
//        consultarDependenteComId(em);
//        consultarDependentesComCodigoMaior(em);
//        consultarDependentesComIdEntreBETWEEN(em);
//        consultarAbreviacaoDoDepartamentoEGerente(em);
//        consultarNomeDoDepartamentoEGerenteComConstrutor(em);
//        consultarAQuantidadeDeDependentes(em);
//        consultarNomeDoFuncionarioQuePossuiDependentes(em);
//        consultarNomeDoFuncionarioNomeDoDependenteJOIN(em);
//        consultarFuncionarioDependenteIniciandoComLetra(em);
//        consultarNomeDoFuncionarioEQuantidadeDependentes(em);
        consultarDependenteComIdSuperiorAMedia(em);
    }

    private static void consultarTodosOsFuncionarios(EntityManager em) {
        // SELECT f FROM Funcionario f
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Funcionario> createQuery = builder.createQuery(Funcionario.class);
//        Root<Funcionario> root = createQuery.from(Funcionario.class);
//        createQuery.select(root);
        createQuery.select(
            createQuery.from(Funcionario.class)
        );

        TypedQuery<Funcionario> query = em.createQuery(createQuery);
        query.getResultList().forEach(System.out::println);

    }

    private static void consultarNomeDoDependentes(EntityManager em) {
        // SELECT d.nome FROM Dependente d
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<String> createQuery = builder.createQuery(String.class);
        Root<Dependente> root = createQuery.from(Dependente.class);
        createQuery.select(
            root.get("nome")
        );
        TypedQuery<String> query = em.createQuery(createQuery);
        query.getResultList().forEach(System.out::println);
    }

    private static void consultarDependenteComId(EntityManager em) {
        // SELECT d FROM Dependente d WHERE d.codigo= 16
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Dependente> createQuery = builder.createQuery(Dependente.class);
        Root<Dependente> root = createQuery.from(Dependente.class);
        Predicate predicate = builder.equal(
            root.get("codigo"),16
        );
        createQuery.select(root).where(predicate);

        TypedQuery<Dependente> query = em.createQuery(createQuery);
        query.getResultList().forEach(System.out::println);

    }

    //parametro
    private static void consultarDependentesComCodigoMaior(EntityManager em) {
        //SELECT d FROM Dependente d WHERE d.codigo > :id
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Dependente> createQuery = builder.createQuery(Dependente.class);
        Root<Dependente> root = createQuery.from(Dependente.class);
//        Predicate predicate = builder.greaterThan(
//                root.get("codigo"),
//                builder.parameter(int.class, "id")
//        );
        Predicate predicate = builder.greaterThan(
            root.get(Dependente_.codigo), // Dependente_
            builder.parameter(Integer.class,"id")
        );

        createQuery.select(root).where(predicate);

        em.createQuery(createQuery)
            .setParameter("id",16)
            .getResultList()
            .forEach(c -> System.out.println(c));
    }

    private static void consultarAbreviacaoDoDepartamentoEGerente(EntityManager em) {
        //SELECT d.abreviacao, d.gerente, d.id FROM Departamento d
        // d.abreviacao, d.gerente, d.id -> Gerencia
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Gerencia> criteria = builder.createQuery(Gerencia.class);
        Root<Departamento> root = criteria.from(Departamento.class);

        criteria.multiselect(
            root.get("gerente"),
            root.get("abreviacao"),
            root.get(Departamento_.id)
        );

        em.createQuery(criteria)
            .getResultList()
            .forEach(c -> System.out.println(c));

    }

    private static void consultarNomeDoDepartamentoEGerenteComConstrutor(EntityManager em) {
        //SELECT NEW br.edu.ifpb.domain.Gerencia(d.gerente, d.abreviacao, d.id) FROM Departamento d
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Gerencia> criteria = builder.createQuery(Gerencia.class);
        Root<Departamento> root = criteria.from(Departamento.class);
        CompoundSelection<Gerencia> construct = builder.construct(
            Gerencia.class,
            root.get(Departamento_.gerente),
            root.get(Departamento_.abreviacao),
            root.get(Departamento_.id)
        );

        criteria.select(construct);

        em.createQuery(criteria)
            .getResultList()
            .forEach(System.out::println);

    }

    private static void consultarAQuantidadeDeDependentes(EntityManager em) {
        // SELECT COUNT(d) FROM Dependente d
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

//        Root<Dependente> root = criteria.from(Dependente.class);
//        Expression<Long> count = builder.count(root);
//        criteria.select(count);
        criteria.select(
            builder.count(
                criteria.from(Dependente.class)
            )
        );

        em.createQuery(criteria)
            .getResultList()
            .forEach(System.out::println);
    }

    private static void consultarNomeDoFuncionarioQuePossuiDependentes(EntityManager em) {
        // SELECT f.nome FROM Funcionario f WHERE f.dependentes IS NOT EMPTY
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<String> criteria = builder.createQuery(String.class);
        Root<Funcionario> root = criteria.from(Funcionario.class);

        Predicate isNotEmpty = builder.isNotEmpty(
            root.get(Funcionario_.dependentes)
        );
        criteria.select(root.get("nome")).where(isNotEmpty);
        em.createQuery(criteria)
            .getResultList()
            .forEach(System.out::println);

    }

    private static void consultarNomeDoFuncionarioNomeDoDependenteJOIN(EntityManager em) {
        // SELECT f.nome AS nomeFuncionario, d.nome FROM Funcionario f JOIN f.depentendes d
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = builder.createTupleQuery(); // ResultSet
        Root<Funcionario> root = criteria.from(Funcionario.class);
        Join<Funcionario,Dependente> join = root.join(Funcionario_.dependentes);
        CompoundSelection<Tuple> tuple = builder.tuple(//f.nome, d.nome 
            //                root.get("nome").alias("nomeFuncionario"),
            join.getParent().get("nome").alias("nomeFuncionario"), // join.getParent() -> Funcionario
            join.get("nome").alias("nome")
        );

        criteria.select(tuple);
        List<Tuple> resultList = em.createQuery(criteria).getResultList();
        for (Tuple tupla : resultList) {
            String nome = tupla.get("nome",String.class);
            String nomeFuncionario = tupla.get("nomeFuncionario",String.class);
            System.out.println(
                String.format(
                    "Funcionario: %s, Dependente: %s",nomeFuncionario,nome
                )
            );
        }

    }

    private static void consultarDependentesComIdEntreBETWEEN(EntityManager em) {
        // SELECT d FROM Dependente d WHERE d.codigo BETWEEN 1 AND 10
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Dependente> criteria = builder.createQuery(Dependente.class);
        Root<Dependente> root = criteria.from(Dependente.class);

        Predicate between = builder.between(
            root.get("codigo"),1,10
        );
        criteria.select(root).where(between);
        em.createQuery(criteria)
            .getResultList()
            .forEach(System.out::println);

    }

    private static void consultarFuncionarioDependenteIniciandoComLetra(EntityManager em) {
        // SELECT f FROM Funcionario f JOIN f.dependentes d WHERE d.nome like 'm%'
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Funcionario> criteria = builder.createQuery(Funcionario.class);
        Root<Funcionario> root = criteria.from(Funcionario.class);
        ListJoin<Funcionario,Dependente> join = root.join(Funcionario_.dependentes);

        Expression<String> lower = builder.lower(
            join.get("nome")
        );
        Predicate like = builder.like(
            lower,"m%"
        );

        criteria.select(root).where(like);

        em.createQuery(criteria)
            .getResultList()
            .forEach(System.out::println);
    }

    private static void consultarNomeDoFuncionarioEQuantidadeDependentes(EntityManager em) {
        // SELECT f.nome, COUNT(d) FROM Funcionario f LEFT JOIN f.dependentes d GROUP BY f.nome
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = builder.createTupleQuery();
        Root<Funcionario> root = criteria.from(Funcionario.class);
        ListJoin<Funcionario,Dependente> join = root.join(
            Funcionario_.dependentes,JoinType.LEFT
        );
//        Expression<Long> count = builder.count(join);
//
//        CompoundSelection<Tuple> tuple = builder.tuple(
//                root.get("nome").alias("nome"),
//                count.alias("count")
//        );
//
//        criteria.select(tuple)
//                .groupBy(
//                        root.get("nome")
//                );

        criteria.select(
            builder.tuple(
                root.get("nome").alias("nome"),
                builder.count(join).alias("count")
            )
        ).groupBy(
            root.get("nome")
        );

        em.createQuery(criteria)
            .getResultList()
            .forEach(
                tupla -> {
                    String nome = tupla.get("nome",String.class);
                    Long quantidade = tupla.get("count",Long.class);
                    System.out.println(
                        String.format(
                            "Nome: %s %d",nome,quantidade
                        )
                    );
                }
            );
    }

    private static void consultarDependenteComIdSuperiorAMedia(EntityManager em) {
        // SELECT d.nome FROM Dependente d WHERE d.codigo > (SELECT AVG(d.codigo) FROM Dependente d )
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Dependente> criteria = builder.createQuery(Dependente.class);
        Root<Dependente> root = criteria.from(Dependente.class);

        //SubQuery
        Subquery<Double> subquery = criteria.subquery(Double.class);
        Root<Dependente> rootSubQuery = subquery.from(Dependente.class);
//        Expression<Double> avg = builder.avg(
//                rootSubQuery.get("codigo")
//        );
//        subquery.select(avg);
//        
//        Predicate maior = builder.gt(
//                root.get("codigo"), subquery
//        );
//        criteria.select(root).where(maior);
        criteria.select(root)
            .where(
                builder.gt(
                    root.get("codigo"),
                    subquery
                        .select(
                            builder.avg(
                                rootSubQuery.get("codigo")
                            )
                        )
                )
            );

        em.createQuery(criteria)
            .getResultList()
            .forEach(System.out::println);

    }

}
