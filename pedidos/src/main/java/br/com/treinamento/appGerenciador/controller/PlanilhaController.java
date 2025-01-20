package br.com.treinamento.appGerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinamento.appGerenciador.service.ProdutoPlanilhaService;
import br.com.treinamento.appGerenciador.service.VendedorPlanilhaService;

@RestController
@RequestMapping("/planilha")
public class PlanilhaController {
	
	@Autowired
	VendedorPlanilhaService vendedorPlanilha;
	
	@Autowired
	ProdutoPlanilhaService produtoPlanilha;
	
	
	@GetMapping
	private String populando() {
		
		vendedorPlanilha.lerPlanilha();
		
		return "<h1>Banco populado com sucesso!</h1>";
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/{modelo}")
	public ResponseEntity pesquisar(@PathVariable String modelo) {
		
		String retorno;
		
		if(modelo.equals("vendedor")) {
			retorno = vendedorPlanilha.lerPlanilha();
			return ResponseEntity.ok(retorno);
		} 
		if(modelo.equals("produto")) {
			retorno = produtoPlanilha.lerPlanilha();
			return ResponseEntity.ok(retorno);
		}
	
		return ResponseEntity.notFound().build();
	}
	
	
}

