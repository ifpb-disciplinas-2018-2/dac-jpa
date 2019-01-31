package br.edu.ifpb.app;

import br.edu.ifpb.domain.Aluno;
import br.edu.ifpb.domain.ImageFromFile;
import br.edu.ifpb.domain.Contato;
import br.edu.ifpb.domain.Perfil;
import br.edu.ifpb.domain.Professor;
import br.edu.ifpb.domain.Sexo;
import br.edu.ifpb.domain.Tecnico;
import br.edu.ifpb.domain.chave.Telefone;
import br.edu.ifpb.domain.chave.TelefoneChaveComposta;
import br.edu.ifpb.domain.chave.TelefonePK;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 17/12/2018, 09:57:00
 */
public class Principal {

    private static EntityManager em = Persistence
            .createEntityManagerFactory("DACPU")
            .createEntityManager();

    public static void main(String[] args) {
//        salvarTecnico();
//        criarUmPerfil();
//        salvarAluno();
//        salvarProfessor();
//        listarOsProfessores();
//        salvarUmContato();
//        listarTodosOsContatos();
//        salvarTelefone();
//        listarTelefone();
    }

    private static void salvarUmContato() {

        EntityTransaction transaction = em.getTransaction();
        Contato chaves = Contato.of("florind2a@chaves.com", "Florind2a");

        transaction.begin();
        em.persist(chaves);
        transaction.commit();
    }

    private static void listarTodosOsContatos() {
        // select * from contatotelefonico
        List<Contato> resultList = em.createQuery("SELECT c FROM Contato c", Contato.class).getResultList();

        resultList.forEach(c -> System.out.println(c.getEmail()));
    }

    private static void salvarProfessor() {
        Professor job = Professor.of(1500.09, "1234567", 28, Sexo.MASCULINO);
        job.novoTel("35324165");
        job.novoTel("35324192");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(job);
        transaction.commit();
    }

    private static void salvarAluno() {
        Aluno rogerio = Aluno.of("Rogerio", 8.54);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(rogerio);
        transaction.commit();
    }

    private static void criarUmPerfil() {
//        Perfil chaves = Perfil.of("chaves.gatinho");
//        ImageFromFile file = new ImageFromFile("src/main/resources/imagens/chaves.jpeg");
//        chaves.setFoto(
//                file.toBytes()
//        );

        Perfil chaves = Perfil.of(
                "chaves.gatinho",
                "src/main/resources/imagens/chaves.jpeg"
        );
        chaves.setDescricao("El Chavo del Ocho (Chaves, no Brasil e em Angola, ou O Xavier, em Portugal) é um seriado de televisão mexicano criado por Roberto Gómez Bolaños (conhecido em seu país como Chespirito) produzida pela Televisión Independiente de México (posteriormente chamada de Televisa) e exibida pelo Canal 2 (na época também chamada de XEW-TV, atual Las Estrellas).[5] Exibido pela primeira vez no Canal 8, o roteiro veio de um esquete escrito por Chespirito, onde uma criança de oito anos discutia com um vendedor de balões em um parque (interpretado por Ramón Valdez).[6] Bolaños deu importância ao desenvolvimento dos personagens, aos quais foram distribuídas personalidades distintas. Desde o início, seu criador percebeu que o seriado seria destinado ao público de todas as idades, mesmo se tratando de adultos interpretando crianças.[3] O elenco principal é composto por Roberto Bolaños, María Antonieta de las Nieves, Ramón Valdés, Florinda Meza, Carlos Villagrán, Edgar Vivar, Rubén Aguirre, Angelines Fernández, Horacio Gómez e Raúl Padilla.[7]");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(chaves);
        transaction.commit();
    }

    private static void salvarTecnico() {
//        Tecnico madruga = Tecnico.of("Florinda", "124", null);
        Tecnico madruga = Tecnico.of("Florinda", "124", "1231231231p");
        EntityTransaction transaction = em.getTransaction();

        if (madruga.valido()) {
            transaction.begin();
            em.persist(madruga);
            transaction.commit();
        } else {
            System.out.println("CPF inválido");
        }
    }

    private static void listarOsProfessores() {
        em.createQuery("FROM Professor p", Professor.class)
                .getResultList()
                .stream()
                .flatMap((Professor t) -> t.getTelefones().stream())
                .forEach(p -> System.out.println(p));
    }

    private static void salvarTelefone() {
        Telefone ifpb = new Telefone("83", "3532-4100", "+55");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(ifpb);
        transaction.commit();
    }

    private static void listarTelefone() {
        Aluno find = em.find(Aluno.class, 2);
        System.out.println("rua:" + find.getEndereco().getRua());

//        TelefoneChaveComposta chave = new TelefoneChaveComposta(
//                "83", "3532-4100"
//        );
//Telefone tel = em.find(Telefone.class, chave);
//        System.out.println("numero: "+tel.getNumero());
//        System.out.println("ddd: "+tel.getDdd());
        TelefonePK chave = new TelefonePK(
                "83", "3532-4100"
        );
        Telefone tel = em.find(Telefone.class, chave);
        System.out.println("numero: "+tel.getChave().getNumero());
        System.out.println("ddd: "+tel.getChave().getDdd());
    }
}
