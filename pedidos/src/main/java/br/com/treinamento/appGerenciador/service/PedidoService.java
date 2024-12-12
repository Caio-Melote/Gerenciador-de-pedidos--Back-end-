package br.com.treinamento.appGerenciador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.treinamento.appGerenciador.model.Cliente;
import br.com.treinamento.appGerenciador.model.Pedido;
import br.com.treinamento.appGerenciador.model.Vendedor;
import br.com.treinamento.appGerenciador.repository.ClienteRepository;
import br.com.treinamento.appGerenciador.repository.PedidoRepository;
import br.com.treinamento.appGerenciador.repository.VendedorRepository;

@Service
public class PedidoService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private VendedorRepository vendedorRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Cliente validarClienteAtivo(Long idCliente) {
        var cliente = clienteRepository.findByIdClienteAndAtivoTrue(idCliente);
        if(cliente.isEmpty() || cliente == null) {
            throw new IllegalArgumentException("Cliente com ID não encontrado!!");
        }
        return cliente.get();
    }

    public Vendedor validarVendedorAtivo(Long idVendedor) {
    	
    	var vendedor = vendedorRepository.findByIdVendedorAndAtivoTrue(idVendedor);
        if(vendedor.isEmpty()) {
            throw new IllegalArgumentException("Vendedor com ID não encontrado!!");
        }
        return vendedor.get();
    }

    public void salvarPedido(Pedido pedido) {
    	pedidoRepository.save(pedido);
    }
}

