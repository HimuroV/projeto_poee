package com.projeto.model.models;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="TAB_INGREDIENTE")
public class Ingrediente {
	private Integer id;
	private String nome;
	private Integer quantidade_estoque;
	private double custo_unitario;
	
	private List<Produto> produtos;
	
	public Ingrediente(Integer id, String nome, Integer quantidade_estoque, double custo_unitario) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidade_estoque = quantidade_estoque;
		this.custo_unitario = custo_unitario;
	}

	public Ingrediente() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INGREDIENTE_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "INGREDIENTE_NOME", length = 40, nullable = false )
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "INGREDIENTE_QTDE_ESTOQUE", nullable = false )
	public Integer getQuantidade_estoque() {
		return quantidade_estoque;
	}
	public void setQuantidade_estoque(Integer quantidade_estoque) {
		this.quantidade_estoque = quantidade_estoque;
	}
	
	@Column(name = "INGREDIENTE_CUSTO_UNITARIO", nullable = false )
	public double getCusto_unitario() {
		return custo_unitario;
	}
	public void setCusto_unitario(double d) {
		this.custo_unitario = d;
	}
	
	//muitos para muitos
	
		@ManyToMany(mappedBy = "ingredientes")
		public List<Produto> getProdutos() {
			return produtos;
		}

		public void setProdutos(List<Produto> produtos) {
			this.produtos = produtos;
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
		Ingrediente other = (Ingrediente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nome=" + nome + ", qtde_estoque=" + quantidade_estoque + ", custo_unitario="
				+ custo_unitario + "]";
	}
	
}
