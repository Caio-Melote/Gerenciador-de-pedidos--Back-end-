package br.com.treinamento.appGerenciador.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.treinamento.appGerenciador.pedido.dto.PedidoDadosAtualizacao;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoDadosCadastro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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
    private Boolean ativo; 
    
    public Pedido(PedidoDadosCadastro dados) {
    	
    	this.cliente = null;
        this.vendedor = null;
        this.data = dados.getData();
        this.valorTotal = dados.getValorTotal();
        this.status = dados.getStatus();
        this.ativo = true;
    	}
    
    public void excluir() {
		this.ativo = false;
	}

	public void atualizarInformacoes(@Valid PedidoDadosAtualizacao dados) {

		if (dados.getData() != null) {
			this.data = dados.getData();
		}
		
		if (dados.getValorTotal() != null) {
			this.valorTotal = dados.getValorTotal();
		}
		
		if (dados.getStatus() != null) {
			this.status = dados.getStatus();
		}

		if (dados.getAtivo()) {
			this.ativo = dados.getAtivo();
		}
		
	}
    
    
    }

