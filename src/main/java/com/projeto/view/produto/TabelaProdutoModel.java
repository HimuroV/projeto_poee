package com.projeto.view.produto;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.projeto.model.models.Produto;

public class TabelaProdutoModel extends AbstractTableModel{

	private static final long serialVersionUID = -4218080616820337048L;

	private final String colunas[] = {"Código", "Nome", "Valor de Venda", "Descrição"};
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int VALOR_VENDA = 2;
	private static final int DESCRICAO = 3;
		
	private List<Produto> listaProduto;
		
		
	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

		
	public Produto getProduto(int rowIndex) {
		return getListaProduto().get(rowIndex);
	}
		
	public void saveProduto(Produto produto) {
		getListaProduto().add(produto);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
		
	public void updateProduto(Produto produto, int rowIndex) {
		getListaProduto().set(rowIndex, produto);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
		
	public void removeProduto(int rowIndex) {
		getListaProduto().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
		
	public void removeAll() {
		getListaProduto().clear();
		fireTableDataChanged();
	}
		
	@Override
	public int getRowCount() {
		return getListaProduto().size();
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
		Produto produto = getListaProduto().get(rowIndex);
		switch(columnIndex) {
		case CODIGO:
			return produto.getId();
		case NOME:
			return produto.getNome();
		case VALOR_VENDA:
			return produto.getValor_venda();
		case DESCRICAO:
			return produto.getDescricao();
		default:
			return produto;
		}
	}
		
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
		case CODIGO:
			return Integer.class;
		case NOME:
			return String.class;
		case VALOR_VENDA:
			return String.class;
		case DESCRICAO:
			return String.class;
		default:
			return null;
		}
	}
		

	public String[] getColunas() {
		return colunas;
	}
}
