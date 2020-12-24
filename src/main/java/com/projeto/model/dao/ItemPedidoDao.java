package com.projeto.model.dao;

import javax.persistence.EntityManager;

import com.projeto.model.models.ItemPedido;

public class ItemPedidoDao extends GenericDao<ItemPedido, Integer>{
	
	public ItemPedidoDao(EntityManager entityManager) {
		super(entityManager);
	}

	
}
