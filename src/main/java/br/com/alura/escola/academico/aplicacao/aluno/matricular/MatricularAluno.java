package br.com.alura.escola.academico.aplicacao.aluno.matricular;

import br.com.alura.escola.academico.dominio.aluno.AlunoMatriculado;
import br.com.alura.escola.shared.dominio.evento.PublicadorDeEventos;
import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.RepositorioDeAlunos;

// serviço que representa a camada de aplicação (use case)
public class MatricularAluno
{

    private final RepositorioDeAlunos repositorio;
    private final PublicadorDeEventos publicador;

    public MatricularAluno(RepositorioDeAlunos repositorio, PublicadorDeEventos publicador)
    {
        // inversão de dependencia (esta classe não instancia os objetos, que devem ser passados no contrutor)
        this.repositorio = repositorio;
        this.publicador  = publicador;
    }

    // padrão COMMAND (classe que realiza um comando)
    public void executa(MatricularAlunoDTO dados)
    {

        // realiza a matrícula do aluno
        Aluno novo = dados.criarAluno();
        repositorio.matricular(novo);

        // dispara o evento no publicador de eventos
        AlunoMatriculado evento = new AlunoMatriculado(novo.getCpf());
        publicador.publicar(evento);

    }

}
