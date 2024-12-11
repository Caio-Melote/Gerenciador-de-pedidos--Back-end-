package br.com.treinamento.appGerenciador.produto.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDadosCadastro {
	
	@NotBlank
    private String nome;
    
    @NotBlank
    private String descricao;
    
    @NotNull
    private BigDecimal preco;   
    
    @NotBlank
    private String categoria;  
    
    @NotNull
    private Integer codigoBarras;
    
    @NotNull
    private boolean ativo;
}
