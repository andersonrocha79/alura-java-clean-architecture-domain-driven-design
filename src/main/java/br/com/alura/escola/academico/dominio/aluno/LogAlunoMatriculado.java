package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.evento.Evento;
import br.com.alura.escola.shared.dominio.evento.Ouvinte;

import java.time.format.DateTimeFormatter;

// classe ouvinte
public class LogAlunoMatriculado extends Ouvinte
{

    public void reageAo(Evento evento)
    {

        // armazena o momento de geração do evento
        String momento = evento.momento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        // faz o cast do evento e armazena o cpf do aluno
        String cpf = ((AlunoMatriculado) evento).getCpfAluno().getNumero();

        // registra no log
        System.out.println(String.format("Aluno com CPF %s matriculado em %s", cpf, momento));

    }

    @Override
    protected boolean deveProcessar(Evento evento)
    {
        // verifica se o objeto 'evento' é um 'AlunoMatriculado'
        return evento instanceof AlunoMatriculado;
    }


}
