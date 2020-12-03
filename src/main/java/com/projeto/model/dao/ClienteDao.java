package com.projeto.model.dao;

import javax.persistence.EntityManager;

import com.projeto.model.models.Cliente;

public class ClienteDao extends GenericDao<Cliente, Integer>{

	public ClienteDao(EntityManager entityManager) {
		super(entityManager);
	}
}
