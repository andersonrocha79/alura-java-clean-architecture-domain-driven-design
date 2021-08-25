package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.Cpf;

public class AlunoNaoEncontradoException extends RuntimeException
{

    private static final long serialVersionUID = 1l;

    public AlunoNaoEncontradoException(Cpf cpf)
    {
        super("Aluno não encontrado com CPF: " + cpf.getNumero());
    }

}
