package br.com.alura.escola.academico.dominio.aluno;

/*

Entidades possuem uma identidade única, enquanto VOs são considerados iguais, se todos os seus atributos tiverem valores iguais.

Se dois e-mails possuem o mesmo endereço, podemos considerá-los como o mesmo e-mail.
Já duas pessoas com o nome, altura e idade não necessariamente são a mesma pessoa.

Se a igualdade entre dois objetos de uma classe é verificada através da comparação de todos os seus valores, se trata de um 'Value Object'

modelos de arquitetura

    - Onion (cebola)
    - Hexagonal
    - Clean
    - Clean simplificado (domínio > aplicação > infraestrutura)

Se um aluno tiver mais do que 2 telefones em nosso sistema, essa regra foi violada, logo, o Aluno estará em um estado inválido.
Invariantes nada mais são do que regras de negócio que precisam ser verificadas para garantir sua consistência.

*/

import br.com.alura.escola.shared.dominio.Cpf;

import java.util.ArrayList;
import java.util.List;

// Agregate Root (a raiz de outras classes dependentes)
public class Aluno
{

    // entidade

    private Cpf cpf;         // tipo de classe é uma entidade pq tem um campo que identifica o aluno, que seria o cpf
    private String  nome;
    private Email   email;
    private String  senha;

    private List<Telefone> telefones = new ArrayList<>();

    public void adicionarTelefone(String ddd, String numero)
    {

        // business invariant
        if (this.telefones.size() >= 2)
        {
            throw new LimiteRegistrosAlcancadoException("Número máximo de telefones já atingido.");
        }

        this.telefones.add(new Telefone(ddd, numero));
    }

    public Aluno(Cpf cpf, String nome, Email email)
    {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email.getEndereco();
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }
}
