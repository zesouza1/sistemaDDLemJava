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

public class TelaCadastro extends JFrame{

    private final JPanel tela;
    private final JTextField txtNome;
    private final JTextField txtUsuario;
    private final JPasswordField passSenha;
    private final JPasswordField passConfSenha;
    
    private boolean usuarioValido;
    
    private boolean cadastroValido;
    
    
    public TelaCadastro() {
    
    setLocationRelativeTo(null);
    setResizable(false);
    setTitle("Cadastro");
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    setBounds(500, 200, 426, 230);
    
    tela = new JPanel();
    tela.setBackground(SystemColor.gray);
    setContentPane(tela);
    tela.setLayout(null);
    
    
    JLabel lblIdentificao = new JLabel("Informação");
    lblIdentificao.setBounds(60, 0, 500, 39);
    lblIdentificao.setFont(new Font("Arial", 3, 19));
    tela.add(lblIdentificao); 
    
    JLabel lblNome = new JLabel("Nome");
    lblNome.setBounds(24,50,70,15);
    tela.add(lblNome);
    
    txtNome = new JTextField();
    txtNome.setBounds(120,50,219,19);
    tela.add(txtNome);
    txtNome.setColumns(10);
    
    JLabel lblUsuario = new JLabel("Usuario");
    lblUsuario.setBounds(24,75,70,15);
    tela.add(lblUsuario);
    
    txtUsuario = new JTextField();
    txtUsuario.setBounds(120,75,219,19);
    tela.add(txtUsuario);
    txtUsuario.setColumns(10);
    
    JLabel lblSenha = new JLabel("Senha");
    lblSenha.setBounds(24,100,70,15);
    tela.add(lblSenha);
    
    passSenha = new JPasswordField();
    passSenha.setBounds(120,100,219,19);
    tela.add(passSenha);
    
    JLabel lblconfsenha = new JLabel("Confirmar Senha");
    lblconfsenha.setBounds(24,125,100,15);
    tela.add(lblconfsenha);
    
    passConfSenha = new JPasswordField();
    passConfSenha.setBounds(120,125,219,19);
    tela.add(passConfSenha);
   
    JButton btnCadastrar = new JButton("Cadastrar");
    btnCadastrar.setBounds(200,156,117,25);
    tela.add(btnCadastrar);
    
    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.setBounds(50,156,117,25);
    tela.add(btnCancelar);
    
      
    btnCadastrar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e ){
            Usuario usu = new Usuario();            
            usu.setNome(txtNome.getText());
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(passSenha.getText());
            
            if ("".equals(usu.getNome())){
                JOptionPane.showMessageDialog(null, "Campo nome do usuário precisa ser informado!", "Atenção", 
                        JOptionPane.ERROR_MESSAGE);
                txtNome.grabFocus();
                
            }else if("".equals(usu.getUsuario())){
                JOptionPane.showMessageDialog(null, "Campo usuário precisa ser informado!", "Atenção!",
                        JOptionPane.ERROR_MESSAGE);
                
            }else if("".equals(usu.getSenha())){
                JOptionPane.showMessageDialog(null, "Campo senha precisa ser informado!", "Atenção", 
                        JOptionPane.ERROR_MESSAGE);
                passSenha.grabFocus();
                
            }else if (!passSenha.getText().equals(passConfSenha.getText())){
                JOptionPane.showMessageDialog(null, "Campo de senha e confirmação não são iguais!", "Atenção", 
                        JOptionPane.ERROR_MESSAGE);
                passSenha.grabFocus();
                
            }else{
                
                usuarioValido = usu.verificaUsuario(usu.getUsuario()); // verifica o usuario no banco de dados com base no que foi passado para ver se já nao ha cadastro
                
                if(usuarioValido == true){
                    JOptionPane.showMessageDialog(null, "Usuario já existente na nossa base de dados", "Atenção", 
                        JOptionPane.ERROR_MESSAGE);
                    txtUsuario.grabFocus();
                    
                }else{
                    cadastroValido =
                        usu.cadastraUsuario(usu.getNome(),
                                            usu.getUsuario(),
                                            usu.getSenha());
                    
                    if(cadastroValido == true){
                        JOptionPane.showMessageDialog(null, "Usuario cadastrado corretamente, voltaremos " + "a tela de login", "Atenção", 
                        JOptionPane.INFORMATION_MESSAGE);
                        
                        TelaLogin tLogin = new TelaLogin();
                        tLogin.AbreTela();
                        
                        dispose();
                    
                    }else{
                        JOptionPane.showMessageDialog(null, "Problemas ao inserir o usuário", "Atenção", 
                        JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    });  
    btnCancelar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            TelaLogin tl=new TelaLogin();
            tl.setVisible(true);
            dispose();
        }

      });
}
        

public void AbreTela(){
        TelaCadastro panelCadastro = new TelaCadastro();
        panelCadastro.setVisible(true);
    }
 }
