package br.com.alura.escola.academico.dominio.aluno;

public class LimiteRegistrosAlcancadoException extends RuntimeException
{

    private static final long serialVersionUID = 1l;

    public LimiteRegistrosAlcancadoException(String msg)
    {
        super(msg);
    }

}
