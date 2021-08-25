package br.com.alura.sistema.prompt;

import br.com.alura.escola.academico.dominio.aluno.RepositorioDeAlunos;
import br.com.alura.escola.gamificacao.dominio.selo.RepositorioDeSelos;
import br.com.alura.escola.gamificacao.dominio.selo.Selo;
import br.com.alura.escola.shared.dominio.Cpf;
import br.com.alura.escola.shared.dominio.evento.PublicadorDeEventos;

import br.com.alura.escola.academico.aplicacao.aluno.matricular.MatricularAluno;
import br.com.alura.escola.academico.aplicacao.aluno.matricular.MatricularAlunoDTO;
import br.com.alura.escola.academico.dominio.aluno.LogAlunoMatriculado;
import br.com.alura.escola.academico.infra.aluno.RepositorioDeAlunosEmMemoria;

import br.com.alura.escola.gamificacao.aplicacao.GeraSeloAlunoNovato;
import br.com.alura.escola.gamificacao.infra.selo.RepositorioDeSelosEmMemoria;

/*
    DDD
    Domain Driven Design
    significa literalmente modelar nosso software nos orientando pelo domínio do negócio.
    Aprendemos que os estudos de Clean Architecture e DDD geralmente se complementam;
    Conhecemos o termo Linguagem Ubíqua que consiste em ter uma linguagem onipresente entre a equipe de desenvolvimento e a equipe de negócios

    Bounded Contexts, ou contextos delimitados, são um dos conceitos mais complexos de entender e implementar do estudo do DDD.
    Vale a pena leituras mais aprofundadas para conhecer técnicas que envolvem esse princípio.
    Aqui deixo um breve artigo com uma introdução ao tema: https://martinfowler.com/bliki/BoundedContext.html
    http://www.fabriciorissetto.com/blog/ddd-bounded-context/
    http://www.fabriciorissetto.com/blog/microservices-parte-1/
    http://www.fabriciorissetto.com/blog/microservices-parte-2/
    http://www.fabriciorissetto.com/blog/clean-code/

    https://www.amazon.com.br/Domain-Driven-Design-Eric-Evans/dp/8550800651/


*/

public class MatricularAlunoViaLinhaComando
{

    public static void main(String[] args)
    {

        PublicadorDeEventos publicador = new PublicadorDeEventos();



        RepositorioDeAlunos alunos     = new RepositorioDeAlunosEmMemoria();
        RepositorioDeSelos  selos      = new RepositorioDeSelosEmMemoria();

        MatricularAluno     matricular = new MatricularAluno(alunos, publicador);

        // adiciona no publicador de eventos
        publicador.adicionar(new LogAlunoMatriculado());
        publicador.adicionar(new GeraSeloAlunoNovato(selos));

        // registra o aluno
        matricular.executa(new MatricularAlunoDTO("fulano1", "111.111.111-01", "fulano1@email.com.br"));
        matricular.executa(new MatricularAlunoDTO("fulano2", "111.111.111-02", "fulano2@email.com.br"));
        matricular.executa(new MatricularAlunoDTO("fulano3", "111.111.111-03", "fulano3@email.com.br"));
        matricular.executa(new MatricularAlunoDTO("fulano4", "111.111.111-04", "fulano4@email.com.br"));

        // registra novos selos
        selos.adicionar(new Selo(new Cpf("111.111.111-01"), "Gerente"));
        selos.adicionar(new Selo(new Cpf("111.111.111-02"), "Coordenador"));
        selos.adicionar(new Selo(new Cpf("111.111.111-03"), "Admistrador"));

        System.out.println("*** Lista de Alunos / Selos");

        alunos.listarTodosAlunosMatriculados()
                .stream()
                .forEach(a ->
                {

                    System.out.println("---------------------------------------------------------------------------");

                    // aluno
                    System.out.println(a.getNome() + " - " + a.getCpf().getNumero() + " - " + a.getEmail());

                    System.out.println("Selos:");

                    // selos
                    selos.selosDoAlunos(a.getCpf())
                            .stream()
                            .forEach(s ->
                            {
                                System.out.println(s.getNome());
                            });

                });

    }

}
