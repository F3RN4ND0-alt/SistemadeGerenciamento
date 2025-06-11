package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Classes.Disciplina;
import Classes.Professor;

public class TelaProfessor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	

	 public TelaProfessor(Professor professor) {
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setBounds(100, 100, 800, 600);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	        setLocationRelativeTo(null); 
	        
	        JPanel panel = new JPanel();
	        panel.setBackground(Color.GRAY);
	        panel.setBounds(10, 11, 764, 539);
	        contentPane.add(panel);
	        panel.setLayout(null);
	        
	       
	        JLabel lblNewLabel = new JLabel("Professor");
	        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
	        lblNewLabel.setBounds(325, 11, 113, 31);
	        panel.add(lblNewLabel);
	        
	     // Nome
	        JLabel lblNome = new JLabel("Nome");
	        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNome.setBounds(10, 60, 100, 20); // ajustado
	        panel.add(lblNome);

	        textField = new JTextField();
	        textField.setBounds(10, 80, 250, 25); // ajustado
	        panel.add(textField);
	        textField.setColumns(10);

	        // Idade
	        JLabel lblIdade = new JLabel("Idade");
	        lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblIdade.setBounds(10, 115, 100, 20); // ajustado
	        panel.add(lblIdade);

	        textField_2 = new JTextField();
	        textField_2.setBounds(10, 135, 250, 25); // ajustado
	        panel.add(textField_2);
	        textField_2.setColumns(10);

	        // Telefone
	        JLabel lblTelefone = new JLabel("Telefone");
	        lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblTelefone.setBounds(10, 170, 100, 20); // ajustado
	        panel.add(lblTelefone);

	        textField_3 = new JTextField();
	        textField_3.setBounds(10, 190, 250, 25); // ajustado
	        panel.add(textField_3);
	        textField_3.setColumns(10);
	        
	        JLabel lblEspecialidade = new JLabel("Especialidade");
	        lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblEspecialidade.setBounds(451, 65, 87, 16);
	        panel.add(lblEspecialidade);

	        JTextPane txtEspecialidade = new JTextPane();
	        txtEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        txtEspecialidade.setFocusable(false);
	        txtEspecialidade.setEditable(false);
	        txtEspecialidade.setBackground(Color.WHITE);
	        txtEspecialidade.setBounds(451, 84, 180, 120);
	        panel.add(txtEspecialidade);
	        
	        JLabel lblDisciplinas = new JLabel("Disciplinas");
	        lblDisciplinas.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblDisciplinas.setBounds(451, 215, 87, 16);
	        panel.add(lblDisciplinas);

	        JTextArea txtDisciplinas = new JTextArea();
	        txtDisciplinas.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        txtDisciplinas.setEditable(false);
	        txtDisciplinas.setBackground(Color.WHITE);
	        txtDisciplinas.setBounds(451, 235, 180, 120);
	        panel.add(txtDisciplinas);

	        JButton btnVoltar = new JButton("Voltar");
	        btnVoltar.setBounds(650, 500, 100, 30);
	        contentPane.add(btnVoltar);
	        btnVoltar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	                
	                 new Login().setVisible(true);
	            }
	        });
	        
	        if (professor != null) {
	            textField.setText(professor.getNomeUsuario());

	           
	            Calendar nascimento = professor.getDataNascimento();
	            int idade = calcularIdade(nascimento);
	            textField_2.setText(String.valueOf(idade));

	            if (professor.getTelefone() != null)
	                textField_3.setText(professor.getTelefone().getTelefone());

	            if (professor.getEspecialidade() != null)
	                txtEspecialidade.setText(professor.getEspecialidade().name());
	            
	            if (professor.getDisciplinas() != null) {
	                StringBuilder sb = new StringBuilder();
	                for (Disciplina d : professor.getDisciplinas()) {
	                    sb.append(d.getNome()).append("\n");
	                }
	                txtDisciplinas.setText(sb.toString());
	            }
	        }
	    }

	    private int calcularIdade(Calendar nascimento) {
	        Calendar hoje = Calendar.getInstance();
	        int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
	        if (hoje.get(Calendar.DAY_OF_YEAR) < nascimento.get(Calendar.DAY_OF_YEAR)) {
	            idade--;
	        }
	        return idade;
	    }
	}