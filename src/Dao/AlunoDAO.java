package Dao;

import Classes.Aluno;
import Classes.Curso;
import Enums.Permissao;
import Enums.Sexo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlunoDAO extends UsuarioDAO<Aluno> {
    private final TelefoneDAO telefoneDAO;
    private final EnderecoDAO enderecoDAO;
    private final CursoDAO cursoDAO;
    private MatriculaDAO matriculaDAO; 

    public AlunoDAO(Connection connection) {
        super(connection);
        this.telefoneDAO = new TelefoneDAO(connection);
        this.enderecoDAO = new EnderecoDAO(connection);
        this.cursoDAO = new CursoDAO(connection);
        this.matriculaDAO = new MatriculaDAO(connection);
    }

    public void setMatriculaDAO(MatriculaDAO matriculaDAO) {
        this.matriculaDAO = matriculaDAO;
    }

    public void save(Aluno aluno) throws SQLException {
        int telefoneId = telefoneDAO.save(aluno.getTelefone());
        int enderecoId = enderecoDAO.save(aluno.getEndereco());

        String sqlUsuario = "INSERT INTO usuario (nomeUsuario, senha, cpf, sexo, dataNascimento, email, telefone_id, endereco_id, ativo, permissao) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
            preencherStatementUsuario(stmt, aluno, telefoneId, enderecoId);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);
                    aluno.setIdUsuario(idUsuario);
                    salvarAluno(idUsuario);
                }
            }
        }
    }

    public void update(Aluno aluno) throws SQLException {
        telefoneDAO.update(aluno.getTelefone().getIdTelefone(), aluno.getTelefone());
        enderecoDAO.update(aluno.getEndereco().getIdEndereco(), aluno.getEndereco());

        String sqlUsuario = "UPDATE usuario SET nomeUsuario = ?, senha = ?, cpf = ?, sexo = ?, dataNascimento = ?, email = ?, telefone_id = ?, endereco_id = ?, ativo = ?, permissao = ? WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sqlUsuario)) {
            preencherStatementUsuario(stmt, aluno, aluno.getTelefone().getIdTelefone(), aluno.getEndereco().getIdEndereco());
            stmt.setInt(11, aluno.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    public void delete(Integer idUsuario) throws SQLException {
        int telefoneId = -1, enderecoId = -1;

        String select = "SELECT telefone_id, endereco_id FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(select)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    telefoneId = rs.getInt("telefone_id");
                    enderecoId = rs.getInt("endereco_id");
                }
            }
        }
        
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM matricula WHERE id_aluno = ?")) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }

        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM aluno WHERE id_usuario = ?")) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }

        try (PreparedStatement stmt = connection.prepareStatement("UPDATE usuario SET telefone_id = NULL, endereco_id = NULL WHERE id_usuario = ?")) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }

        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM usuario WHERE id_usuario = ?")) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }
       

        telefoneDAO.delete(telefoneId);
        enderecoDAO.delete(enderecoId);
    }

    public Aluno findById(Integer id) throws SQLException {
        String sql = "SELECT u.* FROM usuario u JOIN aluno a ON u.id_usuario = a.id_usuario WHERE u.id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = mapRowToUsuario(rs);
                    // Carrega as matrículas e cursos associados
                    matriculaDAO.carregarMatriculas(aluno);
                    aluno.getCursosMatriculados();
                    return aluno;
                }
            }
        }
        return null;
    }
    public List<Curso> buscarCursosDoAluno(int idAluno) throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT c.* FROM curso c INNER JOIN aluno_curso ac ON c.id = ac.id_curso WHERE ac.id_aluno = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idAluno);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Curso curso = new Curso();
            curso.setIdCurso(rs.getInt("id_curso"));
            curso.setNome(rs.getString("nomeCurso"));
            cursos.add(curso);
        }
        return cursos;
    }


    public Aluno mapRowToUsuario(ResultSet rs) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setIdUsuario(rs.getInt("id_usuario"));
        aluno.setNomeUsuario(rs.getString("nomeUsuario"));
        aluno.setSenha(rs.getString("senha"));
        aluno.setCpf(rs.getString("cpf"));
        aluno.setSexo(Sexo.valueOf(rs.getString("sexo")));

        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(rs.getDate("dataNascimento"));
        aluno.setDataNascimento(dataNascimento);

        aluno.setEmail(rs.getString("email"));
        aluno.setTelefone(telefoneDAO.buscarPorId(rs.getInt("telefone_id")));
        aluno.setEndereco(enderecoDAO.buscarPorId(rs.getInt("endereco_id")));
        aluno.setAtivo(rs.getBoolean("ativo"));
        aluno.setPermissao(Permissao.valueOf(rs.getString("permissao")));
        return aluno;
    }

    private void preencherStatementUsuario(PreparedStatement stmt, Aluno aluno, int telefoneId, int enderecoId) throws SQLException {
        stmt.setString(1, aluno.getNomeUsuario());
        stmt.setString(2, aluno.getSenha());
        stmt.setString(3, aluno.getCpf());
        stmt.setString(4, aluno.getSexo().name());
        stmt.setDate(5, new java.sql.Date(aluno.getDataNascimento().getTimeInMillis()));
        stmt.setString(6, aluno.getEmail());
        stmt.setInt(7, telefoneId);
        stmt.setInt(8, enderecoId);
        stmt.setBoolean(9, aluno.getAtivo());
        stmt.setString(10, aluno.getPermissao().name());
    }

    private void salvarAluno(int idUsuario) throws SQLException {
        String sql = "INSERT INTO aluno (id_usuario) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }
    }
}
