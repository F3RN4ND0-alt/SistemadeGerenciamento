package Dao;

import Classes.Telefone;
import Classes.Endereco;
import Classes.Professor;
import Enums.Permissao;
import Enums.Sexo;
import Enums.Especialidade;

import java.sql.*;
import java.util.Calendar;

public class ProfessorDAO extends UsuarioDAO<Professor> {
	private TelefoneDAO telefoneDAO;
	private EnderecoDAO enderecoDAO;
    public ProfessorDAO(Connection connection) {
        super(connection);
        this.telefoneDAO = new TelefoneDAO(connection);
        this.enderecoDAO = new EnderecoDAO(connection);
    }

    public void save(Professor professor) throws SQLException {
    	int telefoneId = telefoneDAO.save(professor.getTelefone());
    	int enderecoId = enderecoDAO.save(professor.getEndereco());
    	String sqlUsuario = "INSERT INTO usuario (nomeUsuario, senha, cpf, sexo, dataNascimento, email, telefone_id, endereco_id, ativo, permissao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	try (PreparedStatement stmt = connection.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
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

            stmt.executeUpdate();
            int idUsuario = -1;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idUsuario = rs.getInt(1);
                professor.setIdUsuario(idUsuario);
            }
            if(idUsuario != -1) {
                String sqlProfessor = "INSERT INTO professor (id_usuario, especialidade) VALUES (?, ?)";
                try (PreparedStatement stmtProf = connection.prepareStatement(sqlProfessor)) {
                    stmtProf.setInt(1, idUsuario);
                    stmtProf.setString(2, professor.getEspecialidade().name());
                    stmtProf.executeUpdate();
                }
            }

        }
    }
   
    public void update(Professor professor) throws SQLException {
        String sqlUsuario = "UPDATE usuario SET nomeUsuario = ?, senha = ?, cpf = ?, sexo = ?, dataNascimento = ?, email = ?, telefone = ?, endereco = ?, ativo = ?, permissao = ? WHERE id_usuario = ?";
        try (PreparedStatement stmtu = connection.prepareStatement(sqlUsuario)) {
            stmtu.setString(1, professor.getNomeUsuario());
            stmtu.setString(2, professor.getSenha());
            stmtu.setString(3, professor.getCpf());
            stmtu.setString(4, professor.getSexo().name());
            stmtu.setDate(5, new java.sql.Date(professor.getDataNascimento().getTimeInMillis()));
            stmtu.setString(6, professor.getEmail());
            stmtu.setString(7, professor.getTelefone().toString());
            stmtu.setString(8, professor.getEndereco().toString());
            stmtu.setBoolean(9, professor.getAtivo());
            stmtu.setString(10, professor.getPermissao().name());
            stmtu.setInt(11, professor.getIdUsuario());
            
       String sqlProfessor = "UPDATE professor SET especialidade = ? WHERE id_usuario = ? ";  
       try (PreparedStatement stmtp = connection.prepareStatement(sqlProfessor)) {
           stmtp.setString(1, professor.getEspecialidade().name());
           stmtp.setInt(2, professor.getIdUsuario());
            stmtp.executeUpdate();
       		}
       }
    }
    public void delete(Integer idUsuario) throws SQLException {
        String sqlProf = "DELETE FROM professor WHERE id_usuario = ?";
        try (PreparedStatement stmtProf = connection.prepareStatement(sqlProf)) {
            stmtProf.setInt(1, idUsuario);
            stmtProf.executeUpdate();
        }
        String sqlUser = "DELETE FROM usuario WHERE id_usuario = ?";
        try (PreparedStatement stmtUser = connection.prepareStatement(sqlUser)) {
            stmtUser.setInt(1, idUsuario);
            stmtUser.executeUpdate();
        }
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
        int telefoneId =rs.getInt("telefone_id");
        int enderecoId =rs.getInt("endereco_id");
        
        Telefone telefone = telefoneDAO.buscarPorId(telefoneId);
        Endereco endereco = enderecoDAO.buscarPorId(enderecoId);
        
        professor.setTelefone(telefone);
        professor.setEndereco(endereco);
        professor.setAtivo(rs.getBoolean("ativo"));
        professor.setPermissao(Permissao.valueOf(rs.getString("permissao")));
        professor.setEspecialidade(Especialidade.valueOf(rs.getString("especialidade")));

        return professor;
    }
    
    public Professor findById(Integer id) throws SQLException {
        String sql = "SELECT u.*, p.especialidade FROM usuario u JOIN professor p ON u.id_usuario = p.id_usuario WHERE u.id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToUsuario(rs);
            }
        }
        return null;
    }

}


