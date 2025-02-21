package br.com.treinamento.appGerenciador.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.treinamento.appGerenciador.model.Cliente;
import br.com.treinamento.appGerenciador.model.Pedido;
import br.com.treinamento.appGerenciador.model.Vendedor;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoDadosAtualizacao;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoDadosCadastro;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoListagem;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoRespostaPaginada;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoSemPaginacao;
import br.com.treinamento.appGerenciador.repository.PedidoRepository;
import br.com.treinamento.appGerenciador.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PedidoService pedidoService;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity listar(@PageableDefault(size = 20) Pageable paginacao,
			@RequestParam(required = false) String cliente,  
			@RequestParam(required = false) String vendedor,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) LocalDate dataStartCompra, 
			@RequestParam(required = false) LocalDate dataEndCompra,
			@RequestParam(required = false) LocalDate dataStartEntrega, 
			@RequestParam(required = false) LocalDate dataEndEntrega,
			@RequestParam(required = false) BigDecimal valorTotal, 
			@RequestParam(required = false) String status,
			@RequestParam(required = false, defaultValue = "true") String ativos) {
		
		boolean ativo = Boolean.valueOf(ativos);
		LocalDateTime dataStartCompraFormatada = null;
		LocalDateTime dataEndCompraFormatada = null;
		LocalDateTime dataStartEntregaFormatada = null;
		LocalDateTime dataEndEntregaFormatada = null;
		
		if(dataStartCompra != null) {
			dataStartCompraFormatada = dataStartCompra.atStartOfDay();
		} 
		
		if(dataEndCompra != null) {
			dataEndCompraFormatada = dataEndCompra.atStartOfDay();
		} 
		
		if(dataStartEntrega != null) {
			dataStartEntregaFormatada = dataStartEntrega.atStartOfDay();
		} 
		
		if(dataEndEntrega != null) {
			dataEndEntregaFormatada = dataEndEntrega.atStartOfDay();
		} 

		
		
		var page = pedidoRepository
				.findAllByFilters(cliente, vendedor, id, dataStartCompraFormatada, dataEndCompraFormatada, dataStartEntregaFormatada ,dataEndEntregaFormatada, valorTotal, status, ativo, paginacao)
				.map(PedidoListagem::new);

		PedidoRespostaPaginada<PedidoListagem> response = new PedidoRespostaPaginada<>(page);
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid PedidoDadosCadastro dados, UriComponentsBuilder uriBuilder) {

		Cliente cliente = pedidoService.validarClienteAtivo(dados.getIdCliente());
		Vendedor vendedor = pedidoService.validarVendedorAtivo(dados.getIdVendedor());

		Pedido pedido = new Pedido(cliente, vendedor, dados.getData(), dados.getValorTotal(), dados.getStatus(), dados.getIdentificadorPedido(), 
				dados.getIdentificadorCliente(), dados.getDataCompra(), dados.getDataAprovacao(), dados.getDataEntregaTransportadora(), 
				dados.getDataEntregaCliente(), dados.getDataEntregaEstimada());
		pedidoService.salvarPedido(pedido);
		
		var uri = uriBuilder.path("/pedidoproduto/{id}").buildAndExpand(pedido.getIdPedido()).toUri();
		return ResponseEntity.created(uri).body(new PedidoSemPaginacao(pedido));
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid PedidoDadosAtualizacao dados) {

		var pedidoOptional = pedidoRepository.findById(id);

		if (pedidoOptional.isPresent() && pedidoOptional.get().getAtivo()) {

			Cliente cliente = pedidoService.validarClienteAtivo(dados.getIdCliente());

			var vendedor = pedidoService.validarVendedorAtivo(dados.getIdVendedor());

			Pedido pedido = pedidoOptional.get();
			pedido.atualizarInformacoes(cliente, vendedor, dados.getData(), dados.getValorTotal(), dados.getStatus(), dados.getIdentificadorPedido(), 
					dados.getIdentificadorCliente(), dados.getDataCompra(), dados.getDataAprovacao(), dados.getDataEntregaTransportadora(), 
					dados.getDataEntregaCliente(), dados.getDataEntregaEstimada());

			pedidoService.salvarPedido(pedido);

			var resposta = new PedidoSemPaginacao(pedido);
			return ResponseEntity.ok(resposta);
		}

		return ResponseEntity.notFound().build();

	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {

		Pedido pedido = pedidoRepository.getReferenceById(id);
		pedido.getAtivo();

		if (pedido.getAtivo()) {
			pedido.excluir();
		} else {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}/reativar")
	public ResponseEntity reativa(@PathVariable Long id) {
		Pedido pedido = pedidoRepository.findById(id).get();
		if (pedido == null) {
			return ResponseEntity.notFound().build();
		}
		if (pedido.getAtivo()) {
			return ResponseEntity.ok("Já está ativo!");
		}

		pedido.setAtivo(true);
		pedidoRepository.save(pedido);
		return ResponseEntity.ok(pedido);
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity pesquisar(@PathVariable Long id) {

		Pedido pedido = pedidoRepository.findById(id).get();
		if (pedido.getAtivo()) {

			var resposta = ResponseEntity.ok(new PedidoSemPaginacao(pedido));
			return resposta;
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
