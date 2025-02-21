package br.com.treinamento.appGerenciador.infra;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionResolver {

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity erro404() {
		return ResponseEntity.notFound().build();
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity notFound() {
		return ResponseEntity.notFound().build();
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity handleIllegalArgumentException(SQLIntegrityConstraintViolationException e) {
		
		Map<String, Object> response = new HashMap<>();
		
		if(e.getErrorCode() == 1062) {
			response.put("status", "error");
	        response.put("message", "Violação de restrição de integridade (chave duplicada).");
	        response.put("code", HttpStatus.BAD_REQUEST.value());
	        response.put("details", e.getMessage()); 
		} else { 
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleIllegalArgumentException(MethodArgumentNotValidException e) {
		Map<String, Object> response = new HashMap<>();
		
		response.put("status", "error");
        response.put("message", "Dados inválidos!!");
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("details", e.getMessage()); 
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	
}
