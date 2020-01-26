import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.print.*;
import java.io.*;
import java.util.*;

public class ListarRelatorios extends JFrame implements ActionListener{
   
   private JButton voltar, imprimir;
   private JPanel painel;
   private JLabel lista;
   private String impressao;
   private static PrintService impressora;
   
   public ListarRelatorios(String script){
      super("Lista");
      
      Gerenciador gerenciador = new Gerenciador();
      
      Container container = getContentPane();
      container.setLayout(new BorderLayout());
      
      JPanel tela = new JPanel();
      JScrollPane scroll = new JScrollPane(tela);
      tela.setLayout(new GridLayout(1,1));
      lista = new JLabel(gerenciador.emitirRelatorio(script));
      impressao = new String(gerenciador.impressaoR(script)); 
      tela.add(lista);
      
      JPanel painel = new JPanel();
      painel.setLayout(new GridLayout(1, 2, 10, 10));
      voltar = new JButton("Voltar");
      painel.add(voltar);
      imprimir = new JButton("Imprimir");
      painel.add(imprimir);
      
      voltar.addActionListener(this);
      imprimir.addActionListener(this);
      
      container.add(scroll, BorderLayout.CENTER);
      container.add(painel , BorderLayout.SOUTH);
      
      setVisible(true);
      setSize(300, 500);
      setLocation(525, 175);}
      
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
      if(evento.getSource() == voltar){
         this.dispose();}
      if(evento.getSource() == imprimir){
         detectaImpressoras();
         imprime(impressao);}}
}