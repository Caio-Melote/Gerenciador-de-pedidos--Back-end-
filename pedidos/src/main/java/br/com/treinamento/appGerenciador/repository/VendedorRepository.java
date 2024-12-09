package br.com.treinamento.appGerenciador.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.treinamento.appGerenciador.model.Produto;
import br.com.treinamento.appGerenciador.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{
	
	 @Query("SELECT v FROM Vendedor v "
	            + "WHERE (:nome IS NULL OR v.nome LIKE %:nome%) "
	            + "AND (:email IS NULL OR v.email LIKE %:email%) "
	            + "AND (:cpf_vendedor IS NULL OR v.cpf_vendedor LIKE %:cpf_vendedor%) "
	            + "AND v.ativo = TRUE")
	    Page<Produto> findAllByFilters(@Param("nome") String nome, 
	            @Param("email") String email,
	            @Param("cpf_vendedor") String cpf_vendedor,
	            Pageable pageable);
}