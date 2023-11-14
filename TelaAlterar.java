import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaAlterar extends JFrame {
    private final JPanel tela;
    private final JTextField txtAlteraNome;
    private final JPasswordField passSenha;
    private final JPasswordField passNovaSenha;
    private final JPasswordField passConfNovaSenha;
    private String usuarioLogin;
    
    

    public TelaAlterar(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Alterar Dados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 230);

        tela = new JPanel();
        tela.setBackground(SystemColor.gray);
        setContentPane(tela);
        tela.setLayout(null);

        JLabel lblIdentificacao = new JLabel("Alteracao de Dados");
        lblIdentificacao.setBounds(60, 0, 500, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        tela.add(lblIdentificacao);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(24, 50, 70, 15);
        tela.add(lblNome);

        txtAlteraNome = new JTextField();
        txtAlteraNome.setBounds(120, 50, 219, 19);
        tela.add(txtAlteraNome);
        txtAlteraNome.setColumns(10);
        Usuario u = new Usuario(); // para trazer a instancia do Usuario
        String nome = u.obterNome(usuarioLogin); // obterNome que esta na clssa Usuario
        txtAlteraNome.setText(nome); // para setar o nome do usuario no campo de txtAlterarNome
        
        

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(24, 75, 70, 15);
        tela.add(lblSenha);

        passSenha = new JPasswordField();
        passSenha.setBounds(120, 75, 219, 19);
        tela.add(passSenha);

        JLabel lblNovaSenha = new JLabel("Nova Senha");
        lblNovaSenha.setBounds(24, 100, 70, 15);
        tela.add(lblNovaSenha);

        passNovaSenha = new JPasswordField();
        passNovaSenha.setBounds(120, 100, 219, 19);
        tela.add(passNovaSenha);

        JLabel lblConfNovaSenha = new JLabel("Confirmar Senha");
        lblConfNovaSenha.setBounds(24, 125, 70, 15);
        tela.add(lblConfNovaSenha);

        passConfNovaSenha = new JPasswordField();
        passConfNovaSenha.setBounds(120, 125, 219, 19);
        tela.add(passConfNovaSenha);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 156, 117, 25);
        tela.add(btnCancelar);

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(200, 156, 117, 25);
        tela.add(btnConfirmar);
        
        btnCancelar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
           TelaLogin tl = new TelaLogin();
                tl.setVisible(true);
                dispose(); 
        }
    });

        btnConfirmar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String novoNome = txtAlteraNome.getText();
        String senha = new String(passSenha.getPassword());
        String novaSenha = new String(passNovaSenha.getPassword());
        String confNovaSenha = new String(passConfNovaSenha.getPassword());
        Usuario u = new Usuario();

        if ("".equals(senha)) {
            JOptionPane.showMessageDialog(null, "Campo senha precisa ser informado", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(novaSenha)) {
            JOptionPane.showMessageDialog(null, "Campo Nova Senha precisa ser informado", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(confNovaSenha)) {
            JOptionPane.showMessageDialog(null, "Campo Nova Senha precisa ser informado", "Atenção",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (u.verificaUsuario(usuarioLogin, senha)) { // paradinha do verificaUsuario utilizado na Tela Login, que vem da Classe Usuario, mas apenas para verificar senha
                if (u.alterarDados(usuarioLogin, novoNome, novaSenha)) {
                    JOptionPane.showMessageDialog(null, "Usuario alterado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar dados", "Atenção", JOptionPane.ERROR_MESSAGE);
                }
                TelaLogin tLogin = new TelaLogin();
                tLogin.AbreTela();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Senha atual incorreta", "Atenção", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});
    }

    public void AbreTela() {
        TelaAlterar tela = new TelaAlterar(usuarioLogin);
        tela.setVisible(true);
    }
}