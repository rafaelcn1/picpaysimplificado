package com.picpaysimplificado.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpaysimplificado.dto.UsuarioDTO;
import com.picpaysimplificado.model.TipoUsuario;
import com.picpaysimplificado.model.Usuario;
import com.picpaysimplificado.repository.UsuarioRepository;

/**
 * Classe responsável por conter as regras de negócio relacionadas aos usuários.
 */
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * Método responsável por validar uma transferência de valor entre usuários.
	 * 
	 * @param origem             O usuário de origem da transferência.
	 * @param valorTransferencia O valor a ser transferido.
	 * @throws Exception Caso ocorra algum erro na validação da transferência.
	 */
	public void validacaoTransferencia(Usuario origem, BigDecimal valorTransferencia) throws Exception {
		// Verifica se o usuário não é um lojista
		if (origem.getTipoUsuario() == TipoUsuario.LOJISTA) {
			throw new Exception("Lojista não pode realizar transferencias!");
		}

		// Verifica se o valor da transferencia é maior que ZERO
		if (valorTransferencia.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("Valor da transferencia tem que ser maior que ZERO!");
		}
		// Verifica se o saldo na carteira é suficiente para a transferência
		if (origem.getCarteira().compareTo(valorTransferencia) < 0) {
			throw new Exception("Saldo insuficiente!");
		}

	}

	/**
	 * Método responsável por buscar um usuário pelo seu ID.
	 * 
	 * @param id O ID do usuário a ser buscado.
	 * @return O usuário encontrado.
	 * @throws Exception Caso o usuário não seja encontrado.
	 */
	public Usuario findById(Integer id) throws Exception {
		return this.usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado!"));
	}

	/**
	 * Método responsável por salvar um usuário.
	 * 
	 * @param usuario O usuário a ser salvo.
	 */
	public void save(Usuario usuario) {
		this.usuarioRepository.save(usuario);
	}

	/**
	 * Método responsável por criar um novo usuário a partir de um DTO.
	 * 
	 * @param usuario O DTO contendo os dados do usuário a ser criado.
	 * @return O usuário criado.
	 */
	public Usuario criarUsuario(UsuarioDTO usuario) {
		Usuario novoUsuario = new Usuario(usuario);
		this.save(novoUsuario);
		return novoUsuario;

	}

	/**
	 * Método responsável por listar todos os usuários cadastrados.
	 * 
	 * @return Uma lista contendo todos os usuários cadastrados.
	 */
	public List<Usuario> listarTodos() {
		return this.usuarioRepository.findAll();
	}

	/**
	 * Método responsável por buscar um usuário pelo seu documento (CPF).
	 * 
	 * @param documento O documento (CPF/CNPJ) do usuário a ser buscado.
	 * @return O usuário encontrado.
	 * @throws Exception Caso o CPF não esteja cadastrado.
	 */
	public Usuario buscarPorDocumento(String documento) throws Exception {
		return this.usuarioRepository.findUsuarioByDocumento(documento)
				.orElseThrow(() -> new Exception("CPF/CNPJ não cadastrado!"));
	}

}