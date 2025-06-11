package Dao;

import Classes.Curso;
import Enums.ModalidadeCurso;
import Enums.NivelCurso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private Connection connection;

    public CursoDAO(Connection connection) {
        this.connection = connection;
    }


    public int save(Curso curso) throws SQLException {
        String sql = "INSERT INTO curso (nome, descricao, nivel, modalidade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricaoCurso());
            stmt.setString(3, curso.getNivel().name());
            stmt.setString(4, curso.getModalidade().name());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                curso.setIdCurso(id);
                return id;
            }
        }
        return -1;
    }

   
    public void update(Curso curso) throws SQLException {
        String sql = "UPDATE curso SET nome = ?, descricao = ?, nivel = ?, modalidade = ? WHERE id_curso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricaoCurso());
            stmt.setString(3, curso.getNivel().name());
            stmt.setString(4, curso.getModalidade().name());
            stmt.setInt(5, curso.getIdCurso());
            stmt.executeUpdate();
        }
    }

   
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM curso WHERE id_curso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

 
    public Curso findById(int id) throws SQLException {
        String sql = "SELECT * FROM curso WHERE id_curso = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToCurso(rs);
            }
        }
        return null;
    }

   
    public List<Curso> findAll() throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                cursos.add(mapRowToCurso(rs));
            }
        }
        return cursos;
    }

    
    private Curso mapRowToCurso(ResultSet rs) throws SQLException {
        Curso curso = new Curso();
        curso.setIdCurso(rs.getInt("id_curso"));
        curso.setNome(rs.getString("nome"));
        curso.setDescricaoCurso(rs.getString("descricao"));
        curso.setNivel(NivelCurso.valueOf(rs.getString("nivel")));
        curso.setModalidade(ModalidadeCurso.valueOf(rs.getString("modalidade")));
       
        return curso;
    }
}

