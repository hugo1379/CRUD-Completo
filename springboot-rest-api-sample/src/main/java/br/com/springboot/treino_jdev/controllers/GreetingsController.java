package br.com.springboot.treino_jdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.treino_jdev.model.Usuario;
import br.com.springboot.treino_jdev.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired /* Injeção de dependencia */
	private UsuarioRepository usuarioRepository;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	
	
	@RequestMapping(value = "/olamundo/{nome}",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornaOlaMundo(@PathVariable String nome) {
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		
		usuarioRepository.save(usuario);/*grava no banco de dados*/
		
		return "Ola Mundo " + nome;
	}
	
	@GetMapping(value = "listatodos" )
	@ResponseBody/*retorna os dados para o corpo da resposta*/
	public ResponseEntity<List<Usuario>> listaUsuario(){
		
		List<Usuario> usuarios = usuarioRepository.findAll();/*executa a consulta no banco de dados*/
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/*retorna a lista em json*/
	}

	
	@PostMapping(value = "salvar")/*mapeia a URL*/
	@ResponseBody/*Descrição da resposta*/
	public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){/*Recebe os dados para salvar*/
		
		Usuario user = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);/*no lugar de 'created' pode ser 'ok'*/
		
	}
	@DeleteMapping(value = "delete")
	@ResponseBody/*Descrição da resposta*/
	public ResponseEntity<String> delete(@RequestParam Long iduser){
		
		usuarioRepository.deleteById(iduser);
		
		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping(value = "buscaruserid")
	@ResponseBody/*Descrição da resposta*/
	public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser){/*Recebe os dados para consultar*/
		
		Usuario usuario = usuarioRepository.findById(iduser).get();
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PutMapping(value = "atualizar")/*mapea a URL*/
	@ResponseBody/*Descrição da resposta*/
	public ResponseEntity<?> atualizar (@RequestBody Usuario usuario){/*Quando tem o '?' pode retornar qualquer coisa(String ou Objeto)*/
		
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização.", HttpStatus.OK);
		}
		
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "buscarPorNome")
	@ResponseBody/*Descrição da resposta*/
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name){/**/
		
		List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
		/*Vai ignorar se é maiusculo ou minuscol e se tem espaço ou não.
		 * O trim tira o espaço na digitação da palavra que esta buscando,
		 uppercase para poder usar letras maisculas,
		 */
		
		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	}
	
		
	}

