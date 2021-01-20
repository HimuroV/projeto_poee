package com.projeto.model.service;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.dao.PedidoDao;
import com.projeto.model.models.Pedido;

public class PedidoService extends ConexaoBancoService{
	private PedidoDao pedidoDao;
	
	public PedidoService() {
		this.pedidoDao = new PedidoDao(this.getEntityManager());
	}
	
	public Integer save(Pedido pedido) {
		
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		if ( validarDigitacao(pedido) == VariaveisProjeto.DIGITACAO_OK) {
		
			try {
			
				trx.begin();
				this.getPedidoDao().save(pedido);
				trx.commit();
				toReturn = VariaveisProjeto.INCLUSAO_REALIZADA;
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
	
	public Integer update(Pedido pedido) {
		
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		toReturn = validarDigitacao(pedido);
		if ( toReturn == VariaveisProjeto.DIGITACAO_OK) {
		
			try {
			
				trx.begin();
				this.getPedidoDao().update(pedido);
				trx.commit();
				toReturn = VariaveisProjeto.ALTERACAO_REALIZADA;
			} catch (Exception ex) {
				ex.printStackTrace();
				if ( trx.isActive() ) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_ALTERACAO;
				
			} finally {
				this.close();
			}
		
		}
		return toReturn;
	}
	
	public Integer delete(Pedido pedido) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();


		try {

			trx.begin();
			Pedido pedidoEncontrado = this.getPedidoDao().findById(pedido.getId());
			this.getPedidoDao().remove(pedidoEncontrado);
			trx.commit();
			toReturn = VariaveisProjeto.EXCLUSAO_REALIZADA;
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
	
	public Pedido findById(Integer id) {
		return this.getPedidoDao().findById(id);
	}
	
	
	public List<Pedido> findAll(){
		return this.getPedidoDao().findAll(Pedido.class);
	}
	
	public Integer validarDigitacao(Pedido pedido) {
		
		if ( VariaveisProjeto.digitacaoCampo(pedido.getData())) {
			return VariaveisProjeto.PEDIDO_DATA;
		}
		if ( VariaveisProjeto.digitacaoCampo(pedido.getHora())) {
			return VariaveisProjeto.PEDIDO_HORA;
		}
		if ( VariaveisProjeto.digitacaoCampo(pedido.getValor_total())) {
			return VariaveisProjeto.PEDIDO_VALOR_TOTAL;
		}
		if ( VariaveisProjeto.digitacaoCampo(pedido.getTipo_pagamento())) {
			return VariaveisProjeto.PEDIDO_TIPO_PAGAMENTO;
		}
		if ( VariaveisProjeto.digitacaoCampo(pedido.getTroco())) {
			return VariaveisProjeto.PEDIDO_TROCO;
		}
		return VariaveisProjeto.DIGITACAO_OK;
	}

	public PedidoDao getPedidoDao() {
		return pedidoDao;
	}
	
	public Integer countTotalRegister() {
		return pedidoDao.countTotalRegister(Pedido.class);
	}

	public List<Pedido> listPedidoPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		return pedidoDao.listPedidoPaginacao(numeroPagina,defaultPagina);
	}
}
