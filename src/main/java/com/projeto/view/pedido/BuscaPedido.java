package com.projeto.view.pedido;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.projeto.model.models.Pedido;
import com.projeto.model.models.Pedido;
import com.projeto.model.service.PedidoService;
import com.projeto.view.pedido.TabelaPedidoModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BuscaPedido extends JDialog {

	private static final long serialVersionUID = 3411110896500586230L;
	
	private static final int CODIGO = 0;
	private static final int DATA = 1;
	private static final int HORA = 2;
	private static final int VALOR_TOTAL = 3;
	private static final int TIPO_PAGAMENTO = 4;
	private static final int TROCO = 5;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable tablePedido;
	
	private TabelaPedidoModel tabelaPedidoModel;
	private TableRowSorter<TabelaPedidoModel> sortTabelaPedido;
	private List<Pedido> listaPedido;
	
	private Integer codigoPedido;
	private String dataPedido;
	private String horaPedido;
	private double valor_totalPedido;
	private String tipo_pagamentoPedido;
	private double trocoPedido;
	private boolean selectPedido;
	
	public void setSelectPedido(boolean selectPedido) {
		this.selectPedido = selectPedido;
	}


	public BuscaPedido(JFrame frame, boolean modal) {
		super(frame, modal);
		initComponents();
		setResizable(false);
		iniciarDados();
	}
	
	private void iniciarDados() {
		listaPedido = new ArrayList<Pedido>();
	}
	
	private void initComponents() {
		setBounds(100, 100, 618, 396);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("Pesquisa Cod Pedido");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String filtro = textField.getText();
				filtraCodigoPedido(filtro);
			}
		});
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 542, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		tablePedido = new JTable();
		tabelaPedidoModel = new TabelaPedidoModel();
		tabelaPedidoModel.setListaPedido(carregarListaPedido());
		tablePedido.setModel(tabelaPedidoModel);
		scrollPane.setViewportView(tablePedido);
		
		tablePedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sortTabelaPedido = new TableRowSorter<TabelaPedidoModel>(tabelaPedidoModel);
		tablePedido.setRowSorter(sortTabelaPedido);
		
		tablePedido.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selecionaPedido();
						
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
						setSelectPedido(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void selecionaPedido() {
		if(tablePedido.getSelectedRow() != -1 && tablePedido.getSelectedRow() < tabelaPedidoModel.getRowCount()) {
			setCodigoPedido(Integer.valueOf(tablePedido.getValueAt(tablePedido.getSelectedRow(), CODIGO).toString()));
			setDataPedido(tablePedido.getValueAt(tablePedido.getSelectedRow(), DATA).toString());
			setHoraPedido(tablePedido.getValueAt(tablePedido.getSelectedRow(), HORA).toString());
			setValor_totalPedido(Double.valueOf(tablePedido.getValueAt(tablePedido.getSelectedRow(), VALOR_TOTAL).toString()));
			setTipo_pagamentoPedido(tablePedido.getValueAt(tablePedido.getSelectedRow(), TIPO_PAGAMENTO).toString());
			setTrocoPedido(Double.valueOf(tablePedido.getValueAt(tablePedido.getSelectedRow(), TROCO).toString()));
			setSelectPedido(true);
			dispose();
		} else {
			setSelectPedido(false);
		}
		
	}

	private List<Pedido> carregarListaPedido() {
		PedidoService pedidoService = new PedidoService();
		return pedidoService.findAll();
	}
	
	private void filtraCodigoPedido(String filtro) {
		RowFilter<TabelaPedidoModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaPedido.setRowFilter(rowFilter);
	}


	public Integer getCodigoPedido() {
		return codigoPedido;
	}


	public void setCodigoPedido(Integer codigoPedido) {
		this.codigoPedido = codigoPedido;
	}


	public String getDataPedido() {
		return dataPedido;
	}


	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}


	public String getHoraPedido() {
		return horaPedido;
	}


	public void setHoraPedido(String horaPedido) {
		this.horaPedido = horaPedido;
	}


	public double getValor_totalPedido() {
		return valor_totalPedido;
	}


	public void setValor_totalPedido(double valor_totalPedido) {
		this.valor_totalPedido = valor_totalPedido;
	}


	public String getTipo_pagamentoPedido() {
		return tipo_pagamentoPedido;
	}


	public void setTipo_pagamentoPedido(String tipo_pagamentoPedido) {
		this.tipo_pagamentoPedido = tipo_pagamentoPedido;
	}


	public double getTrocoPedido() {
		return trocoPedido;
	}


	public void setTrocoPedido(double trocoPedido) {
		this.trocoPedido = trocoPedido;
	}
	

	public boolean isSelectPedido() {
		return selectPedido;
	}
}


