package br.com.treinamento.appGerenciador.cliente.dto;

import br.com.treinamento.appGerenciador.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClienteListagem {
	
	private long idCliente;
	private String nome;
	private String email;
	private String cpfCliente;
	private String telefone;
	private String endereco;
	private String identificadorCliente;
	private String idUnicoCliente;
	private Long prefixoCepCliente;
	private String cidadeCliente;
	private String estadoCliente;
	
	public ClienteListagem(Cliente cliente) {
        this.idCliente = cliente.getIdCliente(); 
        this.nome = cliente.getNome();    
        this.email = cliente.getEmail(); 
        this.cpfCliente = cliente.getCpfCliente();   
        this.telefone = cliente.getTelefone();   
        this.endereco = cliente.getEndereco();
        this.identificadorCliente = cliente.getIdentificadorCliente();  
        this.idUnicoCliente = cliente.getIdUnicoCliente();  
        this.prefixoCepCliente = cliente.getPrefixoCepCliente();  
        this.cidadeCliente = cliente.getCidadeCliente();  
        this.estadoCliente = cliente.getEstadoCliente();  
    }
}
