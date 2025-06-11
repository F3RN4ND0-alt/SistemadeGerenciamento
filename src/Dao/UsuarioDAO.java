package Dao;

import Classes.Usuario;
import Enums.Permissao;

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
        List<T> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(mapRowToUsuario(rs));
            }
        }
        return usuarios;
    }
    public Usuario verificarLogin(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	Permissao permissao = Permissao.valueOf(rs.getString("permissao"));

                    Usuario usuario;
                    switch (permissao) {
                        case PROFESSOR:
                            usuario = new Classes.Professor();
                            break;
                        case ALUNO:
                            usuario = new Classes.Aluno();
                            break;
                        default:
                            usuario = null;
                    }

                    if (usuario != null) {
                        usuario.setIdUsuario(rs.getInt("id_usuario"));
                        usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                        usuario.setPermissao(permissao);
                        usuario.setEmail(rs.getString("email"));
                  
                        return usuario;
                    }
                }
            }
        }
        return null;
    }

    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}


