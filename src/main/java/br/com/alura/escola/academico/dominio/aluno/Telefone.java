package br.com.alura.escola.academico.dominio.aluno;

public class Telefone
{

    private String ddd;
    private String numero;

    public Telefone(String ddd, String numero)
    {

        if (ddd == null || numero == null)
            throw new IllegalArgumentException("O DDD e o número são obrigatórios");

        if (!ddd.matches("\\d{2}"))
            throw new IllegalArgumentException("O DDD deve ter 2 dígitos");

        if (!numero.matches("\\d{8}|\\d{9}"))
            throw new IllegalArgumentException("O número de telefone deve ter 8 ou 9 dígitos");

        this.ddd = ddd;

        this.numero = numero;

    }

    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }

}
