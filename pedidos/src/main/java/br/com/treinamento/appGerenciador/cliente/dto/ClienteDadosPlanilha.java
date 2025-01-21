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
public class ClienteDadosPlanilha {
	
	@NotBlank
	private String identificadorCliente;
	
	@NotBlank
	private String idUnicoCliente;
	
	@NotNull
	private Long prefixoCepCliente;
	
	@NotBlank
	private String cidadeCliente;
	
	@NotBlank
	private String estadoCliente;
	
	@NotNull
	private boolean ativo;
}
