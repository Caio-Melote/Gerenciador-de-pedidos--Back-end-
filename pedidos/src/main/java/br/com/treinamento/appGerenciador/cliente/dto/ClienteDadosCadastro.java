package br.com.treinamento.appGerenciador.cliente.dto;

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
public class ClienteDadosCadastro {
	
	@NotBlank
    private String nome;
	
	@NotBlank
	private String email;
    
	@NotBlank
	private String cpfCliente;  
	
	@NotBlank
	private String telefone;
	
	@NotBlank
	private String endereco;
	
	@NotNull
	private boolean ativo;
}
