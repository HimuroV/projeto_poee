package com.projeto.view.pedido;

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
import com.projeto.model.models.Pedido;
import com.projeto.model.service.PedidoService;

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

public class TabelaPedido extends JInternalFrame {
	
	private static final int CODIGO = 0;
	private static final int DATA = 1;
	private static final int HORA = 2;
	private static final int VALOR_TOTAL = 3;
	private static final int TIPO_PAGAMENTO = 4;
	private static final int TROCO = 5;

	private static final long serialVersionUID = -8472662070119559107L;
	private JLabel lblNewLabel;
	private JTextField textFieldNome;
	private JButton btnPesquisar;
	private JScrollPane scrollPane;
	private JTable tabelaPedido;
	private JLabel lblNewLabel_1;
	private JComboBox<String> comboBox;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JLabel lblNewLabel_2;
	private JLabel lblInicio;
	private JLabel lblTotal;
	private JLabel lblFinal;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnNewButton;
	
	private TabelaPedidoModel tabelaPedidoModel;
	private TableRowSorter<TabelaPedidoModel> sortTabelaPedido;
	
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaPedido frame = new TabelaPedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TabelaPedido() {

		initComponents();
		iniciaPaginacao();
	}
	
	private void initComponents() {
		setBounds(100, 100, 713, 461);
		
		lblNewLabel = new JLabel("Pesquisar");
		
		textFieldNome = new JTextField();
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = textFieldNome.getText();
				filtraNomePedido(filtro);
			}
		});
		textFieldNome.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
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
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = 1;
				iniciaPaginacao();
			}
		});
		btnPrimeiro.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		
		btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numeroPagina > 1) {
					numeroPagina = numeroPagina -1;
					iniciaPaginacao();
				}
			}
		});
		btnAnterior.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		
		btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina < totalPagina ) {
					numeroPagina = numeroPagina + 1;
					iniciaPaginacao();
				}
			}
		});
		btnProximo.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		
		lblNewLabel_2 = new JLabel("Página");
		
		lblInicio = new JLabel("10");
		
		lblTotal = new JLabel("de");
		
		lblFinal = new JLabel("50");
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirPedido();
				iniciaPaginacao();
			}
		});
		btnIncluir.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarPedido();
				iniciaPaginacao();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirPedido();
				iniciaPaginacao();
			}
		});
		btnExcluir.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnNewButton = new JButton("Sair");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
		btnNewButton.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/book_go.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addGap(321))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnPrimeiro)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAnterior)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnProximo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnUltimo)
									.addPreferredGap(ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblInicio)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotal)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblFinal))
								.addComponent(scrollPane, Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnPesquisar)))
							.addContainerGap(28, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel_1)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnPrimeiro))
								.addComponent(btnAnterior)
								.addComponent(btnProximo))
							.addComponent(btnUltimo))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(lblInicio)
							.addComponent(lblTotal)
							.addComponent(lblFinal)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnNewButton))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		
		tabelaPedido = new JTable();
		scrollPane.setViewportView(tabelaPedido);
		getContentPane().setLayout(groupLayout);
	}

	protected void filtraNomePedido(String filtro) {
		RowFilter<TabelaPedidoModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaPedido.setRowFilter(rowFilter);
	}

	private void alterarPedido() {
		if(tabelaPedido.getSelectedRow() != -1 && tabelaPedido.getSelectedRow() < tabelaPedidoModel.getRowCount()) {
			int linha = tabelaPedido.getSelectedRow();
			PedidoGUI pedido = new PedidoGUI(new JFrame(), true, tabelaPedido, tabelaPedidoModel, linha, VariaveisProjeto.ALTERACAO);
			pedido.setLocationRelativeTo(null);
			pedido.setVisible(true);
			
		}
	}
	
	private void incluirPedido() {
		PedidoGUI pedido = new PedidoGUI(new JFrame(), true, tabelaPedido, tabelaPedidoModel, 0, VariaveisProjeto.INCLUSAO);
		pedido.setLocationRelativeTo(null);
		pedido.setResizable(false);
		pedido.setVisible(true);
	}
	
	private void excluirPedido() {
		if(tabelaPedido.getSelectedRow() != -1 && tabelaPedido.getSelectedRow() < tabelaPedidoModel.getRowCount()) {
			int linha = tabelaPedido.getSelectedRow();
			PedidoGUI pedido = new PedidoGUI(new JFrame(), true, tabelaPedido, tabelaPedidoModel, linha, VariaveisProjeto.EXCLUSAO);
			pedido.setLocationRelativeTo(null);
			pedido.setVisible(true);
			
		}
	}

	protected void iniciaPaginacao() {
		
		totalData = buscaTotalRegistroPedido();
		
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
		
		tabelaPedidoModel = new TabelaPedidoModel();
		
		tabelaPedidoModel.setListaPedido(carregaListaPedido(numeroPagina, defaultPagina));
		
		tabelaPedido.setModel(tabelaPedidoModel);
		
		tabelaPedido.setFillsViewportHeight(true);
		
		tabelaPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaPedidoModel.fireTableDataChanged();
		
		sortTabelaPedido = new TableRowSorter<TabelaPedidoModel>(tabelaPedidoModel);
		
		tabelaPedido.setRowSorter(sortTabelaPedido);
		
		tabelaPedido.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaPedido.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaPedido.getColumnModel().getColumn(DATA).setWidth(20);
		tabelaPedido.getColumnModel().getColumn(HORA).setWidth(20);
		tabelaPedido.getColumnModel().getColumn(VALOR_TOTAL).setWidth(20);
		tabelaPedido.getColumnModel().getColumn(TIPO_PAGAMENTO).setWidth(20);
		tabelaPedido.getColumnModel().getColumn(TROCO).setWidth(20);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblFinal.setText(String.valueOf(totalData));
		
	}

	
	private List<Pedido> carregaListaPedido(Integer numeroPagina, Integer defaultPagina) {
		
		PedidoService pedidoService = new PedidoService();
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		
		listaPedido = pedidoService.listPedidoPaginacao( (defaultPagina * (numeroPagina -1 )), defaultPagina);
		
		return listaPedido;
	}

	private Integer buscaTotalRegistroPedido() {
		
		Integer totalRegistro = 0;
		
		PedidoService pedidoService = new PedidoService();
		
		totalRegistro = pedidoService.countTotalRegister();
		
		return totalRegistro;
	}

	public JTable getTabelaPedido() {
		return tabelaPedido;
	}
}
