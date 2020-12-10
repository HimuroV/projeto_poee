package com.projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Cliente;
import com.projeto.model.models.Usuario;
import com.projeto.model.service.ClienteService;
import com.projeto.model.service.UsuarioService;
import com.projeto.view.usuario.UsuarioGUI;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteGUI extends JFrame {

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
	
	private boolean status = true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteGUI frame = new ClienteGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClienteGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Código");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarCliente();
			}
		});
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarCliente();
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
								.addComponent(checkNumero)
								.addComponent(checkRua)
								.addComponent(checkBairro)
								.addComponent(checkTelefone)
								.addComponent(checkNome)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
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
						.addComponent(checkNumero))
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
			checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
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
			checkTelefone.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkTelefone.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
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
			checkBairro.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkBairro.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
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
			checkRua.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkRua.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
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
			checkNumero.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkNumero.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
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
		
		Cliente cliente = pegarDadosCliente();
		
		ClienteService clienteService = new ClienteService();
		
		clienteService.delete(cliente);
		
		limpaTextoCampo();
	}
	
	protected void incluirCliente() {
		
		Cliente cliente = pegarDadosCliente();
		
		ClienteService clienteService = new ClienteService();
		
		clienteService.save(cliente);
		
		limpaTextoCampo();
	}
	
	private void fecharCliente() {
		dispose();
	}
	
	protected void alterarCliente() {
		
		Cliente cliente = pegarDadosCliente();
		
		ClienteService clienteService = new ClienteService();
		
		clienteService.update(cliente);
		
		limpaTextoCampo();
		
	}
	
	private void buscarCliente() {
		
		Cliente cliente = new Cliente();
				
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
			return;
		}
		
			
		Integer id = Integer.valueOf(textFieldCodigo.getText());
		
		ClienteService clienteService = new ClienteService();
		
		cliente = clienteService.findById(id);
		
		textFieldNome.setText(cliente.getNome());
		textFieldTelefone.setText(cliente.getTelefone());
		textFieldBairro.setText(cliente.getBairro());
		textFieldRua.setText(cliente.getRua());
		textFieldNumero.setText(cliente.getNumero());
				
	}
	
	public Cliente pegarDadosCliente() {
		
		Cliente cliente = new Cliente();
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		
		
		cliente.setId( VariaveisProjeto.converteToInteger(textFieldCodigo.getText()));
		cliente.setNome(textFieldNome.getText());
		cliente.setTelefone(textFieldTelefone.getText());
		cliente.setBairro(textFieldBairro.getText());
		cliente.setRua(textFieldRua.getText());
		cliente.setNome(textFieldNome.getText());
		   
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
