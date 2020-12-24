package com.projeto.model.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAB_PEDIDO")
public class Pedido {
	
	private Integer id;
	private Date data; 
	private Date hora;
	private float valor_total;
	private String tipo_pagamento;
	private float troco;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PEDIDO_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "PEDIDO_DATA", nullable = false )
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@Column(name = "PEDIDO_HORA", nullable = false )
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	
	@Column(name = "PEDIDO_VALOR_TOTAL", nullable = false )
	public Float getValor_total() {
		return valor_total;
	}
	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
	}
	
	@Column(name = "PEDIDO_TIPO_PAGAMENTO", length = 10, nullable = false )
	public String getTipo_pagamento() {
		return tipo_pagamento;
	}
	public void setTipo_pagamento(String tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}
	
	@Column(name = "PEDIDO_TROCO", nullable = true )
	public float getTroco() {
		return troco;
	}
	public void setTroco(float troco) {
		this.troco = troco;
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
