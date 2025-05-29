package Dao;

import Classes.Endereco;
import java.sql.*;

public class EnderecoDAO {
    private Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    public int save(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO endereco (endereco, cep, numero, complemento, bairro, cidade, estado, pais) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, endereco.getEndereco());
            stmt.setString(2, endereco.getCep());
            stmt.setString(3, endereco.getNumero());
            stmt.setString(4, endereco.getComplemento());
            stmt.setString(5, endereco.getBairro());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getEstado());
            stmt.setString(8, endereco.getPais());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public Endereco buscarPorId(int id) throws SQLException {
        String sql = "SELECT endereco, cep, numero, complemento, bairro, cidade, estado, pais FROM endereco WHERE id_endereco = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Endereco(
                        rs.getString("endereco"),
                        rs.getString("cep"),
                        rs.getString("numero"),
                        rs.getString("complemento"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("pais")
                    );
                }
            }
        }
        return null;
    }
}

