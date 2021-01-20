package com.projeto.model.service;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.dao.ItemPedidoDao;
import com.projeto.model.models.ItemPedido;

public class ItemPedidoService extends ConexaoBancoService {
	private ItemPedidoDao itempedidoDao;
	
	public ItemPedidoService() {
		this.itempedidoDao = new ItemPedidoDao(this.getEntityManager());
	}
	
	public Integer save(ItemPedido itempedido) {
		
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		if ( validarDigitacao(itempedido) == VariaveisProjeto.DIGITACAO_OK) {
		
			try {
			
				trx.begin();
				this.getItemPedidoDao().save(itempedido);
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
	
	public Integer update(ItemPedido itempedido) {
		
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		toReturn = validarDigitacao(itempedido);
		if (  toReturn == VariaveisProjeto.DIGITACAO_OK) {
		
			try {
			
				trx.begin();
				this.getItemPedidoDao().update(itempedido);
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
	
	public Integer delete(ItemPedido itempedido) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();


		try {

			trx.begin();
			ItemPedido itempedidoEncontrado = this.getItemPedidoDao().findById(itempedido.getId());
			this.getItemPedidoDao().remove(itempedidoEncontrado);
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
	
	public ItemPedido findById(Integer id) {
		return this.getItemPedidoDao().findById(id);
	}
	
	
	public List<ItemPedido> findAll(){
		return this.getItemPedidoDao().findAll(ItemPedido.class);
	}
	
	public Integer validarDigitacao(ItemPedido itempedido) {
		
		if ( VariaveisProjeto.digitacaoCampo(itempedido.getQuantidade())) {
			return VariaveisProjeto.ITEM_PEDIDO_QUANTIDADE;
		}
		if ( VariaveisProjeto.digitacaoCampo(itempedido.getValor_unitario())) {
			return VariaveisProjeto.ITEM_PEDIDO_VALOR_UNITARIO;
		}
		if ( VariaveisProjeto.digitacaoCampo(itempedido.getValor_total_item())) {
			return VariaveisProjeto.ITEM_PEDIDO_VALOR_TOTAL_ITEM;
		}
		
		return VariaveisProjeto.DIGITACAO_OK;
	}
	
	public ItemPedidoDao getItemPedidoDao() {
		return itempedidoDao;
	}
	
	public Integer countTotalRegister() {
		return itempedidoDao.countTotalRegister(ItemPedido.class);
	}

	public List<ItemPedido> listItemPedidoPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		return itempedidoDao.listItemPedidoPaginacao(numeroPagina,defaultPagina);
	}
}
