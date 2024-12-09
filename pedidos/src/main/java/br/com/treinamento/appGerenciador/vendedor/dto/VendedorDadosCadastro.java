package br.com.treinamento.appGerenciador.vendedor.dto;

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
public class VendedorDadosCadastro {
	
	@NotBlank
    private String nome;
	
	@NotBlank
	private String email;
    
	@NotBlank
	private String cpf_vendedor;  
    
	@NotNull
	private boolean ativo;
}
