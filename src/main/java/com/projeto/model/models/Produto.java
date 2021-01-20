package com.projeto.model.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TAB_PRODUTO")
public class Produto {
	private Integer id;
	private String nome;
	private double valor_venda;
	private String descricao;
	
	private Set<Ingrediente> ingredientes;
	
	private List<ItemPedido> itempedido;
	
	public Produto() {}
	
	public Produto(Integer id, String nome, double valor_venda, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor_venda = valor_venda;
		this.descricao = descricao;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUTO_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "PRODUTO_NOME", length = 40, nullable = false )
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "PRODUTO_VALOR_VENDA", nullable = false )
	public double getValor_venda() {
		return valor_venda;
	}
	public void setValor_venda(double d) {
		this.valor_venda = d;
	}
	
	@Column(name = "PRODUTO_DESCRICAO", length = 200, nullable = false )
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	// muitos para muitos
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TAB_PRODUTO_INGREDIENTE",
	     joinColumns = @JoinColumn(name="PRODUTO_ID"),
	     inverseJoinColumns = @JoinColumn(name="INGREDIENTE_ID"))
	public Set<Ingrediente> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(Set<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	//um para muitos
	
	@OneToMany( mappedBy = "produto", cascade = CascadeType.ALL)
	public List<ItemPedido> getItemPedido() {
		return itempedido;
	}

	public void setItemPedido(List<ItemPedido> itempedido) {
		this.itempedido = itempedido;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor_venda=" + valor_venda + ", descricao=" + descricao
				+ "]";
	}

	
}
