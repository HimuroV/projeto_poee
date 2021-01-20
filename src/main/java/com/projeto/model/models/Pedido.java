package com.projeto.model.models;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TAB_PEDIDO")
public class Pedido {
	
	private Integer id;
	private String data; 
	private String hora;
	private double valor_total;
	private String tipo_pagamento;
	private double troco;
	
	private Cliente cliente;
	
	private List<ItemPedido> itempedido;
	
	public Pedido() {}
	
	public Pedido(Integer id, String data, String hora, double valor_total, String tipo_pagamento, double troco) {
		super();
		this.id = id;
		this.data = data;
		this.hora = hora;
		this.valor_total = valor_total;
		this.tipo_pagamento = tipo_pagamento;
		this.troco = troco;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PEDIDO_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "PEDIDO_DATA", length = 10, nullable = false )
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Column(name = "PEDIDO_HORA",length = 10, nullable = false )
	public String getHora() {
		return hora;
	}
	public void setHora(String string) {
		this.hora = string;
	}
	
	@Column(name = "PEDIDO_VALOR_TOTAL", nullable = false )
	public double getValor_total() {
		return valor_total;
	}
	public void setValor_total(double d) {
		this.valor_total = d;
	}
	
	@Column(name = "PEDIDO_TIPO_PAGAMENTO", length = 10, nullable = false )
	public String getTipo_pagamento() {
		return tipo_pagamento;
	}
	public void setTipo_pagamento(String tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}
	
	@Column(name = "PEDIDO_TROCO", nullable = true )
	public double getTroco() {
		return troco;
	}
	public void setTroco(double d) {
		this.troco = d;
	}
	
	//um para muitos
	
	@OneToMany( mappedBy = "pedido", cascade = CascadeType.ALL)
	public List<ItemPedido> getItemPedido() {
		return itempedido;
	}

	public void setItemPedido(List<ItemPedido> itempedido) {
		this.itempedido = itempedido;
	}
	
	// muitos para um
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID", nullable = true)
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data=" + data + ", hora=" + hora + ", valor_total=" + valor_total
				+ ", tipo_pagamento=" + tipo_pagamento + ", troco=" + troco + "]";
	}
	
	
}
