package com.picpaysimplificado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpaysimplificado.dto.TransferenciaDTO;
import com.picpaysimplificado.model.Transferencia;
import com.picpaysimplificado.service.TransferenciaService;

/**
 * Esta classe é responsável por controlar as requisições relacionadas a
 * transferências. Ela utiliza o framework Spring Boot para facilitar o
 * desenvolvimento e integração com outros componentes.
 */
@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {
	@Autowired
	private TransferenciaService transferenciaService;

	/**
	 * Método responsável por realizar uma transferência.
	 * 
	 * @param transferencia Objeto TransferenciaDTO contendo os dados da
	 *                      transferência a ser realizada.
	 * @return ResponseEntity contendo a Transferencia criada e o status HTTP OK.
	 * @throws Exception Caso ocorra algum erro durante a realização da
	 *                   transferência.
	 */
	@PostMapping
	public ResponseEntity<Transferencia> realizarTransferencia(@RequestBody TransferenciaDTO transferencia)
			throws Exception {
		Transferencia novaTransferencia = new Transferencia(transferencia);
		this.transferenciaService.criarTransferencia(transferencia);
		return new ResponseEntity<Transferencia>(novaTransferencia, HttpStatus.OK);
	}

	/**
	 * Método responsável por listar todas as transferências realizadas.
	 * 
	 * @return ResponseEntity contendo a lista de Transferencia e o status HTTP OK.
	 */
	@GetMapping
	public ResponseEntity<List<Transferencia>> listarTodas() {
		List<Transferencia> listarTodas = this.transferenciaService.listarTodas();
		return new ResponseEntity<List<Transferencia>>(listarTodas, HttpStatus.OK);
	}
}