package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.academico.dominio.aluno.Telefone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TelefoneTest
{

    @Test
    void naoDeveriaCriarTelefoneInvalido()
    {

        // teste para validar telefones inválidos

        assertThrows(IllegalArgumentException.class, () -> new Telefone(null, null) );

        assertThrows(IllegalArgumentException.class, () -> new Telefone("", "") );

        assertThrows(IllegalArgumentException.class, () -> new Telefone("1", "1") );

        assertThrows(IllegalArgumentException.class, () -> new Telefone("11", "1111111") );

        assertThrows(IllegalArgumentException.class, () -> new Telefone("2", "11111111") );

    }

    @Test
    void devePermitirCriarTelefoneValido()
    {

        // teste para permitir cadastro de telefones válidos

        String ddd       = "31";
        String numero    = "991884444";
        Telefone numero1 = new Telefone(ddd, numero);

        assertEquals(ddd, numero1.getDdd());
        assertEquals(numero, numero1.getNumero());

        numero  = "91885555";
        numero1 = new Telefone(ddd, numero);

        assertEquals(ddd, numero1.getDdd());
        assertEquals(numero, numero1.getNumero());

    }

}
