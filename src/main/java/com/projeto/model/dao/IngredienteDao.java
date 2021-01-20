package com.projeto.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.projeto.model.models.Ingrediente;

public class IngredienteDao extends GenericDao<Ingrediente, Integer>{

	public IngredienteDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<Ingrediente> listIngredientePaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		List<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Ingrediente u "
				 							  + "LEFT JOIN FETCH u.produtos ")
											 .setFirstResult(numeroPagina)
											 .setMaxResults(defaultPagina);
		listaIngrediente = query.getResultList();
		
		return listaIngrediente;
	}
}
