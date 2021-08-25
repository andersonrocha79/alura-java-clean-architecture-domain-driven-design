package br.com.alura.escola.shared.dominio;

public class Cpf
{

    // value object

    private String numero;

    public Cpf(String numero)
    {

        if (numero == null || !numero.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}"))
            throw new IllegalArgumentException("CPF inválido");

        this.numero = numero;

    }

    public String getNumero() {
        return numero;
    }

}
