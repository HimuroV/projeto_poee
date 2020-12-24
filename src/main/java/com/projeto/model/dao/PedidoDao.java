package com.projeto.model.dao;

import javax.persistence.EntityManager;

import com.projeto.model.models.Pedido;

public class PedidoDao extends GenericDao<Pedido, Integer> {
	
	public PedidoDao(EntityManager entityManager) {
		super(entityManager);
	}
}
