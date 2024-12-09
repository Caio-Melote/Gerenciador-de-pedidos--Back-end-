package br.com.treinamento.appGerenciador.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDadosAtualizacao {
	
	private long idCliente;
	private String nome;
	private String email;
	private String cpfCliente;
	private String telefone;
	private String endereco;
	private boolean ativo;
}
