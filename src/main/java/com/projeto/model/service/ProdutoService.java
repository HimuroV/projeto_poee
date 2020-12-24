package com.projeto.model.service;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.dao.ProdutoDao;
import com.projeto.model.models.Produto;

public class ProdutoService extends ConexaoBancoService{
	private ProdutoDao produtoDao;
	
	public ProdutoService() {
		this.produtoDao = new ProdutoDao(this.getEntityManager());
	}
	
	public Integer save(Produto produto) {
		
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		if ( validarDigitacao(produto) == VariaveisProjeto.DIGITACAO_OK) {
		
			try {
			
				trx.begin();
				this.getProdutoDao().save(produto);
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
	
	public Integer update(Produto produto) {
		
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		if ( validarDigitacao(produto) == VariaveisProjeto.DIGITACAO_OK) {
		
			try {
			
				trx.begin();
				this.getProdutoDao().update(produto);
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
	
	public Integer delete(Produto produto) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();


		try {

			trx.begin();
			Produto produtoEncontrado = this.getProdutoDao().findById(produto.getId());
			this.getProdutoDao().remove(produtoEncontrado);
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
	
	public Produto findById(Integer id) {
		return this.getProdutoDao().findById(id);
	}
	
	
	public List<Produto> findAll(){
		return this.getProdutoDao().findAll(Produto.class);
	}
	
	public Integer validarDigitacao(Produto produto) {
		
		if ( VariaveisProjeto.digitacaoCampo(produto.getNome())) {
			return VariaveisProjeto.PRODUTO_NOME;
		}
		if ( VariaveisProjeto.digitacaoCampo(produto.getValor_venda())) {
			return VariaveisProjeto.PRODUTO_VALOR_VENDA;
		}
		if ( VariaveisProjeto.digitacaoCampo(produto.getDescricao())) {
			return VariaveisProjeto.PRODUTO_DESCRICAO;
		}
		
		return VariaveisProjeto.DIGITACAO_OK;
	}
	
	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}
}
