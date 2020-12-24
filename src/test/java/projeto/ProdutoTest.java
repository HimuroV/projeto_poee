package projeto;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.projeto.model.models.Produto;
import com.projeto.model.service.ProdutoService;

public class ProdutoTest {
			//@Test(expected = Exception.class)
			public void salvarProdutoBancoDadosTeste() {
				
				Produto produto = new Produto();
				
				//produto.setId(2);
				produto.setNome("X-bacon");
				produto.setDescricao("Lanche com pao etc etc");
				produto.setValor_venda(12.50);
		
				
				ProdutoService produtoService = new ProdutoService();
				
				produtoService.save(produto);
				
				System.out.println("Gravando usuário no banco de dados");
				
				//assertTrue(true);
				
				produto = new Produto();
				
				//produto.setId(2);
				produto.setNome("Coca-cola");
				produto.setDescricao("300 ml");
				produto.setValor_venda(4.50);
				
				ProdutoService produtoService1 = new ProdutoService();
				
				produtoService1.save(produto);
				
				System.out.println("Gravando usuário no banco de dados");
			}
			
			//@Test(expected = Exception.class)
			public void alterarProdutoBancoDadosTeste() {
				
				Produto produto = new Produto();
				
				produto.setId(1);
				
				//produto.setUsername("Roberto Carlos da Silva");
				//produto.setPassword("123456");
				//produto.setEmail("roberto@carlos.com.br");
				//produto.setAtivo(false);
				//produto.setAdmin(false);
				
				ProdutoService produtoService = new ProdutoService();
				
				produto = produtoService.findById(produto.getId());
				
				System.out.print(produto.toString());
				
				produto.setNome("X-Bacon");
				
				produtoService.update(produto);
						
				System.out.println("Alterando usuário no banco de dados");
				
				//assertTrue(true);
			}
				
				//@Test(expected = Exception.class)
				public void listarTodosProdutoTabelProduto() {
					
					ProdutoService produtoService = new ProdutoService();
					
					List<Produto> listaProduto = produtoService.findAll();
					
					for (Produto produto : listaProduto) {
						System.out.println(produto.toString());
					}
					
				}
				
				//@Test
				public void excluirProdutoDaTabela() {
					
					Produto produto = new Produto();
					
					produto.setId(2);
					
					ProdutoService produtoService = new ProdutoService();
					
					produtoService.delete(produto);
					
					
				}
}
