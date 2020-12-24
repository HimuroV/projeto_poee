package projeto;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.projeto.model.models.ItemPedido;
import com.projeto.model.service.ItemPedidoService;

public class ItemPedidoTest {
			//@Test(expected = Exception.class)
			public void salvarItemPedidoBancoDadosTeste() {
				
				ItemPedido itempedido = new ItemPedido();
				
				//itempedido.setId(2);
				itempedido.setQuantidade(2);
				itempedido.setValor_total_item(44.66);
				itempedido.setValor_unitario(22.33);
				
				ItemPedidoService itempedidoService = new ItemPedidoService();
				
				itempedidoService.save(itempedido);
				
				System.out.println("Gravando usuário no banco de dados");
				
				//assertTrue(true);
				
				itempedido = new ItemPedido();
				
				//itempedido.setId(2);
				itempedido.setQuantidade(3);
				itempedido.setValor_total_item(33.33);
				itempedido.setValor_unitario(11.11);
				
				ItemPedidoService itempedidoService1 = new ItemPedidoService();
				
				itempedidoService1.save(itempedido);
				
				System.out.println("Gravando usuário no banco de dados");
			}
			
			//@Test(expected = Exception.class)
			public void alterarItemPedidoBancoDadosTeste() {
				
				ItemPedido itempedido = new ItemPedido();
				
				itempedido.setId(1);
				
				//itempedido.setUsername("Roberto Carlos da Silva");
				//itempedido.setPassword("123456");
				//itempedido.setEmail("roberto@carlos.com.br");
				//itempedido.setAtivo(false);
				//itempedido.setAdmin(false);
				
				ItemPedidoService itempedidoService = new ItemPedidoService();
				
				itempedido = itempedidoService.findById(itempedido.getId());
				
				System.out.print(itempedido.toString());
				
				itempedido.setQuantidade(2);
				
				itempedidoService.update(itempedido);
						
				System.out.println("Alterando usuário no banco de dados");
				
				//assertTrue(true);
			}
				
				//@Test(expected = Exception.class)
				public void listarTodosItemPedidoTabelItemPedido() {
					
					ItemPedidoService itempedidoService = new ItemPedidoService();
					
					List<ItemPedido> listaItemPedido = itempedidoService.findAll();
					
					for (ItemPedido itempedido : listaItemPedido) {
						System.out.println(itempedido.toString());
					}
					
				}
				
				//@Test
				public void excluirItemPedidoDaTabela() {
					
					ItemPedido itempedido = new ItemPedido();
					
					itempedido.setId(2);
					
					ItemPedidoService itempedidoService = new ItemPedidoService();
					
					itempedidoService.delete(itempedido);
					
					
				}
}
