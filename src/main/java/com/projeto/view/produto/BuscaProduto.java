package com.projeto.view.produto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.projeto.model.models.Produto;
import com.projeto.model.service.ProdutoService;
import com.projeto.view.produto.TabelaProdutoModel;

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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class BuscaProduto extends JDialog {

	private static final long serialVersionUID = 3538690718870328680L;
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int VALOR_VENDA = 2;
	private static final int DESCRICAO = 3;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable tableProduto;
	
	private TabelaProdutoModel tabelaProdutoModel;
	private TableRowSorter<TabelaProdutoModel> sortTabelaProduto;
	private List<Produto> listaProduto;
	
	private Integer codigoProduto;
	private String nomeProduto;
	private double valor_vendaProduto;
	private String descricaoProduto;
	private boolean selectProduto;
	
	public void setSelectProduto(boolean selectProduto) {
		this.selectProduto = selectProduto;
	}

	public BuscaProduto(JFrame frame, boolean modal) {
		super(frame, modal);
		initComponents();
		setResizable(false);
		iniciarDados();
	}
	
	private void iniciarDados() {
		listaProduto = new ArrayList<Produto>();
	}
	
	private void initComponents() {
		setBounds(100, 100, 611, 411);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("Pesquisar Nome do Produto:");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String filtro = textField.getText();
				filtraNomeProduto(filtro);
			}
		});
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		
		tableProduto = new JTable();
		tabelaProdutoModel = new TabelaProdutoModel();
		tabelaProdutoModel.setListaProduto(carregarListaProduto());
		tableProduto.setModel(tabelaProdutoModel);
		scrollPane.setViewportView(tableProduto);
		
		tableProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sortTabelaProduto = new TableRowSorter<TabelaProdutoModel>(tabelaProdutoModel);
		tableProduto.setRowSorter(sortTabelaProduto);
		
		tableProduto.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selecionaProduto();	
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
						setSelectProduto(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	protected void selecionaProduto() {
		if(tableProduto.getSelectedRow() != -1 && tableProduto.getSelectedRow() < tabelaProdutoModel.getRowCount()) {
			setCodigoProduto(Integer.valueOf(tableProduto.getValueAt(tableProduto.getSelectedRow(), CODIGO).toString()));
			setNomeProduto(tableProduto.getValueAt(tableProduto.getSelectedRow(), NOME).toString());
			setValor_vendaProduto(Double.valueOf(tableProduto.getValueAt(tableProduto.getSelectedRow(), VALOR_VENDA).toString()));
			setDescricaoProduto(tableProduto.getValueAt(tableProduto.getSelectedRow(), DESCRICAO).toString());
			setSelectProduto(true);
			dispose();
		} else {
			setSelectProduto(false);
		}
		
	}

	private List<Produto> carregarListaProduto() {
		ProdutoService produtoService = new ProdutoService();
		return produtoService.findAll();
	}
	
	private void filtraNomeProduto(String filtro) {
		RowFilter<TabelaProdutoModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaProduto.setRowFilter(rowFilter);
	}

	public Integer getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getValor_vendaProduto() {
		return valor_vendaProduto;
	}

	public void setValor_vendaProduto(double valor_vendaProduto) {
		this.valor_vendaProduto = valor_vendaProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	
	public boolean isSelectProduto() {
		return selectProduto;
	}
}
