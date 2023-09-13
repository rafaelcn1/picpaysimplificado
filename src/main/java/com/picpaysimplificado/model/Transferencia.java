package com.picpaysimplificado.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpaysimplificado.dto.TransferenciaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transferencia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "origem_id")
	private Usuario origem;

	@ManyToOne
	@JoinColumn(name = "destino_id")
	private Usuario destino;

	private LocalDateTime timeStamp;
	
	public Transferencia(TransferenciaDTO transferencia) {
		this.valor = transferencia.getValor();
		this.origem = transferencia.getOrigem();
		this.destino = transferencia.getDestino();
		this.timeStamp = transferencia.getTimeStamp();
	}

}
