package com.projeto.model.service;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.dao.IngredienteDao;
import com.projeto.model.models.Ingrediente;

public class IngredienteService extends ConexaoBancoService{
private IngredienteDao ingredienteDao;
	
	public IngredienteService() {
		this.ingredienteDao = new IngredienteDao(this.getEntityManager());
	}
	
	public Integer save(Ingrediente ingrediente) {
		
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		if ( validarDigitacao(ingrediente) == VariaveisProjeto.DIGITACAO_OK) {
		
			try {
			
				trx.begin();
				this.getIngredienteDao().save(ingrediente);
				trx.commit();
			
			} catch (Exception ex) {
				ex.printStackTrace();
				if ( trx.isActive() ) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_INCLUSAO;
				
			} finally {
				this.close();
			}
		
		}else {
			toReturn = VariaveisProjeto.NOME_CAMPO_VAZIO;
		}
		
		return toReturn;
	}
	
	public Integer update(Ingrediente ingrediente) {
		
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		if ( validarDigitacao(ingrediente) == VariaveisProjeto.DIGITACAO_OK) {
		
			try {
			
				trx.begin();
				this.getIngredienteDao().update(ingrediente);
				trx.commit();
			
			} catch (Exception ex) {
				ex.printStackTrace();
				if ( trx.isActive() ) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_ALTERACAO;
				
			} finally {
				this.close();
			}
		
		}else {
			toReturn = VariaveisProjeto.NOME_CAMPO_VAZIO;
		}
		
		return toReturn;
	}
	
	public Integer delete(Ingrediente ingrediente) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();


		try {

			trx.begin();
			Ingrediente ingredienteEncontrado = this.getIngredienteDao().findById(ingrediente.getId());
			this.getIngredienteDao().remove(ingredienteEncontrado);
			trx.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			if ( trx.isActive() ) {
				trx.rollback();
			}
			toReturn = VariaveisProjeto.ERRO_EXCLUSAO;

		} finally {
			this.close();
		}

		return toReturn;
	}
	
	public Ingrediente findById(Integer id) {
		return this.getIngredienteDao().findById(id);
	}
	
	
	public List<Ingrediente> findAll(){
		return this.getIngredienteDao().findAll(Ingrediente.class);
	}
	
	public Integer validarDigitacao(Ingrediente ingrediente) {
		
		if ( VariaveisProjeto.digitacaoCampo(ingrediente.getNome())) {
			return VariaveisProjeto.INGREDIENTE_NOME;
		}
		if ( VariaveisProjeto.digitacaoCampo(ingrediente.getQtde_estoque())) {
			return VariaveisProjeto.INGREDIENTE_QTDE_ESTOQUE;
		}
		if ( VariaveisProjeto.digitacaoCampo(ingrediente.getCusto_unitario())) {
			return VariaveisProjeto.INGREDIENTE_CUSTO_UNITARIO;
		}
		
		return VariaveisProjeto.DIGITACAO_OK;
	}
	
	public IngredienteDao getIngredienteDao() {
		return ingredienteDao;
	}
}
