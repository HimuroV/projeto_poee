package com.projeto.view.ItemPedido;

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
import com.projeto.model.models.ItemPedido;
import com.projeto.model.service.ItemPedidoService;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TabelaItemPedido extends JInternalFrame {
	
	private static final long serialVersionUID = 341442875987237266L;
	
	private static final int CODIGO = 0;
	private static final int QUANTIDADE = 1;
	private static final int VALOR_UNITARIO = 2;
	private static final int VALOR_TOTAL_ITEM = 3;
	private JLabel lblNewLabel;
	private JTextField textFieldNome;
	private JButton btnPesquisar;
	private JScrollPane scrollPane;
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
	private JTable tabelaItemPedido;
	
	private TabelaItemPedidoModel tabelaItemPedidoModel;
	private TableRowSorter<TabelaItemPedidoModel> sortTabelaItemPedido;
	
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaItemPedido frame = new TabelaItemPedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TabelaItemPedido() {

		initComponents();
		iniciaPaginacao();
	}
	
	private void initComponents() {
		setBounds(100, 100, 752, 436);
		
		lblNewLabel = new JLabel("Pesquisar:");
		
		textFieldNome = new JTextField();
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = textFieldNome.getText();
				filtraNomeItemPedido(filtro);
			}
		});
		textFieldNome.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TabelaItemPedido.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		scrollPane = new JScrollPane();
		
		lblNewLabel_1 = new JLabel("Página:");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "10", "15", "20"}));
		comboBox.setSelectedIndex(0);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				iniciaPaginacao();
			}
		});
		
		
		btnPrimeiro = new JButton("");
		btnPrimeiro.setIcon(new ImageIcon(TabelaItemPedido.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = 1;
				iniciaPaginacao();
			}
		});
		
		btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numeroPagina > 1) {
					numeroPagina = numeroPagina -1;
					iniciaPaginacao();
				}
			}
		});
		btnAnterior.setIcon(new ImageIcon(TabelaItemPedido.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		
		btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina < totalPagina ) {
					numeroPagina = numeroPagina + 1;
					iniciaPaginacao();
				}
			}
		});
		btnProximo.setIcon(new ImageIcon(TabelaItemPedido.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setIcon(new ImageIcon(TabelaItemPedido.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		
		lblPagina = new JLabel("Página");
		
		lblInicio = new JLabel("10");
		
		lblTotal = new JLabel("de");
		
		lblFinal = new JLabel("50");
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirItemPedido();
				iniciaPaginacao();
			}
		});
		btnIncluir.setIcon(new ImageIcon(TabelaItemPedido.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarItemPedido();
				iniciaPaginacao();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TabelaItemPedido.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirItemPedido();
				iniciaPaginacao();
			}
		});
		btnExcluir.setIcon(new ImageIcon(TabelaItemPedido.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(TabelaItemPedido.class.getResource("/com/projeto/estrutura/imagens/book_go.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnPesquisar)))
							.addContainerGap(51, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPrimeiro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAnterior)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnProximo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUltimo)
							.addPreferredGap(ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
							.addComponent(lblPagina)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblInicio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTotal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFinal)
							.addGap(53))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)
							.addContainerGap(370, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnPrimeiro))
						.addComponent(btnAnterior)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnUltimo)
							.addComponent(btnProximo))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblInicio)
							.addComponent(lblTotal)
							.addComponent(lblFinal)
							.addComponent(lblPagina)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnIncluir)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAlterar)
							.addComponent(btnExcluir)
							.addComponent(btnSair)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		
		tabelaItemPedido = new JTable();
		scrollPane.setViewportView(tabelaItemPedido);
		getContentPane().setLayout(groupLayout);
	}
	
	protected void filtraNomeItemPedido(String filtro) {
		RowFilter<TabelaItemPedidoModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaItemPedido.setRowFilter(rowFilter);
	}

	private void alterarItemPedido() {
		if(tabelaItemPedido.getSelectedRow() != -1 && tabelaItemPedido.getSelectedRow() < tabelaItemPedidoModel.getRowCount()) {
			int linha = tabelaItemPedido.getSelectedRow();
			ItemPedidoGUI itempedido = new ItemPedidoGUI(new JFrame(), true, tabelaItemPedido, tabelaItemPedidoModel, linha, VariaveisProjeto.ALTERACAO);
			itempedido.setLocationRelativeTo(null);
			itempedido.setVisible(true);
			
		}
	}
	
	private void incluirItemPedido() {
		ItemPedidoGUI itempedido = new ItemPedidoGUI(new JFrame(), true, tabelaItemPedido, tabelaItemPedidoModel, 0, VariaveisProjeto.INCLUSAO);
		itempedido.setLocationRelativeTo(null);
		itempedido.setResizable(false);
		itempedido.setVisible(true);
	}
	
	private void excluirItemPedido() {
		if(tabelaItemPedido.getSelectedRow() != -1 && tabelaItemPedido.getSelectedRow() < tabelaItemPedidoModel.getRowCount()) {
			int linha = tabelaItemPedido.getSelectedRow();
			ItemPedidoGUI itempedido = new ItemPedidoGUI(new JFrame(), true, tabelaItemPedido, tabelaItemPedidoModel, linha, VariaveisProjeto.EXCLUSAO);
			itempedido.setLocationRelativeTo(null);
			itempedido.setVisible(true);
			
		}
	}

	protected void iniciaPaginacao() {
		
		totalData = buscaTotalRegistroItemPedido();
		
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
		
		tabelaItemPedidoModel = new TabelaItemPedidoModel();
		
		tabelaItemPedidoModel.setListaItemPedido(carregaListaItemPedido(numeroPagina, defaultPagina));
		
		tabelaItemPedido.setModel(tabelaItemPedidoModel);
		
		tabelaItemPedido.setFillsViewportHeight(true);
		
		tabelaItemPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaItemPedidoModel.fireTableDataChanged();
		
		sortTabelaItemPedido = new TableRowSorter<TabelaItemPedidoModel>(tabelaItemPedidoModel);
		
		tabelaItemPedido.setRowSorter(sortTabelaItemPedido);
		
		tabelaItemPedido.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaItemPedido.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaItemPedido.getColumnModel().getColumn(QUANTIDADE).setWidth(20);
		tabelaItemPedido.getColumnModel().getColumn(VALOR_UNITARIO).setWidth(20);
		tabelaItemPedido.getColumnModel().getColumn(VALOR_TOTAL_ITEM).setWidth(20);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblFinal.setText(String.valueOf(totalData));
		
	}

	
	private List<ItemPedido> carregaListaItemPedido(Integer numeroPagina, Integer defaultPagina) {
		
		ItemPedidoService itempedidoService = new ItemPedidoService();
		List<ItemPedido> listaItemPedido = new ArrayList<ItemPedido>();
		
		listaItemPedido = itempedidoService.listItemPedidoPaginacao( (defaultPagina * (numeroPagina -1 )), defaultPagina);
		
		return listaItemPedido;
	}

	private Integer buscaTotalRegistroItemPedido() {
		
		Integer totalRegistro = 0;
		
		ItemPedidoService itempedidoService = new ItemPedidoService();
		
		totalRegistro = itempedidoService.countTotalRegister();
		
		return totalRegistro;
	}

	public JTable getTabelaItemPedido() {
		return tabelaItemPedido;
	}

}
