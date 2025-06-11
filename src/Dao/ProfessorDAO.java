package Dao;

import Classes.Curso;
import Classes.Disciplina;
import Classes.Professor;
import Enums.Permissao;
import Enums.Sexo;
import Enums.Especialidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProfessorDAO extends UsuarioDAO<Professor> {

    private final TelefoneDAO telefoneDAO;
    private final EnderecoDAO enderecoDAO;
    private final CursoDAO cursoDAO;

    public ProfessorDAO(Connection connection) {
        super(connection);
        this.telefoneDAO = new TelefoneDAO(connection);
        this.enderecoDAO = new EnderecoDAO(connection);
        this.cursoDAO = new CursoDAO(connection);
    }

    public void save(Professor professor) throws SQLException {
        int telefoneId = telefoneDAO.save(professor.getTelefone());
        int enderecoId = enderecoDAO.save(professor.getEndereco());

        String sqlUsuario = """
            INSERT INTO usuario (nomeUsuario, senha, cpf, sexo, dataNascimento, email, telefone_id, endereco_id, ativo, permissao)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
            preencherStatementUsuario(stmt, professor, telefoneId, enderecoId);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt(1);
                    professor.setIdUsuario(idUsuario);

                    inserirProfessor(idUsuario, professor.getEspecialidade());
                    inserirDisciplinasRelacionadas(idUsuario, professor.getDisciplinas());
                }
            }
        }
    }

    public void update(Professor professor) throws SQLException {
        telefoneDAO.update(professor.getTelefone().getIdTelefone(), professor.getTelefone());
        enderecoDAO.update(professor.getEndereco().getIdEndereco(), professor.getEndereco());

        String sqlUsuario = """
            UPDATE usuario SET nomeUsuario = ?, senha = ?, cpf = ?, sexo = ?, dataNascimento = ?, email = ?, telefone_id = ?, endereco_id = ?, ativo = ?, permissao = ?
            WHERE id_usuario = ?
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sqlUsuario)) {
            preencherStatementUsuario(stmt, professor,
                    professor.getTelefone().getIdTelefone(),
                    professor.getEndereco().getIdEndereco()
            );
            stmt.setInt(11, professor.getIdUsuario());
            stmt.executeUpdate();
        }

        atualizarEspecialidade(professor);
        atualizarDisciplinasRelacionadas(professor);
    }

    public void delete(Integer idUsuario) throws SQLException {
        int telefoneId = -1, enderecoId = -1;

        try (PreparedStatement stmt = connection.prepareStatement("SELECT telefone_id, endereco_id FROM usuario WHERE id_usuario = ?")) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    telefoneId = rs.getInt("telefone_id");
                    enderecoId = rs.getInt("endereco_id");
                }
            }
        }

        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM professor_disciplina WHERE id_professor = ?")) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }

        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM professor WHERE id_usuario = ?")) {
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

    public Professor findById(Integer idUsuario) throws SQLException {
        Professor professor = null;

        String sql = "SELECT u.*, p.especialidade FROM usuario u JOIN professor p ON u.id_usuario = p.id_usuario WHERE u.id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                professor = mapRowToUsuario(rs);
                List<Disciplina> disciplinas = buscarDisciplinasPorProfessor(idUsuario, professor);
                professor.setDisciplinas(disciplinas);
            }
        }

        return professor;
    }

    private void preencherStatementUsuario(PreparedStatement stmt, Professor professor, int telefoneId, int enderecoId) throws SQLException {
        stmt.setString(1, professor.getNomeUsuario());
        stmt.setString(2, professor.getSenha());
        stmt.setString(3, professor.getCpf());
        stmt.setString(4, professor.getSexo().name());
        stmt.setDate(5, new java.sql.Date(professor.getDataNascimento().getTimeInMillis()));
        stmt.setString(6, professor.getEmail());
        stmt.setInt(7, telefoneId);
        stmt.setInt(8, enderecoId);
        stmt.setBoolean(9, professor.getAtivo());
        stmt.setString(10, professor.getPermissao().name());
    }

    private void inserirProfessor(int idUsuario, Especialidade especialidade) throws SQLException {
        String sql = "INSERT INTO professor (id_usuario, especialidade) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setString(2, especialidade.name());
            stmt.executeUpdate();
        }
    }

    private void atualizarEspecialidade(Professor professor) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE professor SET especialidade = ? WHERE id_usuario = ?")) {
            stmt.setString(1, professor.getEspecialidade().name());
            stmt.setInt(2, professor.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    private void atualizarDisciplinasRelacionadas(Professor professor) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM professor_disciplina WHERE id_professor = ?")) {
            stmt.setInt(1, professor.getIdUsuario());
            stmt.executeUpdate();
        }

        inserirDisciplinasRelacionadas(professor.getIdUsuario(), professor.getDisciplinas());
    }

    private void inserirDisciplinasRelacionadas(int idProfessor, List<Disciplina> disciplinas) throws SQLException {
        if (disciplinas == null || disciplinas.isEmpty()) return;

        String sql = "INSERT INTO professor_disciplina (id_professor, id_disciplina) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Disciplina d : disciplinas) {
                stmt.setInt(1, idProfessor);
                stmt.setInt(2, d.getIdDisciplina());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private List<Disciplina> buscarDisciplinasPorProfessor(int idProfessor, Professor professor) throws SQLException {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = """
            SELECT d.* 
            FROM disciplina d 
            JOIN professor_disciplina pd ON d.id_disciplina = pd.id_disciplina 
            WHERE pd.id_professor = ?
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProfessor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setIdDisciplina(rs.getInt("id_disciplina"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setCargaHoraria(rs.getInt("cargaHoraria"));

                int idCurso = rs.getInt("id_curso");
                Curso curso = cursoDAO.findById(idCurso);
                disciplina.setCurso(curso);
                disciplina.setProfessor(professor);

                disciplinas.add(disciplina);
            }
        }

        return disciplinas;
    }

    
    public Professor mapRowToUsuario(ResultSet rs) throws SQLException {
        Professor professor = new Professor();
        professor.setIdUsuario(rs.getInt("id_usuario"));
        professor.setNomeUsuario(rs.getString("nomeUsuario"));
        professor.setSenha(rs.getString("senha"));
        professor.setCpf(rs.getString("cpf"));
        professor.setSexo(Sexo.valueOf(rs.getString("sexo")));

        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(rs.getDate("dataNascimento"));
        professor.setDataNascimento(dataNascimento);

        professor.setEmail(rs.getString("email"));
        professor.setTelefone(telefoneDAO.buscarPorId(rs.getInt("telefone_id")));
        professor.setEndereco(enderecoDAO.buscarPorId(rs.getInt("endereco_id")));
        professor.setAtivo(rs.getBoolean("ativo"));
        professor.setPermissao(Permissao.valueOf(rs.getString("permissao")));
        professor.setEspecialidade(Especialidade.valueOf(rs.getString("especialidade")));

        return professor;
    }
}

