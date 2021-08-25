package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.evento.Evento;
import br.com.alura.escola.shared.dominio.Cpf;
import br.com.alura.escola.shared.dominio.evento.TipoDeEvento;

import java.time.LocalDateTime;
import java.util.Map;

public class AlunoMatriculado implements Evento
{

    private final Cpf cpfAluno;
    private LocalDateTime momento;

    public AlunoMatriculado(Cpf cpfAluno)
    {
        this.cpfAluno = cpfAluno;
        this.momento  = LocalDateTime.now();
    }

    @Override
    public LocalDateTime momento()
    {
        return this.momento;
    }

    @Override
    public TipoDeEvento tipo()
    {
        return TipoDeEvento.ALUNO_MATRICULADO;
    }

    @Override
    public Map<String, Object> informacoes()
    {
        return Map.of("cpf", cpfAluno);
    }

    public Cpf getCpfAluno()
    {
        return cpfAluno;
    }

}
