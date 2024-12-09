package br.com.treinamento.appGerenciador.vendedor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDadosAtualizacao {
	
    private long idVendedor;  
    private String nome;
    private String email;
    private String cpf_vendedor;  
    private boolean ativo;
}
