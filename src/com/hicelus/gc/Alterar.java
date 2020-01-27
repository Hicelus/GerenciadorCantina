import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Alterar extends JFrame implements ActionListener
{
   public static Conexao conexao = new Conexao();
	private JLabel campoBranco, lId, lNome, lDescricao, lPreco, lQuantidade, lAntigo, lNovo;
	private JTextField tIdAntigo, tINovo, tNomeAntigo, tDescAntiga, tPrecoAntigo, tQuantAntiga, tNovoNome, tNovaDesc, tNovoPreco, tNovaQuant;
	private JButton bIncluir, bVoltar;
   private JPanel painel, p;
	
	public Alterar(String script)
	{
      super("Alterar Produto");
		Container c = getContentPane();
		setLayout( new BorderLayout() );
			
		campoBranco = new JLabel ("");
		lId = new JLabel ("ID: ");
		lNome = new JLabel ("Nome: ");
		lDescricao = new JLabel ("Descrição: ");
		lPreco = new JLabel ("Preço: ");
		lQuantidade = new JLabel ("Quantidade: ");
      lAntigo = new JLabel ("Dados do Sistema:");
      lNovo = new JLabel ("Dados a serem alterados:");
		bVoltar = new JButton ("Voltar");
		bIncluir = new JButton ("Alterar");
		
      conexao.getConnection();
      try{
         PreparedStatement comando = conexao.conn.prepareStatement(script);
         ResultSet resultado = comando.executeQuery();
         
      while(resultado.next()){
         tIdAntigo = new JTextField("" + resultado.getInt("produto_id"));
         tINovo = new JTextField("" + resultado.getInt("produto_id"));
         tNomeAntigo = new JTextField("" + resultado.getString("produto_nome"));
         tDescAntiga = new JTextField("" + resultado.getString("produto_descricao"));
         tPrecoAntigo = new JTextField("" + resultado.getDouble("produto_preco"));
         tQuantAntiga = new JTextField("" + resultado.getDouble("produto_quantidade"));}}
      catch(Exception e){
         JOptionPane.showMessageDialog(null, "Erro " + e);}
            
      tNovoNome = new JTextField();//nome
      tNovaDesc = new JTextField();//descrição
      tNovoPreco = new JTextField();//preco
      tNovaQuant = new JTextField();//quantidade
		
		tIdAntigo.setEditable(false);
      tINovo.setEditable(false);
      tNomeAntigo.setEditable(false);
      tDescAntiga.setEditable(false);
      tPrecoAntigo.setEditable(false);
      tQuantAntiga.setEditable(false);

		bIncluir.addActionListener(this);
		bVoltar.addActionListener(this);
      
      c.add(bIncluir);
      c.add(bVoltar);		
      
      painel = new JPanel();
      painel.setLayout(new GridLayout(6, 3));
      painel.add(campoBranco);
      painel.add(lAntigo);
      painel.add(lNovo);
      painel.add(lId);
      painel.add(tIdAntigo);
      painel.add(tINovo);
      painel.add(lNome);
      painel.add(tNomeAntigo);
      painel.add(tNovoNome);
      painel.add(lDescricao);
      painel.add(tDescAntiga);
      painel.add(tNovaDesc);
      painel.add(lPreco);
      painel.add(tPrecoAntigo);
      painel.add(tNovoPreco);
      painel.add(lQuantidade);
      painel.add(tQuantAntiga);
      painel.add(tNovaQuant);
      c.add(painel, BorderLayout.CENTER);
      
      p = new JPanel();
      p.setLayout(new GridLayout( 1, 2, 5, 5 ));
      p.add(bIncluir);
      p.add(bVoltar);
      c.add(p, BorderLayout.SOUTH);
      
		setSize( 700, 200 );
		setLocation( 400, 250 );
		setVisible( true );
		setResizable ( false );}
   
   public void actionPerformed(ActionEvent evento){
      if (evento.getSource() == bIncluir){
         try{
            int confirm = JOptionPane.showConfirmDialog(null, "Confirmar alteração?", "Aviso", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
               int idFixo = Integer.parseInt(tIdAntigo.getText());
               
               String novoNome = tNovoNome.getText();
               if(novoNome.equals("")){
                  novoNome = tNomeAntigo.getText();}
                  
               String novaDescricao = tNovaDesc.getText();
               if(novaDescricao.equals("")){
                  novaDescricao = tDescAntiga.getText();}
               
               String compPreco = tNovoPreco.getText();   
               double novoPreco = Double.parseDouble(tPrecoAntigo.getText());
               if(!(compPreco.equals(""))){
                  novoPreco = Double.parseDouble(tNovoPreco.getText());}
                  
               String compQuantidade = tNovaQuant.getText();
               double novaQuantidade = Double.parseDouble(tQuantAntiga.getText());
               if(!(compQuantidade.equals(""))){
                  novaQuantidade = Double.parseDouble(tNovaQuant.getText());}
               
               String scriptNovoNome = "update Produtos set produto_nome = '" + novoNome + "' where produto_id = " + idFixo + ";";
               String scriptNovaDescricao = "update Produtos set produto_descricao = '" + novaDescricao + "' where produto_id = " + idFixo + ";";
               String scriptNovoPreco = "update Produtos set produto_preco = '" + novoPreco + "' where produto_id = " + idFixo + ";";
               String scriptNovaQuantidade = "update Produtos set produto_quantidade = '" + novaQuantidade + "' where produto_id = " + idFixo + ";";
               
               PreparedStatement comando = conexao.conn.prepareStatement(scriptNovoNome);
               comando.executeUpdate();
               
               comando = conexao.conn.prepareStatement(scriptNovaDescricao);
               comando.executeUpdate();
               
               comando = conexao.conn.prepareStatement(scriptNovoPreco);
               comando.executeUpdate();
               
               comando = conexao.conn.prepareStatement(scriptNovaQuantidade);
               comando.executeUpdate();
               
               JOptionPane.showMessageDialog(null, "Operação Concluída");
               this.dispose();}
            else{
               JOptionPane.showMessageDialog(null, "Operação Cancelada");}}
         catch(Exception e){
            JOptionPane.showMessageDialog(null, "Dados inválidos, revise os campos.");}}
      else{
         this.dispose();}}
}
