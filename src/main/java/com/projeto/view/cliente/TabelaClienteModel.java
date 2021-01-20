package com.projeto.view.cliente;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.projeto.model.models.Cliente;

public class TabelaClienteModel extends AbstractTableModel {
		
	private static final long serialVersionUID = -3267247119437297380L;

	private final String colunas[] = {"Código", "Nome", "Telefone", "Bairro", "Rua", "Número"};
		
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int TELEFONE = 2;
	private static final int BAIRRO = 3;
	private static final int RUA = 4;
	private static final int NUMERO = 5;
		
	private List<Cliente> listaCliente;
		
		
	public List<Cliente> getListaCliente() {
		return listaCliente;
	}

	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}

		
	public Cliente getCliente(int rowIndex) {
		return getListaCliente().get(rowIndex);
	}
		
	public void saveCliente(Cliente cliente) {
		getListaCliente().add(cliente);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
		
	public void updateCliente(Cliente cliente, int rowIndex) {
		getListaCliente().set(rowIndex, cliente);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
		
	public void removeCliente(int rowIndex) {
		getListaCliente().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
		
	public void removeAll() {
		getListaCliente().clear();
		fireTableDataChanged();
	}
		
	@Override
	public int getRowCount() {
		return getListaCliente().size();
	}

	@Override
	public int getColumnCount() {
		return getColunas().length;
	}
		
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente cliente = getListaCliente().get(rowIndex);
		switch(columnIndex) {
		case CODIGO:
			return cliente.getId();
		case NOME:
			return cliente.getNome();
		case TELEFONE:
			return cliente.getTelefone();
		case BAIRRO:
			return cliente.getBairro();
		case RUA:
			return cliente.getRua();
		case NUMERO:
			return cliente.getNumero();
		default:
			return cliente;
		}
	}
		
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
		case CODIGO:
			return Integer.class;
		case NOME:
			return String.class;
		case TELEFONE:
			return String.class;
		case BAIRRO:
			return String.class;
		case RUA:
			return String.class;
		case NUMERO:
			return String.class;
		default:
			return null;
		}
	}
		

	public String[] getColunas() {
		return colunas;
	}
		
}

