package com.picpaysimplificado.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.picpaysimplificado.dto.ExceptionDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerException {

	/**
	 * Trata a exceção de violação de integridade de dados.
	 * 
	 * @param exception A exceção de violação de integridade de dados.
	 * @return Uma resposta HTTP com o corpo contendo um objeto ExceptionDTO.
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionDTO> usuarioDuplicado(DataIntegrityViolationException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado, verifique CPF ou EMAIL", "400");
		return ResponseEntity.badRequest().body(exceptionDTO);
	}

	/**
	 * Trata uma exceção genérica.
	 * 
	 * @param exception A exceção genérica.
	 * @return Uma resposta HTTP com o corpo contendo um objeto ExceptionDTO.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDTO> transferenciaTipoUsuario(Exception exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
		return ResponseEntity.badRequest().body(exceptionDTO);
	}

	/**
	 * Trata a exceção de entidade não encontrada.
	 * 
	 * @param exception A exceção de entidade não encontrada.
	 * @return Uma resposta HTTP com status 404 (Not Found).
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionDTO> usuarioNaoEncontrado(Exception exception) {
		return ResponseEntity.notFound().build();
	}
}
