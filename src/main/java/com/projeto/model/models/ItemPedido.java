package com.projeto.model.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TAB_ITEM_PEDIDO")
public class ItemPedido {
	
	private Integer id;
	private Integer quantidade;
	private double valor_unitario;
	private double valor_total_item;
	
	private Pedido pedido;
	private Produto produto;
	
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
	public double getValor_unitario() {
		return valor_unitario;
	}
	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}
	
	@Column(name = "ITEM_PEDIDO_VALOR_TOTAL_ITEM", nullable = false )
	public double getValor_total_item() {
		return valor_total_item;
	}
	public void setValor_total_item(double d) {
		this.valor_total_item = d;
	}
	
	// muitos para um
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEDIDO_ID", nullable = false)
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	// muitos para um
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Produto_ID", nullable = false)
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
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
