package com.projeto.model.dao;

import javax.persistence.EntityManager;

import com.projeto.model.models.Ingrediente;

public class IngredienteDao extends GenericDao<Ingrediente, Integer>{

	public IngredienteDao(EntityManager entityManager) {
		super(entityManager);
	}
}
