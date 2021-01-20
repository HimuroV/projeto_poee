package com.projeto.view.ItemPedido;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.ItemPedido;
import com.projeto.model.service.ItemPedidoService;
import com.projeto.view.ItemPedido.ItemPedidoGUI;
import com.projeto.view.ItemPedido.TabelaItemPedidoModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemPedidoGUI extends JDialog {

	private static final long serialVersionUID = 8475405631098247873L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblCodigo;
	private JTextField textFieldCodigo;
	private JLabel lblQuantidade;
	private JTextField textFieldQuantidade;
	private JLabel lblValorUnitario;
	private JTextField textFieldValorUnitario;
	private JLabel lblValorTotalItem;
	private JTextField textFieldValorTotalItem;
	private JLabel checkQuantidade;
	private JLabel checkValorUnitario;
	private JLabel checkValorTotalItem;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;

	private JTable tabelaItemPedido;
	private TabelaItemPedidoModel tabelaItemPedidoModel;
	private int linha = 0;
	private int acao = 0;
	
	private boolean status = true;

	public ItemPedidoGUI(JFrame frame, boolean modal, JTable tabelaItemPedido, TabelaItemPedidoModel tabelaItemPedidoModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();
		
		this.tabelaItemPedido = tabelaItemPedido;
		this.tabelaItemPedidoModel = tabelaItemPedidoModel;
		this.linha = linha;
		this.acao = acao;
				
		limpaTextoCampo();
		
		desabilitaCheckCampos();
		
		configuraAcaoItemPedido();
	}
	
private void configuraAcaoItemPedido() {
		
		if (this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if (this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarItemPedido();
		}
		if (this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnIncluir.setVisible(false);
			btnAlterar.setVisible(false);
			buscarItemPedido();
		}
	}
	
	private void initComponents() {
		setBounds(100, 100, 276, 247);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblCodigo = new JLabel("Código");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setEditable(false);
		textFieldCodigo.setColumns(10);
		
		lblQuantidade = new JLabel("Quantidade");
		
		textFieldQuantidade = new JTextField();
		textFieldQuantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDaQuantidade()) {
						textFieldQuantidade.requestFocus();
					}
					else {
						digitacaoQuantidadeValida();
					}
				}
			}
		});
		textFieldQuantidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDaQuantidade()) {
					textFieldQuantidade.requestFocus();
				}
				else {
					digitacaoQuantidadeValida();
				}
			}
		});
		textFieldQuantidade.setColumns(10);
		
		lblValorUnitario = new JLabel("Valor Unitário");
		
		textFieldValorUnitario = new JTextField();
		textFieldValorUnitario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoValorUnitario()) {
					textFieldValorUnitario.requestFocus();
				}
				else {
					digitacaoValorUnitarioValido();
				}
			}
		});
		textFieldValorUnitario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoValorUnitario()) {
						textFieldValorUnitario.requestFocus();
					}
					else {
						digitacaoValorUnitarioValido();
					}
				}
			}
		});
		textFieldValorUnitario.setColumns(10);
		
		lblValorTotalItem = new JLabel("Valor Total Item");
		
		textFieldValorTotalItem = new JTextField();
		textFieldValorTotalItem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoValorTotalItem()) {
					textFieldValorTotalItem.requestFocus();
				}
				else {
					digitacaoValorTotalItemValido();
				}
			}
		});
		textFieldValorTotalItem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoValorTotalItem()) {
						textFieldValorTotalItem.requestFocus();
					}
					else {
						digitacaoValorTotalItemValido();
					}
				}
			}
		});
		textFieldValorTotalItem.setColumns(10);
		
		checkQuantidade = new JLabel("");
		checkQuantidade.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkValorUnitario = new JLabel("");
		checkValorUnitario.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkValorTotalItem = new JLabel("");
		checkValorTotalItem.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirItemPedido();
			}
		});
		btnIncluir.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarItemPedido();
			}
		});
		btnAlterar.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/application_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirItemPedido();
			}
		});
		btnExcluir.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/application_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblValorUnitario)
						.addComponent(lblQuantidade)
						.addComponent(lblCodigo)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblValorTotalItem)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnExcluir)
								.addComponent(btnIncluir))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(checkQuantidade))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textFieldValorUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(checkValorUnitario))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textFieldValorTotalItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(checkValorTotalItem))
						.addComponent(btnAlterar)
						.addComponent(btnSair))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantidade)
						.addComponent(textFieldQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkQuantidade))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblValorUnitario)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldValorUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(checkValorUnitario)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblValorTotalItem)
						.addComponent(textFieldValorTotalItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkValorTotalItem))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		limpaTextoCampo();
		
		desabilitaCheckCampos();
	}

	private boolean verificaDigitacaoDaQuantidade() {

		if (VariaveisProjeto.digitacaoCampo(textFieldQuantidade.getText())) {
			status = false;
			mudaStatusCheckQuantidade();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoQuantidadeValida() {
		status = true;
		mudaStatusCheckQuantidade();
		checkQuantidade.setVisible(true);
		textFieldValorUnitario.requestFocus();	
	}
	
	private void mudaStatusCheckQuantidade() {
		checkQuantidade.setVisible(true);
		if(status == false) {
			checkQuantidade.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkQuantidade.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDoValorUnitario() {

		if (VariaveisProjeto.digitacaoCampo(textFieldValorUnitario.getText())) {
			status = false;
			mudaStatusCheckValorUnitario();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoValorUnitarioValido() {
		status = true;
		mudaStatusCheckValorUnitario();
		checkValorUnitario.setVisible(true);
		textFieldValorTotalItem.requestFocus();	
	}
	
	private void mudaStatusCheckValorUnitario() {
		checkValorUnitario.setVisible(true);
		if(status == false) {
			checkValorUnitario.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkValorUnitario.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDoValorTotalItem() {

		if (VariaveisProjeto.digitacaoCampo(textFieldValorUnitario.getText())) {
			status = false;
			mudaStatusCheckValorUnitario();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoValorTotalItemValido() {
		status = true;
		mudaStatusCheckValorTotalItem();
		checkValorTotalItem.setVisible(true);
	}
	
	private void mudaStatusCheckValorTotalItem() {
		checkValorTotalItem.setVisible(true);
		if(status == false) {
			checkValorTotalItem.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkValorTotalItem.setIcon(new ImageIcon(ItemPedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void desabilitaCheckCampos() {
		checkQuantidade.setVisible(false);
		checkValorUnitario.setVisible(false);
		checkValorTotalItem.setVisible(false);
	}
	
private void excluirItemPedido() {
		
		Integer toReturn = 0;
		
		ItemPedido itemPedido = pegarDadosItemPedido();
		
		ItemPedidoService itemPedidoService = new ItemPedidoService();
		
		toReturn = itemPedidoService.delete(itemPedido);
		
		if (toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na exclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Exclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaItemPedidoModel.fireTableDataChanged();
			itemPedido = new ItemPedido();
		}
	}
	
	protected void incluirItemPedido() {
		
		Integer toReturn = 0; 
		
		ItemPedido itemPedido = pegarDadosItemPedido();
		
		ItemPedidoService itemPedidoService = new ItemPedidoService();
		
		toReturn = itemPedidoService.save(itemPedido);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na inclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaItemPedidoModel.saveItemPedido(itemPedido);
			tabelaItemPedido.setModel(tabelaItemPedidoModel);
			tabelaItemPedidoModel.fireTableDataChanged();
			itemPedido = new ItemPedido();
		}
	}
	
	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem,status, option);
	}
	
	protected void alterarItemPedido() {
		
		Integer toReturn = 0;
		
		ItemPedido itemPedido = pegarDadosItemPedido();
			
		ItemPedidoService itemPedidoService = new ItemPedidoService();
		
		toReturn = itemPedidoService.update(itemPedido);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na alteração do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			tabelaItemPedidoModel.updateItemPedido(itemPedido, this.linha);
			tabelaItemPedido.setModel(tabelaItemPedidoModel);
			tabelaItemPedidoModel.fireTableDataChanged();
			limpaTextoCampo();
			itemPedido = new ItemPedido();
		}
	}
	
	private void erroDigitacao(Integer toReturn) {
		if (toReturn == VariaveisProjeto.ITEM_PEDIDO_QUANTIDADE) {
			status = false;
			mudaStatusCheckQuantidade();
			showMensagem("Erro na digitação da quantidade, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.ITEM_PEDIDO_VALOR_UNITARIO) {
			status = false;
			mudaStatusCheckValorUnitario();
			showMensagem("Erro na digitação do valor unitário, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.ITEM_PEDIDO_VALOR_TOTAL_ITEM) {
			status = false;
			mudaStatusCheckValorTotalItem();
			showMensagem("Erro na digitação do valor total do item, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buscarItemPedido() {
		
		ItemPedido itemPedido = new ItemPedido();
					
		itemPedido = tabelaItemPedidoModel.getItemPedido(this.linha);
		
		textFieldCodigo.setText(String.valueOf(itemPedido.getId()));
		textFieldQuantidade.setText(String.valueOf(itemPedido.getQuantidade()));
		textFieldValorUnitario.setText(String.valueOf(itemPedido.getValor_unitario()));
		textFieldValorTotalItem.setText(String.valueOf(itemPedido.getValor_total_item()));
	}
	
	public ItemPedido pegarDadosItemPedido() {
		
		ItemPedido itemPedido = new ItemPedido();
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false ) {
			itemPedido.setId( Integer.valueOf(textFieldCodigo.getText()));
		}
		
		itemPedido.setQuantidade(Integer.valueOf(textFieldQuantidade.getText()));
		itemPedido.setValor_unitario(Double.valueOf(textFieldValorUnitario.getText()));
		itemPedido.setValor_total_item(Double.valueOf(textFieldValorTotalItem.getText()));
		   
	    return itemPedido;
	}
	
	private void limpaTextoCampo() {

		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldQuantidade.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldValorUnitario.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldValorTotalItem.setText(VariaveisProjeto.LIMPA_CAMPO);
		
	}
}
