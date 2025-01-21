package br.com.treinamento.appGerenciador.service;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinamento.appGerenciador.model.Pedido;
import br.com.treinamento.appGerenciador.pedido.dto.PedidoDadosPlanilha;
import br.com.treinamento.appGerenciador.repository.PedidoRepository;
import lombok.Cleanup;

@Service
public class PedidoPlanilhaService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public String lerPlanilha() {

		try {
			@Cleanup
			FileInputStream file = new FileInputStream("src/main/resources/planilhas/olist_orders_dataset.xlsx");

			@Cleanup
			XSSFWorkbook workBook = new XSSFWorkbook(file);

			XSSFSheet workSheet = workBook.getSheetAt(0);

			Iterator<Row> iteradorLinha = workSheet.iterator();

			if (iteradorLinha.hasNext()) {
				iteradorLinha.next();
			}

			while (iteradorLinha.hasNext()) {
				Row linha = iteradorLinha.next();

				String identificadorPedido = linha.getCell(0).getStringCellValue();
				String identificadorCliente = linha.getCell(1).getStringCellValue();
				String status = linha.getCell(2).getStringCellValue();
				
				Cell cell3 = linha.getCell(3);
				LocalDateTime dataCompra = formataData(cell3);
				
			    Cell cell4 = linha.getCell(4);
			    LocalDateTime dataAprovacao = formataData(cell4);
			    
			    Cell cell5 = linha.getCell(5);
			    LocalDateTime dataEntregaTransportadora = formataData(cell5);
			    
			    Cell cell6 = linha.getCell(6);
			    LocalDateTime dataEntregaCliente = formataData(cell6);
			    
			    Cell cell7 = linha.getCell(7);
			    LocalDateTime dataEntregaEstimada = formataData(cell7);

				PedidoDadosPlanilha pedidoDados = new PedidoDadosPlanilha(identificadorPedido, 
						identificadorCliente, 
						status, 
						dataCompra, 
						dataAprovacao, 
						dataEntregaTransportadora, 
						dataEntregaCliente, 
						dataEntregaEstimada);

				Pedido pedido = new Pedido(pedidoDados);

				pedidoRepository.save(pedido);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Falha na leitura!";
		}
		return "Sucesso na leitura!";
	}
	
	private LocalDateTime formataData(Cell cell) {
		
		if(cell == null) {
			return null;
		} else {
			String dateStr = cell.getStringCellValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		    LocalDateTime dataFormatada = LocalDateTime.parse(dateStr, formatter);
			return dataFormatada;
		}
		
	    
		
	}
}
