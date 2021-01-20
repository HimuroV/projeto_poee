package com.projeto.view.pedido;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Cliente;

import com.projeto.model.models.Pedido;
import com.projeto.model.service.ClienteService;

import com.projeto.model.service.PedidoService;
import com.projeto.view.cliente.BuscaCliente;

import com.projeto.view.pedido.PedidoGUI;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PedidoGUI extends JDialog {

	private static final long serialVersionUID = 795920854721377266L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblCodigo;
	private JTextField textFieldCodigo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textFieldData;
	private JTextField textFieldHora;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textFieldValorTotal;
	private JLabel lblNewLabel_4;
	private JTextField textFieldTipoPagamento;
	private JLabel lblNewLabel_6;
	private JTextField textFieldTroco;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JLabel checkData;
	private JLabel checkHora;
	private JLabel checkValorTotal;
	private JLabel checkTipoPagamento;
	private JLabel checkTroco;
	
	private Cliente cliente;
	private JTable tabelaPedido;
	private TabelaPedidoModel tabelaPedidoModel;
	private int linha = 0;
	private int acao = 0;
	
	private boolean status = true;
	private JLabel lblNewLabel_5;
	private JTextField textFieldNomeCliente;
	private JButton btnCliente;

	public PedidoGUI(JFrame frame, boolean modal, JTable tabelaPedido, TabelaPedidoModel tabelaPedidoModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();
		
		this.tabelaPedido = tabelaPedido;
		this.tabelaPedidoModel = tabelaPedidoModel;
		this.linha = linha;
		this.acao = acao;
				
		limpaTextoCampo();
		
		desabilitaCheckCampos();
		
		configuraAcaoPedido();
	}
	
	private void configuraAcaoPedido() {
		
		if (this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if (this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarPedido();
		}
		if (this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnIncluir.setVisible(false);
			btnAlterar.setVisible(false);
			buscarPedido();
		}
	}
	
	private void initComponents() {
		setBounds(100, 100, 521, 299);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblCodigo = new JLabel("Código:");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setEditable(false);
		textFieldCodigo.setColumns(10);
		
		lblNewLabel = new JLabel("Data:");
		
		lblNewLabel_1 = new JLabel("Hora:");
		
		textFieldData = new JTextField();
		textFieldData.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDaData()) {
					textFieldData.requestFocus();
				}
				else {
					digitacaoDataValida();
				}
			}
		});
		textFieldData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDaData()) {
						textFieldData.requestFocus();
					}
					else {
						digitacaoDataValida();
					}
				}
			}
		});
		textFieldData.setColumns(10);
		
		textFieldHora = new JTextField();
		textFieldHora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDaHora()) {
						textFieldHora.requestFocus();
					}
					else {
						digitacaoHoraValida();
					}
				}
			}
		});
		textFieldHora.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
					if (verificaDigitacaoDaHora()) {
						textFieldHora.requestFocus();
					}
					else {
						digitacaoHoraValida();
					}
			}
		});
		textFieldHora.setColumns(10);
		
		lblNewLabel_2 = new JLabel("");
		
		lblNewLabel_3 = new JLabel("Valor Total:");
		
		textFieldValorTotal = new JTextField();
		textFieldValorTotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoValorTotal()) {
						textFieldValorTotal.requestFocus();
					}
					else {
						digitacaoValorTotalValido();
					}
				}
			}
		});
		textFieldValorTotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoValorTotal()) {
					textFieldValorTotal.requestFocus();
				}
				else {
					digitacaoValorTotalValido();
				}
			}
		});
		textFieldValorTotal.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Tipo Pagamento:");
		
		textFieldTipoPagamento = new JTextField();
		textFieldTipoPagamento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoTipoPagamento()) {
					textFieldTipoPagamento.requestFocus();
				}
				else {
					digitacaoTipoPagamentoValido();
				}
			}
		});
		textFieldTipoPagamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoTipoPagamento()) {
						textFieldTipoPagamento.requestFocus();
					}
					else {
						digitacaoTipoPagamentoValido();
					}
				}
			}
		});
		textFieldTipoPagamento.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Troco");
		
		textFieldTroco = new JTextField();
		
		  textFieldTroco.addFocusListener(new FocusAdapter() {
		  
		  @Override public void focusLost(FocusEvent e) { if
		  (verificaDigitacaoDoTroco()) { textFieldTroco.requestFocus();
		  } else { digitacaoTrocoValido(); } } });
		  textFieldTroco.addKeyListener(new KeyAdapter() {
		  
		  @Override public void keyPressed(KeyEvent e) { if ( e.getKeyCode() ==
		  KeyEvent.VK_ENTER) { if (verificaDigitacaoDoTroco()) {
		  textFieldTroco.requestFocus(); } else {
		  digitacaoTrocoValido(); } } } });
		 
		textFieldTroco.setColumns(10);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirPedido();
			}
		});
		btnIncluir.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarPedido();
			}
		});
		btnAlterar.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirPedido();
			}
		});
		btnExcluir.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_delete.png")));
		
		btnSair = new JButton("Sair ");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharPedido();
			}
		});
		btnSair.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		
		checkData = new JLabel("");
		checkData.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkHora = new JLabel("");
		checkHora.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkValorTotal = new JLabel("");
		checkValorTotal.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkTipoPagamento = new JLabel("");
		checkTipoPagamento.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkTroco = new JLabel("");
		checkTroco.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		lblNewLabel_5 = new JLabel("Cliente");
		
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setEditable(false);
		textFieldNomeCliente.setColumns(10);
		
		btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscaCliente();
			}
		});
		btnCliente.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(58)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel)
												.addComponent(lblCodigo)
												.addComponent(lblNewLabel_1)
												.addComponent(lblNewLabel_3))
											.addPreferredGap(ComponentPlacement.RELATED))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblNewLabel_2)
											.addGap(73)))
									.addGap(10))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_5)
										.addComponent(lblNewLabel_6))
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(checkData))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(textFieldHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(checkHora))
								.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(textFieldValorTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(checkValorTotal))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(textFieldTipoPagamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(checkTipoPagamento))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(textFieldTroco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(checkTroco))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(textFieldNomeCliente, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnCliente))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)))
					.addContainerGap(64, Short.MAX_VALUE))
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
						.addComponent(lblNewLabel)
						.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkData))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkHora))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldValorTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_3)
							.addComponent(checkValorTotal)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(textFieldTipoPagamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkTipoPagamento))
					.addGap(10)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(textFieldTroco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkTroco))
					.addGap(13)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(textFieldNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCliente))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		limpaTextoCampo();
		
		desabilitaCheckCampos();
	}
	
	protected void buscaCliente() {
		cliente = new Cliente();
		BuscaCliente buscaCliente = new BuscaCliente(new JFrame(), true);
		buscaCliente.setLocationRelativeTo(null);
		buscaCliente.setVisible(true);
		
		if(buscaCliente.isSelectCliente()) {
			ClienteService clienteService = new ClienteService();
			cliente = clienteService.findById(buscaCliente.getCodigoCliente()); 
			textFieldNomeCliente.setText(cliente.getNome());
		}
		
		
	}
	
	private boolean verificaDigitacaoDaData() {

		if (VariaveisProjeto.digitacaoCampo(textFieldData.getText())) {
			status = false;
			mudaStatusCheckData();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoDataValida() {
		status = true;
		mudaStatusCheckData();
		checkData.setVisible(true);
		textFieldHora.requestFocus();	
	}
	
	private void mudaStatusCheckData() {
		checkData.setVisible(true);
		if(status == false) {
			checkData.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkData.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDaHora() {

		if (VariaveisProjeto.digitacaoCampo(textFieldHora.getText())) {
			status = false;
			mudaStatusCheckHora();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoHoraValida() {
		status = true;
		mudaStatusCheckHora();
		checkHora.setVisible(true);
		textFieldValorTotal.requestFocus();	
	}
	
	private void mudaStatusCheckHora() {
		checkHora.setVisible(true);
		if(status == false) {
			checkHora.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkHora.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDoValorTotal() {

		if (VariaveisProjeto.digitacaoCampo(textFieldValorTotal.getText())) {
			status = false;
			mudaStatusCheckValorTotal();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoValorTotalValido() {
		status = true;
		mudaStatusCheckValorTotal();
		checkValorTotal.setVisible(true);
		textFieldTipoPagamento.requestFocus();	
	}
	
	private void mudaStatusCheckValorTotal() {
		checkValorTotal.setVisible(true);
		if(status == false) {
			checkValorTotal.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkValorTotal.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDoTipoPagamento() {

		if (VariaveisProjeto.digitacaoCampo(textFieldTipoPagamento.getText())) {
			status = false;
			mudaStatusCheckTipoPagamento();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoTipoPagamentoValido() {
		status = true;
		mudaStatusCheckTipoPagamento();
		checkTipoPagamento.setVisible(true);
		textFieldTroco.requestFocus();	
	}
	
	private void mudaStatusCheckTipoPagamento() {
		checkTipoPagamento.setVisible(true);
		if(status == false) {
			checkTipoPagamento.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkTipoPagamento.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDoTroco() {

		if (VariaveisProjeto.digitacaoCampo(textFieldTroco.getText())) {
			status = false;
			mudaStatusCheckTroco();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoTrocoValido() {
		status = true;
		mudaStatusCheckTroco();
		checkTroco.setVisible(true);
	}
	
	private void mudaStatusCheckTroco() {
		checkTroco.setVisible(true);
		if(status == false) {
			checkTroco.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkTroco.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void desabilitaCheckCampos() {
		checkData.setVisible(false);
		checkHora.setVisible(false);
		checkValorTotal.setVisible(false);
		checkTipoPagamento.setVisible(false);
		checkTroco.setVisible(false);
	}
	
private void excluirPedido() {
		
		Integer toReturn = 0;
		
		Pedido pedido = pegarDadosPedido();
		
		PedidoService pedidoService = new PedidoService();
		
		toReturn = pedidoService.delete(pedido);
		
		if (toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na exclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Exclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaPedidoModel.fireTableDataChanged();
			pedido = new Pedido();
		}
	}
	
	protected void incluirPedido() {
		
		Integer toReturn = 0; 
		
		Pedido pedido = pegarDadosPedido();
		
		PedidoService pedidoService = new PedidoService();
		
		toReturn = pedidoService.save(pedido);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na inclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaPedidoModel.savePedido(pedido);
			tabelaPedido.setModel(tabelaPedidoModel);
			tabelaPedidoModel.fireTableDataChanged();
			pedido = new Pedido();
		}
	}
	
	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem,status, option);
	}
	
	private void fecharPedido() {
		dispose();
	}
	
	protected void alterarPedido() {
		
		Integer toReturn = 0;
		
		Pedido pedido = pegarDadosPedido();
			
		PedidoService pedidoService = new PedidoService();
		
		toReturn = pedidoService.update(pedido);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na alteração do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			tabelaPedidoModel.updatePedido(pedido, this.linha);
			tabelaPedido.setModel(tabelaPedidoModel);
			tabelaPedidoModel.fireTableDataChanged();
			limpaTextoCampo();
			pedido = new Pedido();
		}
	}
	
	private void erroDigitacao(Integer toReturn) {
		if (toReturn == VariaveisProjeto.PEDIDO_DATA) {
			status = false;
			mudaStatusCheckData();
			showMensagem("Erro na digitação da data, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.PEDIDO_HORA) {
			status = false;
			mudaStatusCheckHora();
			showMensagem("Erro na digitação da hora, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.PEDIDO_VALOR_TOTAL) {
			status = false;
			mudaStatusCheckValorTotal();
			showMensagem("Erro na digitação do valor total, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.PEDIDO_TIPO_PAGAMENTO) {
			status = false;
			mudaStatusCheckTipoPagamento();
			showMensagem("Erro na digitação do tipo de pagamento, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.PEDIDO_TROCO) {
			status = false;
			mudaStatusCheckTroco();
			showMensagem("Erro na digitação do troco, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buscarPedido() {
		
		Pedido pedido = new Pedido();
					
		pedido = tabelaPedidoModel.getPedido(this.linha);
		
		textFieldCodigo.setText(String.valueOf(pedido.getId()));
		textFieldData.setText(pedido.getData());
		textFieldHora.setText(pedido.getHora());
		textFieldValorTotal.setText(String.valueOf(pedido.getValor_total()));
		textFieldTipoPagamento.setText(pedido.getTipo_pagamento());
		textFieldTroco.setText(String.valueOf(pedido.getTroco()));
		textFieldNomeCliente.setText(pedido.getCliente().getNome());
		cliente = new Cliente();
		cliente.setId(pedido.getCliente().getId());
		cliente.setNome(pedido.getCliente().getNome());
	}
	
	public Pedido pegarDadosPedido() {
		
		Pedido pedido = new Pedido();
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false ) {
			pedido.setId( Integer.valueOf(textFieldCodigo.getText()));
		}
		
		pedido.setData(textFieldData.getText());
		pedido.setHora(textFieldHora.getText());
		pedido.setValor_total(Double.valueOf(textFieldValorTotal.getText()));
		pedido.setTipo_pagamento(textFieldTipoPagamento.getText());
		pedido.setTroco(Double.valueOf(textFieldTroco.getText()));
		pedido.setCliente(cliente);
	    return pedido;
	}
	
	private void limpaTextoCampo() {

		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldData.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldHora.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldValorTotal.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldTipoPagamento.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNomeCliente.setText(VariaveisProjeto.LIMPA_CAMPO);
	}
}
