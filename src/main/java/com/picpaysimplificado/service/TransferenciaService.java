package com.picpaysimplificado.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpaysimplificado.dto.TransferenciaDTO;
import com.picpaysimplificado.model.Transferencia;
import com.picpaysimplificado.model.Usuario;
import com.picpaysimplificado.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private NotificacaoService notificacaoService;

	private final String urlAutorizacaoOnline = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";

	/**
	 * Cria uma transferência com base nos dados fornecidos.
	 * 
	 * @param transferencia O objeto TransferenciaDTO contendo os dados da
	 *                      transferência.
	 * @throws Exception Se ocorrer algum erro durante a criação da transferência.
	 */
	public Transferencia criarTransferencia(TransferenciaDTO transferencia) throws Exception {
		
		Transferencia novaTransferencia = null;
		Integer origemId = transferencia.getOrigem().getId();
		Integer destinoId = transferencia.getDestino().getId();
		BigDecimal valorTransferencia = transferencia.getValor();

		Usuario usuarioOrigem = this.usuarioService.findById(origemId);
		Usuario usuarioDestino = this.usuarioService.findById(destinoId);

		this.usuarioService.validacaoTransferencia(usuarioOrigem, valorTransferencia);
		boolean autorizacaoTransferencia = autorizacaoTransferencia(usuarioOrigem, valorTransferencia);

		if (autorizacaoTransferencia) {
			novaTransferencia = new Transferencia(null, valorTransferencia, usuarioOrigem, usuarioDestino,
					LocalDateTime.now());

			// Removendo o dinheiro da conta de origem
			usuarioOrigem.setCarteira(usuarioOrigem.getCarteira().subtract(valorTransferencia));

			// Adicionando o dinheiro na conta de destino
			usuarioDestino.setCarteira(usuarioDestino.getCarteira().add(valorTransferencia));

			// Atualizar os usuarios origem e dentino
			this.usuarioService.save(usuarioOrigem);
			this.usuarioService.save(usuarioDestino);

			// Registrar a transferencia
			this.transferenciaRepository.save(novaTransferencia);
			this.notificacaoService.enviarNotificacao(usuarioOrigem, "Mensagem: Transferencia enviada com sucesso!");
			this.notificacaoService.enviarNotificacao(usuarioDestino, "Mensagem: Você recebeu o valor: " + valorTransferencia + "!");

		} else {
			throw new Exception("Não foi possivel efetuar a transferencia! Autorizador online fora!");
		}
		
		return novaTransferencia;
	}

	/**
	 * Verifica se a transferência está autorizada para o usuário de origem e o
	 * valor da transferência.
	 * 
	 * @param origem             O usuário de origem da transferência.
	 * @param valorTransferencia O valor da transferência.
	 * @return true se a transferência estiver autorizada, false caso contrário.
	 */
	public boolean autorizacaoTransferencia(Usuario origem, BigDecimal valorTransferencia) {

		// Consultar a url do webservice de autorização
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> autorizacaoResponse = this.restTemplate.getForEntity(urlAutorizacaoOnline, Map.class);

		// Capturando o codigo da conexão
		HttpStatusCode statusCode = autorizacaoResponse.getStatusCode();

		// Capturando a mensagem do corpo da autorização
		Object message = autorizacaoResponse.getBody().get("message");

		// Validação da autorização online
		if (statusCode.equals(HttpStatus.OK) && message.equals("Autorizado")) {
			return true;
		} else {
			return false;
		}
	}

	public List<Transferencia> listarTodas() {
		return this.transferenciaRepository.findAll();

	}

}
