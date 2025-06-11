package View;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Classes.Aluno;
import Classes.Professor;
import Classes.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

import Dao.AlunoDAO;
import Dao.ConexaoBD;
import Dao.ProfessorDAO;
import Dao.UsuarioDAO;
import Enums.Permissao;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldEmail;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(10, 11, 764, 539);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Usuário");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTitulo.setBounds(337, 11, 120, 42);
        panel.add(lblTitulo);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setBounds(280, 98, 46, 20);
        panel.add(lblEmail);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(280, 118, 204, 20);
        panel.add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSenha.setBounds(280, 216, 46, 14);
        panel.add(lblSenha);

        passwordField = new JPasswordField();
        passwordField.setBounds(280, 230, 204, 20);
        panel.add(passwordField);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnEntrar.setBounds(288, 347, 89, 23);
        panel.add(btnEntrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnVoltar.setBounds(387, 347, 89, 23);
        panel.add(btnVoltar);

        // Listener do botão Entrar
        btnEntrar.addActionListener(e -> autenticarUsuario());

        // Listener do botão Voltar
        btnVoltar.addActionListener(e -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
            dispose();
        });
    }

    
    	private void autenticarUsuario() {
    	    String email = textFieldEmail.getText();
    	    String senha = String.valueOf(passwordField.getPassword());

    	    try (Connection connection = ConexaoBD.getConnection()) {

    	        UsuarioDAO<Usuario> usuarioDAO = new UsuarioDAO<Usuario>(connection) {
    	            @Override
    	            public void save(Usuario usuario) throws SQLException {
    	                throw new UnsupportedOperationException("Não implementado.");
    	            }

    	            @Override
    	            public void update(Usuario usuario) throws SQLException {
    	                throw new UnsupportedOperationException("Não implementado.");
    	            }

    	            @Override
    	            public Usuario mapRowToUsuario(ResultSet rs) throws SQLException {
    	                Permissao permissao = Permissao.valueOf(rs.getString("permissao"));
    	                Usuario usuario;
    	                switch (permissao) {
    	                    case PROFESSOR:
    	                        usuario = new Professor();
    	                        break;
    	                    case ALUNO:
    	                        usuario = new Aluno();
    	                        break;
    	                    default:
    	                        usuario = null;
    	                }

    	                if (usuario != null) {
    	                    usuario.setIdUsuario(rs.getInt("id_usuario"));
    	                    usuario.setNomeUsuario(rs.getString("nomeUsuario"));
    	                    usuario.setPermissao(permissao);
    	                    usuario.setEmail(rs.getString("email"));
    	                }

    	                return usuario;
    	            }
    	        };

    	        Usuario usuario = usuarioDAO.verificarLogin(email, senha);

    	        if (usuario == null) {
    	            JOptionPane.showMessageDialog(null, "Email ou senha incorretos!");
    	            return;
    	        }

    	        if (usuario.getPermissao() == Permissao.PROFESSOR) {
    	            ProfessorDAO professorDAO = new ProfessorDAO(connection);
    	            Professor professor = professorDAO.findById(usuario.getIdUsuario());

    	            if (professor != null) {
    	                JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
    	                new TelaProfessor(professor).setVisible(true);
    	                dispose();
    	            } else {
    	                JOptionPane.showMessageDialog(null, "Erro ao carregar dados do professor.");
    	            }

    	        } else if (usuario.getPermissao() == Permissao.ALUNO) {
    	            AlunoDAO alunoDAO = new AlunoDAO(connection);
    	            Aluno aluno = alunoDAO.findById(usuario.getIdUsuario());

    	            if (aluno != null) {
    	                JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
    	                new TelaAluno(aluno).setVisible(true);
    	                dispose();
    	            } else {
    	                JOptionPane.showMessageDialog(null, "Erro ao carregar dados do aluno.");
    	            }
    	        } else {
    	            JOptionPane.showMessageDialog(null, "Permissão não reconhecida.");
    	        }

    	    } catch (Exception ex) {
    	        ex.printStackTrace();
    	        JOptionPane.showMessageDialog(null, "Erro ao conectar ou consultar: " + ex.getMessage());
    	    }
    	}
    }
