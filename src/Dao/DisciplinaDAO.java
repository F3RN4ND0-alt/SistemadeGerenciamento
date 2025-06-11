package Dao;

import Classes.Disciplina;
import Classes.Curso;
import Classes.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    private Connection connection;
    private CursoDAO cursoDAO;
    private ProfessorDAO professorDAO;

    public DisciplinaDAO(Connection connection) {
        this.connection = connection;
        this.cursoDAO = new CursoDAO(connection);
        this.professorDAO = new ProfessorDAO(connection);
    }

   
    public int save(Disciplina disciplina) throws SQLException {
        String sql = "INSERT INTO disciplina (nome, cargaHoraria, id_curso, id_professor) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setInt(3, disciplina.getCurso().getIdCurso());
            stmt.setInt(4, disciplina.getProfessor().getIdUsuario());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                disciplina.setIdDisciplina(id);
                return id;
            }
        }
        return -1;
    }

    
    public void update(Disciplina disciplina) throws SQLException {
        String sql = "UPDATE disciplina SET nome = ?, cargaHoraria = ?, id_curso = ?, id_professor = ? WHERE id_disciplina = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getCargaHoraria());
            stmt.setInt(3, disciplina.getCurso().getIdCurso());
            stmt.setInt(4, disciplina.getProfessor().getIdUsuario());
            stmt.setInt(5, disciplina.getIdDisciplina());
            stmt.executeUpdate();
        }
    }

    
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM disciplina WHERE id_disciplina = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    
    public Disciplina findById(int id) throws SQLException {
        String sql = "SELECT * FROM disciplina WHERE id_disciplina = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToDisciplina(rs);
            }
        }
        return null;
    }

    
    public List<Disciplina> findAll() throws SQLException {
        List<Disciplina> lista = new ArrayList<>();
        String sql = "SELECT * FROM disciplina";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(mapRowToDisciplina(rs));
            }
        }
        return lista;
    }

    
    private Disciplina mapRowToDisciplina(ResultSet rs) throws SQLException {
        Disciplina disciplina = new Disciplina();
        disciplina.setIdDisciplina(rs.getInt("id_disciplina"));
        disciplina.setNome(rs.getString("nome"));
        disciplina.setCargaHoraria(rs.getInt("cargaHoraria"));

        int idCurso = rs.getInt("id_curso");
        Curso curso = cursoDAO.findById(idCurso);
        disciplina.setCurso(curso);

    
        Professor professor = new Professor();
        professor.setIdUsuario(rs.getInt("id_professor"));
        disciplina.setProfessor(professor);

        return disciplina;
    }

}