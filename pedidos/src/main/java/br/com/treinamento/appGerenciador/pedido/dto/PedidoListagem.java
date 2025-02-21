package br.com.treinamento.appGerenciador.pedido.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import br.com.treinamento.appGerenciador.model.Cliente;
import br.com.treinamento.appGerenciador.model.Pedido;
import br.com.treinamento.appGerenciador.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoListagem {
	

    private Long idPedido; 
    private Cliente cliente;
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

    public PedidoListagem(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.cliente = pedido.getCliente();
        this.vendedor = pedido.getVendedor();
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotal();
        this.status = pedido.getStatus();
        this.identificadorPedido = pedido.getIdentificadorPedido();
        this.identificadorCliente = pedido.getIdentificadorCliente();
        this.dataCompra = pedido.getDataCompra();
        this.dataAprovacao = pedido.getDataAprovacao();
        this.dataEntregaTransportadora = pedido.getDataEntregaTransportadora();
        this.dataEntregaCliente = pedido.getDataEntregaCliente();
        this.dataEntregaEstimada = pedido.getDataEntregaEstimada();
    }
}

