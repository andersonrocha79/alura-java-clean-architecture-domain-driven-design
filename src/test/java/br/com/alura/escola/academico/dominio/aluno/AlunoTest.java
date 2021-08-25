package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.Cpf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlunoTest
{

    private Aluno aluno;

    @BeforeEach
    void beforeEach()
    {
        this.aluno = new Aluno(new Cpf("123.456.789-00"), "fulano", new Email("anderson@teste.com"));
    }

    @Test
    void deveriaPermitirAdicionar1Telefone()
    {
        this.aluno.adicionarTelefone("11", "111111111");
        Assertions.assertEquals(this.aluno.getTelefones().size(), 1);
    }

    @Test
    void deveriaPermitirAdicionar2Telefones()
    {
        this.aluno.adicionarTelefone("11", "111111111");
        this.aluno.adicionarTelefone("22", "22222222");
        Assertions.assertEquals(this.aluno.getTelefones().size(), 2);
    }

    @Test
    void naoDevePermitirCadastrarMaisQueDoisTelefonesPorAluno()
    {

        Assertions.assertThrows(LimiteRegistrosAlcancadoException.class, () ->
        {

            aluno.adicionarTelefone("11", "111111111");
            aluno.adicionarTelefone("22", "222222222");
            aluno.adicionarTelefone("33", "333333333");
            aluno.adicionarTelefone("44", "444444444");

        });

    }

}
