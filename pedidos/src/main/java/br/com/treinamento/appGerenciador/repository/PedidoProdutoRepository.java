package br.com.treinamento.appGerenciador.repository;

import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.treinamento.appGerenciador.model.PedidoProduto;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {

	@Query("SELECT pp FROM PedidoProduto pp " 
			+ "WHERE (:pedido IS NULL OR pp.pedido.idPedido = :pedido) "
			+ "AND (:produto IS NULL OR pp.produto.idProduto = :produto) "
			+ "AND (:quantidade IS NULL OR pp.quantidade = :quantidade) "
			+ "AND (:precoMin IS NULL OR pp.precoUnitario = :precoMin) "
			+ "AND (:precoMax IS NULL OR pp.precoUnitario = :precoMax) "
			+ "AND (:desconto IS NULL OR pp.desconto = :desconto) " 
			+ "AND pp.ativo = TRUE")
	Page<PedidoProduto> findAllByFilters(@Param("pedido") Long pedido, 
			@Param("produto") Long produto,
			@Param("quantidade") Integer quantidade, 
			@Param("precoMin") BigDecimal precoMin,
			@Param("precoMax") BigDecimal precoMax,
			@Param("desconto") BigDecimal desconto, 
			Pageable pageable);
}
