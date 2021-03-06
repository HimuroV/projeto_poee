package com.projeto.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.projeto.model.models.Usuario;

public class UsuarioDao extends GenericDao<Usuario, Integer >{

	public UsuarioDao(EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listUsuarioPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		boolean ativo = true;
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Usuario u "
											+ "LEFT JOIN FETCH u.departamento "
											+ "LEFT JOIN FETCH u.roles "
											+ "WHERE u.ativo =:ativo")
											 .setParameter("ativo", ativo)
											 .setFirstResult(numeroPagina)
											 .setMaxResults(defaultPagina);
		listaUsuario = query.getResultList();
		
		return listaUsuario;
	}
	
	
}
