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
	private String identificador_prod;
	
	@NotBlank
	private String categoria;
	
	@NotNull
    private Long tamanho_nome;
    
	@NotNull
	private Long tamanho_descricao;
    
	@NotNull
	private Long quantidade_fotos;
    
	@NotNull
	private Long peso_gramas;
    
	@NotNull
	private Long comprimento_cm;
    
	@NotNull
	private Long altura_cm;
    
	@NotNull
	private Long largura_cm;
    
	@NotNull
	private boolean ativo;
}
