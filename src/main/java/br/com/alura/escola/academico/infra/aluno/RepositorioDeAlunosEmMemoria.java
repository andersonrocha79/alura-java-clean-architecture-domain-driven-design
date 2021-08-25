package br.com.alura.escola.academico.infra.aluno;

import br.com.alura.escola.academico.dominio.aluno.*;
import br.com.alura.escola.shared.dominio.Cpf;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeAlunosEmMemoria implements RepositorioDeAlunos
{

    private List<Aluno> matriculados = new ArrayList<>();

    @Override
    public void matricular(Aluno aluno)
    {

        // dispara o erro caso o aluno já exista no sistema
        if (this.matriculados.stream().filter( a->a.getCpf().getNumero().equals(aluno.getCpf().getNumero())).count() > 0)
        {
            throw new AlunoJaExisteException(aluno.getCpf());
        }

        this.matriculados.add(aluno);
    }

    @Override
    public Aluno buscarPorCpf(Cpf cpf)
    {
        return this
                .matriculados
                .stream()
                .filter(a -> a.getCpf().getNumero().equals(cpf.getNumero())) // retorna stream filtrado
                .findFirst() // retorna optional
                .orElseThrow( () -> new AlunoNaoEncontradoException(cpf)); // retorna Aluno ou dispara erro se não encontrar
    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados()
    {
        return this.matriculados;
    }
}
