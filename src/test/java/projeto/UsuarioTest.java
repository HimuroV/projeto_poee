package projeto;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.projeto.model.models.Usuario;
import com.projeto.model.service.UsuarioService;

public class UsuarioTest {

	//@Test(expected = Exception.class)
	public void salvarUsuarioBancoDadosTeste() {
		
		Usuario usuario = new Usuario();
		
		//usuario.setId(2);
		usuario.setUsername("Roberto Carlos da Silva");
		usuario.setPassword("123456");
		usuario.setEmail("roberto@carlos.silva.com.br");
		usuario.setAtivo(false);
		usuario.setAdmin(false);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.save(usuario);
		
		System.out.println("Gravando usuário no banco de dados");
		
		//assertTrue(true);
	}
	
	
	
	@Test(expected = Exception.class)
	public void alterarUsuarioBancoDadosTeste() {
		
		Usuario usuario = new Usuario();
		
		usuario.setId(1);
		
		
		
		
		
		//usuario.setUsername("Roberto Carlos da Silva");
		//usuario.setPassword("123456");
		//usuario.setEmail("roberto@carlos.com.br");
		//usuario.setAtivo(false);
		//usuario.setAdmin(false);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuario = usuarioService.findById(usuario.getId());
		
		System.out.print(usuario.toString());
		
		usuario.setEmail("roberto@carlos.com.br");
		
		usuarioService.update(usuario);
				
		System.out.println("Alterando usuário no banco de dados");
		
		//assertTrue(true);
	}
}
