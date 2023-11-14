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

public class TelaLogin extends JFrame{
    
    private final JPanel panelTela;
    private final JTextField txtUsuario;
    private final JPasswordField pswSenha;
    private String usuarioLogin;
    
    private boolean usuarioValido;


    public TelaLogin() {   
        
        setLocationRelativeTo(null);        
        setResizable(false);        
        setTitle("Login - Fatec");    
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        setBounds(500,200,426,212);
    
        panelTela = new JPanel();
        panelTela.setBackground(SystemColor.gray);
        setContentPane(panelTela);
        panelTela.setLayout(null);   
        
        JLabel lblIdentificacao = new JLabel("IDENTIFACAÇAO");        
        lblIdentificacao.setBounds(144,0,160,39);    
        lblIdentificacao.setFont(new Font("Arial",3,19));    
        panelTela.add(lblIdentificacao);
        
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(24,65,70,15);
        panelTela.add(lblUsuario);
    
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(24,92,70,15);
        panelTela.add(lblSenha);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(112,63,219,19);
        panelTela.add(txtUsuario);
        txtUsuario.setColumns(10);
    
        pswSenha = new JPasswordField();
        pswSenha.setBounds(112, 90,219,19);
        panelTela.add(pswSenha);
    
        JButton btnEntrar = new JButton ("Entrar");
        btnEntrar.setBounds(200,136,117,25);
        panelTela.add(btnEntrar);
    
        JButton btnCadastrar = new JButton ("Cadastrar");
        btnCadastrar.setBounds(50,136,117,25);
        panelTela.add(btnCadastrar);
        
        btnCadastrar.addActionListener(new ActionListener(){
           public void actionPerformed (ActionEvent e){
               TelaCadastro tc= new TelaCadastro();
               tc.setVisible(true);
               dispose();
           } 
        });
        
    
        
        btnEntrar.addActionListener (new ActionListener(){
        public void actionPerformed (ActionEvent e){
            Usuario usu = new Usuario();
        
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(pswSenha.getText());
            
        
            if ("".equals(txtUsuario.getText())){
                JOptionPane.showMessageDialog(null, "Campo usuário precisa ser informado!",
                                            "Atenção", JOptionPane.ERROR_MESSAGE);
            
                txtUsuario.grabFocus();
            } else if ("".equals(pswSenha.getText())) {
                JOptionPane.showMessageDialog(null, "O Campo senha precisa ser informado!",
                                             "Atenção", JOptionPane.ERROR_MESSAGE);
                
                pswSenha.grabFocus();
            } else {                
                usuarioValido = usu.verificaUsuario(usu.getUsuario(),
                                                    usu.getSenha());                
                if (usuarioValido == true){
                    String nomeUsuario = usu.obterNome(usu.getUsuario());
                    usuarioLogin = usu.getUsuario();
                JOptionPane.showMessageDialog(null, "Bem vindo: "+ nomeUsuario,
                        "Atenção", JOptionPane.INFORMATION_MESSAGE);
                Menu m = new Menu(usuarioLogin);
                m.AbreTelinha();
                dispose();
            }else{
                JOptionPane.showMessageDialog(null, "Usuario invalido ou inexistente",
                        "Atenção", JOptionPane.ERROR_MESSAGE);
                
                LimpaText();
                
                txtUsuario.grabFocus();
            }
        }
    }
    });
           
 }

   
    public void AbreTela() {
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);
    }
  
    public void LimpaText() {
        txtUsuario.setText("");
        pswSenha.setText("");
    }
 }