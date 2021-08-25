package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.Cpf;

public class AlunoJaExisteException extends RuntimeException
{

    private static final long serialVersionUID = 1l;

    public AlunoJaExisteException(Cpf cpf)
    {
        super("Já existe aluno com este CPF matriculado. Duplicação não autorizada. CPF: " + cpf.getNumero());
    }

}
