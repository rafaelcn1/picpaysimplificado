package com.picpaysimplificado.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Transferencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "origem_id")
	private Usuario origem;
	
	@ManyToOne
	@JoinColumn(name = "destino_id")
	private Usuario destino;
	
	private LocalDateTime timeStamp;

}
