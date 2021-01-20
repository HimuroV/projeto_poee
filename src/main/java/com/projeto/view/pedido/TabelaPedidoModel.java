package com.projeto.view.pedido;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.projeto.model.models.Pedido;

public class TabelaPedidoModel extends AbstractTableModel{

	private static final long serialVersionUID = 2897120274869127876L;

	private final String colunas[] = {"Código", "Data", "Hora", "Valor Total", "Tipo Pagamento", "Troco"};
	
	private static final int CODIGO = 0;
	private static final int DATA = 1;
	private static final int HORA = 2;
	private static final int VALOR_TOTAL = 3;
	private static final int TIPO_PAGAMENTO = 4;
	private static final int TROCO = 5;
		
	private List<Pedido> listaPedido;
		
		
	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

		
	public Pedido getPedido(int rowIndex) {
		return getListaPedido().get(rowIndex);
	}
		
	public void savePedido(Pedido pedido) {
		getListaPedido().add(pedido);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
		
	public void updatePedido(Pedido pedido, int rowIndex) {
		getListaPedido().set(rowIndex, pedido);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
		
	public void removePedido(int rowIndex) {
		getListaPedido().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
		
	public void removeAll() {
		getListaPedido().clear();
		fireTableDataChanged();
	}
		
	@Override
	public int getRowCount() {
		return getListaPedido().size();
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
		Pedido pedido = getListaPedido().get(rowIndex);
		switch(columnIndex) {
		case CODIGO:
			return pedido.getId();
		case DATA:
			return pedido.getData();
		case HORA:
			return pedido.getHora();
		case VALOR_TOTAL:
			return pedido.getValor_total();
		case TIPO_PAGAMENTO:
			return pedido.getTipo_pagamento();
		case TROCO:
			return pedido.getTroco();
		default:
			return pedido;
		}
	}
		
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
		case CODIGO:
			return Integer.class;
		case DATA:
			return String.class;
		case HORA:
			return String.class;
		case VALOR_TOTAL:
			return String.class;
		case TIPO_PAGAMENTO:
			return String.class;
		case TROCO:
			return String.class;
		default:
			return null;
		}
	}
		

	public String[] getColunas() {
		return colunas;
	}

}
