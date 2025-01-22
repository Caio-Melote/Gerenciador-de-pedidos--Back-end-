package br.com.treinamento.appGerenciador.pedidoProduto.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoDadosPlanilha {
    
	@NotBlank
	private String identificadorPedido;
	
	@NotBlank
	private Long identificadorItemPedido;
    
	@NotBlank
	private String identificadorVend;
    
	@NotBlank
	private String identificadorProd;
    
	@NotNull
	private BigDecimal precoUnitario;
	
	@NotNull
	private LocalDateTime dataEnvioLimite;
    
    @NotNull
    private BigDecimal valorFrete;
}	

