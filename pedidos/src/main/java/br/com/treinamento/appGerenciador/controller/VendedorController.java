package br.com.treinamento.appGerenciador.controller;

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
import br.com.treinamento.appGerenciador.model.Vendedor;
import br.com.treinamento.appGerenciador.repository.VendedorRepository;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorDadosAtualizacao;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorDadosCadastro;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorListagem;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorRespostaPaginada;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorSemPaginacao;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/vendedor")
public class VendedorController {

	@Autowired
	private VendedorRepository vendedorRepository;

	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity listar(@PageableDefault(size = 20) Pageable paginacao,
			@RequestParam(required = false) String nome, 
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String cpfVendedor,
			@RequestParam(required = false, defaultValue = "true") String ativos) {
		
		boolean ativo = Boolean.valueOf(ativos);

		var page = vendedorRepository.findAllByFilters(nome, id, email, cpfVendedor, ativo, paginacao).map(VendedorListagem::new);

		VendedorRespostaPaginada<VendedorListagem> response = new VendedorRespostaPaginada<>(page);
		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid VendedorDadosCadastro dados, UriComponentsBuilder uriBuilder) {

		Vendedor vendedor = new Vendedor(dados);
		vendedorRepository.save(vendedor);

		var uri = uriBuilder.path("/vendedor/{id}").buildAndExpand(vendedor.getIdVendedor()).toUri();

		return ResponseEntity.created(uri).body(new VendedorSemPaginacao(vendedor));
	}
	

	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid VendedorDadosAtualizacao dados) {

		var vendedorOptional = vendedorRepository.findById(id);

		if (vendedorOptional.get().isAtivo()) {

			var vendedor = vendedorOptional.get();
			vendedor.atualizarInformacoes(dados);

			vendedorRepository.save(vendedor);

			var resposta = new VendedorSemPaginacao(vendedor);

			return ResponseEntity.ok(resposta);

		} else {

			return ResponseEntity.notFound().build();
		}

	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {

		var vendedor = vendedorRepository.getReferenceById(id);
		vendedor.isAtivo();

		if (vendedor.isAtivo()) {
			vendedor.excluir();
		} else {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}/reativar")
	public ResponseEntity reativa(@PathVariable Long id) {
	    Vendedor vendedor = vendedorRepository.findById(id).get();
	    if (vendedor == null) {
	        return ResponseEntity.notFound().build();
	    }
	    if (vendedor.isAtivo()){
	    	return ResponseEntity.ok("Já está ativo!");
	    }
	    
	    vendedor.setAtivo(true);
	    vendedorRepository.save(vendedor);
	    return ResponseEntity.ok(vendedor);
	}


	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity pesquisar(@PathVariable Long id) {

		var vendedor = vendedorRepository.findById(id);
		if (vendedor.get().isAtivo()) {

			var resposta = ResponseEntity.ok(new VendedorSemPaginacao(vendedor.get()));
			return resposta;
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
