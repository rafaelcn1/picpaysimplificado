package com.picpaysimplificado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nomeCompleto;

	@Column(unique = true)
	private String documento;

	@Column(unique = true)
	private String email;

	private String password;
	
	private TipoUsuario tipoUsuario;

}
