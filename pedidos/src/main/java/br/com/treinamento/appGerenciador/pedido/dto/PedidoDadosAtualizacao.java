package br.com.treinamento.appGerenciador.pedido.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;

@Getter
public class PedidoDadosAtualizacao {
	
	@Null
	private Long idPedido;
	
	@NotNull
	private Long idCliente;

	@NotNull
	private Long idVendedor;
	
	@NotNull
	private LocalDate data;

	private BigDecimal valorTotal;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String identificadorPedido;
	
	@NotBlank
	private String identificadorCliente;
	
	@NotNull
	private LocalDateTime dataCompra;
	
	@NotNull
	private LocalDateTime dataAprovacao;
	
	@NotNull
	private LocalDateTime dataEntregaTransportadora;
	
	@NotNull
	private LocalDateTime dataEntregaCliente;
	
	@NotNull
	private LocalDateTime dataEntregaEstimada;
	
	private Boolean ativo;
}
