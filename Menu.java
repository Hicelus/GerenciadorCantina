import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Menu extends JFrame implements ActionListener{
   
   private JButton incluir, consultar, listar, alterar, deletar, vender, relatorios, sair;
   
   public Menu(){
      super("Sistema de Mercadorias");
      Container container = getContentPane();
      container.setLayout(new GridLayout(4, 2, 10, 10));
      
      incluir = new JButton("<html><b>Incluir Produto</b></html>");
      consultar = new JButton("<html><b>Consultar Produto</b></html>");
      listar = new JButton("<html><b>Listar Produtos</b></html>");
      alterar = new JButton("<html><b>Alterar Produto</b></html>");
      deletar = new JButton("<html><b>Deletar Produto</b></html>");
      vender = new JButton("<html><b>Realizar Vendas</b></html>");
      relatorios = new JButton("<html><b>Relatórios</b></html>");
      sair = new JButton("<html><b>Sair</b></html>");
      
      String familia = "Arial";
      int estilo = Font.PLAIN;
      int tamanho = 34;
      Font fonte = new Font(familia, estilo, tamanho);
      incluir.setFont(fonte);
      listar.setFont(fonte);
      consultar.setFont(fonte);
      alterar.setFont(fonte);
      deletar.setFont(fonte);
      vender.setFont(fonte);
      relatorios.setFont(fonte);
      sair.setFont(fonte);
      
      incluir.addActionListener(this);
      consultar.addActionListener(this);
      listar.addActionListener(this);
      alterar.addActionListener(this);
      deletar.addActionListener(this);
      vender.addActionListener(this);
      relatorios.addActionListener(this);
      sair.addActionListener(this);
            
      container.add(incluir);
      container.add(consultar);
      container.add(listar);
      container.add(alterar);
      container.add(deletar);
      container.add(vender);
      container.add(relatorios);
      container.add(sair);
      
      setVisible(true);
      setSize(800,400);
      setLocation(250, 150);}
      
   public void actionPerformed(ActionEvent evento){
      if(evento.getSource() == incluir){
         Inclusao inclusao = new Inclusao();}
      
      if(evento.getSource() == listar){
         String script = "select * from Produtos;";
         ListarTudo list = new ListarTudo(script);}
      
      if(evento.getSource() == consultar){
         Consulta consulta = new Consulta();}
      
      if(evento.getSource() == deletar){
         Gerenciador gerenciador = new Gerenciador();
         gerenciador.deletar();}
      
      if(evento.getSource() == alterar){
         String codigo = JOptionPane.showInputDialog("Digite o código do produto a ser alterado:");
         String script = "select * from Produtos where produto_id = " + codigo + ";";
         int teste = 0;
         try{
            Conexao conexao = new Conexao();
            conexao.getConnection();
            PreparedStatement comando = conexao.conn.prepareStatement(script);
            ResultSet resultado = comando.executeQuery();
            
            while(resultado.next()){
               teste = resultado.getInt("produto_id");}
               
            if(teste == 0){
               JOptionPane.showMessageDialog(null, "Produto não encontrado");}
            else{
            Alterar alterar = new Alterar(script);}}
         catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro " + e);}}
            
      if(evento.getSource() == vender){
         Vendas vendas = new Vendas();}
      
      if(evento.getSource() == relatorios){
         ConsultaRelatorio consultaR = new ConsultaRelatorio();}
         
      if(evento.getSource() == sair){
         Conexao conexao = new Conexao();
         conexao.closeConnection();
         System.exit(0);}
         
              
   }
}