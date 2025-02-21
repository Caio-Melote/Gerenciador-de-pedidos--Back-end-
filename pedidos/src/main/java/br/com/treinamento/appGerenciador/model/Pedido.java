package br.com.treinamento.appGerenciador.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoDadosPlanilha;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pedido")
@Table(name = "pedido")
@NoArgsConstructor
@Getter
@Setter
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_vendedor")
	private Vendedor vendedor;
	private LocalDate data;
	private BigDecimal valorTotal;
	private String status;
	private String identificadorPedido;
	private String identificadorCliente;
	private LocalDateTime dataCompra;
	private LocalDateTime dataAprovacao;
	private LocalDateTime dataEntregaTransportadora;
	private LocalDateTime dataEntregaCliente;
	private LocalDateTime dataEntregaEstimada;
	private Boolean ativo;

	@SuppressWarnings("static-access")
	public Pedido(Cliente cliente, Vendedor vendedor, LocalDate data, BigDecimal valorTotal, String status, String identificadorPedido, 
			String identificadorCliente, LocalDateTime dataCompra, LocalDateTime dataAprovacao, LocalDateTime dataEntregaTransportadora,
			LocalDateTime dataEntregaCliente, LocalDateTime dataEntregaEstimada) {

		this.cliente = cliente;
		this.vendedor = vendedor;
		this.data = data;
		this.valorTotal = this.valorTotal.ZERO;
		this.status = status;
		this.identificadorPedido = identificadorPedido;
		this.identificadorCliente = identificadorCliente;
		this.dataCompra = dataCompra;
		this.dataAprovacao = dataAprovacao;
		this.dataEntregaTransportadora = dataEntregaTransportadora;
		this.dataEntregaCliente = dataEntregaCliente;
		this.dataEntregaEstimada = dataEntregaEstimada;
		this.ativo = true;
	}

	public Pedido(PedidoDadosPlanilha dados) {

		this.identificadorCliente = dados.getIdentificadorCliente();
		this.identificadorPedido = dados.getIdentificadorPedido();
		this.status = dados.getStatus();
		this.dataCompra = dados.getDataCompra();
		this.dataAprovacao = dados.getDataAprovacao();
		this.dataEntregaTransportadora = dados.getDataEntregaTransportadora();
		this.dataEntregaCliente = dados.getDataEntregaCliente();
		this.dataEntregaEstimada = dados.getDataEntregaEstimada();
		this.ativo = true;
	}

	public void excluir() {
		this.ativo = false;
	}

	public void atualizarInformacoes(Cliente cliente, Vendedor vendedor, LocalDate data, BigDecimal valorTotal,
			String status, String identificadorPedido, String identificadorCliente, LocalDateTime dataCompra, LocalDateTime dataAprovacao,
			LocalDateTime dataEntregaTransportadora, LocalDateTime dataEntregaCliente, LocalDateTime dataEntregaEstimada) {

		if (cliente != null) {
			this.cliente = cliente;
		}

		if (vendedor != null) {
			this.vendedor = vendedor;
		}

		if (data != null) {
			this.data = data;
		}

		if (valorTotal != null) {
			this.valorTotal = valorTotal;
		}

		if (status != null) {
			this.status = status;
		}
		
		if (identificadorPedido != null) {
			this.identificadorPedido = identificadorPedido;
		}

		if (identificadorCliente != null) {
			this.identificadorCliente = identificadorCliente;
		}
		
		if (dataCompra != null) {
			this.dataCompra = dataCompra;
		}
		
		if (dataAprovacao != null) {
			this.dataAprovacao = dataAprovacao;
		}
		
		if (dataEntregaTransportadora != null) {
			this.dataEntregaTransportadora = dataEntregaTransportadora;
		}
		
		if (dataEntregaCliente != null) {
			this.dataEntregaCliente = dataEntregaCliente;
		}
		
		if (dataEntregaEstimada != null) {
			this.dataEntregaEstimada = dataEntregaEstimada;
		}

	}

}
