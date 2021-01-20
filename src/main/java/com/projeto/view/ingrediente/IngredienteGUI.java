package com.projeto.view.ingrediente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Ingrediente;
import com.projeto.model.service.IngredienteService;
import com.projeto.view.ingrediente.IngredienteGUI;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngredienteGUI extends JDialog {

	private static final long serialVersionUID = 4600220517428902075L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField textFieldCodigo;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textFieldNome;
	private JTextField textFieldQuantidadeEstoque;
	private JTextField textFieldCustoUnitario;
	private JLabel checkNome;
	private JLabel checkQuantidadeEstoque;
	private JLabel checkCustoUnitario;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	
	private JTable tabelaIngrediente;
	private TabelaIngredienteModel tabelaIngredienteModel;
	private int linha = 0;
	private int acao = 0;
	
	private boolean status = true;

	public IngredienteGUI(JFrame frame, boolean modal, JTable tabelaIngrediente, TabelaIngredienteModel tabelaIngredienteModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();
		
		this.tabelaIngrediente = tabelaIngrediente;
		this.tabelaIngredienteModel = tabelaIngredienteModel;
		this.linha = linha;
		this.acao = acao;
				
		limpaTextoCampo();
		
		desabilitaCheckCampos();
		
		configuraAcaoIngrediente();
		
	}
	
	private void configuraAcaoIngrediente() {
		
		if (this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if (this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarIngrediente();
		}
		if (this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnIncluir.setVisible(false);
			btnAlterar.setVisible(false);
			buscarIngrediente();
		}
	}
	
	private void initComponents() {
		setBounds(100, 100, 415, 231);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("Código");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setEditable(false);
		textFieldCodigo.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Nome");
		
		lblNewLabel_2 = new JLabel("Quantidade em Estoque");
		
		lblNewLabel_3 = new JLabel("Custo Unitário");
		
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
		
		textFieldQuantidadeEstoque = new JTextField();
		textFieldQuantidadeEstoque.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDaQuantidadeEstoque()) {
					textFieldQuantidadeEstoque.requestFocus();
				}
				else {
					digitacaoQuantidadeEstoqueValida();
				}
			}
		});
		textFieldQuantidadeEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDaQuantidadeEstoque()) {
						textFieldQuantidadeEstoque.requestFocus();
					}
					else {
						digitacaoQuantidadeEstoqueValida();
					}
				}
			}
		});
		textFieldQuantidadeEstoque.setColumns(10);
		
		textFieldCustoUnitario = new JTextField();
		textFieldCustoUnitario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoCustoUnitario()) {
					textFieldCustoUnitario.requestFocus();
				}
				else {
					digitacaoCustoUnitarioValido();
				}
			}
		});
		textFieldCustoUnitario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoCustoUnitario()) {
						textFieldCustoUnitario.requestFocus();
					}
					else {
						digitacaoCustoUnitarioValido();
					}
				}
			}
		});
		textFieldCustoUnitario.setColumns(10);
		
		checkNome = new JLabel("");
		checkNome.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkQuantidadeEstoque = new JLabel("");
		checkQuantidadeEstoque.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkCustoUnitario = new JLabel("");
		checkCustoUnitario.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirIngrediente();
			}
		});
		btnIncluir.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/application_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarIngrediente();
			}
		});
		btnAlterar.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/application_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirIngrediente();
			}
		});
		btnExcluir.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/application_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_3)
										.addComponent(lblNewLabel_2))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(textFieldCustoUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(checkCustoUnitario))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(textFieldQuantidadeEstoque, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(checkQuantidadeEstoque))))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(77)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(checkNome))
										.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkNome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textFieldQuantidadeEstoque, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkQuantidadeEstoque))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textFieldCustoUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkCustoUnitario))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap(108, Short.MAX_VALUE))
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
		textFieldQuantidadeEstoque.requestFocus();	
	}
	
	private void mudaStatusCheckNome() {
		checkNome.setVisible(true);
		if(status == false) {
			checkNome.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkNome.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDaQuantidadeEstoque() {

		if (VariaveisProjeto.digitacaoCampo(textFieldQuantidadeEstoque.getText())) {
			status = false;
			mudaStatusCheckQuantidadeEstoque();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoQuantidadeEstoqueValida() {
		status = true;
		mudaStatusCheckQuantidadeEstoque();
		checkQuantidadeEstoque.setVisible(true);
		textFieldCustoUnitario.requestFocus();	
	}
	
	private void mudaStatusCheckQuantidadeEstoque() {
		checkQuantidadeEstoque.setVisible(true);
		if(status == false) {
			checkQuantidadeEstoque.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkQuantidadeEstoque.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDoCustoUnitario() {

		if (VariaveisProjeto.digitacaoCampo(textFieldCustoUnitario.getText())) {
			status = false;
			mudaStatusCheckCustoUnitario();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoCustoUnitarioValido() {
		status = true;
		mudaStatusCheckCustoUnitario();
		checkCustoUnitario.setVisible(true);
	}
	
	private void mudaStatusCheckCustoUnitario() {
		checkCustoUnitario.setVisible(true);
		if(status == false) {
			checkCustoUnitario.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkCustoUnitario.setIcon(new ImageIcon(IngredienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}

	private void desabilitaCheckCampos() {
		checkNome.setVisible(false);
		checkQuantidadeEstoque.setVisible(false);
		checkCustoUnitario.setVisible(false);
	}
	
	private void excluirIngrediente() {
		
		Integer toReturn = 0;
		
		Ingrediente ingrediente = pegarDadosIngrediente();
		
		IngredienteService ingredienteService = new IngredienteService();
		
		toReturn = ingredienteService.delete(ingrediente);
		
		if (toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na exclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Exclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaIngredienteModel.fireTableDataChanged();
			ingrediente = new Ingrediente();
		}
	}
	
	protected void incluirIngrediente() {
		
		Integer toReturn = 0; 
		
		Ingrediente ingrediente = pegarDadosIngrediente();
		
		IngredienteService ingredienteService = new IngredienteService();
		
		toReturn = ingredienteService.save(ingrediente);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na inclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaIngredienteModel.saveIngrediente(ingrediente);
			tabelaIngrediente.setModel(tabelaIngredienteModel);
			tabelaIngredienteModel.fireTableDataChanged();
			ingrediente = new Ingrediente();
		}
	}
	
	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem,status, option);
	}
	
	protected void alterarIngrediente() {
		
		Integer toReturn = 0;
		
		Ingrediente ingrediente = pegarDadosIngrediente();
			
		IngredienteService ingredienteService = new IngredienteService();
		
		toReturn = ingredienteService.update(ingrediente);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na alteração do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			tabelaIngredienteModel.updateIngrediente(ingrediente, this.linha);
			tabelaIngrediente.setModel(tabelaIngredienteModel);
			tabelaIngredienteModel.fireTableDataChanged();
			limpaTextoCampo();
			ingrediente = new Ingrediente();
		}
	}
	
	private void erroDigitacao(Integer toReturn) {
		if (toReturn == VariaveisProjeto.CLIENTE_NOME) {
			status = false;
			mudaStatusCheckNome();
			showMensagem("Erro na digitação do nome, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.CLIENTE_TELEFONE) {
			status = false;
			mudaStatusCheckQuantidadeEstoque();
			showMensagem("Erro na digitação da quantidade no estoque, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.CLIENTE_BAIRRO) {
			status = false;
			mudaStatusCheckCustoUnitario();
			showMensagem("Erro na digitação do custo unitário, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buscarIngrediente() {
		
		Ingrediente ingrediente = new Ingrediente();
					
		ingrediente = tabelaIngredienteModel.getIngrediente(this.linha);
		
		textFieldCodigo.setText(String.valueOf(ingrediente.getId()));
		textFieldNome.setText(ingrediente.getNome());
		textFieldQuantidadeEstoque.setText(String.valueOf(ingrediente.getQuantidade_estoque()));
		textFieldCustoUnitario.setText(String.valueOf(ingrediente.getCusto_unitario()));
				
	}
	
	public Ingrediente pegarDadosIngrediente() {
		
		Ingrediente ingrediente = new Ingrediente();
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false ) {
			ingrediente.setId( Integer.valueOf(textFieldCodigo.getText()));
		}
		
		ingrediente.setNome(textFieldNome.getText());
		ingrediente.setQuantidade_estoque(Integer.valueOf(textFieldQuantidadeEstoque.getText()));
		ingrediente.setCusto_unitario(Double.valueOf(textFieldCustoUnitario.getText()));
		
	    return ingrediente;
	}
	
	private void limpaTextoCampo() {

		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNome.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldQuantidadeEstoque.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldCustoUnitario.setText(VariaveisProjeto.LIMPA_CAMPO);
		
	}
}
