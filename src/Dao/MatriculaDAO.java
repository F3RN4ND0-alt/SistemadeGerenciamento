package Dao;

import Classes.Aluno;
import Classes.Curso;
import Classes.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MatriculaDAO {

    private final Connection connection;
    private  AlunoDAO alunoDAO;
    private final CursoDAO cursoDAO;

    public MatriculaDAO(Connection connection) {
        this.connection = connection;
        this.cursoDAO = new CursoDAO(connection);
    }
    public void setAlunoDAO(AlunoDAO alunoDAO){
    	this.alunoDAO = alunoDAO;
    }

    public void save(Matricula matricula) throws SQLException {
        String sql = "INSERT INTO matricula (id_aluno, id_curso, data_matricula) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, matricula.getAluno().getIdUsuario());
            stmt.setInt(2, matricula.getCurso().getIdCurso());
            stmt.setDate(3, new java.sql.Date(matricula.getDataMatricula().getTimeInMillis()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    matricula.setIdMatricula(rs.getInt(1));
                }
            }
        }
    }

    public void update(Matricula matricula) throws SQLException {
        String sql = "UPDATE matricula SET id_curso = ?, data_matricula = ? WHERE id_matricula = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, matricula.getCurso().getIdCurso());
            stmt.setDate(2, new java.sql.Date(matricula.getDataMatricula().getTimeInMillis()));
            stmt.setInt(3, matricula.getIdMatricula());
            stmt.executeUpdate();
        }
    }

    public void delete(int idMatricula) throws SQLException {
        String sql = "DELETE FROM matricula WHERE id_matricula = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMatricula);
            stmt.executeUpdate();
        }
    }
    public void carregarMatriculas(Aluno aluno) throws SQLException {
        List<Matricula> matriculas = new ArrayList<>();

        String sql = "SELECT * FROM matricula WHERE id_aluno = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, aluno.getIdUsuario());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Matricula matricula = new Matricula();
                    matricula.setIdMatricula(rs.getInt("id_matricula"));
                    java.sql.Date dataSql = rs.getDate("data_matricula");
                    Calendar dataMatricula = Calendar.getInstance();
                    dataMatricula.setTime(dataSql);
                    matricula.setDataMatricula(dataMatricula);
                    
                    // Recuperar o curso associado
                    int idCurso = rs.getInt("id_curso");
                    Curso curso = new CursoDAO(connection).findById(idCurso); 
                    matricula.setCurso(curso);

                    matricula.setAluno(aluno); 
                    matriculas.add(matricula);
                }
            }
        }

        aluno.getMatriculas().clear();
        for (Matricula m : matriculas) {
            aluno.adicionarMatricula(m);
            aluno.matricularCurso(m.getCurso());
        }
    }



    public List<Matricula> findAll() throws SQLException {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT * FROM matricula";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Matricula matricula = new Matricula(
                    rs.getInt("id_matricula"),
                    dateToCalendar(rs.getDate("data_matricula"))
                );

                Aluno aluno = alunoDAO.findById(rs.getInt("id_aluno"));
                Curso curso = cursoDAO.findById(rs.getInt("id_curso"));

                matricula.setAluno(aluno);
                matricula.setCurso(curso);

                matriculas.add(matricula);
            }
        }
        return matriculas;
    }

    public List<Matricula> findByAluno(int idAluno, Aluno aluno) throws SQLException {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT * FROM matricula WHERE id_aluno = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Matricula matricula = new Matricula(
                        rs.getInt("id_matricula"),
                        dateToCalendar(rs.getDate("data_matricula"))
                    );

                   
                    matricula.setAluno(aluno);

                    Curso curso = cursoDAO.findById(rs.getInt("id_curso"));
                    matricula.setCurso(curso);

                    matriculas.add(matricula);
                }
            }
        }
        return matriculas;
    }

    private Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}
