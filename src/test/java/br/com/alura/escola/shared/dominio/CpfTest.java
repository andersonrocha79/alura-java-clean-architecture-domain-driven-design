package br.com.alura.escola.shared.dominio;

import br.com.alura.escola.shared.dominio.Cpf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CpfTest
{

    @Test
    void naoDeveriaCriarCpfInvalido()
    {

        assertThrows(IllegalArgumentException.class, () -> new Cpf(null) );

        assertThrows(IllegalArgumentException.class, () -> new Cpf("") );

        assertThrows(IllegalArgumentException.class, () -> new Cpf("111") );

        assertThrows(IllegalArgumentException.class, () -> new Cpf("123.456.789.00") );


    }

    @Test
    void devePermitirCriarCpfValido()
    {

        String cpf = "001.002.003-04";
        Cpf cpf1 = new Cpf(cpf);

        assertEquals(cpf1.getNumero(), cpf);

    }

}
