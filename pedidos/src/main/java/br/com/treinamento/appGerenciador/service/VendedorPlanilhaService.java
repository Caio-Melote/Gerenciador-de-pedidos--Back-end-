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
import br.com.treinamento.appGerenciador.model.Vendedor;
import br.com.treinamento.appGerenciador.repository.VendedorRepository;
import br.com.treinamento.appGerenciador.vendedor.dto.VendedorDadosPlanilha;
import lombok.Cleanup;

@Service
public class VendedorPlanilhaService {

	@Autowired
	private VendedorRepository vendedorRepository;

	public String lerPlanilha() {

		try {
			@Cleanup 
			FileInputStream file = new FileInputStream("src/main/resources/planilhas/olist_sellers_dataset.xlsx");

			@Cleanup
			XSSFWorkbook workBook = new XSSFWorkbook(file);

			XSSFSheet workSheet = workBook.getSheetAt(0);

			Iterator<Row> iteradorLinha = workSheet.iterator();

			if (iteradorLinha.hasNext()) {
				iteradorLinha.next();
			}
			
			List<Vendedor> vendedores = new ArrayList<>();
			while (iteradorLinha.hasNext()) {
				Row linha = iteradorLinha.next();
				
				String identificadorVend = linha.getCell(0).getStringCellValue();
				Long prefixoCep = (long)linha.getCell(1).getNumericCellValue();
				String cidadeVend = linha.getCell(2).getStringCellValue();
				String estadoVend = linha.getCell(3).getStringCellValue();

				VendedorDadosPlanilha vendedorDados = new VendedorDadosPlanilha(identificadorVend, prefixoCep, cidadeVend, estadoVend, true);

				Vendedor vendedor = new Vendedor(vendedorDados);
				vendedores.add(vendedor);
			}
			
			vendedorRepository.saveAll(vendedores);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Falha na leitura!";
		}
		return "Sucesso na leitura!";
	}
}
