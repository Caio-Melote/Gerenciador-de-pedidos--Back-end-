package br.com.treinamento.appGerenciador.vendedor.dto;

import br.com.treinamento.appGerenciador.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VendedorListagem {
	
	private Long idVendedor;
    private String nome;
    private String email; 
    private String cpfVendedor; 

    public VendedorListagem(Vendedor vendedor) {
        this.idVendedor = vendedor.getIdVendedor(); 
        this.nome = vendedor.getNome();    
        this.email = vendedor.getEmail(); 
        this.cpfVendedor = vendedor.getCpfVendedor();   
    }
}

