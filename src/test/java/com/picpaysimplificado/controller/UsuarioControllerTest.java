package com.picpaysimplificado.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.picpaysimplificado.dto.UsuarioDTO;
import com.picpaysimplificado.model.Usuario;
import com.picpaysimplificado.service.UsuarioService;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UsuarioService usuarioService;

	@Test
	public void testCriarUsuario() throws Exception {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		// Defina as propriedades do usuário DTO simulado

		Usuario novoUsuario = new Usuario();
		// Defina as propriedades do novo usuário simulado

		Mockito.when(usuarioService.criarUsuario(Mockito.any(UsuarioDTO.class))).thenReturn(novoUsuario);

		mockMvc.perform(MockMvcRequestBuilders.post("/usuarios").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(usuarioDTO))).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(novoUsuario.getId()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nomeCompleto").value(novoUsuario.getNomeCompleto()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.documento").value(novoUsuario.getDocumento()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.password").value(novoUsuario.getPassword()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.carteira").value(novoUsuario.getCarteira()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.tipoUsuario").value(novoUsuario.getTipoUsuario()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value(novoUsuario.getEmail()));
		// Verificar o resultado esperado
	}

	@Test
	public void testListarTodos() throws Exception {
		List<Usuario> usuarios = new ArrayList<>();
		// Adicione usuários simulados à lista

		Mockito.when(usuarioService.listarTodos()).thenReturn(usuarios);

		mockMvc.perform(MockMvcRequestBuilders.get("/usuarios")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(usuarios.size()));
	}
	
	@Test
	public void testBuscarPorId() throws Exception {
		Integer id = 9;
		Usuario usuario = new Usuario();
		// Defina as propriedades do usuário simulado

		Mockito.when(usuarioService.findById(id)).thenReturn(usuario);

		mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{id}", id))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
	}

	@Test
	public void testBuscarPorDocumento() throws Exception {
		String documento = "123456789";
		Usuario usuario = new Usuario();
		// Defina as propriedades do usuário simulado

		Mockito.when(usuarioService.buscarPorDocumento(documento)).thenReturn(usuario);

		mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/documento/{documento}", documento))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.documento").value(documento));
	}

	// Método auxiliar para converter um objeto em uma string JSON
	private static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
