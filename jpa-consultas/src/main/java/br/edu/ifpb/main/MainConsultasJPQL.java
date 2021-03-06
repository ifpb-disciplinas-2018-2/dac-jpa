package br.edu.ifpb.main;

import br.edu.ifpb.domain.Departamento;
import br.edu.ifpb.domain.Dependente;
import br.edu.ifpb.domain.Funcionario;
import br.edu.ifpb.domain.FuncionarioNativo;
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
        consultarNomeDoDepartamentoEGerente(em);
//        consultarNomeDoDepartamentoEGerenteComTipo(em);
//        consultarNomeDoFuncionarioQuePossuiDependentes(em);
//        consultarNomeDoFuncionarioNomeDoDependente(em);
//        consultarNomeDoFuncionarioQuantidadeDeDependentes(em);
//        consultarDependentesComIdEntre(em);
//        consultarDependentesComIdFora(em);
//        consultarDependentesComIdEntreBETWEEN(em);
//        consultarDependentesComIdForaBETWEEN(em);
//        consultarDepartamentoSemGerente(em);
//        consultarDepartamentoComGerente(em);
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
//        consultarNomeIdEmpregadosNativeQueryComTipo(em);
//        consultarNomeIdFuncionarioNativeQueryComTipoEntidade(em);
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
                        System.out::println
                );
    }

    /* Selecionar os Departamentos que possuem Gerente */
    private static void consultarDepartamentoComGerente(EntityManager em) {
        //SELECT * FROM Departamento d, Gerente g WHERE g.dep_id=d.id
        String sql = "SELECT d.abreviacao FROM Departamento d LEFT JOIN d.gerente g WHERE g.dep IS NOT NULL";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList()
                .forEach(
                        System.out::println
                );
    }

    /* Selecionar o nome dos Funcionarios que possuem Dependentes */
    private static void consultarFuncionarioPossuiDependente(EntityManager em) {
//         String sql = "SELECT f.nome FROM Funcionario f JOIN f.dependentes d";
        String sql = "SELECT f.nome FROM Funcionario f WHERE f.dependentes IS NOT EMPTY";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList().forEach(System.out::println);
    }

    /* Selecionar o nome dos Funcionarios que possuem Dependentes e o nome do Dependente começa com letra M */
    private static void consultarFuncionarioDependenteIniciandoComLetra(EntityManager em) {
        //SELECT f.nome, d.nome FROM Funcionario f 
        // INNER JOIN Dependente d ON d.funcionario_id = f.ID WHERE d.nome ILIKE 'm%'
        String letra = "m%";
//        String sql = "SELECT UPPER(f.nome) FROM Funcionario f, IN (f.dependentes) d "
//                + " WHERE LOWER(d.nome) LIKE :nome";
        String sql = "SELECT UPPER(f.nome) FROM Funcionario f, Dependente d "
                + " WHERE d MEMBER OF f.dependentes AND LOWER(d.nome) LIKE :nome";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery
                .setParameter("nome", letra)
                .getResultList()
                .forEach(System.out::println);
    }

    /* Selecionar a primeira letra do nome dos Dependentes  */
    private static void consultarPrimeiraLetraNomesDosDependente(EntityManager em) {
        String sql = " SELECT SUBSTRING(d.nome,1,1) FROM Dependente d";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery
                .getResultList()
                //                .forEach(nome-> System.out.println(nome.substring(0, 1)));
                .forEach(System.out::println);
    }

    /* Selecionar o total de Dependentes  */
    private static void consultarNumeroDeTodosOsDependentes(EntityManager em) {
        String sql = " SELECT COUNT(d) FROM Dependente d";
        TypedQuery<Long> createQuery = em.createQuery(sql, Long.class);
        Long total = createQuery.getSingleResult();
        System.out.println("Total: " + total);
    }

    /* Selecionar o nomes dos Funcionarios e quantidade de seus Dependentes, se a quantidade for superior ou igual a 2*/
    private static void consultarNomeDoFuncionarioEQuantidadeDependentes(EntityManager em) {
        String sql = "SELECT f.nome, COUNT(d) FROM Funcionario f LEFT JOIN f.dependentes d"
                + " GROUP BY f.nome HAVING COUNT(d) >=2 ";
        TypedQuery<Object[]> createQuery = em.createQuery(sql, Object[].class);
        createQuery.getResultList().forEach(
                c -> System.out.println(Arrays.toString(c))
        );
    }

    /* Selecionar o nome do Dependente que possui o código superior a média */
    private static void consultarDependenteComIdSuperiorAMedia(EntityManager em) {
        // AVG = 11.2
        String sql = " SELECT d.nome FROM Dependente d WHERE d.codigo > "
                + "(SELECT AVG(d.codigo) FROM Dependente d ) ";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList().forEach(
                System.out::println
        );
    }

    /* Selecionar o nome dos Dependentes SE TODOS os códigos forem superior a dez */
    private static void consultarDependenteSeTodosIdSuperiorADez(EntityManager em) {
//        String sql = " SELECT d.nome FROM Dependente d WHERE 2 <= ALL"
//                + "(SELECT dep.codigo FROM Dependente dep ) ";
        String sql = " SELECT d.nome FROM Dependente d WHERE 10 < ALL"
                + "(SELECT dep.codigo FROM Dependente dep ) ";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList().forEach(
                System.out::println
        );
    }

    /* Selecionar o nome dos Dependentes SE ALGUM dos códigos for superior a dez */
    private static void consultarDependenteSeQualquerIdSuperiorADez(EntityManager em) {
//     String sql = " SELECT d.nome FROM Dependente d WHERE 10 < ANY"
//                + "(SELECT dep.codigo FROM Dependente dep ) ";
        String sql = " SELECT d.nome FROM Dependente d WHERE 10 < SOME"
                + "(SELECT dep.codigo FROM Dependente dep ) ";
        TypedQuery<String> createQuery = em.createQuery(sql, String.class);
        createQuery.getResultList().forEach(
                System.out::println
        );
    }

    /* Atualizar o nome dos Dependentes para Maisculo*/
    private static void atualizarNomeTodosDependentes(EntityManager em) {
        em.getTransaction().begin();
        String sql = " UPDATE Dependente d SET d.nome=UPPER(d.nome)";
        int total = em.createQuery(sql).executeUpdate();
        em.getTransaction().commit();
        System.out.println("total = " + total);
    }

    /* Remover o Dependente com código igual a 2 */
    private static void removerDependenteComId(EntityManager em) {
        em.getTransaction().begin();
        String sql = " DELETE FROM Dependente d WHERE d.codigo=2";
        int total = em.createQuery(sql).executeUpdate();
        em.getTransaction().commit();
        System.out.println("total = " + total);
    }

    /* Selecionar todos os Dependentes  */
    private static void consultarTodosOsDependentesNamedQuery(EntityManager em) {
//        String sql = "SELECT d FROM Dependente d";
        TypedQuery<Dependente> query = em.createNamedQuery("Dependente.todos", Dependente.class);
        query.getResultList()
                .forEach(System.out::println);

    }

    /* Selecionar todos os Dependentes com id superior a 5 */
    private static void consultarOsDependentesComIdNamedQuery(EntityManager em) {
        // SELECT d FROM Dependente d WHERE d.codigo >= 5
        TypedQuery<Dependente> query = em.createNamedQuery("Dependente.id", Dependente.class);
        query.setParameter("id", 17);
        query.getResultList()
                .forEach(System.out::println);
    }

    private static void consultarTodosOsFuncionariosNativeQuery(EntityManager em) {
        String sql = " SELECT * FROM Funcionario";
        // Mapeamento da consulta para a classe Funcionario
        Query createQuery = em.createNativeQuery(sql, Funcionario.class);
        List<Funcionario> resultList = createQuery.getResultList();
        resultList.forEach(
                c -> System.out.println(
                        c
                )
        );
    }

    private static void consultarNomeIdFuncionariosNativeQuery(EntityManager em) {
        String sql = " SELECT id, nome FROM Funcionario";
        // Mapeamento da consulta para a classe Funcionario
        Query createQuery = em.createNativeQuery(sql);
        List<Object[]> resultList = createQuery.getResultList();

        resultList.forEach(
                c -> System.out.println(
                        Arrays.toString(c)
                )
        );
    }

    private static void consultarNomeIdEmpregadosNativeQueryComTipo(EntityManager em) {
        String sql = "SELECT id, nome FROM Funcionario";
        Query query = em.createNativeQuery(sql, "FuncionarioNativoMapping");
        List<FuncionarioNativo> lista = query.getResultList();
        lista.forEach(e -> System.out.println("nome: " + e.getNome()));
    }

    private static void consultarNomeIdFuncionarioNativeQueryComTipoEntidade(EntityManager em) {
        String sql = "SELECT id, nome AS NOMEEMP FROM Funcionario";
        Query query = em.createNativeQuery(sql, "FuncionarioMapping");
        List<Funcionario> lista = query.getResultList();
        lista.forEach(System.out::println);

    }
}
