package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCursos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaCursos frame = new TelaCursos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaCursos() {
        setTitle("Tela de Cursos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.menu);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(10, 11, 764, 539);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Cursos");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(330, 20, 150, 40);
        panel.add(lblTitulo);

        // Painel Engenharia
        JPanel panelEngenharia = new JPanel();
        panelEngenharia.setBackground(Color.LIGHT_GRAY);
        panelEngenharia.setBounds(30, 100, 200, 320);
        panel.add(panelEngenharia);
        panelEngenharia.setLayout(null);

        JLabel lblEngenharia = new JLabel("Engenharia");
        lblEngenharia.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblEngenharia.setForeground(Color.BLACK);
        lblEngenharia.setBounds(50, 10, 120, 25);
        panelEngenharia.add(lblEngenharia);

        JTextPane txtEngenharia = new JTextPane();
        txtEngenharia.setText("Desenvolve soluções tecnológicas, projetos e sistemas para a indústria e sociedade.");
        txtEngenharia.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtEngenharia.setForeground(new Color(30, 30, 30));
        txtEngenharia.setBackground(new Color(255, 255, 255));
        txtEngenharia.setBounds(10, 50, 180, 120);
        txtEngenharia.setEditable(false);
        txtEngenharia.setFocusable(false);
        panelEngenharia.add(txtEngenharia);

        // Painel Ciências
        JPanel panelCiencias = new JPanel();
        panelCiencias.setBackground(Color.LIGHT_GRAY);
        panelCiencias.setBounds(280, 100, 200, 320);
        panel.add(panelCiencias);
        panelCiencias.setLayout(null);

        JLabel lblCiencias = new JLabel("Ciências");
        lblCiencias.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCiencias.setForeground(Color.BLACK);
        lblCiencias.setBounds(65, 10, 100, 25);
        panelCiencias.add(lblCiencias);

        JTextPane txtCiencias = new JTextPane();
        txtCiencias.setText("Estuda fenômenos naturais e busca entender o mundo.");
        txtCiencias.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtCiencias.setForeground(new Color(30, 30, 30));
        txtCiencias.setBackground(new Color(255, 255, 255));
        txtCiencias.setBounds(10, 50, 180, 120);
        txtCiencias.setEditable(false);
        txtCiencias.setFocusable(false);
        panelCiencias.add(txtCiencias);

        // Painel Sistemas
        JPanel panelSistemas = new JPanel();
        panelSistemas.setBackground(Color.LIGHT_GRAY);
        panelSistemas.setBounds(530, 100, 200, 320);
        panel.add(panelSistemas);
        panelSistemas.setLayout(null);

        JLabel lblSistemas = new JLabel("Sistemas");
        lblSistemas.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSistemas.setForeground(Color.BLACK);
        lblSistemas.setBounds(65, 10, 100, 25);
        panelSistemas.add(lblSistemas);

        JTextPane txtSistemas = new JTextPane();
        txtSistemas.setText("Forma profissionais para desenvolver, gerenciar e otimizar softwares.");
        txtSistemas.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtSistemas.setForeground(new Color(30, 30, 30));
        txtSistemas.setBackground(new Color(255, 255, 255));
        txtSistemas.setBounds(10, 50, 180, 120);
        txtSistemas.setEditable(false);
        txtSistemas.setFocusable(false);
        panelSistemas.add(txtSistemas);

        // Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnVoltar.setBounds(10, 500, 89, 25);
        panel.add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();                 
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                telaPrincipal.setVisible(true);
            }
        });
    }
}