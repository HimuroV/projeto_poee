package com.projeto.model.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAB_ITEM_PEDIDO")
public class ItemPedido {
	
	private Integer id;
	private Integer quantidade;
	private float valor_unitario;
	private float valor_total_item;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_PEDIDO_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "ITEM_PEDIDO_QUANTIDADE", nullable = false )
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Column(name = "ITEM_PEDIDO_VALOR_UNITARIO", nullable = false )
	public float getValor_unitario() {
		return valor_unitario;
	}
	public void setValor_unitario(float valor_unitario) {
		this.valor_unitario = valor_unitario;
	}
	
	@Column(name = "ITEM_PEDIDO_VALOR_TOTAL_ITEM", nullable = false )
	public float getValor_total_item() {
		return valor_total_item;
	}
	public void setValor_total_item(float valor_total_item) {
		this.valor_total_item = valor_total_item;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", quantidade=" + quantidade + ", valor_unitario=" + valor_unitario
				+ ", valor_total=" + valor_total_item + "]";
	}
	
}
