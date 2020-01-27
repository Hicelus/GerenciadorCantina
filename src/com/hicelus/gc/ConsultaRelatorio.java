import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConsultaRelatorio extends JFrame implements ActionListener{

	private JButton b1, b2, b3, b4, b5, b6;
	private JLabel l1;
	private JPanel painel;
   
	public ConsultaRelatorio(){  
      super ("Consultar Produto");
		Container c = getContentPane();
		c.setLayout(new GridLayout( 2, 1 ));
		
		l1 = new JLabel("Escolha o Relatório");
		b1 = new JButton("Diário");
		b2 = new JButton("Mensal");
		b3 = new JButton("Anual");
      b4 = new JButton("Período");
      b5 = new JButton("ID");
      b6 = new JButton("Voltar");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
      b4.addActionListener(this);
      b5.addActionListener(this);
      b6.addActionListener(this);
		
		c.add(l1);
		
		painel = new JPanel();
		painel.setLayout( new GridLayout(2, 3, 5, 5 ));
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
		int entradaA;
      int entradaB;
      int entradaC;
      String script;
      
		if ( evento.getSource() == b1 ){
			try{
            entradaA = Integer.parseInt(JOptionPane.showInputDialog( "Digite o dia desejado" ));
            entradaB = Integer.parseInt(JOptionPane.showInputDialog( "Digite o mês desejado" ));
            entradaC = Integer.parseInt(JOptionPane.showInputDialog( "Digite o ano desejado" )); 
            script = "select * from Relatorio where relatorio_dia = " + entradaA + " and relatorio_mes = " + entradaB
                   + " and relatorio_ano = " + entradaC + " order by relatorio_id;";
            ListarRelatorios lr = new ListarRelatorios(script);}
         catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valor Inválido");}
         this.dispose();}
         
		if ( evento.getSource() == b2 ){
         try{
            entradaB = Integer.parseInt(JOptionPane.showInputDialog( "Digite o mês desejado" ));
            entradaC = Integer.parseInt(JOptionPane.showInputDialog( "Digite o ano desejado" )); 
            script = "select * from Relatorio where relatorio_mes = " + entradaB
                   + " adn wherer relatorio_ano = " + entradaC + " order by relatorio_id;";
            ListarRelatorios lr = new ListarRelatorios(script);}
         catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valor Inválido");}
         this.dispose();}
         
		if ( evento.getSource() == b3 ){
         try{
            entradaC = Integer.parseInt(JOptionPane.showInputDialog( "Digite o ano desejado" )); 
            script = "select * from Relatorio where relatorio_ano = " + entradaC + " order by relatorio_id;";
            ListarRelatorios lr = new ListarRelatorios(script);}
         catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valor Inválido");}
         this.dispose();}
         
      if (evento.getSource() == b4){
         JOptionPane.showMessageDialog(null, "Função Inativa");}
         
      if (evento.getSource() == b5){
         try{
            entradaC = Integer.parseInt(JOptionPane.showInputDialog( "Digite o ID desejado" )); 
            script = "select from Relatorio where relatorio_id = " + entradaC + " order by relatorio_id;";
            ListarRelatorios lr = new ListarRelatorios(script);}
         catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valor Inválido");}
         this.dispose();
         }
         
      if (evento.getSource() == b6){
         this.dispose();}
     
     
      }	
}