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
	private String cpfVendedor;  
	
	@NotBlank
	private String identificadorVend;
	
	@NotNull
    private Long prefixoCepVend;
    
    @NotBlank
    private String cidadeVend;
    
    @NotBlank
    private String estadoVend;
    
	@NotNull
	private boolean ativo;
}
