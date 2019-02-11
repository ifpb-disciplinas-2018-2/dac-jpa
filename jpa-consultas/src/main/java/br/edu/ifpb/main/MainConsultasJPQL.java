package br.edu.ifpb.main;

import br.edu.ifpb.domain.Departamento;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Funcionario;
import br.edu.ifpb.domain.Gerencia;
import br.edu.ifpb.domain.Gerente;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/02/2019, 22:51:20
 */
public class MainConsultasJPQL {

    public static void main(String[] args) {
        EntityManager em = Persistence
                .createEntityManagerFactory("ExemploPU")
                .createEntityManager();
        new IniciadorBancoDeDados(em).dadosIniciais();

//        consultarTodosOsFuncionarios(em);
//        consultarDepartamentoComId(em);
//        consultarDepartamentoComIdParametros(em);
//        consultarDependentesComPaginacao(em);
//        consultarNomeDoDependentes(em);
//        consultarNomeDoDepartamentoEGerente(em);
//        consultarNomeDoDepartamentoEGerenteComTipo(em);
//        consultarNomeDoFuncionarioQuePossuiDependentes(em);
//        consultarNomeDoFuncionarioNomeDoDependente(em);
//        consultarNomeDoFuncionarioQuantidadeDeDependentes(em);
//        consultarDependentesComIdEntre(em);
//        consultarDependentesComIdFora(em);
//        consultarDependentesComIdEntreBETWEEN(em);
//        consultarDependentesComIdForaBETWEEN(em);
//        consultarDepartamentoSemGerente(em);
        consultarDepartamentoComGerente(em);
//        consultarFuncionarioPossuiDependente(em);
//        consultarFuncionarioDependenteIniciandoComLetra(em);
//        consultarPrimeiraLetraNomesDosDependente(em);
//        consultarNumeroDeTodosOsDependentes(em);
//        consultarNomeDoFuncionarioEQuantidadeDependentes(em);
//        consultarDependenteComIdSuperiorAMedia(em);
//        consultarDependenteSeTodosIdSuperiorADez(em);
//        consultarDependenteSeQualquerIdSuperiorADez(em);
//        atualizarNomeTodosDependentes(em);
//        removerDependenteComId(em);
//        consultarTodosOsDependentesNamedQuery(em);
//        consultarOsDependentesComIdNamedQuery(em);
//        consultarTodosOsFuncionariosNativeQuery(em);
//        consultarNomeIdFuncionariosNativeQuery(em);
    }

    /* Selecionar todos os Funcionarios */
    private static void consultarTodosOsFuncionarios(EntityManager em) {
        //select * From funcionario f -- tabelas e colunas
        String sql = "SELECT f FROM Funcionario f"; // objetos e atributos
//        Query query = em.createQuery(sql);
//        List resultList = query.getResultList();
//        resultList.forEach(
//                f -> System.out.println(f)
//        );
        TypedQuery<Funcionario> query = em.createQuery(sql, Funcionario.class);
        List<Funcionario> resultList = query.getResultList();
        resultList.forEach(
                f -> System.out.println(f.getDependentes())
        );
    }

    /* Selecionar o Departamento com id igual 6 */
    private static void consultarDepartamentoComId(EntityManager em) {
        //select * from departamento d where d.id = 6
        String sql = "SELECT d FROM Departamento d WHERE d.id = 6";
        Query query = em.createQuery(sql);
        Departamento d = (Departamento) query.getSingleResult();
        System.out.println(d.getAbreviacao());
    }

    /* Selecionar o Departamento com id igual a um determinado parametro */
    private static void consultarDepartamentoComIdParametros(EntityManager em) {
//        String id = "6 OR 1=1"; //sql injection        
//        String sql = "SELECT d FROM Departamento d WHERE d.id = " + id + "";
//        TypedQuery<Departamento> query = em.createQuery(sql, Departamento.class);
//        Departamento d = (Departamento) query.getSingleResult();
//        System.out.println(d.getAbreviacao());

//        int id = 6;
////        String sql = "SELECT d FROM Departamento d WHERE d.id = 6 AND d.abreviacao='UNINFO'";
//        String sql = "SELECT d FROM Departamento d WHERE d.id = ?1 AND d.abreviacao= ?2 ";
//        TypedQuery<Departamento> query = em.createQuery(sql, Departamento.class);
//        query.setParameter(1, id);
//        query.setParameter(2, "UNINFO");
//        query.getResultList().forEach(f -> System.out.println(f.getAbreviacao()));
        int id = 6;
//        String sql = "SELECT d FROM Departamento d WHERE d.id = 6 AND d.abreviacao='UNINFO'";
        String sql = "SELECT d FROM Departamento d WHERE d.id = :codigo AND d.abreviacao= :abrev ";
        TypedQuery<Departamento> query = em.createQuery(sql, Departamento.class);
        query.setParameter("codigo", id);
        query.setParameter("abrev", "UNINFO");
        query.getResultList().forEach(f -> System.out.println(f.getAbreviacao()));

    }

    /* Selecionar os Dependentes por página */
    private static void consultarDependentesComPaginacao(EntityManager em) {
        // select * from dependente
        String sql = "SELECT d FROM Dependente d ORDER BY d.codigo ASC";
        /*
        Chiquinha
        Godiles
        Mariana
        jose
        Tulio
         */
        int inicio = 0;
        int porPagina = 3;
        int totalPaginas = 5 / porPagina + 1;
        TypedQuery<Dependente> dependente = em.createQuery(sql, Dependente.class);

        for (int i = 0; i < totalPaginas; i++) {

            System.out.println(
                    String.format("--- Página %d --", i + 1)
            );
            dependente.setMaxResults(porPagina)
                    //                    .setFirstResult(i*porPagina);
                    .setFirstResult(inicio);
            inicio += porPagina;
            dependente.getResultList()
                    .forEach(f -> System.out.println(f.getNome()));

        }

    }

    /* Selecionar o nome de todos os Dependentes*/
    private static void consultarNomeDoDependentes(EntityManager em) {
        //SELECT d.nome FROM Dependente d
        String sql = "SELECT d.nome FROM Dependente d";
        TypedQuery<String> query = em.createQuery(sql, String.class);
        query.getResultList().forEach(
                f -> System.out.println(f)
        );
    }

    /* Selecionar a abreviação do Departamento e o seu Gerente */
    private static void consultarNomeDoDepartamentoEGerente(EntityManager em) {
        //SELECT nome FROM Dependentes
        String sql = "SELECT d.abreviacao, d.gerente FROM Departamento d";
        Query query = em.createQuery(sql); // List<Object[]>
//        List resultList = query.getResultList();
//        for (Object object : resultList) {
//            Object[] objectos = (Object[]) object;
//            System.out.println(Arrays.toString(objectos));
//            /*
//            [UNINFO, br.edu.ifpb.domain.Gerente@350d3f4d]
//            [UNIND, null]
//            */
//        }
        List<Object[]> list = query.getResultList();

        for (Object[] object : list) {
//            System.out.println(Arrays.toString(object));
            System.out.println(object[0] + " - " + object[1]);

            Gerente gerente = (Gerente) object[1];
            if (gerente != null) {
                System.out.println(gerente.getCpf());
            }
        }
    }

    /* Selecionar a abreviação do Departamento e o seu Gerente usando um Tipo específico*/
    private static void consultarNomeDoDepartamentoEGerenteComTipo(EntityManager em) {
        //SELECT nome FROM Dependentes
        String sql = "SELECT NEW br.edu.ifpb.domain.Gerencia(d.gerente, d.abreviacao, d.id) "
                + " FROM Departamento d";
        TypedQuery<Gerencia> query = em.createQuery(sql, Gerencia.class); // List<Object[]>
        query.getResultList()
                .forEach(
                        f -> System.out.println(
                                f.getId() + " - " + f.getDepartamento() + " - " + f.getGerente()
                        )
                );
//        List<Object[]> list = query.getResultList();
//        List<Gerencia> resultado = new ArrayList<>();
//        for (Object[] objects : list) {
//            resultado.add(
//                    new Gerencia(
//                            (Gerente) objects[0],
//                            String.valueOf(objects[1])
//                    )
//            );
//        }
//        List<Gerencia> resultado = list.stream()
//                .map(
//                        (Object[] o) -> new Gerencia(
//                                (Gerente) o[0],
//                                String.valueOf(o[1])
//                        ))
//                .collect(Collectors.toList());
//        resultado.forEach(f -> System.out.println(f.getDepartamento() + " - " + f.getGerente()));
    }

    /* Selecionar o nomes dos Funcionarios que possuem Dependentes */
    private static void consultarNomeDoFuncionarioQuePossuiDependentes(EntityManager em) {
        //SELECT * FROM Funcionario f INNER JOIN dependente d  ON d.funcionario_id=f.id 
        String sql = "SELECT DISTINCT(f.nome) FROM Funcionario f, IN (f.dependentes) d";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList()
                .forEach(
                        System.out::println
                );

    }

    /* Selecionar o nomes dos Funcionarios e dos respectivos Dependentes */
    private static void consultarNomeDoFuncionarioNomeDoDependente(EntityManager em) {
        //SELECT * FROM Funcionario f INNER JOIN dependente d  ON d.funcionario_id=f.id 
        String sql = "SELECT f.nome, d.nome FROM Funcionario f JOIN f.dependentes d";
        Query createQuery = em.createQuery(sql);
        List<Object[]> resultList = createQuery.getResultList();
        resultList.forEach(
                c -> System.out.println(
                        Arrays.toString(c)
                )
        );
    }

    /* Selecionar o nomes dos Funcionarios e quantidade de seus Dependentes */
    private static void consultarNomeDoFuncionarioQuantidadeDeDependentes(EntityManager em) {
        //SELECT f.nome, COUNT(d) FROM Funcionario f INNER JOIN dependente d ON d.funcionario_id=f.id  GROUP BY f.nome 
        String sql = "SELECT f.nome, COUNT(d) FROM Funcionario f LEFT JOIN f.dependentes d"
                + " GROUP BY f.nome";
        Query createQuery = em.createQuery(sql);
        List<Object[]> resultList = createQuery.getResultList();
        resultList.forEach(
                c -> System.out.println(
                        Arrays.toString(c)
                )
        );
    }

    /* Selecionar o nome dos Dependentes que possuem id entre 15 e 17*/
    private static void consultarDependentesComIdEntre(EntityManager em) {
        // SELECT * FROM Dependente d WHERE d.codigo>=15 AND d.codigo<=17
        String sql = "SELECT d.nome FROM Dependente d WHERE d.codigo>=15 AND d.codigo<=15+2";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList()
                .forEach(
                        System.out::println // ....
                );
    }

    /* Selecionar o nome dos Dependentes que possuem id fora do intervalo de 15 a 17*/
    private static void consultarDependentesComIdFora(EntityManager em) {
        // SELECT * FROM Dependente d WHERE d.codigo>=15 AND d.codigo<=17
        String sql = "SELECT d.nome FROM Dependente d WHERE d.codigo<15 OR d.codigo>17";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList()
                .forEach(
                        System.out::println // ....
                );
    }

    /* Selecionar o nome dos Dependentes que possuem id entre 15 e 17, usando a clausa BETWEEN*/
    private static void consultarDependentesComIdEntreBETWEEN(EntityManager em) {
        // SELECT * FROM Dependente d WHERE codigo BETWEEN 15 and 17
        String sql = "SELECT d.nome FROM Dependente d WHERE d.codigo BETWEEN 15 AND 17";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList()
                .forEach(
                        System.out::println // ....
                );
    }

    /* Selecionar o nome dos Dependentes que possuem id fora do intervalo de 15 a 17, usando BETWEEN*/
    private static void consultarDependentesComIdForaBETWEEN(EntityManager em) {
        // SELECT d.nome FROM Dependente d WHERE d.codigo<15 OR d.codigo>17
        String sql = "SELECT d.nome FROM Dependente d WHERE d.codigo NOT BETWEEN 15 AND 17";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList()
                .forEach(
                        System.out::println // ....
                );
    }

    /* Selecionar os Departamentos que não possuem Gerente */
    private static void consultarDepartamentoSemGerente(EntityManager em) {
        //SELECT * FROM Departamento d, Gerente g WHERE g.dep_id<>d.id
//        String sql = "SELECT d.abreviacao FROM Departamento d WHERE d.gerente IS NULL";
        String sql = "SELECT d.abreviacao FROM Departamento d LEFT JOIN d.gerente g WHERE g.dep IS NULL";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList()
                .forEach(
                        System.out::println // ....
                );
    }

    /* Selecionar os Departamentos que possuem Gerente */
    private static void consultarDepartamentoComGerente(EntityManager em) {
        //SELECT * FROM Departamento d, Gerente g WHERE g.dep_id=d.id
        String sql = "SELECT d.abreviacao FROM Departamento d LEFT JOIN d.gerente g WHERE g.dep IS NOT NULL";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList()
                .forEach(
                        System.out::println // ....
                );
    }

}
