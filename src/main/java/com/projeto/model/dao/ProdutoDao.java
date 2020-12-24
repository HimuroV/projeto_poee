package com.projeto.model.dao;

import javax.persistence.EntityManager;

import com.projeto.model.models.Produto;

public class ProdutoDao extends GenericDao<Produto, Integer>{

	public ProdutoDao(EntityManager entityManager) {
		super(entityManager);
	}
}
