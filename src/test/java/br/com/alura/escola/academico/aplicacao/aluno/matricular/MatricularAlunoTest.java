package br.com.alura.escola.academico.aplicacao.aluno.matricular;

import br.com.alura.escola.shared.dominio.evento.PublicadorDeEventos;
import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.AlunoJaExisteException;
import br.com.alura.escola.shared.dominio.Cpf;
import br.com.alura.escola.academico.dominio.aluno.LogAlunoMatriculado;
import br.com.alura.escola.academico.infra.aluno.RepositorioDeAlunosEmMemoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatricularAlunoTest
{

    private PublicadorDeEventos publicador;
    private RepositorioDeAlunosEmMemoria repositorio;

    @BeforeEach
    void beforeEach()
    {

        publicador = new PublicadorDeEventos();
        publicador.adicionar(new LogAlunoMatriculado());

        repositorio = new RepositorioDeAlunosEmMemoria();

    }


    @Test
    void alunoDeveriaSerPersistido()
    {

        MatricularAluno useCase = new MatricularAluno(repositorio, publicador);

        String cpf   = "123.456.789-00";
        String nome  = "Fulano";
        String email = "aluno@email.com.br";

        MatricularAlunoDTO dto = new MatricularAlunoDTO(nome, cpf, email);

        useCase.executa(dto);

        Aluno encontrado = repositorio.buscarPorCpf(new Cpf(cpf));

        Assertions.assertEquals(nome , encontrado.getNome());
        Assertions.assertEquals(cpf  , encontrado.getCpf().getNumero());
        Assertions.assertEquals(email, encontrado.getEmail());

    }

    @Test
    void naoPermiteMatricularAlunoDuplicado()
    {

        MatricularAluno useCase = new MatricularAluno(repositorio, publicador);

        Assertions.assertThrows(AlunoJaExisteException.class, () ->
        {

            useCase.executa(new MatricularAlunoDTO("fulano1", "111.111.111-01", "fulano1@email.com.br"));
            useCase.executa(new MatricularAlunoDTO("fulano2", "111.111.111-01", "fulano2@email.com.br"));

        });


    }

}
