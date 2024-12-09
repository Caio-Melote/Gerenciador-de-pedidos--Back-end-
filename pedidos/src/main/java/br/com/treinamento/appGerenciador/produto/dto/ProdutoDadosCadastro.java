package br.com.treinamento.appGerenciador.produto.dto;

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
    private Double preco;   
    
    @NotBlank
    private String categoria;  
    
    @NotNull
    private Integer codigoBarras;
    
    @NotNull
    private boolean ativo;
}
