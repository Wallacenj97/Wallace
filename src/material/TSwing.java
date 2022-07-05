package material;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class TSwing {
	public static void main(String[] args) {  
		// Define a janela
		JFrame janela = new JFrame("Cadastro de estoque"); // Janela Normal
		janela.setTitle("Estoque de loja");
		janela.setLocation(500,300);
		janela.setSize(500, 500);
		janela.setBackground(Color.gray);
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		 // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janela.getContentPane();
		caixa.setLayout(null);
		caixa.setBackground(Color.gray);
		// Define os labels dos campos
		JLabel labelCodigo = new JLabel("Codigo: ");
		JLabel labelValor = new JLabel("Valor:");
		JLabel labelProduto = new JLabel("Produto: ");
		// Posiciona os labels na janela
		labelCodigo.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelValor.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelProduto.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextValor = new JTextField();
		JTextField jTextCodigo = new JTextField();
		JTextField jTextProduto = new JTextField();
		jTextValor.setEnabled(false);
		jTextCodigo.setEnabled(true);
		jTextProduto.setEnabled(false);
		jTextCodigo.setBounds(180, 40, 50, 20);
		jTextValor.setBounds(180, 80, 50, 20);
		jTextProduto.setBounds(180, 120, 150, 20);
		janela.add(labelCodigo);
		janela.add(labelProduto);
		janela.add(labelValor);
		janela.add(jTextValor);
		janela.add(jTextCodigo);
		janela.add(jTextProduto);
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janela.add(botaoConsultar);
		JButton botaoGravar = new JButton("Salvar");
		botaoGravar.setBounds(50, 200, 100, 20);
		botaoGravar.setEnabled(false);
		janela.add(botaoGravar);
		JButton botaoLimpar = new JButton("resetar");
		botaoLimpar.setBounds(250, 200, 100, 20);
		janela.add(botaoLimpar);
		JButton botaoDeletar = new JButton("deletar");
		botaoDeletar.setBounds(150, 200, 100, 20);
		janela.add(botaoDeletar);
		
		
		estoque produto = new estoque();
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo = Integer.parseInt(jTextCodigo.getText());
				String nome,valor;
				    try{
				        botaoGravar.setEnabled(false);
				        if(!produto.consultarEstoque(codigo)){
				            nome = "";
				        }
					else {
						nome = produto.getProduto();
						valor = Integer.toString(produto.getValor());
						jTextValor.setText(valor);
						jTextProduto.setText(nome);
					}
				    jTextValor.setEnabled(true);
					jTextCodigo.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextProduto.setEnabled(true);
					jTextValor.requestFocus();
					botaoGravar.setEnabled(true);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janela,"Codigo não registrado");
				}
			}
		});
		botaoGravar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			int codigo =Integer.parseInt(jTextCodigo.getText());
				String valor = jTextValor.getText().trim();
				String nome = jTextProduto.getText().trim();
				if (valor.length()==0){
					JOptionPane.showMessageDialog(janela,"Valor não preenchido");
					jTextValor.requestFocus();
				}
				else if(nome.length()==0){
					JOptionPane.showMessageDialog(janela,"Nome do produto não preenchido");
					jTextProduto.requestFocus();
				}
				else{
					if(!produto.consultarEstoque(codigo)){
						produto.cadastrarProduto(Integer.parseInt(valor), codigo, nome);
					}
				}
			}
		}
								
		);
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextValor.setText(""); 
				jTextCodigo.setText(""); 
				jTextProduto.setText(""); 
				botaoConsultar.setEnabled(true);
				jTextCodigo.setEnabled(true);
				jTextValor.setEnabled(false);
				jTextProduto.setEnabled(false);
			}
		});
		botaoDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int codigo = Integer.parseInt(jTextCodigo.getText());
				produto.deletarProduto(codigo);
			}
		});
		// Apresenta a janela
		janela.setVisible(true);
	}
}
