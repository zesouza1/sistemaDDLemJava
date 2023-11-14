import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Menu extends JFrame {
    private final JPanel telinha;
    private String usuarioLogin; // Para saber que e daquele usuario

    public Menu(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
        
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Menu - FATEC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212);

        telinha = new JPanel();
        telinha.setBackground(SystemColor.gray);
        setContentPane(telinha);
        telinha.setLayout(null);
        
        Usuario usu=new Usuario();
        String nomeUsuario = usu.obterNome(usuarioLogin); // Trazer apenas o nome do Usuario

        JLabel lblBemVindo = new JLabel("Bem vindo " + nomeUsuario + " ");
        lblBemVindo.setBounds(60, 0, 500, 39);
        lblBemVindo.setFont(new Font("Arial", 3, 19));
        telinha.add(lblBemVindo);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(20, 80, 117, 25);
        telinha.add(btnExcluir);

        JButton btnAlterarDados = new JButton("Alterar Dados");
        btnAlterarDados.setBounds(150, 80, 117, 25);
        telinha.add(btnAlterarDados);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(280, 80, 117, 25);
        telinha.add(btnVoltar);

        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaLogin tl = new TelaLogin();
                tl.setVisible(true);
                dispose();
            }
        });

        btnExcluir.addActionListener(new ActionListener() { // para fazer a exclusao do usuario
            public void actionPerformed(ActionEvent e) {
                int confirmacao = JOptionPane.showConfirmDialog(null,
                        "Tem certeza de que Deseja excluir o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
                Usuario u = new Usuario();
                if (confirmacao == JOptionPane.YES_OPTION) {
                    if (u.excluirCadastro(usuarioLogin)) {
                        JOptionPane.showMessageDialog(null, "Usuario excluido", "Atençäo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir", "Atenção", JOptionPane.ERROR_MESSAGE);
                    }
                    TelaLogin tl = new TelaLogin();
                    tl.setVisible(true);
                    dispose();
                }
            }
        });

        btnAlterarDados.addActionListener(new ActionListener() { // para ir para tela de alterar dados
            public void actionPerformed(ActionEvent e) {
                TelaAlterar tela = new TelaAlterar(usuarioLogin);
                tela.setVisible(true);
                dispose();
            }
        });
    }

    public void AbreTelinha() {
        this.setVisible(true);
    }
}