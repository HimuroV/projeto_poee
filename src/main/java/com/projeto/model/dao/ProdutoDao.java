package com.projeto.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.projeto.model.models.Produto;

public class ProdutoDao extends GenericDao<Produto, Integer>{

	public ProdutoDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> listProdutoPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		List<Produto> listaProduto = new ArrayList<Produto>();
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Produto u ")
											 .setFirstResult(numeroPagina)
											 .setMaxResults(defaultPagina);
		listaProduto = query.getResultList();
		
		return listaProduto;
	}
}
