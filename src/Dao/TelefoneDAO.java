package Dao;

import Classes.Telefone;
import java.sql.*;

public class TelefoneDAO {
    private final Connection connection;

    public TelefoneDAO(Connection connection) {
        this.connection = connection;
    }

    public int save(Telefone telefone) throws SQLException {
    	if (telefone == null) {
    		return -1;
    	}
        String selectSql = "SELECT id_telefone FROM telefone WHERE ddd = ? AND telefone = ?";
        try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
            selectStmt.setString(1, telefone.getDdd());
            selectStmt.setString(2, telefone.getTelefone());
            try (ResultSet rs = selectStmt.executeQuery()) {
            	if (rs.next()) {
            	    int id = rs.getInt(1);
            	    telefone.setIdTelefone(id);
            	    return id;
            	}
            }
        }

        
        String insertSql = "INSERT INTO telefone (ddd, telefone) VALUES (?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, telefone.getDdd());
            insertStmt.setString(2, telefone.getTelefone());
            insertStmt.executeUpdate();

            try (ResultSet rs = insertStmt.getGeneratedKeys()) {
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

    public int update(int id, Telefone telefone) throws SQLException {
        if (buscarPorId(id) == null) {
            return save(telefone); 
        }

        String sql = "UPDATE telefone SET ddd = ?, telefone = ? WHERE id_telefone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, telefone.getDdd());
            stmt.setString(2, telefone.getTelefone());
            stmt.setInt(3, id);
            stmt.executeUpdate();
            return id;
        }
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM telefone WHERE id_telefone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
