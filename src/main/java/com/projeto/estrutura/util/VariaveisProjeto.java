package com.projeto.estrutura.util;

import java.util.Date;
import java.util.Objects;

public class VariaveisProjeto {
	
	
	public static final String PERSISTENCE_UNIT_NAME = "projeto";
	
	public static final Integer INCLUSAO = 1;
	public static final Integer ALTERACAO = 2;
	public static final Integer EXCLUSAO = 3;
	public static final Integer CONSULTA =4;
	
	public static final Integer ERRO_INCLUSAO = 10;
	public static final Integer ERRO_ALTERACAO = 20;
	public static final Integer ERRO_EXCLUSAO = 30;
	
	public static final Integer INCLUSAO_REALIZADA = 1;
	public static final Integer ALTERACAO_REALIZADA = 2;
	public static final Integer EXCLUSAO_REALIZADA =3;
	
	public static final Integer DIGITACAO_OK = 100;
	public static final Integer NOME_CAMPO_VAZIO = 200;
	
	//Usuário
	public static final Integer USUARIO_USERNAME = 201;
	public static final Integer USUARIO_EMAIL = 202;
	public static final Integer USUARIO_PASSWORD = 203;
	
	//Departamento
	public static final Integer DEPARTAMENTO_NOME = 300;
	
	//Cliente
	public static final Integer CLIENTE_NOME = 400;
	public static final Integer CLIENTE_TELEFONE = 401;
	public static final Integer CLIENTE_BAIRRO = 402;
	public static final Integer CLIENTE_RUA = 403;
	public static final Integer CLIENTE_NUMERO = 404;
	
	//Pedido
	public static final Integer PEDIDO_DATA = 500;
	public static final Integer PEDIDO_HORA = 501;
	public static final Integer PEDIDO_VALOR_TOTAL = 502;
	public static final Integer PEDIDO_TIPO_PAGAMENTO = 503;
	public static final Integer PEDIDO_TROCO = 504;
	
	//ItemPedido
	public static final Integer ITEM_PEDIDO_QUANTIDADE = 600;
	public static final Integer ITEM_PEDIDO_VALOR_UNITARIO = 601;
	public static final Integer ITEM_PEDIDO_VALOR_TOTAL_ITEM = 602;
	
	//Produto
	public static final Integer PRODUTO_NOME = 700;
	public static final Integer PRODUTO_VALOR_VENDA = 701;
	public static final Integer PRODUTO_DESCRICAO = 702;
	
	//Ingrediente 
	public static final Integer INGREDIENTE_NOME = 800;
	public static final Integer INGREDIENTE_QTDE_ESTOQUE = 801;
	public static final Integer INGREDIENTE_CUSTO_UNITARIO = 802;
	
	public static final String LIMPA_CAMPO = "";
	
	public static boolean digitacaoCampo(Integer texto) {
		
		if( Objects.isNull(texto)) {
			return true;
		}
		
		if("".equals(String.valueOf(texto))) {
			return true;
		}
		
		return false;
	}
	
	public static boolean digitacaoCampo(Date texto) {
		
		if( Objects.isNull(texto)) {
			return true;
		}
		
		if("".equals(String.valueOf(texto))) {
			return true;
		}
		
		return false;
	}
	
	public static boolean digitacaoCampo(float texto) {
		
		if( Objects.isNull(texto)) {
			return true;
		}
		
		if("".equals(String.valueOf(texto))) {
			return true;
		}
		
		return false;
	}
	
	public static boolean digitacaoCampo(String texto) {
				
		if( Objects.isNull(texto)) {
			return true;
		}
		
		if("".equals(texto.trim())) {
			return true;
		}
		
		return false;
	}
	
	public static Integer converteToInteger(String texto) {
		return Integer.parseInt(texto);
	}
	
}
