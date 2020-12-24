package projeto;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.projeto.model.models.Cliente;
import com.projeto.model.service.ClienteService;

public class ClienteTest {
		@Test(expected = Exception.class)
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
			
			assertTrue(true);
			
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
}
