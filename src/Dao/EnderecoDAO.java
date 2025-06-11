package Dao;

import Classes.Endereco;
import java.sql.*;

public class EnderecoDAO {
    private Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    public int save(Endereco endereco) throws SQLException {
    	if (endereco == null) {
    		return -1;
    	}
        String selectSql = "SELECT id_endereco FROM endereco WHERE endereco = ? AND cep = ? AND numero = ? AND complemento = ? AND bairro = ? AND cidade = ? AND estado = ? AND pais = ?";
        try (PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
            selectStmt.setString(1, endereco.getEndereco());
            selectStmt.setString(2, endereco.getCep());
            selectStmt.setString(3, endereco.getNumero());
            selectStmt.setString(4, endereco.getComplemento());
            selectStmt.setString(5, endereco.getBairro());
            selectStmt.setString(6, endereco.getCidade());
            selectStmt.setString(7, endereco.getEstado());
            selectStmt.setString(8, endereco.getPais());

            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                endereco.setIdEndereco(id); 
                return id;
            }
        }

        String insertSql = "INSERT INTO endereco (endereco, cep, numero, complemento, bairro, cidade, estado, pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, endereco.getEndereco());
            insertStmt.setString(2, endereco.getCep());
            insertStmt.setString(3, endereco.getNumero());
            insertStmt.setString(4, endereco.getComplemento());
            insertStmt.setString(5, endereco.getBairro());
            insertStmt.setString(6, endereco.getCidade());
            insertStmt.setString(7, endereco.getEstado());
            insertStmt.setString(8, endereco.getPais());
            insertStmt.executeUpdate();

            ResultSet rs = insertStmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return -1;
    }

    public Endereco buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(id);
                endereco.setEndereco(rs.getString("endereco"));
                endereco.setCep(rs.getString("cep"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setPais(rs.getString("pais"));
                return endereco;
            }
        }
        return null;
    }

    public int update(int id, Endereco endereco) throws SQLException {
        if (buscarPorId(id) == null) {
            return save(endereco); 
        }

        String sql = """
            UPDATE endereco 
            SET endereco = ?, cep = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, pais = ?
            WHERE id_endereco = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, endereco.getEndereco());
            stmt.setString(2, endereco.getCep());
            stmt.setString(3, endereco.getNumero());
            stmt.setString(4, endereco.getComplemento());
            stmt.setString(5, endereco.getBairro());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getEstado());
            stmt.setString(8, endereco.getPais());
            stmt.setInt(9, id);
            stmt.executeUpdate();
            return id;
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM endereco WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

