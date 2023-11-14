import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexao {
    
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;
    
    
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/login"; // Alterar o banco de dados aqui, tem hora que nao vai.
    
    
   private final String usuario = "root";
   private final String senha = ""; // verifica se o banco de dados tem senha, por padrao nao tem
   private final String driver = "com.mysql.cj.jdbc.Driver";
   
   public Connection AbrirConexao(){
       try {
           Class.forName(driver);
           con = DriverManager.getConnection(servidor,usuario,senha);
           stmt = con.createStatement();
           System.out.println("Conexao aberta com sucesso");
       } catch (Exception e){
           System.out.println("Erro ao acessar o banco de dados, verifique.");
       }
       return con;
   }
   
   public void FecharConexao(){
       try {
           con.close();
           System.out.println("Conexao finalizada com sucesso");
       } catch (Exception e){
           System.out.println("Erro ao encerrar a conexão" + e.getMessage());
       }
   }
}

	
