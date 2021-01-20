package com.projeto.view.ItemPedido;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.projeto.model.models.ItemPedido;

public class TabelaItemPedidoModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -7272036159674913981L;

	private final String colunas[] = {"Código", "Quantidade", "Valor Unitário", "Valor Total do Item"};
	
	private static final int CODIGO = 0;
	private static final int QUANTIDADE = 1;
	private static final int VALOR_UNITARIO = 2;
	private static final int VALOR_TOTAL_ITEM = 3;
		
	private List<ItemPedido> listaItemPedido;
		
		
	public List<ItemPedido> getListaItemPedido() {
		return listaItemPedido;
	}

	public void setListaItemPedido(List<ItemPedido> listaItemPedido) {
		this.listaItemPedido = listaItemPedido;
	}

		
	public ItemPedido getItemPedido(int rowIndex) {
		return getListaItemPedido().get(rowIndex);
	}
		
	public void saveItemPedido(ItemPedido itempedido) {
		getListaItemPedido().add(itempedido);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
		
	public void updateItemPedido(ItemPedido itempedido, int rowIndex) {
		getListaItemPedido().set(rowIndex, itempedido);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
		
	public void removeItemPedido(int rowIndex) {
		getListaItemPedido().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
		
	public void removeAll() {
		getListaItemPedido().clear();
		fireTableDataChanged();
	}
		
	@Override
	public int getRowCount() {
		return getListaItemPedido().size();
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
		ItemPedido itempedido = getListaItemPedido().get(rowIndex);
		switch(columnIndex) {
		case CODIGO:
			return itempedido.getId();
		case QUANTIDADE:
			return itempedido.getQuantidade();
		case VALOR_UNITARIO:
			return itempedido.getValor_unitario();
		case VALOR_TOTAL_ITEM:
			return itempedido.getValor_total_item();
		default:
			return itempedido;
		}
	}
		
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
		case CODIGO:
			return Integer.class;
		case QUANTIDADE:
			return String.class;
		case VALOR_UNITARIO:
			return String.class;
		case VALOR_TOTAL_ITEM:
			return String.class;
		default:
			return null;
		}
	}
		

	public String[] getColunas() {
		return colunas;
	}
		
}
