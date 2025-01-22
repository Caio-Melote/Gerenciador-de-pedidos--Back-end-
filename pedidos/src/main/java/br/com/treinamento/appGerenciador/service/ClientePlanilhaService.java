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

import br.com.treinamento.appGerenciador.cliente.dto.ClienteDadosPlanilha;
import br.com.treinamento.appGerenciador.model.Cliente;
import br.com.treinamento.appGerenciador.repository.ClienteRepository;
import lombok.Cleanup;

@Service
public class ClientePlanilhaService {

	@Autowired
	private ClienteRepository clienteRepository;

	public String lerPlanilha() {

		try {
			@Cleanup 
			FileInputStream file = new FileInputStream("src/main/resources/planilhas/olist_customers_dataset.xlsx");

			@Cleanup
			XSSFWorkbook workBook = new XSSFWorkbook(file);

			XSSFSheet workSheet = workBook.getSheetAt(0);

			Iterator<Row> iteradorLinha = workSheet.iterator();

			if (iteradorLinha.hasNext()) {
				iteradorLinha.next();
			}
			
			List<Cliente> clientes = new ArrayList<>();
			while (iteradorLinha.hasNext()) {
				Row linha = iteradorLinha.next();
				
				 String identificadorCliente = linha.getCell(0).getStringCellValue();
				 String idUnicoCliente = linha.getCell(1).getStringCellValue();
				 Long prefixoCepCliente = (long) linha.getCell(2).getNumericCellValue();
				 String cidadeCliente  = linha.getCell(3).getStringCellValue();
				 String estadoCliente  = linha.getCell(4).getStringCellValue();

				ClienteDadosPlanilha clienteDados = new ClienteDadosPlanilha(identificadorCliente, idUnicoCliente, prefixoCepCliente, cidadeCliente, estadoCliente, true);

				Cliente cliente = new Cliente(clienteDados);
				clientes.add(cliente);
						
			}
			
			clienteRepository.saveAll(clientes);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Falha na leitura!";
		}
		return "Sucesso na leitura!";
	}
}
