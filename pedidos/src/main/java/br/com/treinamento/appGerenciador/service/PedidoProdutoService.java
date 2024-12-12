package br.com.treinamento.appGerenciador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.treinamento.appGerenciador.model.Pedido;
import br.com.treinamento.appGerenciador.model.PedidoProduto;
import br.com.treinamento.appGerenciador.model.Produto;
import br.com.treinamento.appGerenciador.repository.PedidoProdutoRepository;
import br.com.treinamento.appGerenciador.repository.PedidoRepository;
import br.com.treinamento.appGerenciador.repository.ProdutoRepository;

@Service
public class PedidoProdutoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    public Pedido validarPedidoAtivo(Long idPedido) {
        var pedido = pedidoRepository.findByIdPedidoAndAtivoTrue(idPedido);
        if(pedido.isEmpty() || pedido == null) {
            throw new IllegalArgumentException("Pedido com ID não encontrado!!");
        }
        return pedido.get();
    }

    public Produto validarProdutoAtivo(Long idProduto) {
    	
    	var produto = produtoRepository.findByIdProdutoAndAtivoTrue(idProduto);
        if(produto.isEmpty()) {
            throw new IllegalArgumentException("Produto com ID não encontrado!!");
        }
        return produto.get();
    }

    public void salvarPedidoProduto(PedidoProduto pedidoProduto) {
        pedidoProdutoRepository.save(pedidoProduto);
    }
}

