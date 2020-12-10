package com.projeto.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.view.menu.Menu;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	
	private static final long serialVersionUID = -7595674081554841251L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField textFieldEmail;
	private JLabel lblNewLabel_1;
	private JTextField textFieldSenha;
	private JButton btnAcesso;
	private JButton btnCancelar;
	private JButton btnSair;
	
	private static Login frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
	public Login() {
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1087, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(153, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 769, GroupLayout.PREFERRED_SIZE)
					.addGap(139))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		
		lblNewLabel = new JLabel("E-mail:");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Senha:");
		
		textFieldSenha = new JTextField();
		textFieldSenha.setColumns(10);
		
		btnAcesso = new JButton("Acesso");
		btnAcesso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		btnAcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginSistema();
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldEmail.requestFocus();
			}
		});
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(80)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(159)
							.addComponent(btnAcesso)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSair))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textFieldSenha)
							.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)))
					.addContainerGap(107, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(70)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAcesso)
						.addComponent(btnCancelar)
						.addComponent(btnSair))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	protected void loginSistema() {
		acessoMenu();
		
	}

	private void acessoMenu() {
		Menu menu = new Menu(frame);
		menu.setVisible(true);
		menu.setResizable(false);
		menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}
}
