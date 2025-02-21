package br.com.treinamento.appGerenciador.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDadosPlanilha {
	
	@NotBlank
	private String identificadorProd;
	
	@NotBlank
	private String categoria;
	
	@NotNull
    private Long tamanhoNome;
    
	@NotNull
	private Long tamanhoDescricao;
    
	@NotNull
	private Long quantidadeFotos;
    
	@NotNull
	private Long pesoGramas;
    
	@NotNull
	private Long comprimentoCm;
    
	@NotNull
	private Long alturaCm;
    
	@NotNull
	private Long larguraCm;
    
	@NotNull
	private boolean ativo;
}
