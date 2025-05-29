package Dao;

import Classes.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class UsuarioDAO<T extends Usuario> {
    protected Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public abstract void save(T usuario) throws SQLException;

    public abstract void update(T usuario) throws SQLException;

    public abstract T mapRowToUsuario(ResultSet rs) throws SQLException;

    public T findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToUsuario(rs);
            }
        }
        return null;
    }

    public List<T> findAll() throws SQLException {
        List<T> usuario = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuario.add(mapRowToUsuario(rs));
            }
        }
        return usuario;
    }

    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}


