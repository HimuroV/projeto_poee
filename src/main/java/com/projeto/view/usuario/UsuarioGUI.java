package com.projeto.view.usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.estrutura.util.imagem.ImageFilter;
import com.projeto.estrutura.util.imagem.ImagePreview;
import com.projeto.model.models.Departamento;
import com.projeto.model.models.Foto;
import com.projeto.model.models.Usuario;
import com.projeto.model.service.DepartamentoService;
import com.projeto.model.service.LocalFotoStorageService;
import com.projeto.model.service.UsuarioService;
import com.projeto.view.departamento.BuscaDepartamento;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

public class UsuarioGUI extends JDialog {
	
	private static final long serialVersionUID = -2638637223036799312L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JRadioButton rdbtnAtivo;
	private JRadioButton rdbtnAdmin;
	private JButton btnSair;
	private JPasswordField passwordFieldSenha;
	private JLabel lblNewLabel;
	private JTextField textFieldCodigo;
	
	private JLabel checkNome;
	private JLabel checkEmail;
	private JLabel checkSenha;
	
	private boolean status = true;
	
	private JTable tabelaUsuario;
	private TabelaUsuarioModel tabelaUsuarioModel;
	private int linha = 0;
	private int acao = 0;
	private JLabel lblDepartamento;
	private JTextField textFieldNomeDepartamento;
	private JButton btnNewButton;
	
	private Departamento departamento;
	private JPanel panel;
	private JButton btnNewButton_1;
	private JLabel lblIconFoto;
	private JButton btnFoto;
	private JButton btnFecharFoto;
	
	private String nomeFoto;
	
	public UsuarioGUI(JFrame frame, boolean modal, JTable tabelaUsuario, TabelaUsuarioModel tabelaUsuarioModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();
		
		this.tabelaUsuario = tabelaUsuario;
		this.tabelaUsuarioModel = tabelaUsuarioModel;
		this.linha = linha;
		this.acao = acao;
				
		limpaTextoCampo();
		
		desabilitaCheckCampos();
		
		configuraAcaoUsuario();
	}
	
	
	private void configuraAcaoUsuario() {
		
		if (this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if (this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarUsuario();
		}
		if (this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnIncluir.setVisible(false);
			btnAlterar.setVisible(false);
			buscarUsuario();
		}
	}


	private void initComponents() {
		setTitle("Cadastro de Usuário");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNome = new JLabel("Nome");
		
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
		
		JLabel lblEmail = new JLabel("Email");
		
		textFieldEmail = new JTextField();
		textFieldEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDoEmail()) {
					textFieldEmail.requestFocus();
				}
				else {
					digitacaoEmailValido();
				}
			}
		});
		textFieldEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDoEmail()) {
						textFieldEmail.requestFocus();
					}
					else {
						digitacaoEmailValido();
					}
				}
				
			}
		});
		textFieldEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (verificaDigitacaoDaSenha()) {
					passwordFieldSenha.requestFocus();
				}
				else {
					digitacaoSenhaValida();
				}
			}
		});
		passwordFieldSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaDigitacaoDaSenha()) {
						passwordFieldSenha.requestFocus();
					}
					else {
						digitacaoSenhaValida();
					}
				}
				
			}
		});
		
		rdbtnAtivo = new JRadioButton("Ativo");
		rdbtnAtivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					rdbtnAdmin.requestFocus();
				}
				
			}
		});
		
		rdbtnAdmin = new JRadioButton("Admin");
		
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_form_add.png")));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirUsuario();
			}
		});
		
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_edit.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
		});
		
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		
		btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharUsuario();
			}
		});
		
		lblNewLabel = new JLabel("Código");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					//buscarUsuario();
					textFieldNome.requestFocus();
				}
				
			}
		});
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//buscarUsuario();
			}

		});
		textFieldCodigo.setColumns(10);
		
		checkNome = new JLabel("");
		checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkEmail = new JLabel("");
		checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkSenha = new JLabel("");
		checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		lblDepartamento = new JLabel("Departamento");
		
		textFieldNomeDepartamento = new JTextField();
		textFieldNomeDepartamento.setEditable(false);
		textFieldNomeDepartamento.setColumns(10);
		
		btnNewButton = new JButton("Departamento");
		btnNewButton.setMnemonic(KeyEvent.VK_D);
		btnNewButton.setToolTipText("Buscar Departamento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscaDepartamento();
			}

		});
		btnNewButton.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		lblIconFoto = new JLabel("");
		
		btnFoto = new JButton("");
		btnFoto.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/class.png")));
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarFoto();
			}

		});
		
		btnFecharFoto = new JButton("");
		btnFecharFoto.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		btnFecharFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFoto();
			}
		});


		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(53)
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(61)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(rdbtnAtivo)
											.addGap(32)
											.addComponent(rdbtnAdmin))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(btnIncluir)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnAlterar)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnExcluir)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnSair)))))
							.addPreferredGap(ComponentPlacement.RELATED, 133, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(46)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSenha)
								.addComponent(lblDepartamento)
								.addComponent(lblEmail)
								.addComponent(lblNome))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordFieldSenha, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
								.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
								.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldNomeDepartamento, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton)))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(checkSenha, 0, 0, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(checkEmail, 0, 0, Short.MAX_VALUE)
									.addComponent(checkNome, GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblIconFoto, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnFoto)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFecharFoto)))
					.addContainerGap(68, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkNome, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkEmail, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail)
								.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblSenha)
									.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(checkSenha, 0, 0, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblIconFoto, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblDepartamento)
									.addComponent(textFieldNomeDepartamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnNewButton)
									.addComponent(btnFoto)))
							.addGap(34)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnAtivo)
								.addComponent(rdbtnAdmin))
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnAlterar)
									.addComponent(btnExcluir)
									.addComponent(btnSair)
									.addComponent(btnIncluir))
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnFecharFoto))
					.addContainerGap(56, Short.MAX_VALUE))
		);
		
		btnNewButton_1 = new JButton("Relatório");
		btnNewButton_1.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/pdf.png")));
		btnNewButton_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimeRelatorio();
			}

			
		});
		panel.add(btnNewButton_1);
		contentPane.setLayout(gl_contentPane);
		
		/*
		 * btnAlterar.setEnabled(false); btnIncluir.setEnabled(false);
		 * btnExcluir.setEnabled(false);
		 */
	}
	
	protected void excluirFoto() {
		Usuario usuario = tabelaUsuarioModel.getUsuario(this.linha);
		nomeFoto = usuario.getFoto();
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
	
	private void imprimeRelatorio() {       //esse n é aqui, é na tabela :p
		RelUsuario relUsuario = new RelUsuario(new JFrame(), true);
		relUsuario.setLocationRelativeTo(null);
		setVisible(false);
		relUsuario.setVisible(true);
	}
	
	protected void buscaDepartamento() {
		departamento = new Departamento();
		BuscaDepartamento buscaDepartamento = new BuscaDepartamento(new JFrame(), true);
		buscaDepartamento.setLocationRelativeTo(null);
		buscaDepartamento.setVisible(true);
		
		if(buscaDepartamento.isSelectDepartamento()) {
			DepartamentoService departamentoService = new DepartamentoService();
			departamento = departamentoService.findById(buscaDepartamento.getCodigoDepartamento()); 
			textFieldNomeDepartamento.setText(departamento.getNome());
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
		textFieldEmail.requestFocus();	
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
	
	private boolean verificaDigitacaoDoEmail() {

		if (VariaveisProjeto.digitacaoCampo(textFieldEmail.getText())) {
			status = false;
			mudaStatusCheckEmail();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoEmailValido() {
		status = true;
		mudaStatusCheckEmail();
		checkEmail.setVisible(true);
		passwordFieldSenha.requestFocus();	
	}
	
	private void mudaStatusCheckEmail() {
		checkEmail.setVisible(true);
		if(status == false) {
			checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	@SuppressWarnings("deprecation")
	private boolean verificaDigitacaoDaSenha() {

		if (VariaveisProjeto.digitacaoCampo(passwordFieldSenha.getText())) {
			status = false;
			mudaStatusCheckSenha();
			return true;
		}
		
		return false;
		
	}
	
	private void digitacaoSenhaValida() {
		status = true;
		mudaStatusCheckSenha();
		checkSenha.setVisible(true);
		rdbtnAtivo.requestFocus();	
	}
	
	private void mudaStatusCheckSenha() {
		checkSenha.setVisible(true);
		if(status == false) {
			checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		}
		else {
			checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void desabilitaCheckCampos() {
		checkNome.setVisible(false);
		checkEmail.setVisible(false);
		checkSenha.setVisible(false);
		
	}

	private void excluirUsuario() {
		
		Integer toReturn = 0;
		
		Usuario usuario = pegarDadosUsuario();
		
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.delete(usuario);
		
		if (toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na exclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Exclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaUsuarioModel.fireTableDataChanged();
			usuario = new Usuario();
		}
	}
	
	protected void incluirUsuario() {
		
		Integer toReturn = 0; 
		
		Usuario usuario = pegarDadosUsuario();
		
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.save(usuario);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na inclusão do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaUsuarioModel.fireTableDataChanged();
			usuario = new Usuario();
		}
	}

	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem,"Erro", option);
	}
	

	private void fecharUsuario() {
		dispose();
	}
	
	protected void alterarUsuario() {
		Integer toReturn = 0;
		
		Usuario usuario = pegarDadosUsuario();
			
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.update(usuario);
		
		erroDigitacao(toReturn);
		
		if (toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na alteração do registro, verifique com seu administrador!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!", "OK", JOptionPane.OK_OPTION);
			
			tabelaUsuarioModel.fireTableDataChanged();
			
			limpaTextoCampo();
			usuario = new Usuario();
		}
	}

	private void erroDigitacao(Integer toReturn) {
		if (toReturn == VariaveisProjeto.USUARIO_USERNAME) {
			status = false;
			mudaStatusCheckNome();
			showMensagem("Erro na digitação do nome, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.USUARIO_EMAIL) {
			status = false;
			mudaStatusCheckNome();
			showMensagem("Erro na digitação do e-mail, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if (toReturn == VariaveisProjeto.USUARIO_PASSWORD) {
			status = false;
			mudaStatusCheckNome();
			showMensagem("Erro na digitação da senha, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void buscarUsuario() {
		
		Usuario usuario = new Usuario();
				
		usuario = tabelaUsuarioModel.getUsuario(this.linha);
		
		textFieldCodigo.setText(String.valueOf(usuario.getId()));
		textFieldNome.setText(usuario.getUsername());
		textFieldEmail.setText(usuario.getEmail());
		passwordFieldSenha.setText(usuario.getPassword());
		textFieldNomeDepartamento.setText(usuario.getDepartamento().getNome());
		nomeFoto = usuario.getFoto();
		departamento = new Departamento();
		departamento.setId(usuario.getDepartamento().getId());
		departamento.setNome(usuario.getDepartamento().getNome());
		
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
		if(usuario.isAdmin()) {
			rdbtnAdmin.setSelected(true);
		}
		
		if(usuario.isAtivo()) {
			rdbtnAtivo.setSelected(true);
		}
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	public Usuario pegarDadosUsuario() {
		
		Usuario usuario = new Usuario();
		
	
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false ) {
			usuario.setId( Integer.valueOf(textFieldCodigo.getText()));
		}
		
		usuario.setUsername(textFieldNome.getText());
		usuario.setEmail(textFieldEmail.getText());
		usuario.setPassword(passwordFieldSenha.getText());
		usuario.setDepartamento(departamento);
		usuario.setFoto(nomeFoto);
		
		
	    if(rdbtnAtivo.isSelected()) {
	    	usuario.setAtivo(true);
	    } else {
	    	usuario.setAtivo(false);
	    }
		
	    if(rdbtnAdmin.isSelected()) {
	    	usuario.setAdmin(true);
	    }else {
	    	usuario.setAdmin(false);
	    }
	    
	    
		return usuario;
	}
	
	private void limpaTextoCampo() {

		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNome.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldEmail.setText(VariaveisProjeto.LIMPA_CAMPO);
		passwordFieldSenha.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNomeDepartamento.setText(VariaveisProjeto.LIMPA_CAMPO);
		rdbtnAdmin.setSelected(false);
		rdbtnAtivo.setSelected(false);
	}
}
