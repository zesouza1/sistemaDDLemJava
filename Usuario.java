
import java.sql.ResultSet;


public class Usuario {
    
    private String usuario;
    private String nome;
    private String senha;
    
    
    private boolean resultUsuario;
    private boolean resultCadastro;
    private boolean resultExcluir;
    private boolean resultAlterar;
    
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
        public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public boolean verificaUsuario(String usuario, String senha) {       
        Conexao banco = new Conexao();    
        try {    
            banco.AbrirConexao();        
            banco.stmt= banco.con.createStatement();        
            banco.resultset =
                banco.stmt.executeQuery("SELECT * FROM login " 
                                      + "WHERE usuario = '" + usuario + "'" 
                                      + "AND senha = '" + senha + "'");                                              
            if (banco.resultset.next()) {
                resultUsuario = true;         
            } else {
                resultUsuario = false;
            }        
            banco.FecharConexao();            
        } catch (Exception ec) {
            System.out.println("Erro ao consultar usuario " + ec.getMessage());
        }
        return resultUsuario;                   
    }
    
    public Boolean verificaUsuario(String usuario){        
        Conexao banco = new Conexao();        
        try{            
            banco.AbrirConexao();            
            banco.stmt = banco.con.createStatement();            
            banco.resultset =
                    banco.stmt.executeQuery("SELECT * FROM login " + "WHERE usuario = '" + usuario
                    + "'" );            
            if (banco.resultset.next()){
                resultUsuario = true;                
            } else{                           
                resultUsuario = false;
            }            
            banco.FecharConexao();           
        } catch (Exception ec){
            System.out.println("Erro ao consultar usuário" + ec.getMessage());
        }        
        return resultUsuario;
    }
    
    public Boolean cadastraUsuario(String nome, String usuario, String senha){        
        Conexao banco = new Conexao();        
        try{
            banco.AbrirConexao();            
            banco.stmt = banco.con.createStatement();            
            banco.stmt.execute("INSERT INTO login (nome, usuario, senha)" +
                    "VALUES ('" + nome + "','" + usuario + "','" + senha + "')");            
            resultCadastro = true;            
        } catch (Exception ec) {            
            System.out.println("Erro ao inserior usuario " + ec.getMessage());
            resultCadastro = false;
        }        
        banco.FecharConexao();        
        return resultCadastro;
        
    }  
    
    public Boolean excluirCadastro(String usuario){
     Conexao banco = new Conexao();
     try{
        banco.AbrirConexao();
        banco.stmt = banco.con.createStatement();
        banco.stmt.executeUpdate("DELETE FROM login WHERE usuario = '" + usuario + "'");
        resultExcluir = true;        
     }catch (Exception ec){
         System.out.println("Erro ao excluir usuario: " + ec.getMessage());
         resultExcluir = false;
     }
     banco.FecharConexao();
     return resultExcluir;
    }
    
    
    public String obterNome(String usuario) {
    Conexao banco = new Conexao();
    String nomeNome = null;    
    try {
        banco.AbrirConexao();
        banco.stmt = banco.con.createStatement();        
        banco.resultset = banco.stmt.executeQuery("SELECT nome FROM login WHERE usuario = '" + usuario + "'");        
        if (banco.resultset.next()) {
            nomeNome = banco.resultset.getString("nome");
        }        
        banco.FecharConexao();
    } catch (Exception ec) {
        System.out.println("Erro ao obter o nome do usuário " + ec.getMessage());
    }    
    return nomeNome; // Para retornar apenas o nome
}
    
   public Boolean alterarDados(String usuario, String novoNome, String novaSenha) {
    Conexao banco = new Conexao();
    try {
        banco.AbrirConexao();
        banco.stmt = banco.con.createStatement();
        String sql = "UPDATE login SET nome = '" + novoNome + "', senha = '" + novaSenha + "' WHERE usuario = '" + usuario + "'";
        banco.stmt.executeUpdate(sql);
        resultAlterar = true;
    } catch (Exception ec) {
        System.out.println("Erro ao alterar os dados: " + ec.getMessage());
        resultAlterar = false;
    }
    banco.FecharConexao();
    return resultAlterar;
}
    

     
}