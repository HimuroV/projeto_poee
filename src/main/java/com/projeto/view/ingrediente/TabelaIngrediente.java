package com.projeto.view.ingrediente;

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
import com.projeto.model.models.Ingrediente;
import com.projeto.model.service.IngredienteService;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TabelaIngrediente extends JInternalFrame {
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int QUANTIDADE_ESTOQUE = 2;
	private static final int CUSTO_UNITARIO = 3;


	private static final long serialVersionUID = 3290651597206376171L;
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
	private JLabel lblNewLabel_2;
	private JLabel lblInicio;
	private JLabel lblTotal;
	private JTable tabelaIngrediente;
	private JLabel lblFinal;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	
	private TabelaIngredienteModel tabelaIngredienteModel;
	private TableRowSorter<TabelaIngredienteModel> sortTabelaIngrediente;
	
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaIngrediente frame = new TabelaIngrediente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TabelaIngrediente() {

		initComponents();
		iniciaPaginacao();
	}
	private void initComponents() {
		setBounds(100, 100, 808, 489);
		
		lblNewLabel = new JLabel("Pesquisar");
		
		textFieldNome = new JTextField();
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = textFieldNome.getText();
				filtraNomeIngrediente(filtro);
			}
		});
		textFieldNome.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TabelaIngrediente.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
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
		btnPrimeiro.setIcon(new ImageIcon(TabelaIngrediente.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		
		btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numeroPagina > 1) {
					numeroPagina = numeroPagina -1;
					iniciaPaginacao();
				}
			}
		});
		btnAnterior.setIcon(new ImageIcon(TabelaIngrediente.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		
		btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina < totalPagina ) {
					numeroPagina = numeroPagina + 1;
					iniciaPaginacao();
				}
			}
		});
		btnProximo.setIcon(new ImageIcon(TabelaIngrediente.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setIcon(new ImageIcon(TabelaIngrediente.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		
		lblNewLabel_2 = new JLabel("Página");
		
		lblInicio = new JLabel("10");
		
		lblTotal = new JLabel("de");
		
		lblFinal = new JLabel("50");
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirIngrediente();
				iniciaPaginacao();
			}
		});
		btnIncluir.setIcon(new ImageIcon(TabelaIngrediente.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarIngrediente();
				iniciaPaginacao();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TabelaIngrediente.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirIngrediente();
				iniciaPaginacao();
			}
		});
		btnExcluir.setIcon(new ImageIcon(TabelaIngrediente.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(TabelaIngrediente.class.getResource("/com/projeto/estrutura/imagens/book_go.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 581, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnPesquisar))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
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
									.addPreferredGap(ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblInicio)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotal)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblFinal)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(50))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)
							.addContainerGap(387, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnPesquisar))
						.addComponent(lblNewLabel))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnPrimeiro))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnProximo)
							.addComponent(btnAnterior))
						.addComponent(btnUltimo)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblInicio)
							.addComponent(lblTotal)
							.addComponent(lblFinal)
							.addComponent(lblNewLabel_2)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		
		tabelaIngrediente = new JTable();
		scrollPane.setViewportView(tabelaIngrediente);
		getContentPane().setLayout(groupLayout);
	}
	
	protected void filtraNomeIngrediente(String filtro) {
		RowFilter<TabelaIngredienteModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaIngrediente.setRowFilter(rowFilter);
	}

	private void alterarIngrediente() {
		if(tabelaIngrediente.getSelectedRow() != -1 && tabelaIngrediente.getSelectedRow() < tabelaIngredienteModel.getRowCount()) {
			int linha = tabelaIngrediente.getSelectedRow();
			IngredienteGUI ingrediente = new IngredienteGUI(new JFrame(), true, tabelaIngrediente, tabelaIngredienteModel, linha, VariaveisProjeto.ALTERACAO);
			ingrediente.setLocationRelativeTo(null);
			ingrediente.setVisible(true);
			
		}
	}
	
	private void incluirIngrediente() {
		IngredienteGUI ingrediente = new IngredienteGUI(new JFrame(), true, tabelaIngrediente, tabelaIngredienteModel, 0, VariaveisProjeto.INCLUSAO);
		ingrediente.setLocationRelativeTo(null);
		ingrediente.setResizable(false);
		ingrediente.setVisible(true);
	}
	
	private void excluirIngrediente() {
		if(tabelaIngrediente.getSelectedRow() != -1 && tabelaIngrediente.getSelectedRow() < tabelaIngredienteModel.getRowCount()) {
			int linha = tabelaIngrediente.getSelectedRow();
			IngredienteGUI ingrediente = new IngredienteGUI(new JFrame(), true, tabelaIngrediente, tabelaIngredienteModel, linha, VariaveisProjeto.EXCLUSAO);
			ingrediente.setLocationRelativeTo(null);
			ingrediente.setVisible(true);
			
		}
	}

	protected void iniciaPaginacao() {
		
		totalData = buscaTotalRegistroIngrediente();
		
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
		
		tabelaIngredienteModel = new TabelaIngredienteModel();
		
		tabelaIngredienteModel.setListaIngrediente(carregaListaIngrediente(numeroPagina, defaultPagina));
		
		tabelaIngrediente.setModel(tabelaIngredienteModel);
		
		tabelaIngrediente.setFillsViewportHeight(true);
		
		tabelaIngrediente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaIngredienteModel.fireTableDataChanged();
		
		sortTabelaIngrediente = new TableRowSorter<TabelaIngredienteModel>(tabelaIngredienteModel);
		
		tabelaIngrediente.setRowSorter(sortTabelaIngrediente);
		
		tabelaIngrediente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaIngrediente.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaIngrediente.getColumnModel().getColumn(NOME).setWidth(100);
		tabelaIngrediente.getColumnModel().getColumn(QUANTIDADE_ESTOQUE).setWidth(20);
		tabelaIngrediente.getColumnModel().getColumn(CUSTO_UNITARIO).setWidth(20);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblFinal.setText(String.valueOf(totalData));
		
	}

	
	private List<Ingrediente> carregaListaIngrediente(Integer numeroPagina, Integer defaultPagina) {
		
		IngredienteService ingredienteService = new IngredienteService();
		List<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();
		
		listaIngrediente = ingredienteService.listIngredientePaginacao( (defaultPagina * (numeroPagina -1 )), defaultPagina);
		
		return listaIngrediente;
	}

	private Integer buscaTotalRegistroIngrediente() {
		
		Integer totalRegistro = 0;
		
		IngredienteService ingredienteService = new IngredienteService();
		
		totalRegistro = ingredienteService.countTotalRegister();
		
		return totalRegistro;
	}

	public JTable getTabelaIngrediente() {
		return tabelaIngrediente;
	}

}
