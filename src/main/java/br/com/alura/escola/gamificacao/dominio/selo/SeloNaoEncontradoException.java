package br.com.alura.escola.gamificacao.dominio.selo;

import br.com.alura.escola.shared.dominio.Cpf;

public class SeloNaoEncontradoException extends RuntimeException
{

    private static final long serialVersionUID = 1l;

    public SeloNaoEncontradoException(Cpf cpf)
    {
        super("Selo não encontrado para o Aluno com CPF: " + cpf.getNumero());
    }

}
