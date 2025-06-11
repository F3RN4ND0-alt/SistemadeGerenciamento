package View;

import Classes.Aluno;
import Classes.Professor;
import Enums.Permissao;
import Enums.Sexo;
import View.TelaPrincipal.Conexao;
import Enums.Especialidade; 
import Dao.AlunoDAO;
import Dao.ProfessorDAO;


import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class CadastroUsuario extends JFrame {
	
	Connection connection = Conexao.getConexao();
	
	
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JFormattedTextField formattedTextFieldDataNascimento;
    private JTextField textFieldEmail;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CadastroUsuario frame = new CadastroUsuario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CadastroUsuario() {
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

        JLabel lblTitulo = new JLabel("Cadastro Usuarios");
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTitulo.setBounds(277, 11, 209, 36);
        panel.add(lblTitulo);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setForeground(Color.BLACK);
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNome.setBounds(261, 76, 51, 14);
        panel.add(lblNome);

        JLabel lblSexo = new JLabel("Sexo");
        lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSexo.setForeground(Color.BLACK);
        lblSexo.setBounds(261, 225, 100, 14);
        panel.add(lblSexo);

        JLabel lblDataNascimento = new JLabel("Data de Nascimento");
        lblDataNascimento.setForeground(Color.BLACK);
        lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDataNascimento.setBounds(261, 275, 160, 14);
        panel.add(lblDataNascimento);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(261, 94, 242, 20);
        panel.add(textFieldNome);
        textFieldNome.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.BLACK);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setBounds(261, 125, 46, 14);
        panel.add(lblEmail);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(261, 142, 242, 20);
        panel.add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(Color.BLACK);
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSenha.setBounds(261, 173, 46, 14);
        panel.add(lblSenha);

        passwordField = new JPasswordField();
        passwordField.setBounds(261, 194, 242, 20);
        panel.add(passwordField);

        String[] opcoesSexo = {"Masculino", "Feminino", "Outro"};
        JComboBox<String> comboBoxSexo = new JComboBox<>(opcoesSexo);
        comboBoxSexo.setBounds(261, 242, 242, 22);
        panel.add(comboBoxSexo);

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            formattedTextFieldDataNascimento = new JFormattedTextField(mascaraData);
            formattedTextFieldDataNascimento.setHorizontalAlignment(SwingConstants.CENTER);
            formattedTextFieldDataNascimento.setBounds(261, 294, 242, 20);
            panel.add(formattedTextFieldDataNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JLabel lblTipoUsuario = new JLabel("Tipo de Usuário");
        lblTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTipoUsuario.setForeground(Color.BLACK);
        lblTipoUsuario.setBounds(261, 325, 160, 14);
        panel.add(lblTipoUsuario);

        String[] tipos = {"Aluno", "Professor"};
        JComboBox<String> comboBoxTipoUsuario = new JComboBox<>(tipos);
        comboBoxTipoUsuario.setBounds(261, 345, 242, 22);
        panel.add(comboBoxTipoUsuario);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCadastrar.setBounds(261, 376, 111, 23);
        panel.add(btnCadastrar);
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 TelaPrincipal telaPrincipal = new TelaPrincipal();
                 telaPrincipal.setVisible(true);
                 dispose();
        	}
        });
        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnVoltar.setBounds(392, 376, 111, 23);
        panel.add(btnVoltar);

        
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText().trim();
                String email = textFieldEmail.getText().trim();
                String senha = new String(passwordField.getPassword()).trim();
                String sexoSelecionado = (String) comboBoxSexo.getSelectedItem();
                String dataNascimento = formattedTextFieldDataNascimento.getText().trim();
                String tipoUsuario = (String) comboBoxTipoUsuario.getSelectedItem();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || sexoSelecionado.isEmpty() || dataNascimento.contains("_")) {
                    JOptionPane.showMessageDialog(null,
                        "Por favor, preencha todos os campos corretamente!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                
                Calendar nascimento = Calendar.getInstance();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    nascimento.setTime(sdf.parse(dataNascimento));
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,
                        "Data de nascimento inválida!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (tipoUsuario.equals("Aluno")) {
                        Aluno aluno = new Aluno();
                        aluno.setNomeUsuario(nome);
                        aluno.setEmail(email);
                        aluno.setSenha(senha);
                        Sexo sexo;
                        switch (sexoSelecionado) {
                            case "Masculino":
                                sexo = Sexo.MASCULINO;
                                break;
                            case "Feminino":
                                sexo = Sexo.FEMININO;
                                break;
                          default :
                                sexo = Sexo.OUTRO;
                                break; 
                        }
                        aluno.setSexo(sexo);
                        aluno.setDataNascimento(nascimento);
                        aluno.setPermissao(Permissao.ALUNO);
                        aluno.setAtivo(true);

                        AlunoDAO alunoDAO = new AlunoDAO(connection);
                        alunoDAO.save(aluno);

                    } else if (tipoUsuario.equals("Professor")) {
                        Professor professor = new Professor();
                        professor.setNomeUsuario(nome);
                        professor.setEmail(email);
                        professor.setSenha(senha);
                        Sexo sexo;
                        switch (sexoSelecionado) {
                            case "Masculino":
                                sexo = Sexo.MASCULINO;
                                break;
                            case "Feminino":
                                sexo = Sexo.FEMININO;
                                break;
                            default:
                                sexo = Sexo.OUTRO;
                                break;
                        }
                        professor.setSexo(sexo);
                        professor.setDataNascimento(nascimento);
                        professor.setPermissao(Permissao.PROFESSOR);
                        professor.setAtivo(true);
                        professor.setEspecialidade(Especialidade.INFORMATICA);


                        ProfessorDAO professorDAO = new ProfessorDAO(connection);
                        professorDAO.save(professor);
                    }

                    JOptionPane.showMessageDialog(null,
                        tipoUsuario + " cadastrado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                    
                    textFieldNome.setText("");
                    textFieldEmail.setText("");
                    passwordField.setText("");
                    formattedTextFieldDataNascimento.setValue(null);
                    comboBoxSexo.setSelectedIndex(0);
                    comboBoxTipoUsuario.setSelectedIndex(0);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                        "Erro ao cadastrar o usuário!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                
                }
            }
        });
    }
}