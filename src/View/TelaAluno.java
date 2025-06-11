package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import Classes.Aluno;
import Classes.Curso;
import Classes.Disciplina;


public class TelaAluno extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextField txtTelefone;
    private JTextField txtCurso;
    private JTextArea txtDisciplina;

    public TelaAluno(Aluno aluno) {
        setTitle("Área do Aluno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        setLocationRelativeTo(null); 

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.GRAY);

        JLabel lblTitulo = new JLabel("Aluno");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTitulo.setBounds(350, 11, 100, 31);
        lblTitulo.setForeground(Color.WHITE);
        contentPane.add(lblTitulo);

        // Nome
        JLabel lblNome = new JLabel("Nome");
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNome.setBounds(10, 60, 100, 20);
        lblNome.setForeground(Color.WHITE);
        contentPane.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(10, 80, 250, 25);
        txtNome.setEditable(false);
        contentPane.add(txtNome);

        // Idade
        JLabel lblIdade = new JLabel("Idade");
        lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblIdade.setBounds(10, 115, 100, 20);
        lblIdade.setForeground(Color.WHITE);
        contentPane.add(lblIdade);

        txtIdade = new JTextField();
        txtIdade.setBounds(10, 135, 250, 25);
        txtIdade.setEditable(false);
        contentPane.add(txtIdade);

        // Telefone
        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTelefone.setBounds(10, 170, 100, 20);
        lblTelefone.setForeground(Color.WHITE);
        contentPane.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(10, 190, 250, 25);
        txtTelefone.setEditable(false);
        contentPane.add(txtTelefone);

        // Curso
        JLabel lblCurso = new JLabel("Curso");
        lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCurso.setBounds(451, 65, 100, 20);
        lblCurso.setForeground(Color.WHITE);
        contentPane.add(lblCurso);

        txtCurso = new JTextField();
        txtCurso.setBounds(451, 85, 180, 25);
        txtCurso.setEditable(false);
        contentPane.add(txtCurso);

        // Disciplina
        JLabel lblDisciplina = new JLabel("Disciplina");
        lblDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDisciplina.setBounds(451, 120, 100, 20);
        lblDisciplina.setForeground(Color.WHITE);
        contentPane.add(lblDisciplina);

        txtDisciplina = new JTextArea();
        txtDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtDisciplina.setEditable(false);
        txtDisciplina.setBackground(Color.WHITE);
        txtDisciplina.setBounds(451, 140, 180, 120);
        contentPane.add(txtDisciplina);

       
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

        // Preencher dados do aluno
        if (aluno != null) {
            txtNome.setText(aluno.getNomeUsuario());
            txtIdade.setText(String.valueOf(obterIdade(aluno)));

            if (aluno.getTelefone() != null)
                txtTelefone.setText(aluno.getTelefone().getTelefone());

            if (aluno.getCursos() != null && !aluno.getCursos().isEmpty()) {
                StringBuilder cursosTexto = new StringBuilder();
                for (Curso curso : aluno.getCursos()) {
                    cursosTexto.append(curso.getDescricaoCurso()).append(", ");
                }
                
                if (cursosTexto.length() > 0) {
                    cursosTexto.setLength(cursosTexto.length() - 2); 
                    txtCurso.setText(cursosTexto.toString());
                }
            }

            if (aluno.getDisciplinas() != null && !aluno.getDisciplinas().isEmpty()) {
                StringBuilder disciplinasTexto = new StringBuilder();
                for (Disciplina disciplina : aluno.getDisciplinas()) {
                    disciplinasTexto.append(disciplina.getNome()).append("\n");
                }
                txtDisciplina.setText(disciplinasTexto.toString());
            }

        }
    }

    private int obterIdade(Aluno aluno) {
        if (aluno.getDataNascimento() == null) return 0;
        Calendar hoje = Calendar.getInstance();
        int anoAtual = hoje.get(Calendar.YEAR);
        int anoNascimento = aluno.getDataNascimento().get(Calendar.YEAR);
        return anoAtual - anoNascimento;
    }
}
