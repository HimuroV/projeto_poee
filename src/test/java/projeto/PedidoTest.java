package projeto;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import com.projeto.model.models.Pedido;
import com.projeto.model.service.PedidoService;

public class PedidoTest {
			//@Test(expected = Exception.class)
			public void salvarPedidoBancoDadosTeste() {
				
				Pedido pedido = new Pedido();
				//pedido.setId(2);
				pedido.setData(dataatual());
				pedido.setHora(horaatual());
				pedido.setValor_total(50.00);
				pedido.setTipo_pagamento("Dinheiro");
				pedido.setTroco(29.98);
				
				PedidoService pedidoService = new PedidoService();
				
				pedidoService.save(pedido);
				
				System.out.println("Gravando usuário no banco de dados");
				
				//assertTrue(true);
				
				pedido = new Pedido();
				
				//pedido.setId(2);
				pedido.setData(dataatual());
				pedido.setHora(horaatual());
				pedido.setValor_total(60.00);
				pedido.setTipo_pagamento("Débito");
				pedido.setTroco(0);
				
				PedidoService pedidoService1 = new PedidoService();
				
				pedidoService1.save(pedido);
				
				System.out.println("Gravando usuário no banco de dados");
			}
			
			private String dataatual() {
				String data_aux= "dd/MM/yyyy";
				return new SimpleDateFormat(data_aux).format(GregorianCalendar.getInstance().getTime());
			}

			private String horaatual() {
				String hora_aux= "HH:mm:ss";
				return new SimpleDateFormat(hora_aux).format(GregorianCalendar.getInstance().getTime());
			}
			
			//@Test(expected = Exception.class)
			public void alterarPedidoBancoDadosTeste() {
				
				Pedido pedido = new Pedido();
				
				pedido.setId(1);
				
				//pedido.setUsername("Roberto Carlos da Silva");
				//pedido.setPassword("123456");
				//pedido.setEmail("roberto@carlos.com.br");
				//pedido.setAtivo(false);
				//pedido.setAdmin(false);
				
				PedidoService pedidoService = new PedidoService();
				
				pedido = pedidoService.findById(pedido.getId());
				
				System.out.print(pedido.toString());
				
				pedido.setValor_total(100.56);
				
				pedidoService.update(pedido);
						
				System.out.println("Alterando usuário no banco de dados");
				
				//assertTrue(true);
			}
				
				//@Test(expected = Exception.class)
				public void listarTodosPedidoTabelPedido() {
					
					PedidoService pedidoService = new PedidoService();
					
					List<Pedido> listaPedido = pedidoService.findAll();
					
					for (Pedido pedido : listaPedido) {
						System.out.println(pedido.toString());
					}
					
				}
				
				//@Test
				public void excluirPedidoDaTabela() {
					
					Pedido pedido = new Pedido();
					
					pedido.setId(2);
					
					PedidoService pedidoService = new PedidoService();
					
					pedidoService.delete(pedido);
					
					
				}
}
