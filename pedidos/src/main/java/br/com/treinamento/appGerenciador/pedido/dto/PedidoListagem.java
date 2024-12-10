package br.com.treinamento.appGerenciador.pedido.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    public PedidoListagem(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.cliente = pedido.getCliente();
        this.vendedor = pedido.getVendedor();
        this.data = pedido.getData();
        this.valorTotal = pedido.getValorTotal();
        this.status = pedido.getStatus();
    }
}

