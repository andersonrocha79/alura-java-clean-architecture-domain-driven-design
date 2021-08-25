package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.academico.dominio.aluno.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest
{

    @Test
    void naoDeveriaCriarEmailComEnderecosInvalidos()
    {

        // teste para validar emails inválidos

        assertThrows(IllegalArgumentException.class, () -> new Email(null) );

        assertThrows(IllegalArgumentException.class, () -> new Email("") );

        assertThrows(IllegalArgumentException.class, () -> new Email("teste@") );

    }


}
