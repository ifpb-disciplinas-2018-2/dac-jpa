package br.edu.ifpb.domain;

import javax.persistence.Column;
import javax.persistence.SecondaryTable;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/12/2018, 07:45:08
 */
@SecondaryTable(name = "")
public class Pessoa {

    private int id;
    @Column(table = "individuo" )
    private String nome;
    @Column(table = "individuo" )
    private String cpf;
    @Column(table = "endereco" )
    private String rua;
    @Column(table = "endereco" )
    private String cidade;
}

/**
 
 Individuo
 * id | CPF | nome
 
 Endereco
 * id | rua | cidade
 
 */
