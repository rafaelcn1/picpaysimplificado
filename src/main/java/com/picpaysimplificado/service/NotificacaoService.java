package com.picpaysimplificado.service;

import org.springframework.stereotype.Service;

import com.picpaysimplificado.dto.NotificacaoDTO;
import com.picpaysimplificado.model.Usuario;

@Service
public class NotificacaoService {

	/*@Autowired
	private RestTemplate restTemplate;*/

	//private final String url = "http://o4d9z.mocklab.io/notify";

	public void enviarNotificacao(Usuario usuario, String mensagem) throws Exception {
		String email = usuario.getEmail();
		NotificacaoDTO notificacao = new NotificacaoDTO(email, mensagem);
		/*
		ResponseEntity<String> notificacaoReponse = this.restTemplate.postForEntity(url, notificacao, String.class);
		
		if(!notificacaoReponse.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("Erro ao enviar notificação de transferência!");
			throw new Exception("Serviço de notificação fora do ar!");
		}
		*/
		
		System.out.println(notificacao.toString());

	}

}
