package br.com.alura.escola.gamificacao.dominio.selo;

import br.com.alura.escola.shared.dominio.Cpf;

import java.util.List;

public interface RepositorioDeSelos
{

    void adicionar(Selo selo);

    List<Selo> selosDoAlunos(Cpf cpf);
}
