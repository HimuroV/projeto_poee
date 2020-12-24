package com.projeto.view.departamento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.projeto.model.models.Departamento;
import com.projeto.model.service.DepartamentoService;
import com.projeto.view.usuario.TabelaUsuarioModel;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscaDepartamento extends JDialog {

	
	private static final long serialVersionUID = 7656297193063077391L;
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	
	private final JPanel contentPanel = new JPanel();
	private JLabel lblPesquisarDepartamento;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable tableDepartamento;
	
	private TabelaDepartamentoModel tabelaDepartamentoModel;
	private TableRowSorter<TabelaDepartamentoModel> sortTabelaDepartamento;
	private List<Departamento> listaDepartamento;
	
	private Integer codigoDepartamento;
	private String nomeDepartamento;
	private boolean selectDepartamento;
	

	public void setSelectDepartamento(boolean selectDepartamento) {
		this.selectDepartamento = selectDepartamento;
	}

	private JButton btnInserirDepartamento;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { BuscaDepartamento dialog = new
	 * BuscaDepartamento();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public BuscaDepartamento(JFrame frame, boolean modal) {
		super(frame, modal);
		initComponents();
		setResizable(false);
		iniciarDados();
		
	}
	
	private void iniciarDados() {
		listaDepartamento = new ArrayList<Departamento>();
	}

	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 662, 431);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblPesquisarDepartamento = new JLabel("Pesquisar Departamento:");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String filtro = textField.getText();
				filtraNomeDepartamento(filtro);
			}

			
		});
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		
		btnInserirDepartamento = new JButton("Cadastrar Departamento");
		btnInserirDepartamento.setMnemonic(KeyEvent.VK_C);
		btnInserirDepartamento.setIcon(new ImageIcon(BuscaDepartamento.class.getResource("/com/projeto/estrutura/imagens/application_form_add.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnInserirDepartamento)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblPesquisarDepartamento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPesquisarDepartamento)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnInserirDepartamento)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		tableDepartamento = new JTable();
		tabelaDepartamentoModel = new TabelaDepartamentoModel();
		tabelaDepartamentoModel.setListaDepartamento(carregarListaDepartamento());
		tableDepartamento.setModel(tabelaDepartamentoModel);
		scrollPane.setViewportView(tableDepartamento);
		
		tableDepartamento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sortTabelaDepartamento = new TableRowSorter<TabelaDepartamentoModel>(tabelaDepartamentoModel);
		tableDepartamento.setRowSorter(sortTabelaDepartamento);
		
		tableDepartamento.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
//		tableDepartamento.getColumnModel().getColumn(CODIGO).setWidth(11);
//		tableDepartamento.getColumnModel().getColumn(NOME).setWidth(100);
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selecionaDepartamento();
						
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
						setSelectDepartamento(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	protected void selecionaDepartamento() {
		if(tableDepartamento.getSelectedRow() != -1 && tableDepartamento.getSelectedRow() < tabelaDepartamentoModel.getRowCount()) {
			setCodigoDepartamento(Integer.valueOf(tableDepartamento.getValueAt(tableDepartamento.getSelectedRow(), CODIGO).toString()));
			setNomeDepartamento(tableDepartamento.getValueAt(tableDepartamento.getSelectedRow(), NOME).toString());
			setSelectDepartamento(true);
			dispose();
		} else {
			setSelectDepartamento(false);
		}
		
	}

	private List<Departamento> carregarListaDepartamento() {
		DepartamentoService departamentoService = new DepartamentoService();
		return departamentoService.findAll();
	}
	
	private void filtraNomeDepartamento(String filtro) {
		RowFilter<TabelaDepartamentoModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaDepartamento.setRowFilter(rowFilter);
		
	}

	public Integer getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(Integer codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}
	
	public boolean isSelectDepartamento() {
		return selectDepartamento;
	}
	
}
