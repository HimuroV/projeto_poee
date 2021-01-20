package com.projeto.view.cliente;

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
import com.projeto.model.models.Cliente;
import com.projeto.model.service.ClienteService;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TabelaCliente extends JInternalFrame {

	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int TELEFONE = 2;
	private static final int BAIRRO = 3;
	private static final int RUA = 4;
	private static final int NUMERO = 5;

	private static final long serialVersionUID = -5423496758561658914L;
	private JLabel lblNewPesquisar;
	private JTextField textFieldNome;
	private JButton btnPesquisar;
	private JScrollPane scrollPane_1;
	private JLabel lblPagina2;
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
	private JTable tabelaCliente;

	private TabelaClienteModel tabelaClienteModel;
	private TableRowSorter<TabelaClienteModel> sortTabelaCliente;
	
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaCliente frame = new TabelaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TabelaCliente() {

		initComponents();
		//iniciaPaginacao();
	}
	private void initComponents() {
		setBounds(100, 100, 818, 500);
		
		lblNewPesquisar = new JLabel("Pesquisar");
		
		textFieldNome = new JTextField();
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = textFieldNome.getText();
				filtraNomeCliente(filtro);
			}
		});
		textFieldNome.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		scrollPane_1 = new JScrollPane();
		
		lblPagina2 = new JLabel("Página:");
		
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
		btnPrimeiro.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		
		btnAnterior = new JButton("");
		btnAnterior.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numeroPagina > 1) {
					numeroPagina = numeroPagina -1;
					iniciaPaginacao();
				}
			}
		});
		
		btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina < totalPagina ) {
					numeroPagina = numeroPagina + 1;
					iniciaPaginacao();
				}
			}
		});
		btnProximo.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		
		lblPagina = new JLabel("Página");
		
		lblInicio = new JLabel("10");
		
		lblTotal = new JLabel("de");
		
		lblFinal = new JLabel("50");
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirCliente();
				iniciaPaginacao();
			}
		});
		btnIncluir.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
				iniciaPaginacao();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
				iniciaPaginacao();
			}
		});
		btnExcluir.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/book_go.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 771, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewPesquisar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 533, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnPesquisar)))
							.addContainerGap(21, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPagina2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(btnPrimeiro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAnterior)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnProximo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUltimo)
							.addPreferredGap(ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
							.addComponent(lblPagina)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblInicio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTotal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFinal)
							.addGap(23))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)
							.addContainerGap(436, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewPesquisar)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPagina2)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnPrimeiro))
						.addComponent(btnAnterior)
						.addComponent(btnProximo)
						.addComponent(btnUltimo)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPagina)
							.addComponent(lblInicio)
							.addComponent(lblTotal)
							.addComponent(lblFinal)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		
		tabelaCliente = new JTable();
		scrollPane_1.setViewportView(tabelaCliente);
		getContentPane().setLayout(groupLayout);
	}
	
	protected void filtraNomeCliente(String filtro) {
		RowFilter<TabelaClienteModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaCliente.setRowFilter(rowFilter);
	}

	private void alterarCliente() {
		if(tabelaCliente.getSelectedRow() != -1 && tabelaCliente.getSelectedRow() < tabelaClienteModel.getRowCount()) {
			int linha = tabelaCliente.getSelectedRow();
			ClienteGUI cliente = new ClienteGUI(new JFrame(), true, tabelaCliente, tabelaClienteModel, linha, VariaveisProjeto.ALTERACAO);
			cliente.setLocationRelativeTo(null);
			cliente.setVisible(true);
			
		}
	}
	
	private void incluirCliente() {
		ClienteGUI cliente = new ClienteGUI(new JFrame(), true, tabelaCliente, tabelaClienteModel, 0, VariaveisProjeto.INCLUSAO);
		cliente.setLocationRelativeTo(null);
		cliente.setResizable(false);
		cliente.setVisible(true);
	}
	
	private void excluirCliente() {
		if(tabelaCliente.getSelectedRow() != -1 && tabelaCliente.getSelectedRow() < tabelaClienteModel.getRowCount()) {
			int linha = tabelaCliente.getSelectedRow();
			ClienteGUI cliente = new ClienteGUI(new JFrame(), true, tabelaCliente, tabelaClienteModel, linha, VariaveisProjeto.EXCLUSAO);
			cliente.setLocationRelativeTo(null);
			cliente.setVisible(true);
			
		}
	}

	protected void iniciaPaginacao() {
		
		totalData = buscaTotalRegistroCliente();
		
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
		
		tabelaClienteModel = new TabelaClienteModel();
		
		tabelaClienteModel.setListaCliente(carregaListaCliente(numeroPagina, defaultPagina));
		
		tabelaCliente.setModel(tabelaClienteModel);
		
		tabelaCliente.setFillsViewportHeight(true);
		
		tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaClienteModel.fireTableDataChanged();
		
		sortTabelaCliente = new TableRowSorter<TabelaClienteModel>(tabelaClienteModel);
		
		tabelaCliente.setRowSorter(sortTabelaCliente);
		
		tabelaCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaCliente.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaCliente.getColumnModel().getColumn(NOME).setWidth(100);
		tabelaCliente.getColumnModel().getColumn(TELEFONE).setWidth(20);
		tabelaCliente.getColumnModel().getColumn(BAIRRO).setWidth(100);
		tabelaCliente.getColumnModel().getColumn(RUA).setWidth(100);
		tabelaCliente.getColumnModel().getColumn(NUMERO).setWidth(11);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblFinal.setText(String.valueOf(totalData));
		
	}

	
	private List<Cliente> carregaListaCliente(Integer numeroPagina, Integer defaultPagina) {
		
		ClienteService clienteService = new ClienteService();
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		
		listaCliente = clienteService.listClientePaginacao( (defaultPagina * (numeroPagina -1 )), defaultPagina);
		
		return listaCliente;
	}

	private Integer buscaTotalRegistroCliente() {
		
		Integer totalRegistro = 0;
		
		ClienteService clienteService = new ClienteService();
		
		totalRegistro = clienteService.countTotalRegister();
		
		return totalRegistro;
	}

	public JTable getTabelaCliente() {
		return tabelaCliente;
	}
	
}
