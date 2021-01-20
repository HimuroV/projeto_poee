package com.projeto.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.projeto.model.models.Cliente;

public class ClienteDao extends GenericDao<Cliente, Integer>{

	public ClienteDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listClientePaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Cliente u ")
											 .setFirstResult(numeroPagina)
											 .setMaxResults(defaultPagina);
		listaCliente = query.getResultList();
		
		return listaCliente;
	}
}
