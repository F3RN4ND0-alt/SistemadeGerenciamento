package Dao;

import Classes.Telefone;
import java.sql.*;

public class TelefoneDAO {
    private Connection connection;

    public TelefoneDAO(Connection connection) {
        this.connection = connection;
    }

   
    public int save(Telefone telefone) throws SQLException {
        String sql = "INSERT INTO telefone (ddd, telefone) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getTelefone());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

  
    public Telefone buscarPorId(int id) throws SQLException {
        String sql = "SELECT ddd, telefone FROM telefone WHERE id_telefone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Telefone(rs.getString("ddd"), rs.getString("telefone"));
                }
            }
        }
        return null;
    }
}
