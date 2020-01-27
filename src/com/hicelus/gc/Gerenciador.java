import java.sql.*;
import javax.swing.*;

public class Gerenciador{
   
   public static Conexao conexao = new Conexao();
   private double total[];
   
   public Gerenciador(){
      conexao.getConnection();}
      
   public void inserirDados(String nome, String descricao, double preco, double quantidade){
      try{
         PreparedStatement comando = conexao.conn.prepareStatement("insert into Produtos(produto_nome, produto_descricao, " + 
            "produto_preco, produto_quantidade) values(?,?,?,?)");
         comando.setString(1, nome);
         comando.setString(2, descricao);
         comando.setDouble(3, preco);
         comando.setDouble(4, quantidade);
         comando.executeUpdate();}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e);}}
   
   public void inserirRelatorio(int dia, int mes, int ano, String hora, String descricao, double valor){
      try{
         PreparedStatement comando = conexao.conn.prepareStatement("insert into Relatorio(relatorio_dia, relatorio_mes, " + 
            "relatorio_ano, relatorio_hora, relatorio_descricao, relatorio_valor) values(?,?,?,?,?,?)");
         comando.setInt(1, dia);
         comando.setInt(2, mes);
         comando.setInt(3, ano);
         comando.setString(4, hora);
         comando.setString(5, descricao);
         comando.setDouble(6, valor);
         comando.executeUpdate();}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e);}}
   
   public String emitirRelatorio(String script){
      double soma[] = new double[1000];
      String lista = "<html>";
      int i = 0;
      double somaU = 0;
      try{
         PreparedStatement comand = conexao.conn.prepareStatement(script);
         ResultSet resultado = comand.executeQuery();
         
         while(resultado.next()){
            lista += "ID DA VENDA: " + resultado.getInt("relatorio_id") + "<br>DATA: "
                  + resultado.getInt("relatorio_dia") + "/" + resultado.getInt("relatorio_mes")
                  + "/" + resultado.getInt("relatorio_ano") + "<br> HORA: " + resultado.getString("relatorio_hora")
                  + "<br>DESCRIÇÃO:<br> " + resultado.getString("relatorio_descricao") + "<br>PRÓXIMO<br><br><br>";
            soma[i] += resultado.getDouble("relatorio_valor");
            i++;}
         
         for(i = 0; i < soma.length; i++){
            somaU += soma[i];}
         
         lista = lista.replace("</html>", "");
         
         lista += "<br><br><br>Valor Total do Relatório: " + somaU + "</html>";
         if(lista.equals("<html><br><br><br>Valor Total do Relatório: 0.0</html>")){
            lista = "Nenhum relatório corresponde a pesquisa.";}}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e);
         lista = "Erro de Consulta";}
      return lista;}
         
   public String listar(String script){
      String lista = "";
      try{
         PreparedStatement comando = conexao.conn.prepareStatement(script);
         ResultSet resultados = comando.executeQuery();
      
         while(resultados.next()){
            lista += "<html> ID: " + resultados.getInt("produto_id") + "<br> Nome: " + resultados.getString("produto_nome")
                        + "<br> Descrição: " + resultados.getString("produto_descricao") + "<br> Preço: "
                        + resultados.getDouble("produto_preco") + "<br> Quantidade: " + resultados.getDouble("produto_quantidade")
                        + "<br><br>";}
         lista += "</html>";
         if(lista.equals("</html>")){
            lista = "Nenhum produto corresponde a pesquisa.";}}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e);
         lista = "Erro de Consulta";}
      
      return lista;}
      
   public String pedido(String script, int quantos){
      String lista = "";
      try{
         PreparedStatement comando = conexao.conn.prepareStatement(script);
         ResultSet resultados = comando.executeQuery();
         total = new double[50];
         int indice = 0;
         while(resultados.next()){
            double subtotal = quantos * resultados.getDouble("produto_preco");
            lista += "<html> ID: " + resultados.getInt("produto_id") + " - " + resultados.getString("produto_nome")
                  + "<br> Preco: " + resultados.getDouble("produto_preco") + " x " + quantos + " = R$ "
                  + subtotal;
            total[indice] = subtotal;
            indice++;}
            
         lista += "</html>";
         if(lista.equals("</html>")){
            lista = "Nenhum produto corresponde a pesquisa.";}}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e);
         lista = "Erro de Consulta";}
      
      return lista;}
   
   public String impressao(String script){
      String lista = "";
      try{
         PreparedStatement comando = conexao.conn.prepareStatement(script);
         ResultSet resultados = comando.executeQuery();
      
         while(resultados.next()){
            String idL = "ID: " + resultados.getInt("produto_id");
            String nomeL = "Nome: " + resultados.getString("produto_nome");
            String precoL = "Preco: " + resultados.getDouble("produto_preco");
            String quantidadeL = "Quantidade: " + resultados.getDouble("produto_quantidade");
            
            lista += idL + " - " + nomeL + "\n\r" + precoL + " x " + quantidadeL + "\n\r\n\r\n\r\n\r\n\r\n\r";}
         lista += "Fim";
         if(lista.equals("Fim")){
            lista = "Nenhum produto corresponde a pesquisa.";}}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e);
         lista = "Erro de Consulta";}
      
      return lista;}
      
   public String impressaoR(String script){
      String lista = "";
      double totalR = 0;
      try{
         PreparedStatement comando = conexao.conn.prepareStatement(script);
         ResultSet resultados = comando.executeQuery();
      
         while(resultados.next()){
            String idR = "ID: " + resultados.getInt("relatorio_id");
            String dataR = "Data: " + resultados.getInt("relatorio_dia") + "/" + resultados.getInt("relatorio_mes")
                         + "/" + resultados.getInt("relatorio_dia") + " - " + resultados.getString("relatorio_hora");
            String descricaoR = "Descricao: " + resultados.getString("relatorio_descricao");
            lista += idR + "\n\r" + dataR + "\n\r" + descricaoR + "\n\r\n\r\n\r\n\r\n\r\n\r";
            totalR += resultados.getDouble("relatorio_valor");}
            
         lista += "\n\r\n\r\n\rVALOR TOTAL DO RELATORIO: " + totalR + "\n\rFIM-------------------";
         lista = lista.replace("<br>", "\n\r");
         lista = lista.replace("<html>", "");
         lista = lista.replace("</html>", "");
         
         if(lista.equals("-------------------FIM")){
            lista = "Nenhum produto corresponde a pesquisa.";}}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e);
         lista = "Erro de Consulta";}
      
      return lista;}
      
   public String incluirProduto(String script){
      String lista = "";
      try{
         PreparedStatement comando = conexao.conn.prepareStatement(script);
         ResultSet resultados = comando.executeQuery();
      
         while(resultados.next()){
            String idL = "ID: " + resultados.getInt("produto_id");
            String nomeL = "Nome: " + resultados.getString("produto_nome");
            String descricaoL = "Descricao: " + resultados.getString("produto_descricao");
            String precoL = "Preco: " + resultados.getDouble("produto_preco");
            String quantidadeL = "Quantidade: " + resultados.getDouble("produto_quantidade");
            
            lista += idL + "\n\r" + nomeL + "\n\r" + descricaoL + "\n\r" + precoL + "\n\r" + quantidadeL + "\n\r\n\r\n\r\n\r\n\r\n\r";}
         lista += "Fim";
         if(lista.equals("Fim")){
            lista = "Nenhum produto corresponde a pesquisa.";}}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e);
         lista = "Erro de Consulta";}
      
      return lista;}
      
   public void deletar(){
      try{
         String codigo = JOptionPane.showInputDialog("Código do Produto:");
         String mensagem = "";
         int teste = 0;
         
         PreparedStatement comando = conexao.conn.prepareStatement("select * from Produtos where produto_id = ?;");
         comando.setString(1, codigo);
         
         ResultSet resultado = comando.executeQuery();
         
         while(resultado.next()){
            teste = resultado.getInt("produto_id");
            mensagem = "Deseja deletar o seguinte produto?\n\nID: " + resultado.getInt("produto_id") + "\nNome: "
                        + resultado.getString("produto_nome") + "\nDescrição: " + resultado.getString("produto_descricao")
                        + "\nPreço: " + resultado.getDouble("produto_preco") + "\nQuantidade: "
                        + resultado.getDouble("produto_quantidade");}
               
         if(teste == 0){
            JOptionPane.showMessageDialog(null, "Produto não encontrado");}
         else{                           
            int confirmacao = JOptionPane.showConfirmDialog(null, mensagem, "Atenção", JOptionPane.YES_NO_OPTION);
         
            if(confirmacao == JOptionPane.YES_OPTION && !(codigo.equals(""))){
               comando = conexao.conn.prepareStatement("delete from Produtos where produto_id = ?;");
               comando.setString(1, codigo);
               comando.execute();
               
               JOptionPane.showMessageDialog(null, "Produto excluído");}
            else{
               JOptionPane.showMessageDialog(null, "Operação Cancelada");}}}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e);}}
         
   public void setTotal(){
      for(int i = 0; i < total.length; i++){
         total[i] = 0;}}
      
   public double getTotal(){
      double retorno = 0;
      for(int i = 0; i < total.length; i++){
         retorno += total[i];}
      return retorno;}
         

   

}