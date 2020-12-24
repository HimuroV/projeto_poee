package projeto;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.projeto.model.models.Ingrediente;
import com.projeto.model.service.IngredienteService;

public class IngredienteTest {
			//@Test(expected = Exception.class)
			public void salvarIngredienteBancoDadosTeste() {
				
				Ingrediente ingrediente = new Ingrediente();
				
				//ingrediente.setId(2);
				ingrediente.setNome("Tomate");
				ingrediente.setCusto_unitario(0.50);
				ingrediente.setQtde_estoque(50);
				
				IngredienteService ingredienteService = new IngredienteService();
				
				ingredienteService.save(ingrediente);
				
				System.out.println("Gravando usuário no banco de dados");
				
				//assertTrue(true);
				
				ingrediente = new Ingrediente();
				
				//ingrediente.setId(2);
				ingrediente.setNome("Pão");
				ingrediente.setCusto_unitario(0.15);
				ingrediente.setQtde_estoque(85);
				
				IngredienteService ingredienteService1 = new IngredienteService();
				
				ingredienteService1.save(ingrediente);
				
				System.out.println("Gravando usuário no banco de dados");
			}
			
			//@Test(expected = Exception.class)
			public void alterarIngredienteBancoDadosTeste() {
				
				Ingrediente ingrediente = new Ingrediente();
				
				ingrediente.setId(1);
				
				//ingrediente.setUsername("Roberto Carlos da Silva");
				//ingrediente.setPassword("123456");
				//ingrediente.setEmail("roberto@carlos.com.br");
				//ingrediente.setAtivo(false);
				//ingrediente.setAdmin(false);
				
				IngredienteService ingredienteService = new IngredienteService();
				
				ingrediente = ingredienteService.findById(ingrediente.getId());
				
				System.out.print(ingrediente.toString());
				
				ingrediente.setQtde_estoque(45);
				
				ingredienteService.update(ingrediente);
						
				System.out.println("Alterando usuário no banco de dados");
				
				//assertTrue(true);
			}
				
				//@Test(expected = Exception.class)
				public void listarTodosIngredienteTabelIngrediente() {
					
					IngredienteService ingredienteService = new IngredienteService();
					
					List<Ingrediente> listaIngrediente = ingredienteService.findAll();
					
					for (Ingrediente ingrediente : listaIngrediente) {
						System.out.println(ingrediente.toString());
					}
					
				}
				
				//@Test
				public void excluirIngredienteDaTabela() {
					
					Ingrediente ingrediente = new Ingrediente();
					
					ingrediente.setId(2);
					
					IngredienteService ingredienteService = new IngredienteService();
					
					ingredienteService.delete(ingrediente);
					
					
				}
			
}
