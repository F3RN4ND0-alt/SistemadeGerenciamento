package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    // Paleta de cores
    private final Color corFundo = new Color(20, 20, 20);
    private final Color corPainelMenu = new Color(40, 40, 40);
    private final Color corBotao = new Color(40, 150, 255);
    private final Color corBotaoHover = new Color(70, 180, 255);
    private final Color corTexto = new Color(230, 230, 230);

    // Fontes
    private final Font fonteTitulo = new Font("Segoe UI", Font.BOLD, 26);
    private final Font fonteMenu = new Font("Segoe UI", Font.PLAIN, 16);

    // Painel central
    private JLabel labelPainelCentral;

    public TelaPrincipal() {
        setTitle("Sistema de Gerenciamento Acadêmico");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(corFundo);

        // Painel lateral (menu)
        JPanel painelMenu = new JPanel();
        painelMenu.setBackground(corPainelMenu);
        painelMenu.setLayout(new GridBagLayout());
        painelMenu.setPreferredSize(new Dimension(250, getHeight()));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel tituloMenu = new JLabel("Menu Principal");
        tituloMenu.setFont(fonteTitulo);
        tituloMenu.setForeground(corBotao);
        gbc.gridy = 0;
        painelMenu.add(tituloMenu, gbc);

        // Botões de menu
        JButton btnAlunos = criarBotaoMenu("Cadastro de Alunos", "");
        JButton btnProfessores = criarBotaoMenu("Cadastro de Professores", "");
        JButton btnCursos = criarBotaoMenu("Cadastro de Cursos", "");
        JButton btnSair = criarBotaoMenu("Sair", "");

        // Ações dos botões
        btnAlunos.addActionListener(e -> atualizarPainelCentral("Tela de Cadastro de Alunos"));
        btnProfessores.addActionListener(e -> atualizarPainelCentral("Tela de Cadastro de Professores"));
        btnCursos.addActionListener(e -> atualizarPainelCentral("Tela de Cadastro de Cursos"));
        btnSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Adicionando botões ao menu
        gbc.gridy = 1;
        painelMenu.add(btnAlunos, gbc);
        gbc.gridy++;
        painelMenu.add(btnProfessores, gbc);
        gbc.gridy++;
        painelMenu.add(btnCursos, gbc);
        gbc.gridy++;
        painelMenu.add(btnSair, gbc);

        add(painelMenu, BorderLayout.WEST);

        // Painel central de boas-vindas
        labelPainelCentral = new JLabel("<html><div style='text-align: center;'>Bem-vindo ao Sistema Acadêmico<br>Selecione uma opção no menu</div></html>", SwingConstants.CENTER);
        labelPainelCentral.setForeground(corBotao);
        labelPainelCentral.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(corFundo);
        painelCentral.add(labelPainelCentral, BorderLayout.CENTER);

        add(painelCentral, BorderLayout.CENTER);
    }

    private JButton criarBotaoMenu(String texto, String caminhoIcone) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setBackground(corPainelMenu);
        botao.setForeground(corTexto);
        botao.setFont(fonteMenu);
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        // Carregar ícone (se houver)
        if (!caminhoIcone.isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(caminhoIcone));
                Image img = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                botao.setIcon(new ImageIcon(img));
                botao.setIconTextGap(15);
            } catch (Exception e) {
                // ícone não encontrado, ignora
            }
        }

        // Efeito hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(corBotaoHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(corPainelMenu);
            }
        });

        return botao;
    }

    private void atualizarPainelCentral(String texto) {
        labelPainelCentral.setText("<html><div style='text-align: center;'>" + texto + "</div></html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}
