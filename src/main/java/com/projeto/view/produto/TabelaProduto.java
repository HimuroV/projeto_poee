package com.projeto.view.produto;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableRowSorter;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Produto;
import com.projeto.model.service.ProdutoService;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TabelaProduto extends JInternalFrame {
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int VALOR_VENDA = 2;
	private static final int DESCRICAO = 3;
	
	private static final long serialVersionUID = 1872359313435716946L;
	private JLabel lblNewLabel;
	private JTextField textFieldNome;
	private JButton btnPesquisar;
	private JScrollPane scrollPane;
	private JTable tabelaProduto;
	private JLabel lblNewLabel_1;
	private JComboBox<String> comboBox;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JLabel lblPagina;
	private JLabel lblInicio;
	private JLabel lblTotal;
	private JLabel lblFinal;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;

	private TabelaProdutoModel tabelaProdutoModel;
	private TableRowSorter<TabelaProdutoModel> sortTabelaProduto;
	
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaProduto frame = new TabelaProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TabelaProduto() {

		initComponents();
		iniciaPaginacao();
	}
	private void initComponents() {
		setBounds(100, 100, 761, 446);
		
		lblNewLabel = new JLabel("Pesquisar");
		
		textFieldNome = new JTextField();
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = textFieldNome.getText();
				filtraNomeProduto(filtro);
			}
		});
		textFieldNome.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TabelaProduto.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		scrollPane = new JScrollPane();
		
		lblNewLabel_1 = new JLabel("Página");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "10", "15", "20"}));
		comboBox.setSelectedIndex(0);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				iniciaPaginacao();
			}
		});
		
		
		btnPrimeiro = new JButton("");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = 1;
				iniciaPaginacao();
			}
		});
		btnPrimeiro.setIcon(new ImageIcon(TabelaProduto.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		
		btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numeroPagina > 1) {
					numeroPagina = numeroPagina -1;
					iniciaPaginacao();
				}
			}
		});
		btnAnterior.setIcon(new ImageIcon(TabelaProduto.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		
		btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina < totalPagina ) {
					numeroPagina = numeroPagina + 1;
					iniciaPaginacao();
				}
			}
		});
		btnProximo.setIcon(new ImageIcon(TabelaProduto.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setIcon(new ImageIcon(TabelaProduto.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		
		lblPagina = new JLabel("Página");
		
		lblInicio = new JLabel("10");
		
		lblTotal = new JLabel("de");
		
		lblFinal = new JLabel("50");
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirProduto();
				iniciaPaginacao();
			}
		});
		btnIncluir.setIcon(new ImageIcon(TabelaProduto.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarProduto();
				iniciaPaginacao();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TabelaProduto.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto();
				iniciaPaginacao();
			}
		});
		btnExcluir.setIcon(new ImageIcon(TabelaProduto.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(TabelaProduto.class.getResource("/com/projeto/estrutura/imagens/book_go.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPrimeiro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAnterior)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnProximo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUltimo)
							.addPreferredGap(ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
							.addComponent(lblPagina)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblInicio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTotal)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblFinal)
							.addGap(50))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(scrollPane, Alignment.LEADING)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnPesquisar)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel_1)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnPrimeiro))
								.addComponent(btnAnterior))
							.addComponent(btnProximo))
						.addComponent(btnUltimo)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPagina)
							.addComponent(lblInicio)
							.addComponent(lblTotal)
							.addComponent(lblFinal)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		
		tabelaProduto = new JTable();
		scrollPane.setViewportView(tabelaProduto);
		getContentPane().setLayout(groupLayout);
	}
	
	protected void filtraNomeProduto(String filtro) {
		RowFilter<TabelaProdutoModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaProduto.setRowFilter(rowFilter);
	}

	private void alterarProduto() {
		if(tabelaProduto.getSelectedRow() != -1 && tabelaProduto.getSelectedRow() < tabelaProdutoModel.getRowCount()) {
			int linha = tabelaProduto.getSelectedRow();
			ProdutoGUI produto = new ProdutoGUI(new JFrame(), true, tabelaProduto, tabelaProdutoModel, linha, VariaveisProjeto.ALTERACAO);
			produto.setLocationRelativeTo(null);
			produto.setVisible(true);
			
		}
	}
	
	private void incluirProduto() {
		ProdutoGUI produto = new ProdutoGUI(new JFrame(), true, tabelaProduto, tabelaProdutoModel, 0, VariaveisProjeto.INCLUSAO);
		produto.setLocationRelativeTo(null);
		produto.setResizable(false);
		produto.setVisible(true);
	}
	
	private void excluirProduto() {
		if(tabelaProduto.getSelectedRow() != -1 && tabelaProduto.getSelectedRow() < tabelaProdutoModel.getRowCount()) {
			int linha = tabelaProduto.getSelectedRow();
			ProdutoGUI produto = new ProdutoGUI(new JFrame(), true, tabelaProduto, tabelaProdutoModel, linha, VariaveisProjeto.EXCLUSAO);
			produto.setLocationRelativeTo(null);
			produto.setVisible(true);
			
		}
	}

	protected void iniciaPaginacao() {
		
		totalData = buscaTotalRegistroProduto();
		
		defaultPagina = Integer.valueOf(comboBox.getSelectedItem().toString());
		
		Double totalPaginasExistentes = Math.ceil(totalData.doubleValue() / defaultPagina.doubleValue());
		
		totalPagina = totalPaginasExistentes.intValue();
		
		if ( numeroPagina.equals(1)) {
			btnPrimeiro.setEnabled(false);
			btnProximo.setEnabled(false);
		} else {
			btnPrimeiro.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if ( numeroPagina.equals(totalPagina)) {
			btnUltimo.setEnabled(false);
			btnProximo.setEnabled(false);
		} else {
			btnUltimo.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if (numeroPagina > totalPagina) {
			numeroPagina = 1;
		}
		
		tabelaProdutoModel = new TabelaProdutoModel();
		
		tabelaProdutoModel.setListaProduto(carregaListaProduto(numeroPagina, defaultPagina));
		
		tabelaProduto.setModel(tabelaProdutoModel);
		
		tabelaProduto.setFillsViewportHeight(true);
		
		tabelaProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaProdutoModel.fireTableDataChanged();
		
		sortTabelaProduto = new TableRowSorter<TabelaProdutoModel>(tabelaProdutoModel);
		
		tabelaProduto.setRowSorter(sortTabelaProduto);
		
		tabelaProduto.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaProduto.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaProduto.getColumnModel().getColumn(NOME).setWidth(100);
		tabelaProduto.getColumnModel().getColumn(VALOR_VENDA).setWidth(20);
		tabelaProduto.getColumnModel().getColumn(DESCRICAO).setWidth(300);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblFinal.setText(String.valueOf(totalData));
		
	}

	
	private List<Produto> carregaListaProduto(Integer numeroPagina, Integer defaultPagina) {
		
		ProdutoService produtoService = new ProdutoService();
		List<Produto> listaProduto = new ArrayList<Produto>();
		
		listaProduto = produtoService.listProdutoPaginacao( (defaultPagina * (numeroPagina -1 )), defaultPagina);
		
		return listaProduto;
	}

	private Integer buscaTotalRegistroProduto() {
		
		Integer totalRegistro = 0;
		
		ProdutoService produtoService = new ProdutoService();
		
		totalRegistro = produtoService.countTotalRegister();
		
		return totalRegistro;
	}

	public JTable getTabelaProduto() {
		return tabelaProduto;
	}

}
