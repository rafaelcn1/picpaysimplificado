package com.picpaysimplificado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.dto.UsuarioDTO;
import com.picpaysimplificado.model.Usuario;
import com.picpaysimplificado.service.UsuarioService;

/**
 * Esta classe é responsável por controlar as requisições relacionadas aos
 * usuários. Ela utiliza o framework Spring Boot para facilitar o
 * desenvolvimento e configuração do servidor.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Método responsável por criar um novo usuário. Recebe um objeto UsuarioDTO
	 * contendo as informações do usuário a ser criado. Retorna uma resposta HTTP
	 * com o novo usuário criado e o status de criação (201 - Created).
	 */
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO usuario) {
		Usuario novoUsuario = this.usuarioService.criarUsuario(usuario);
		return new ResponseEntity<Usuario>(novoUsuario, HttpStatus.CREATED);
	}

	/**
	 * Método responsável por listar todos os usuários cadastrados. Retorna uma
	 * resposta HTTP com a lista de usuários e o status OK (200).
	 */
	@GetMapping
	public ResponseEntity<List<Usuario>> listarTodos() {
		List<Usuario> listarTodos = this.usuarioService.listarTodos();
		return new ResponseEntity<List<Usuario>>(listarTodos, HttpStatus.OK);
	}

	/**
	 * Método responsável por buscar um usuário pelo seu ID. Recebe o ID do usuário
	 * como parâmetro na URL. Retorna uma resposta HTTP com o usuário encontrado e o
	 * status OK (200). Lança uma exceção caso o usuário não seja encontrado.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) throws Exception {
		Usuario findById = this.usuarioService.findById(id);
		return new ResponseEntity<Usuario>(findById, HttpStatus.OK);
	}

	/**
	 * Método responsável por buscar um usuário pelo seu documento. Recebe o
	 * documento do usuário como parâmetro na URL. Retorna uma resposta HTTP com o
	 * usuário encontrado e o status OK (200). Lança uma exceção caso o usuário não
	 * seja encontrado.
	 */
	@GetMapping("/documento/{documento}")
	public ResponseEntity<Usuario> buscarPorDocumento(@PathVariable String documento) throws Exception {
		Usuario usuario = this.usuarioService.buscarPorDocumento(documento);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
}