package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JOptionPane;

public class TelaUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaUsuario frame = new TelaUsuario();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaUsuario() {
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

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(280, 98, 46, 20);
		panel.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(280, 118, 204, 20);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.BLACK);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setBounds(280, 216, 46, 14);
		panel.add(lblSenha);

		JLabel lblTitulo = new JLabel("Usuário");
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(337, 11, 120, 42);
		panel.add(lblTitulo);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textFieldEmail.getText();
				String senha = String.valueOf(passwordField.getPassword());

				// Validação simples
				if (email.equals("professor@gmail.com") && senha.equals("123")) {
					JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
					TelaProfessor professor = new TelaProfessor();
					professor.setVisible(true);
					dispose(); // Fecha a tela de login
				}
				if (email.equals("aluno@gmail.com") && senha.equals("321")) {
						JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
						TelaAluno aluno = new TelaAluno();
						aluno.setVisible(true);
						dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Email ou senha incorretos!");
				}
			}
		});
		btnEntrar.setBounds(337, 347, 89, 23);
		panel.add(btnEntrar);

		passwordField = new JPasswordField();
		passwordField.setBounds(280, 230, 204, 20);
		panel.add(passwordField);
	}
}
