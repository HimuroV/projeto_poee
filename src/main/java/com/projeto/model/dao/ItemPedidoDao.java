package com.projeto.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.projeto.model.models.ItemPedido;

public class ItemPedidoDao extends GenericDao<ItemPedido, Integer>{
	
	public ItemPedidoDao(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	public List<ItemPedido> listItemPedidoPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		List<ItemPedido> listaItemPedido = new ArrayList<ItemPedido>();
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM ItemPedido u ")
											 .setFirstResult(numeroPagina)
											 .setMaxResults(defaultPagina);
		listaItemPedido = query.getResultList();
		
		return listaItemPedido;
	}
}
