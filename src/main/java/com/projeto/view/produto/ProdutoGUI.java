package com.projeto.view.produto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Produto;
import com.projeto.model.service.ProdutoService;
import com.projeto.view.produto.ProdutoGUI;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProdutoGUI extends JDialog {

	private static final long serialVersionUID = -6600044089718349005L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textFieldCodigo;
	private JTextField textFieldNome;
	private JTextField textFieldValorVenda;
	private JTextField textFieldDescricao;
	private JLabel checkNome;
	private JLabel checkValorVenda;
	private JLabel checkDescricao;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;

	private JTable tabelaProduto;
	private TabelaProdutoModel tabelaProdutoModel;
	private int linha = 0;
	private int acao = 0;
	
	private boolean status = true;
	
	public ProdutoGUI(JFrame frame, boolean modal, JTable tabelaProduto, TabelaProdutoModel tabelaProdutoModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();
		
		this.tabelaProduto = tabelaProduto;
		this.tabelaProdutoModel = tabelaProdutoModel;
		this.linha = linha;
		this.acao = acao;
				
		limpaTextoCampo();
		
		desabilitaCheckCampos();
		
		configuraAcaoProduto();
		
	}
	
	private void configuraAcaoProduto() {
		
		if (this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if (this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarProduto();
		}
		if (this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnIncluir.setVisible(false);
			btnAlterar.setVisible(false);
			buscarProduto();
		}
	}
	
	private void initComponents() {
		setBounds(100, 100, 450, 292);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("Código");
		
		lblNewLabel_1 = new JLabel("Nome");
		
		lblNewLabel_2 = new JLabel("Valor Venda");
		
		lblNewLabel_3 = new JLabel("Descrição");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setEditable(false);
		textFieldCodigo.setColumns(10);
		
		textFieldNome = new JTextField();
		textFieldNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoNome()) {
					textFieldNome.requestFocus();
				}
				else {
					digitacaoNomeValido();
				}
			}
		});
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoNome()) {
						textFieldNome.requestFocus();
					}
					else {
						digitacaoNomeValido();
					}
				}
			}
		});
		textFieldNome.setColumns(10);
		
		textFieldValorVenda = new JTextField();
		textFieldValorVenda.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoValorVenda()) {
					textFieldValorVenda.requestFocus();
				}
				else {
					digitacaoValorVendaValido();
				}
			}
		});
		textFieldValorVenda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoValorVenda()) {
						textFieldValorVenda.requestFocus();
					}
					else {
						digitacaoValorVendaValido();
					}
				}
			}
		});
		textFieldValorVenda.setColumns(10);
		
		textFieldDescricao = new JTextField();
		textFieldDescricao.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDaDescricao()) {
					textFieldDescricao.requestFocus();
				}
				else {
					digitacaoDescricaoValida();
				}
			}
		});
		textFieldDescricao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDaDescricao()) {
						textFieldDescricao.requestFocus();
					}
					else {
						digitacaoDescricaoValida();
					}
				}
			}
		});
		textFieldDescricao.setColumns(10);
		
		checkNome = new JLabel("");
		checkNome.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkValorVenda = new JLabel("");
		checkValorVenda.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkDescricao = new JLabel("");
		checkDescricao.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirProduto();
			}
		});
		btnIncluir.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarProduto();
			}
		});
		btnAlterar.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto();
			}
		});
		btnExcluir.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/application_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(checkDescricao))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(textFieldValorVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(checkValorVenda))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(checkNome))
								.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkNome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldValorVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkValorVenda)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(checkDescricao)
						.addComponent(lblNewLabel_3)
						.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addGap(44))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		limpaTextoCampo();
		
		desabilitaCheckCampos();
	}
	
	private boolean verificaDigitacaoDoNome() {

		if (VariaveisProjeto.digitacaoCampo(textFieldNome.getText())) {
			status = false;
			mudaStatusCheckNome();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoNomeValido() {
		status = true;
		mudaStatusCheckNome();
		checkNome.setVisible(true);
		textFieldValorVenda.requestFocus();	
	}
	
	private void mudaStatusCheckNome() {
		checkNome.setVisible(true);
		if(status == false) {
			checkNome.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkNome.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDoValorVenda() {

		if (VariaveisProjeto.digitacaoCampo(textFieldValorVenda.getText())) {
			status = false;
			mudaStatusCheckValorVenda();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoValorVendaValido() {
		status = true;
		mudaStatusCheckValorVenda();
		checkValorVenda.setVisible(true);
		textFieldDescricao.requestFocus();	
	}
	
	private void mudaStatusCheckValorVenda() {
		checkValorVenda.setVisible(true);
		if(status == false) {
			checkValorVenda.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkValorVenda.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDaDescricao() {

		if (VariaveisProjeto.digitacaoCampo(textFieldDescricao.getText())) {
			status = false;
			mudaStatusCheckDescricao();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoDescricaoValida() {
		status = true;
		mudaStatusCheckDescricao();
		checkDescricao.setVisible(true);
	}
	
	private void mudaStatusCheckDescricao() {
		checkDescricao.setVisible(true);
		if(status == false) {
			checkDescricao.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkDescricao.setIcon(new ImageIcon(ProdutoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	
	}
	
	private void desabilitaCheckCampos() {
		checkNome.setVisible(false);
		checkValorVenda.setVisible(false);
		checkDescricao.setVisible(false);
	}
	
private void excluirProduto() {
		
		Integer toReturn = 0;
		
		Produto produto = pegarDadosProduto();
		
		ProdutoService produtoService = new ProdutoService();
		
		toReturn = produtoService.delete(produto);
		
		if (toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na exclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Exclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaProdutoModel.fireTableDataChanged();
			produto = new Produto();
		}
	}
	
	protected void incluirProduto() {
		
		Integer toReturn = 0; 
		
		Produto produto = pegarDadosProduto();
		
		ProdutoService produtoService = new ProdutoService();
		
		toReturn = produtoService.save(produto);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na inclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaProdutoModel.saveProduto(produto);
			tabelaProduto.setModel(tabelaProdutoModel);
			tabelaProdutoModel.fireTableDataChanged();
			produto = new Produto();
		}
	}
	
	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem,status, option);
	}
	
	protected void alterarProduto() {
		
		Integer toReturn = 0;
		
		Produto produto = pegarDadosProduto();
			
		ProdutoService produtoService = new ProdutoService();
		
		toReturn = produtoService.update(produto);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na alteração do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			tabelaProdutoModel.updateProduto(produto, this.linha);
			tabelaProduto.setModel(tabelaProdutoModel);
			tabelaProdutoModel.fireTableDataChanged();
			limpaTextoCampo();
			produto = new Produto();
		}
	}
	
	private void erroDigitacao(Integer toReturn) {
		if (toReturn == VariaveisProjeto.PRODUTO_NOME) {
			status = false;
			mudaStatusCheckNome();
			showMensagem("Erro na digitação do nome, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.PRODUTO_VALOR_VENDA) {
			status = false;
			mudaStatusCheckValorVenda();
			showMensagem("Erro na digitação do valor da venda, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.PRODUTO_DESCRICAO) {
			status = false;
			mudaStatusCheckDescricao();
			showMensagem("Erro na digitação da descricao, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buscarProduto() {
		
		Produto produto = new Produto();
					
		produto = tabelaProdutoModel.getProduto(this.linha);
		
		textFieldCodigo.setText(String.valueOf(produto.getId()));
		textFieldNome.setText(produto.getNome());
		textFieldValorVenda.setText(String.valueOf(produto.getValor_venda()));
		textFieldDescricao.setText(produto.getDescricao());
				
	}
	
	public Produto pegarDadosProduto() {
		
		Produto produto = new Produto();
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false ) {
			produto.setId( Integer.valueOf(textFieldCodigo.getText()));
		}
		
		produto.setNome(textFieldNome.getText());
		produto.setValor_venda(Double.valueOf(textFieldValorVenda.getText()));
		produto.setDescricao(textFieldDescricao.getText());
		   
	    return produto;
	}
	
	private void limpaTextoCampo() {

		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNome.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldValorVenda.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldDescricao.setText(VariaveisProjeto.LIMPA_CAMPO);
			
	}
}
