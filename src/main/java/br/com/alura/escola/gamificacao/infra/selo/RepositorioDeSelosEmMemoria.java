package br.com.alura.escola.gamificacao.infra.selo;

import br.com.alura.escola.shared.dominio.Cpf;
import br.com.alura.escola.gamificacao.dominio.selo.RepositorioDeSelos;
import br.com.alura.escola.gamificacao.dominio.selo.Selo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDeSelosEmMemoria implements RepositorioDeSelos
{

    private List<Selo> selos = new ArrayList<>();

    @Override
    public void adicionar(Selo selo)
    {
        this.selos.add(selo);
    }

    @Override
    public List<Selo> selosDoAlunos(Cpf cpf)
    {

        return this
                .selos
                .stream()
                .filter(a -> a.getCpfDoAluno().getNumero().equals(cpf.getNumero())) // retorna stream filtrado
                .collect(Collectors.toList());

    }

}
