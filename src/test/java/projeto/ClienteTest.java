package projeto;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.projeto.model.models.Cliente;
import com.projeto.model.service.ClienteService;

public class ClienteTest {
		//@Test(expected = Exception.class)
		public void salvarClienteBancoDadosTeste() {
			
			Cliente cliente = new Cliente();
			
			//cliente.setId(2);
			cliente.setNome("Ana Carolina");
			cliente.setTelefone("24689999");
			cliente.setBairro("concordia");
			cliente.setRua("Fundadores");
			cliente.setNumero("29");
			
			ClienteService clienteService = new ClienteService();
			
			clienteService.save(cliente);
			
			System.out.println("Gravando usuário no banco de dados");
			
			//assertTrue(true);
			
			cliente = new Cliente();
			
			//cliente.setId(2);
			cliente.setNome("Joao da Silva");
			cliente.setTelefone("13792834");
			cliente.setBairro("centro");
			cliente.setRua("rua do fico");
			cliente.setNumero("456");
			
			ClienteService clienteService1 = new ClienteService();
			
			clienteService1.save(cliente);
			
			System.out.println("Gravando usuário no banco de dados");
		}
		
		//@Test(expected = Exception.class)
		public void alterarClienteBancoDadosTeste() {
			
			Cliente cliente = new Cliente();
			
			cliente.setId(1);
			
			//cliente.setUsername("Roberto Carlos da Silva");
			//cliente.setPassword("123456");
			//cliente.setEmail("roberto@carlos.com.br");
			//cliente.setAtivo(false);
			//cliente.setAdmin(false);
			
			ClienteService clienteService = new ClienteService();
			
			cliente = clienteService.findById(cliente.getId());
			
			System.out.print(cliente.toString());
			
			cliente.setNumero("100");
			
			clienteService.update(cliente);
					
			System.out.println("Alterando usuário no banco de dados");
			
			//assertTrue(true);
		}
			
			//@Test(expected = Exception.class)
			public void listarTodosClienteTabelCliente() {
				
				ClienteService clienteService = new ClienteService();
				
				List<Cliente> listaCliente = clienteService.findAll();
				
				for (Cliente cliente : listaCliente) {
					System.out.println(cliente.toString());
				}
				
			}
			
			//@Test
			public void excluirClienteDaTabela() {
				
				Cliente cliente = new Cliente();
				
				cliente.setId(2);
				
				ClienteService clienteService = new ClienteService();
				
				clienteService.delete(cliente);
				
				
			}
		
}
