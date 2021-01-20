package com.projeto.model.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TAB_CLIENTE")
public class Cliente {

	private Integer id;
	private String nome;
	private String telefone;
	private String bairro;
	private String rua;
	private String numero;
	
	private List<Pedido> pedido;
	
	public Cliente() {}
	
	public Cliente(Integer id, String nome, String telefone, String bairro, String rua, String numero) {
		super();
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENTE_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "CLIENTE_NOME", length = 60, nullable = false )
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "CLIENTE_TELEFONE", length = 20, nullable = false )
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Column(name = "CLIENTE_BAIRRO", length = 40, nullable = false )
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	@Column(name = "CLIENTE_RUA", length = 40, nullable = false )
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	@Column(name = "CLIENTE_NUMERO", length = 10, nullable = false )
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	//um para muitos
	
	@OneToMany( mappedBy = "cliente", cascade = CascadeType.ALL)
	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", bairro=" + bairro + ", rua=" + rua
				+ ", numero=" + numero + "]";
	}
	
}
