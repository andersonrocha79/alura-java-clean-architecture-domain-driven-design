package br.com.alura.escola.academico.infra.aluno;

import br.com.alura.escola.academico.dominio.aluno.*;
import br.com.alura.escola.shared.dominio.Cpf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeAlunosComJDBC implements RepositorioDeAlunos
{

    private Connection connection;

    public RepositorioDeAlunosComJDBC(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void matricular(Aluno aluno)
    {

        try
        {

            // inclui o Aluno

            String sql = "INSERT INTO ALUNO VALUES (?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, aluno.getCpf().getNumero());
            ps.setString(2, aluno.getNome());
            ps.setString(3, aluno.getEmail());

            ps.execute();

            // inclui os Telefones

            sql = "INSERT INTO TELEFONE VALUES (?, ?)";

            ps = connection.prepareStatement(sql);

            for (Telefone fone : aluno.getTelefones() )
            {
                ps.setString(1, fone.getDdd());
                ps.setString(2, fone.getNumero());
                ps.execute();
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Aluno buscarPorCpf(Cpf cpf)
    {

        try
        {

            String sql = "SELECT id, nome, email FROM ALUNO WHERE cpf = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, cpf.getNumero());

            ResultSet rs = ps.executeQuery();

            boolean encontrou = rs.next();
            if (!encontrou)
            {
                throw new AlunoNaoEncontradoException(cpf);
            }

            String nome = rs.getString("nome");
            Email email = new Email(rs.getString("email"));
            Aluno encontrado = new Aluno(cpf, nome, email);

            Long id = rs.getLong("id");

            sql = "SELECT ddd, numero FROM TELEFONE WHERE aluno_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String numero = rs.getString("numero");
                String ddd = rs.getString("ddd");
                encontrado.adicionarTelefone(ddd, numero);
            }

            return encontrado;


        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Aluno> listarTodosAlunosMatriculados()
    {

        try
        {

            List<Aluno> lista = new ArrayList<>();

            String sql = "SELECT id, cpf, nome, email FROM ALUNO ORDER BY id";

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {

                // busca os alunos
                Long   id    = rs.getLong("id");
                String nome  = rs.getString("nome");
                Email  email = new Email(rs.getString("email"));
                Cpf    cpf   = new Cpf(rs.getString("cpf"));

                Aluno aluno  = new Aluno(cpf, nome, email);

                // busca os telefones dos alunos

                sql = "SELECT ddd, numero FROM TELEFONE WHERE aluno_id = ?";
                ps = connection.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                while (rs.next())
                {
                    String numero = rs.getString("numero");
                    String ddd = rs.getString("ddd");
                    aluno.adicionarTelefone(ddd, numero);
                }

                lista.add(aluno);

            }

            return lista;

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

}
