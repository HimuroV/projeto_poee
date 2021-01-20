package com.projeto.view.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.main.Login;
import com.projeto.view.ItemPedido.TabelaItemPedido;
import com.projeto.view.cliente.TabelaCliente;
import com.projeto.view.ingrediente.TabelaIngrediente;
import com.projeto.view.pedido.TabelaPedido;
import com.projeto.view.produto.TabelaProduto;
import com.projeto.view.usuario.TabelaUsuario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {


	private static final long serialVersionUID = 473637651720341513L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu arquivo;
	private JMenuItem usuario;
	private JMenuItem logout;
	
	
	private Login login;
	private JMenu sair;
	private JMenuItem sair_sistema;
	private JMenuItem Cliente;
	private JMenuItem Pedido;
	private JMenuItem ItemPedido;
	private JMenuItem Produto;
	private JMenuItem Ingrediente;

	public Menu(Login login) {
		this.login = login;
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1241, 724);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		arquivo = new JMenu("Arquivo");
		menuBar.add(arquivo);
		
		usuario = new JMenuItem("Usuário");
		usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaUsuario tabelaUsuario = new TabelaUsuario();
				centralizaForm(tabelaUsuario);
				contentPane.add(tabelaUsuario);
				tabelaUsuario.setVisible(true);
			}
		});
		
		Cliente = new JMenuItem("Cliente");
		Cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaCliente tabelaCliente = new TabelaCliente();
				centralizaForm(tabelaCliente);
				contentPane.add(tabelaCliente);
				tabelaCliente.setVisible(true);
			}
		});
		arquivo.add(Cliente);
		
		Pedido = new JMenuItem("Pedido");
		Pedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaPedido tabelaPedido = new TabelaPedido();
				centralizaForm(tabelaPedido);
				contentPane.add(tabelaPedido);
				tabelaPedido.setVisible(true);
			}
		});
		arquivo.add(Pedido);
		
		ItemPedido = new JMenuItem("Item Pedido");
		ItemPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaItemPedido tabelaItemPedido = new TabelaItemPedido();
				centralizaForm(tabelaItemPedido);
				contentPane.add(tabelaItemPedido);
				tabelaItemPedido.setVisible(true);
			}
		});
		arquivo.add(ItemPedido);
		
		Produto = new JMenuItem("Produto");
		Produto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaProduto tabelaProduto = new TabelaProduto();
				centralizaForm(tabelaProduto);
				contentPane.add(tabelaProduto);
				tabelaProduto.setVisible(true);
			}
		});
		arquivo.add(Produto);
		
		Ingrediente = new JMenuItem("Ingrediente");
		Ingrediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaIngrediente tabelaIngrediente = new TabelaIngrediente();
				centralizaForm(tabelaIngrediente);
				contentPane.add(tabelaIngrediente);
				tabelaIngrediente.setVisible(true);
			}
		});
		arquivo.add(Ingrediente);
		arquivo.add(usuario);
		
		logout = new JMenuItem("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				login.setVisible(true);
				login.setLocationRelativeTo(null);
			}
		});
		arquivo.add(logout);
		
		sair = new JMenu("Sair");
		menuBar.add(sair);
		
		sair_sistema = new JMenuItem("Sair");
		sair_sistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		sair.add(sair_sistema);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1215, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 675, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void centralizaForm(JInternalFrame frame) {
		Dimension desktopSize = this.getSize();
		Dimension internalFrameSize = frame.getSize();
		frame.setLocation((desktopSize.width - internalFrameSize.width) / 2, (desktopSize.height - internalFrameSize.height) /2);
	}
	
}
