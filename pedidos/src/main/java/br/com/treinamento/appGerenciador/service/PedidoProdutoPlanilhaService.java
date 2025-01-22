package br.com.treinamento.appGerenciador.service;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinamento.appGerenciador.model.PedidoProduto;
import br.com.treinamento.appGerenciador.pedidoProduto.dto.PedidoProdutoDadosPlanilha;
import br.com.treinamento.appGerenciador.repository.PedidoProdutoRepository;
import lombok.Cleanup;

@Service
public class PedidoProdutoPlanilhaService {

	@Autowired
	private PedidoProdutoRepository pedidoProdutoRepository;

	public String lerPlanilha() {

		try {
			@Cleanup
			FileInputStream file = new FileInputStream("src/main/resources/planilhas/olist_order_items_dataset.xlsx");

			@Cleanup
			XSSFWorkbook workBook = new XSSFWorkbook(file);

			XSSFSheet workSheet = workBook.getSheetAt(0);

			Iterator<Row> iteradorLinha = workSheet.iterator();

			if (iteradorLinha.hasNext()) {
				iteradorLinha.next();
			}
			
			Map<String, PedidoProduto> mapItensPedidos = new HashMap<>();
			List<PedidoProduto> pedidoProdutoList = new ArrayList<>();
			while (iteradorLinha.hasNext()) {
				Row linha = iteradorLinha.next();

				String identificador_pedido = linha.getCell(0).getStringCellValue();
				Long identificadorItemPedido = (long) linha.getCell(1).getNumericCellValue();
				String identificador_prod = linha.getCell(2).getStringCellValue();
				String identificador_vend = linha.getCell(3).getStringCellValue();
				
				String dateStr = linha.getCell(4).getStringCellValue();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
				LocalDateTime data_envio_limite = LocalDateTime.parse(dateStr, formatter);
				
				BigDecimal precoUnitario = BigDecimal.valueOf(linha.getCell(5).getNumericCellValue());
				BigDecimal valor_frete =  BigDecimal.valueOf(linha.getCell(6).getNumericCellValue());

				PedidoProdutoDadosPlanilha ppdados = new PedidoProdutoDadosPlanilha(identificador_pedido, identificadorItemPedido, identificador_prod, identificador_vend, precoUnitario, data_envio_limite,  valor_frete);
				PedidoProduto pedido_produto = new PedidoProduto(ppdados);
				
                if (mapItensPedidos.containsKey(identificador_pedido)) {
                    PedidoProduto itemExistente = mapItensPedidos.get(identificador_pedido);
                    if (pedido_produto.getIdentificadorItemPedido() > itemExistente.getIdentificadorItemPedido()) {
                        mapItensPedidos.put(identificador_pedido, pedido_produto);
                    }
                } else {
                    mapItensPedidos.put(identificador_pedido, pedido_produto);
                }			
			}

            pedidoProdutoList.addAll(mapItensPedidos.values());
            pedidoProdutoRepository.saveAll(pedidoProdutoList);

		} catch (Exception e) {
			e.printStackTrace();
			return "Falha na leitura!";
		}
		return "Sucesso na leitura!";
	}
}
