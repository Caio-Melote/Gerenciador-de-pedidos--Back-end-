package br.com.treinamento.appGerenciador.service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.treinamento.appGerenciador.model.Produto;
import br.com.treinamento.appGerenciador.produto.dto.ProdutoDadosPlanilha;
import br.com.treinamento.appGerenciador.repository.ProdutoRepository;
import lombok.Cleanup;

@Service
public class ProdutoPlanilhaService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public String lerPlanilha() {

		try {
			@Cleanup 
			FileInputStream file = new FileInputStream("src/main/resources/planilhas/olist_products_dataset.xlsx");

			@Cleanup
			XSSFWorkbook workBook = new XSSFWorkbook(file);

			XSSFSheet workSheet = workBook.getSheetAt(0);

			Iterator<Row> iteradorLinha = workSheet.iterator();

			if (iteradorLinha.hasNext()) {
				iteradorLinha.next();
			}
			
			List<Produto> produtos = new ArrayList<>();
			while (iteradorLinha.hasNext()) {
				Row linha = iteradorLinha.next();
				
				String identificador_prod = linha.getCell(0).getStringCellValue();
				String categoria = linha.getCell(1).getStringCellValue();
				Long tamanho_nome = (long)linha.getCell(2).getNumericCellValue();
				Long tamanho_descricao = (long)linha.getCell(3).getNumericCellValue();
				Long quantidade_fotos = (long)linha.getCell(4).getNumericCellValue();
				Long peso_gramas = (long)linha.getCell(5).getNumericCellValue();
				Long comprimento_cm = (long)linha.getCell(6).getNumericCellValue();
				Long altura_cm = (long)linha.getCell(7).getNumericCellValue();
				Long largura_cm = (long)linha.getCell(8).getNumericCellValue();

				ProdutoDadosPlanilha produtoDados = new ProdutoDadosPlanilha(identificador_prod, categoria, tamanho_nome, tamanho_descricao, quantidade_fotos, peso_gramas, comprimento_cm, altura_cm, largura_cm, true);

				Produto prod = new Produto(produtoDados);
				produtos.add(prod);
							
			}
			
			produtoRepository.saveAll(produtos);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Falha na leitura!";
		}
		return "Sucesso na leitura!";
	}
}
