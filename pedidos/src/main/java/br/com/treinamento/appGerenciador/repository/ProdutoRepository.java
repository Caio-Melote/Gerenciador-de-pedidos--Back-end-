package br.com.treinamento.appGerenciador.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.treinamento.appGerenciador.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	 @Query("SELECT p FROM Produto p "
	            + "WHERE (:nome IS NULL OR p.nome LIKE %:nome%) "
	            + "AND (:descricao IS NULL OR p.descricao LIKE %:descricao%) "
	            + "AND (:categoria IS NULL OR p.categoria LIKE %:categoria%) "
	            + "AND (:codigoBarras IS NULL OR p.codigoBarras = :codigoBarras) "
	            + "AND (:precoMinimo IS NULL OR p.preco >= :precoMinimo) "  
	            + "AND (:precoMaximo IS NULL OR p.preco <= :precoMaximo)"
	            + "AND p.ativo = TRUE")
	    Page<Produto> findAllByFilters(@Param("nome") String nome, 
	            @Param("descricao") String descricao,
	            @Param("categoria") String categoria,
	            @Param("codigoBarras") Integer codigoBarras,
	            @Param("precoMinimo") Double precoMinimo,  
	            @Param("precoMaximo") Double precoMaximo,  
	            Pageable pageable);
	 
	 Optional<Produto> findByIdProdutoAndAtivoTrue(Long id);
}
