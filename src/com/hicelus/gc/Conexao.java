import java.sql.*;
import javax.swing.*;

public class Conexao{
   
   public Connection conn;
   
   public Conexao(){
      conn = null;
      String url = "jdbc:mysql://localhost/Mercadorias";
      String driver = "com.mysql.jdbc.Driver";
      String login = "root";
      String senha = "admin";
      
      try{
         Class.forName(driver);
         conn = DriverManager.getConnection(url, login, senha);}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e, "Erro de Conexão", 1);}}
         
   public Connection getConnection(){
      return conn;}
      
   public void closeConnection(){
      try{
         conn.close();}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e, "Erro de Conaxão", 1);}}
}