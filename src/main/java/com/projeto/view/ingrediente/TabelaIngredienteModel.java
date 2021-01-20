package com.projeto.view.ingrediente;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.projeto.model.models.Ingrediente;

public class TabelaIngredienteModel extends AbstractTableModel{

	private static final long serialVersionUID = -5426828834886363335L;

	private final String colunas[] = {"Código", "Nome", "Quantidade em Estoque", "Custo Unitário"};
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int QUANTIDADE_ESTOQUE = 2;
	private static final int CUSTO_UNITARIO = 3;
		
	private List<Ingrediente> listaIngrediente;
		
		
	public List<Ingrediente> getListaIngrediente() {
		return listaIngrediente;
	}

	public void setListaIngrediente(List<Ingrediente> listaIngrediente) {
		this.listaIngrediente = listaIngrediente;
	}

		
	public Ingrediente getIngrediente(int rowIndex) {
		return getListaIngrediente().get(rowIndex);
	}
		
	public void saveIngrediente(Ingrediente ingrediente) {
		getListaIngrediente().add(ingrediente);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
		
	public void updateIngrediente(Ingrediente ingrediente, int rowIndex) {
		getListaIngrediente().set(rowIndex, ingrediente);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
		
	public void removeIngrediente(int rowIndex) {
		getListaIngrediente().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
		
	public void removeAll() {
		getListaIngrediente().clear();
		fireTableDataChanged();
	}
		
	@Override
	public int getRowCount() {
		return getListaIngrediente().size();
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
		Ingrediente ingrediente = getListaIngrediente().get(rowIndex);
		switch(columnIndex) {
		case CODIGO:
			return ingrediente.getId();
		case NOME:
			return ingrediente.getNome();
		case QUANTIDADE_ESTOQUE:
			return ingrediente.getQuantidade_estoque();
		case CUSTO_UNITARIO:
			return ingrediente.getCusto_unitario();
		default:
			return ingrediente;
		}
	}
		
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
		case CODIGO:
			return Integer.class;
		case NOME:
			return String.class;
		case QUANTIDADE_ESTOQUE:
			return String.class;
		case CUSTO_UNITARIO:
			return String.class;
		default:
			return null;
		}
	}
		

	public String[] getColunas() {
		return colunas;
	}
		
}
