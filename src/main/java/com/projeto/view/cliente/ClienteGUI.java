package com.projeto.view.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.estrutura.util.imagem.ImageFilter;
import com.projeto.estrutura.util.imagem.ImagePreview;
import com.projeto.model.models.Cliente;
import com.projeto.model.models.Foto;
import com.projeto.model.service.ClienteService;
import com.projeto.model.service.LocalFotoStorageService;
import com.projeto.view.cliente.TabelaClienteModel;
import com.projeto.view.cliente.ClienteGUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteGUI extends JDialog {

	private static final long serialVersionUID = 2374618124149828267L;
	private JPanel contentPane;
	private JTextField textFieldCodigo;
	private JTextField textFieldNome;
	private JTextField textFieldTelefone;
	private JTextField textFieldBairro;
	private JTextField textFieldRua;
	private JTextField textFieldNumero;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	
	private JLabel checkNome;
	private JLabel checkTelefone;
	private JLabel checkBairro;
	private JLabel checkRua;
	private JLabel checkNumero;
	
	private JTable tabelaCliente;
	private TabelaClienteModel tabelaClienteModel;
	private int linha = 0;
	private int acao = 0;
	
	private String nomeFoto;
	
	private boolean status = true;
	private JLabel lblIconFoto;
	private JButton btnFoto;
	private JButton btnFecharFoto;
	
	public ClienteGUI(JFrame frame, boolean modal, JTable tabelaCliente, TabelaClienteModel tabelaClienteModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();
		
		this.tabelaCliente = tabelaCliente;
		this.tabelaClienteModel = tabelaClienteModel;
		this.linha = linha;
		this.acao = acao;
				
		limpaTextoCampo();
		
		desabilitaCheckCampos();
		
		configuraAcaoCliente();
		
	}
	
	private void configuraAcaoCliente() {
		
		if (this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if (this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarCliente();
		}
		if (this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnIncluir.setVisible(false);
			btnAlterar.setVisible(false);
			buscarCliente();
		}
	}
	
	private void initComponents() {
		setBounds(100, 100, 669, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Código");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setEditable(false);
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//buscarCliente();
			}
		});
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					//buscarCliente();
					textFieldNome.requestFocus();
				}
			}
		});
		textFieldCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		
		textFieldNome = new JTextField();
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
		textFieldNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone");
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoTelefone()) {
						textFieldTelefone.requestFocus();
					}
					else {
						digitacaoTelefoneValido();
					}
				}
			}
		});
		textFieldTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoTelefone()) {
					textFieldTelefone.requestFocus();
				}
				else {
					digitacaoTelefoneValido();
				}
			}
		});
		textFieldTelefone.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Bairro");
		
		textFieldBairro = new JTextField();
		textFieldBairro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoBairro()) {
						textFieldBairro.requestFocus();
					}
					else {
						digitacaoBairroValido();
					}
				}
			}
		});
		textFieldBairro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoBairro()) {
					textFieldBairro.requestFocus();
				}
				else {
					digitacaoBairroValido();
				}
			}
		});
		textFieldBairro.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Rua");
		
		textFieldRua = new JTextField();
		textFieldRua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDaRua()) {
						textFieldRua.requestFocus();
					}
					else {
						digitacaoRuaValida();
					}
				}
			}
		});
		textFieldRua.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDaRua()) {
					textFieldRua.requestFocus();
				}
				else {
					digitacaoRuaValida();
				}
			}
		});
		textFieldRua.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Número");
		
		textFieldNumero = new JTextField();
		textFieldNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoNumero()) {
						textFieldNumero.requestFocus();
					}
					else {
						digitacaoNumeroValido();
					}
				}
			}
		});
		textFieldNumero.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoNumero()) {
					textFieldNumero.requestFocus();
				}
				else {
					digitacaoNumeroValido();
				}
			}
		});
		textFieldNumero.setColumns(10);
		
		checkNome = new JLabel("");
		checkNome.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkTelefone = new JLabel("");
		checkTelefone.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkBairro = new JLabel("");
		checkBairro.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkRua = new JLabel("");
		checkRua.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkNumero = new JLabel("");
		checkNumero.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirCliente();
			}
		});
		btnIncluir.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}
		});
		btnAlterar.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharCliente();
			}
		});
		btnSair.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		
		lblIconFoto = new JLabel("");
		
		btnFoto = new JButton("");
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarFoto();
			}
		});
		btnFoto.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/useravatar.png")));
		
		btnFecharFoto = new JButton("");
		btnFecharFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFoto();
			}
		});
		btnFecharFoto.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(60)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_5))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textFieldRua)
									.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textFieldBairro)
									.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
									.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(checkNome)
									.addComponent(checkBairro)
									.addComponent(checkTelefone))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(checkNumero)
									.addComponent(checkRua)))
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(21)
									.addComponent(btnFoto)
									.addGap(18)
									.addComponent(btnFecharFoto))
								.addComponent(lblIconFoto, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIconFoto, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkNome))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_2)
										.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(checkBairro)
										.addComponent(lblNewLabel_3))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_4)
										.addComponent(textFieldRua, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(checkRua)))
								.addComponent(checkTelefone))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(textFieldNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_5))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(checkNumero)
									.addComponent(btnFoto)
									.addComponent(btnFecharFoto)))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		limpaTextoCampo();
		
		desabilitaCheckCampos();
	}
	
	protected void excluirFoto() {
		Cliente cliente = tabelaClienteModel.getCliente(this.linha);
		nomeFoto = cliente.getFoto();
		LocalFotoStorageService localFotoStorageService = new LocalFotoStorageService();
		localFotoStorageService.remover(nomeFoto);
		lblIconFoto.setIcon(null);
		lblIconFoto.revalidate();
		
	}
	
private void carregarFoto() {
		
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new ImageFilter());
		fc.setAcceptAllFileFilterUsed(false);
		fc.setAccessory(new ImagePreview(fc));
		int returnVal = fc.showDialog(lblIconFoto, "Anexar");
		
		if(lblIconFoto.getIcon() != null) {
			excluirFoto();
		}
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fc.getSelectedFile();
				FileInputStream fin = new FileInputStream(file);
				BufferedImage img = ImageIO.read(fin);
				ImageIcon icon = new ImageIcon(img);
				lblIconFoto.setIcon(icon);
				lblIconFoto.setHorizontalAlignment(SwingConstants.CENTER);
				LocalFotoStorageService localFotoStorageService = new LocalFotoStorageService();
				Foto foto = new Foto();
				foto.setNomeArquivo(file.getName());
				foto.setInputStream(fin);
				foto.setFile(file);
				nomeFoto = localFotoStorageService.armazenar(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
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
		textFieldTelefone.requestFocus();	
	}
	
	private void mudaStatusCheckNome() {
		checkNome.setVisible(true);
		if(status == false) {
			checkNome.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkNome.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDoTelefone() {

		if (VariaveisProjeto.digitacaoCampo(textFieldTelefone.getText())) {
			status = false;
			mudaStatusCheckTelefone();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoTelefoneValido() {
		status = true;
		mudaStatusCheckTelefone();
		checkTelefone.setVisible(true);
		textFieldBairro.requestFocus();	
	}
	
	private void mudaStatusCheckTelefone() {
		checkTelefone.setVisible(true);
		if(status == false) {
			checkTelefone.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkTelefone.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDoBairro() {

		if (VariaveisProjeto.digitacaoCampo(textFieldBairro.getText())) {
			status = false;
			mudaStatusCheckBairro();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoBairroValido() {
		status = true;
		mudaStatusCheckBairro();
		checkBairro.setVisible(true);
		textFieldRua.requestFocus();	
	}
	
	private void mudaStatusCheckBairro() {
		checkBairro.setVisible(true);
		if(status == false) {
			checkBairro.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkBairro.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDaRua() {

		if (VariaveisProjeto.digitacaoCampo(textFieldRua.getText())) {
			status = false;
			mudaStatusCheckRua();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoRuaValida() {
		status = true;
		mudaStatusCheckRua();
		checkRua.setVisible(true);
		textFieldNumero.requestFocus();	
	}
	
	private void mudaStatusCheckRua() {
		checkRua.setVisible(true);
		if(status == false) {
			checkRua.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkRua.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private boolean verificaDigitacaoDoNumero() {

		if (VariaveisProjeto.digitacaoCampo(textFieldNumero.getText())) {
			status = false;
			mudaStatusCheckNumero();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoNumeroValido() {
		status = true;
		mudaStatusCheckNumero();
		checkNumero.setVisible(true);
	}
	
	private void mudaStatusCheckNumero() {
		checkNumero.setVisible(true);
		if(status == false) {
			checkNumero.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkNumero.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void desabilitaCheckCampos() {
		checkNome.setVisible(false);
		checkTelefone.setVisible(false);
		checkBairro.setVisible(false);
		checkRua.setVisible(false);
		checkNumero.setVisible(false);
		
	}
	
	private void excluirCliente() {
		
		Integer toReturn = 0;
		
		Cliente cliente = pegarDadosCliente();
		
		ClienteService clienteService = new ClienteService();
		
		toReturn = clienteService.delete(cliente);
		
		if (toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na exclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Exclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaClienteModel.fireTableDataChanged();
			cliente = new Cliente();
		}
	}
	
	protected void incluirCliente() {
		
		Integer toReturn = 0; 
		
		Cliente cliente = pegarDadosCliente();
		
		ClienteService clienteService = new ClienteService();
		
		toReturn = clienteService.save(cliente);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na inclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaClienteModel.saveCliente(cliente);
			tabelaCliente.setModel(tabelaClienteModel);
			tabelaClienteModel.fireTableDataChanged();
			cliente = new Cliente();
		}
	}
	
	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem,status, option);
	}
	
	private void fecharCliente() {
		dispose();
	}
	
	protected void alterarCliente() {
		
		Integer toReturn = 0;
		
		Cliente cliente = pegarDadosCliente();
			
		ClienteService clienteService = new ClienteService();
		
		toReturn = clienteService.update(cliente);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na alteração do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			tabelaClienteModel.updateCliente(cliente, this.linha);
			tabelaCliente.setModel(tabelaClienteModel);
			tabelaClienteModel.fireTableDataChanged();
			limpaTextoCampo();
			cliente = new Cliente();
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
			mudaStatusCheckTelefone();
			showMensagem("Erro na digitação do telefone, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.CLIENTE_BAIRRO) {
			status = false;
			mudaStatusCheckBairro();
			showMensagem("Erro na digitação do bairro, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.CLIENTE_RUA) {
			status = false;
			mudaStatusCheckRua();
			showMensagem("Erro na digitação da rua, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.CLIENTE_NUMERO) {
			status = false;
			mudaStatusCheckNumero();
			showMensagem("Erro na digitação do número, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buscarCliente() {
		
		Cliente cliente = new Cliente();
					
		cliente = tabelaClienteModel.getCliente(this.linha);
		
		textFieldCodigo.setText(String.valueOf(cliente.getId()));
		textFieldNome.setText(cliente.getNome());
		textFieldTelefone.setText(cliente.getTelefone());
		textFieldBairro.setText(cliente.getBairro());
		textFieldRua.setText(cliente.getRua());
		textFieldNumero.setText(cliente.getNumero());
		nomeFoto = cliente.getFoto();
		
		if (!Objects.isNull(nomeFoto)) {
			LocalFotoStorageService localFotoStorageService = new LocalFotoStorageService();
			String fileInput = localFotoStorageService.recuperar(nomeFoto);
			File file = new File(fileInput);
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				BufferedImage img = ImageIO.read(fis);
				ImageIcon imagem = new ImageIcon(img);
				lblIconFoto.setIcon(imagem);
				lblIconFoto.setHorizontalAlignment(SwingConstants.CENTER);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				
	}
	
	public Cliente pegarDadosCliente() {
		
		Cliente cliente = new Cliente();
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false ) {
			cliente.setId( Integer.valueOf(textFieldCodigo.getText()));
		}
		
		cliente.setNome(textFieldNome.getText());
		cliente.setTelefone(textFieldTelefone.getText());
		cliente.setBairro(textFieldBairro.getText());
		cliente.setRua(textFieldRua.getText());
		cliente.setNumero(textFieldNumero.getText());
		cliente.setFoto(nomeFoto);
		   
	    return cliente;
	}
	
	private void limpaTextoCampo() {

		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNome.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldTelefone.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldBairro.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldRua.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNumero.setText(VariaveisProjeto.LIMPA_CAMPO);
		
	}
}
