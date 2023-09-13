package com.picpaysimplificado.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.picpaysimplificado.dto.UsuarioDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // Parar Criar todos os gets, sets, equals, toString e Hashcode
@AllArgsConstructor // Parar criar o construtor que aceita todos os campos da classe como
					// argumentos.
@NoArgsConstructor
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nomeCompleto;

	@Column(unique = true)
	private String documento;

	@Column(unique = true)
	private String email;

	private String password;

	@Enumerated(EnumType.STRING) // Para dizer que o campo é um dos valores Comum ou Lojista
	private TipoUsuario tipoUsuario;

	private BigDecimal carteira;

	public Usuario(UsuarioDTO usuarioDTO) {
		super();
		this.nomeCompleto = usuarioDTO.getNomeCompleto();
		this.documento = usuarioDTO.getDocumento();
		this.email = usuarioDTO.getEmail();
		this.password = usuarioDTO.getPassword();
		this.tipoUsuario = usuarioDTO.getTipoUsuario();
		this.carteira = usuarioDTO.getCarteira();
	}
}
