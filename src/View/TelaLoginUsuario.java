package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class TelaLoginUsuario extends JFrame {

    private final Color corFundo = new Color(20, 20, 20);
    private final Color corPainel = new Color(40, 40, 40);
    private final Color corBotao = new Color(40, 150, 255);
    private final Color corBotaoHover = new Color(70, 180, 255);
    private final Color corTexto = new Color(230, 230, 230);

    private JCheckBox chkProfessor;
    private JCheckBox chkAluno;

    private JLabel lblLogin;
    private JTextField campoLogin;

    private JLabel lblSenha;
    private JPasswordField campoSenha;

    private JButton btnEntrar;

    public TelaLoginUsuario() {
        setTitle("Login de Usuário");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(corFundo);

        // Título
        JLabel titulo = new JLabel("Login do Usuário", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(corBotao);
        titulo.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 10));
        add(titulo, BorderLayout.NORTH);

        // Painel central
        JPanel painelCentro = new JPanel(new GridBagLayout());
        painelCentro.setBackground(corPainel);
        painelCentro.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 20, 25, 20);

        // Checkboxes (exclusivos)
        chkProfessor = new JCheckBox("Professor");
        chkAluno = new JCheckBox("Aluno");

        chkProfessor.setBackground(corPainel);
        chkProfessor.setForeground(corTexto);
        chkProfessor.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        chkAluno.setBackground(corPainel);
        chkAluno.setForeground(corTexto);
        chkAluno.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(chkProfessor);
        grupoTipo.add(chkAluno);

        // Checkboxes na linha 0, colunas 0 e 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        painelCentro.add(chkProfessor, gbc);
        gbc.gridx = 1;
        painelCentro.add(chkAluno, gbc);

        // Labels coluna (fixa largura)
        lblLogin = new JLabel("Login:");
        lblLogin.setForeground(corTexto);
        lblLogin.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        // Define tamanho fixo para o label para que não mude quando o texto for alterado
        Dimension tamanhoLabelLogin = new Dimension(65, 30);
        lblLogin.setPreferredSize(tamanhoLabelLogin);
        lblLogin.setMinimumSize(tamanhoLabelLogin);
        lblLogin.setMaximumSize(tamanhoLabelLogin);

        lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(corTexto);
        lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        // Campos com tamanho fixo maiores
        campoLogin = new JTextField();
        campoSenha = new JPasswordField();

        Dimension tamanhoCampo = new Dimension(400, 45);

        campoLogin.setPreferredSize(tamanhoCampo);
        campoSenha.setPreferredSize(tamanhoCampo);

        campoLogin.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        campoSenha.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        campoLogin.setBackground(corFundo);
        campoSenha.setBackground(corFundo);

        campoLogin.setForeground(corTexto);
        campoSenha.setForeground(corTexto);

        campoLogin.setCaretColor(corBotao);
        campoSenha.setCaretColor(corBotao);

        campoLogin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(corBotao, 3),
                BorderFactory.createEmptyBorder(8, 120, 8, 120)
        ));
        campoSenha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(corBotao, 3),
                BorderFactory.createEmptyBorder(8, 120, 8, 120)
        ));

        // Linha 1 - Label Login (col 0)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        painelCentro.add(lblLogin, gbc);

        // Linha 1 - Campo Login (col 1)
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        painelCentro.add(campoLogin, gbc);

        // Linha 2 - Label Senha (col 0)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        painelCentro.add(lblSenha, gbc);

        // Linha 2 - Campo Senha (col 1)
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        painelCentro.add(campoSenha, gbc);

        add(painelCentro, BorderLayout.CENTER);

        // Botão entrar
        btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(corBotao);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        btnEntrar.addActionListener(e -> realizarLogin());

        btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntrar.setBackground(corBotaoHover);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEntrar.setBackground(corBotao);
            }
        });

        JPanel painelSul = new JPanel();
        painelSul.setBackground(corFundo);
        painelSul.add(btnEntrar);
        add(painelSul, BorderLayout.SOUTH);

        // Listeners para alterar label login e limpar campos
        chkProfessor.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                lblLogin.setText("Login:");
                limparCampos();
            }
        });

        chkAluno.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                lblLogin.setText("Login:");
                limparCampos();
            }
        });

        // Começa desabilitado
        limparCampos();
        setCamposAtivos(false);

        // Ativa campos ao selecionar checkbox
        chkProfessor.addActionListener(e -> setCamposAtivos(chkProfessor.isSelected()));
        chkAluno.addActionListener(e -> setCamposAtivos(chkAluno.isSelected()));
    }

    private void limparCampos() {
        campoLogin.setText("");
        campoSenha.setText("");
    }

    private void setCamposAtivos(boolean ativo) {
        campoLogin.setEnabled(ativo);
        campoSenha.setEnabled(ativo);
        btnEntrar.setEnabled(ativo);
    }

    private void realizarLogin() {
        if (!chkAluno.isSelected() && !chkProfessor.isSelected()) {
            JOptionPane.showMessageDialog(this, "Selecione Aluno ou Professor.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String tipo = chkProfessor.isSelected() ? "Professor" : "Aluno";
        String login = campoLogin.getText().trim();
        String senha = new String(campoSenha.getPassword());

        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha login e senha.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Tentando login como " + tipo + ":\nLogin: " + login,
                "Login", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLoginUsuario tela = new TelaLoginUsuario();
            tela.setVisible(true);
        });
    }
}
