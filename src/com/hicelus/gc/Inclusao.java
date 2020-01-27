import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Inclusao extends JFrame implements ActionListener{
   
   private JLabel lNome, lDescricao, lPreco, lQuantidade;
   private JTextField tNome, tDescricao, tPreco, tQuantidade;
   private JButton bIncluir, bVoltar;
   
   public Inclusao(){
      super("Inclus�o de Produto");
      Container container = getContentPane();
      container.setLayout(new GridLayout(5, 2, 10, 10));
      
      lNome = new JLabel("Nome:");
      lDescricao = new JLabel("Descri��o:");
      lPreco = new JLabel("Pre�o:");
      lQuantidade = new JLabel("Quantidade:");
      
      tNome = new JTextField(15);
      tDescricao = new JTextField(45);
      tPreco = new JTextField();
      tQuantidade = new JTextField();
      
      bIncluir = new JButton("Incluir");
      bVoltar = new JButton("Voltar");
      
      bIncluir.addActionListener(this);
      bVoltar.addActionListener(this);
      
      container.add(lNome);
      container.add(tNome);
      container.add(lDescricao);
      container.add(tDescricao);
      container.add(lPreco);
      container.add(tPreco);
      container.add(lQuantidade);
      container.add(tQuantidade);
      container.add(bIncluir);
      container.add(bVoltar);
      
      setVisible(true);
      setSize(500, 200);
      setLocation(450, 250);}
      
   public void actionPerformed(ActionEvent evento){
      if(evento.getSource() == bIncluir){
         int confirmacao = JOptionPane.showConfirmDialog(null,"Deseja confirmar a inclus�o do produto?", "Aten��o",
                                                       JOptionPane.YES_NO_OPTION);
         if(confirmacao == JOptionPane.YES_OPTION){
            try{
               String nome = tNome.getText();
               String descricao = tDescricao.getText();
               double preco = Double.parseDouble(tPreco.getText());
               double quantidade = Double.parseDouble(tQuantidade.getText());
               Gerenciador gerenciador = new Gerenciador();
               gerenciador.inserirDados(nome, descricao, preco, quantidade);
               JOptionPane.showMessageDialog(null, "Produto inclu�do com sucesso.");
               this.dispose();}//fechar somente janela sem encerrar a aplica��o
            catch(Exception e){
               JOptionPane.showMessageDialog(null, "Dados inv�lidos, revise os campos.");}}
         else{
            JOptionPane.showMessageDialog(null, "Opera��o Cancelada");}}
      if(evento.getSource() == bVoltar){
         this.dispose();}}
   
}