import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Consulta extends JFrame implements ActionListener{

	private JButton b1, b2, b3, b4, b5, b6;
	private JLabel l1;
	private JPanel painel;
   
	public Consulta(){  
      super ("Consultar Produto");
		Container c = getContentPane();
		c.setLayout(new GridLayout( 2, 1 ));
		
		l1 = new JLabel("<html><b>Selecione o modo de consulta:</b></html>");
		b1 = new JButton("Nome");
		b2 = new JButton ("ID");
		b3 = new JButton ("Descrição");
		b4 = new JButton ("Preço");
		b5 = new JButton ("Quantidade");
		b6 = new JButton ("Voltar");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		
		c.add(l1);
		
		painel = new JPanel();
		painel.setLayout( new GridLayout( 2,3, 5, 5 ));
		painel.add( b1 );
		painel.add( b2 );
		painel.add( b3 );
		painel.add( b4 );
		painel.add( b5 );
		painel.add( b6 );
		c.add( painel );
		
		setSize( 400, 150 );
		setLocation( 500, 270 );
		setVisible( true );
		setResizable ( false );}
	
	public void actionPerformed( ActionEvent evento ){  
		String entrada;
      String script;
		if ( evento.getSource() == b1 ){
			entrada = JOptionPane.showInputDialog( "Digite o nome do(s) produto(s) para consulta" );
         script = "select * from Produtos where produto_nome like '" + entrada + "%' order by produto_nome;";
         ListarTudo consulta = new ListarTudo(script);
         this.dispose();}
		if ( evento.getSource() == b2 ){
			entrada = JOptionPane.showInputDialog( "Digite o ID do produto para consulta" );
         script = "select * from Produtos where produto_id like '" + entrada + "%' order by produto_nome;";
         ListarTudo consulta = new ListarTudo(script);
         this.dispose();}
		if ( evento.getSource() == b3 ){
			entrada = JOptionPane.showInputDialog( "Digite a descrição do(s) produto(s) para consulta" );
         script = "select * from Produtos where produto_descricao like '%" + entrada + "%' order by produto_nome;";
         ListarTudo consulta = new ListarTudo(script);
         this.dispose();}
		if ( evento.getSource() == b4 ){
			entrada = JOptionPane.showInputDialog( "Digite o preço do(s) produto(s) para consulta" );
         script = "select * from Produtos where produto_preco like '" + entrada + "' order by produto_nome;";
         ListarTudo consulta = new ListarTudo(script);
         this.dispose();}
		if ( evento.getSource() == b5 ){
		   entrada = JOptionPane.showInputDialog( "Digite a quantidade de produtos para consulta" );
         script = "select * from Produtos where produto_quantidade like '" + entrada + "' order by produto_nome;";
         ListarTudo consulta = new ListarTudo(script);
         this.dispose();}
		if (evento.getSource() == b6){
			this.dispose();}}	
}