package com.projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Usuario;
import com.projeto.model.service.UsuarioService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UsuarioGUI extends JFrame {

	
	
	/**
	 * 
	 */
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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioGUI frame = new UsuarioGUI();
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
	public UsuarioGUI() {
		setTitle("Cadastro de Usuário");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNome = new JLabel("Nome");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		
		passwordFieldSenha = new JPasswordField();
		
		rdbtnAtivo = new JRadioButton("Ativo");
		
		rdbtnAdmin = new JRadioButton("Admin");
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirUsuario();
			}
		});
		
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
		});
		
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharUsuario();
			}
		});
		
		lblNewLabel = new JLabel("Código");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarUsuario();
			}

		});
		textFieldCodigo.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEmail)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldEmail))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSenha)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtnAtivo)
									.addGap(18)
									.addComponent(rdbtnAdmin))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnIncluir)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnAlterar)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnExcluir)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSair))
								.addComponent(passwordFieldSenha)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(lblNome))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNome))))
					.addContainerGap(97, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnAtivo)
						.addComponent(rdbtnAdmin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	private void excluirUsuario() {
		
		Usuario usuario = pegarDadosUsuario();
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.delete(usuario);
	}
	
	protected void incluirUsuario() {
		
		Usuario usuario = pegarDadosUsuario();
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.save(usuario);
	}
	
	private void fecharUsuario() {
		dispose();
	}
	
	protected void alterarUsuario() {
		
		Usuario usuario = pegarDadosUsuario();
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.update(usuario);
		
	}
	
	private void buscarUsuario() {
		
		Usuario usuario = new Usuario();
				
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
			return;
		}
		
			
		Integer id = Integer.valueOf(textFieldCodigo.getText());
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuario = usuarioService.findById(id);
		
		textFieldNome.setText(usuario.getUsername());
		textFieldEmail.setText(usuario.getEmail());
		passwordFieldSenha.setText(usuario.getPassword());
		
		if(usuario.isAdmin()) {
			rdbtnAdmin.setSelected(true);
		}
		
		if(usuario.isAtivo()) {
			rdbtnAtivo.setSelected(true);
		}
		
	}
	
	public Usuario pegarDadosUsuario() {
		
		Usuario usuario = new Usuario();
		
		if(!"".equals(textFieldCodigo.getText())) {
			usuario.setId(Integer.valueOf(textFieldCodigo.getText()));
		}
		
		usuario.setId( Integer.valueOf(textFieldCodigo.getText()));
		usuario.setUsername(textFieldNome.getText());
		usuario.setEmail(textFieldEmail.getText());
		usuario.setPassword(passwordFieldSenha.getText());
		
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
	

	
}
