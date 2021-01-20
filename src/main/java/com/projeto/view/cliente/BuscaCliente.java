package com.projeto.view.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.projeto.model.models.Cliente;
import com.projeto.model.service.ClienteService;
import com.projeto.view.cliente.TabelaClienteModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTable;

public class BuscaCliente extends JDialog {

	private static final long serialVersionUID = 1236662859117500571L;
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int TELEFONE = 2;
	private static final int BAIRRO = 3;
	private static final int RUA = 4;
	private static final int NUMERO = 5;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblPesquisarCliente;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable tableCliente;
	
	private TabelaClienteModel tabelaClienteModel;
	private TableRowSorter<TabelaClienteModel> sortTabelaCliente;
	private List<Cliente> listaCliente;
	
	private Integer codigoCliente;
	private String nomeCliente;
	private String telefoneCliente;
	private String bairroCliente;
	private String ruaCliente;
	private String numeroCliente;
	private boolean selectCliente;
	

	public void setSelectCliente(boolean selectCliente) {
		this.selectCliente = selectCliente;
	}

	public BuscaCliente(JFrame frame, boolean modal) {
		super(frame, modal);
		initComponents();
		setResizable(false);
		iniciarDados();
	}
	
	private void iniciarDados() {
		listaCliente = new ArrayList<Cliente>();
	}
	
	private void initComponents() {
		setBounds(100, 100, 662, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblPesquisarCliente = new JLabel("Pesquisar Cliente:");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String filtro = textField.getText();
				filtraNomeCliente(filtro);
			}
		});
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 533, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblPesquisarCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPesquisarCliente)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(85, Short.MAX_VALUE))
		);
		
		tableCliente = new JTable();
		tabelaClienteModel = new TabelaClienteModel();
		tabelaClienteModel.setListaCliente(carregarListaCliente());
		tableCliente.setModel(tabelaClienteModel);
		scrollPane.setViewportView(tableCliente);
		
		tableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sortTabelaCliente = new TableRowSorter<TabelaClienteModel>(tabelaClienteModel);
		tableCliente.setRowSorter(sortTabelaCliente);
		
		tableCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selecionaCliente();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setSelectCliente(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void selecionaCliente() {
		if(tableCliente.getSelectedRow() != -1 && tableCliente.getSelectedRow() < tabelaClienteModel.getRowCount()) {
			setCodigoCliente(Integer.valueOf(tableCliente.getValueAt(tableCliente.getSelectedRow(), CODIGO).toString()));
			setNomeCliente(tableCliente.getValueAt(tableCliente.getSelectedRow(), NOME).toString());
			setTelefoneCliente(tableCliente.getValueAt(tableCliente.getSelectedRow(), TELEFONE).toString());
			setBairroCliente(tableCliente.getValueAt(tableCliente.getSelectedRow(), BAIRRO).toString());
			setRuaCliente(tableCliente.getValueAt(tableCliente.getSelectedRow(), RUA).toString());
			setNumeroCliente(tableCliente.getValueAt(tableCliente.getSelectedRow(), NUMERO).toString());
			setSelectCliente(true);
			dispose();
		} else {
			setSelectCliente(false);
		}
		
	}

	private List<Cliente> carregarListaCliente() {
		ClienteService clienteService = new ClienteService();
		return clienteService.findAll();
	}
	
	private void filtraNomeCliente(String filtro) {
		RowFilter<TabelaClienteModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaCliente.setRowFilter(rowFilter);
		
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getBairroCliente() {
		return bairroCliente;
	}

	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}

	public String getRuaCliente() {
		return ruaCliente;
	}

	public void setRuaCliente(String ruaCliente) {
		this.ruaCliente = ruaCliente;
	}

	public String getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}
	
	public boolean isSelectCliente() {
		return selectCliente;
	}
}

	
