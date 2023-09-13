package com.picpaysimplificado;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.picpaysimplificado.controller.TransferenciaController;
import com.picpaysimplificado.dto.TransferenciaDTO;
import com.picpaysimplificado.model.Transferencia;
import com.picpaysimplificado.service.TransferenciaService;

@RunWith(SpringRunner.class)
@WebMvcTest(TransferenciaController.class)
public class TransferenciaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransferenciaService transferenciaService;


	@Test
	public void testRealizarTransferencia() throws Exception {
		// Dados de teste
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		// Defina os dados de teste no objeto transferenciaDTO

		Transferencia transferenciaEsperada = new Transferencia();
		// Defina os dados de teste no objeto transferenciaEsperada

		// Configurar comportamento do TransferenciaService
		Mockito.when(transferenciaService.criarTransferencia(transferenciaDTO)).thenReturn(transferenciaEsperada);

		// Executar a requisição POST simulada
		mockMvc.perform(MockMvcRequestBuilders.post("/transferencias").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(transferenciaDTO))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(transferenciaEsperada.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.destino").value(transferenciaEsperada.getDestino()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(transferenciaEsperada.getValor()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.origem").value(transferenciaEsperada.getOrigem()));
		// Verificar o resultado esperado
	}

	// Método auxiliar para converter um objeto em uma string JSON
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
