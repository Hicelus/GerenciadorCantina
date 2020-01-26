import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.print.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Vendas extends JFrame implements ActionListener{
   
   private JButton apagar, incluir, vender;
   private JPanel painel;
   private JLabel lista, valorTotal;
   private String impressao, pedido = "";
   private static PrintService impressora;
   private Gerenciador gerenciador = new Gerenciador();
   private double valorVenda;
   
   public Vendas(){
      super("Lista");
      
      Container container = getContentPane();
      container.setLayout(new BorderLayout());
      
      JPanel tela = new JPanel();
      JScrollPane scroll = new JScrollPane(tela);
      tela.setLayout(new GridLayout(1,1));
      lista = new JLabel();
      tela.add(lista);
      
      JPanel painel = new JPanel();
      painel.setLayout(new GridLayout(1, 5, 10, 10));
      incluir = new JButton("<html><b>Incluir</b></html>");
      apagar = new JButton("<html><b>Apagar</b></html>");
      valorTotal = new JLabel("<html><b>Total:<br>R$</b></html>");
      vender = new JButton("<html><b>Vender</b></html>");
      
      String familia = "Arial";
      int estilo = Font.PLAIN;
      int tamanho = 34;
      Font fonte = new Font(familia, estilo, tamanho);
      incluir.setFont(fonte);
      apagar.setFont(fonte);
      valorTotal.setFont(fonte);
      vender.setFont(fonte);
      
      painel.add(incluir);
      painel.add(apagar);
      painel.add(valorTotal);
      painel.add(vender);
      
      incluir.addActionListener(this);
      apagar.addActionListener(this);
      vender.addActionListener(this);
      
      container.add(scroll, BorderLayout.CENTER);
      container.add(painel , BorderLayout.SOUTH);
      
      setVisible(true);
      setSize(800,400);
      setLocation(250, 150);}
      
   public void detectaImpressoras(){
      try {
         DocFlavor df = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
         PrintService[] ps = PrintServiceLookup.lookupPrintServices(df, null);
         for (PrintService p: ps) {
            System.out.println("Impressora encontrada: " + p.getName());
            if (p.getName().contains("HP Photosmart D110 series") || p.getName().contains("Generic"))  {
	            System.out.println("Impressora Selecionada: " + p.getName());
               impressora = p;
               break;}}}
      catch (Exception e) {
         e.printStackTrace();}}
         
   public synchronized boolean imprime(String texto){
      // se nao existir impressora, entao avisa usuario
      // senao imprime texto
      if (impressora == null) {
         String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.";
	      System.out.println(msg);}
      else{
         try {
            Formatter conteudo = new Formatter("impressao.txt");
            conteudo.format(texto);
            conteudo.close();
            
            DocPrintJob dpj = impressora.createPrintJob();
            
            try{
               InputStream stream =  new FileInputStream("impressao.txt");
               DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
               Doc doc = new SimpleDoc(stream, flavor, null);
               dpj.print(doc, null);}
            catch(Exception e){}
            return true;}
         catch (Exception e) {
            e.printStackTrace();}}
      return false;}
      
   public void actionPerformed(ActionEvent evento){
      int entrada;
      String script;
      int quantos;
      
      if(evento.getSource() == incluir){
         try{
            entrada = Integer.parseInt(JOptionPane.showInputDialog( "<html><font size=34 face=Arial><b>Digite o ID do produto</b></font></html>" ));
            quantos = Integer.parseInt(JOptionPane.showInputDialog("<html><font size=34 face=Arial><b>Digite a quantidade</b></font></html>"));
            script = "select * from Produtos where produto_id like '" + entrada + "%' order by produto_nome;";
            pedido = pedido.replace("</html>", "<br><br>");
            pedido += gerenciador.pedido(script, quantos);
            impressao = new String(gerenciador.impressao(script)); 
            valorVenda += gerenciador.getTotal();
            lista.setText(pedido);
            valorTotal.setText("<html><b>Total:<br>R$ " + valorVenda + "</b></html>");}
         catch(Exception e){
            JOptionPane.showMessageDialog(null, "Dados inválidos");}}
         
      if(evento.getSource() == vender){
         try{
            double troco = Double.parseDouble(JOptionPane.showInputDialog("<html><font size=34 face=Arial><b>Insira o Valor recebido</b></font></html>"));
            String mensagem = "<html><font size=34 face=Arial><b>Deseja realizar e registrar a venda?<br>Troco R$: "
                         + (troco - valorVenda) + "</font></b></html>";
            int confirmacao = JOptionPane.showConfirmDialog(null, mensagem, "Atenção", JOptionPane.YES_NO_OPTION);
            if(confirmacao == JOptionPane.YES_OPTION){
               Calendar calendario = Calendar.getInstance();
		         Date data = calendario.getTime();
   		      DateFormat formataData = DateFormat.getDateTimeInstance();
		         String dataF = "" + formataData.format(data);
          
               detectaImpressoras();
               pedido = "Pedido cantina\n\r" + dataF + "\n\r\n\r" + pedido;
               pedido = pedido.replace("<br>", "\n\r");
               pedido = pedido.replace("<html>", "");
               pedido = pedido.replace("</html>", "\n\r\n\rTOTAL: R$ " + valorVenda);
               pedido = pedido + "\n\r\n\rTOTAL: R$ " + valorVenda;
            
               imprime(pedido);
               System.out.println("Imprimindo");
               
               if(pedido.indexOf("LNC")>0){
                  imprime(pedido);}
            
               String desc = "<html>" + pedido;
               desc = desc.replace("\n\r", "<br>");
               desc += "</html>";
            
               double valor = valorVenda;
         
               int d = calendario.get(Calendar.DAY_OF_MONTH);
               int m = calendario.get(Calendar.MONTH) + 1;
               int a = calendario.get(Calendar.YEAR);
               formataData = DateFormat.getTimeInstance();
		         String h = "" + formataData.format(data);
         
               gerenciador.inserirRelatorio(d, m, a, h, desc, valor);
               JOptionPane.showMessageDialog(null, "<html><font size=34 face=Arial><b>Venda realizada e registrada</b></font></html>");
               this.dispose();}}
         catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valor inválido");}}
         
         
      if(evento.getSource() == apagar){
         int confirmacao = JOptionPane.showConfirmDialog(null, "<html><font size=34 face=Arial><b>Deseja apagar o pedido?</b></font></html>", "Atenção",
                           JOptionPane.YES_NO_OPTION);
            if(confirmacao == JOptionPane.YES_OPTION){
            Vendas venda =  new Vendas();
            this.dispose();}}}
}