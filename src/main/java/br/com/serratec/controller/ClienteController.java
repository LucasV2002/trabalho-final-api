package br.com.serratec.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@PostMapping
	public ClienteResponseDTO inserir(@RequestBody ClienteRequestDTO cliente) {
		return service.inserir(cliente);
	}

	@DeleteMapping("/excluir/{id}")
	public HttpStatus deletar(@PathVariable UUID id) {
		return service.deletar(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponseDTO> atualizarCliente(
	        @Valid @PathVariable UUID id,
	        @RequestBody ClienteRequestDTO requestDTO) {

	    // chama o service que retorna o DTO atualizado
	    ClienteResponseDTO responseDTO = service.atualizarCliente(id, requestDTO);

	    return ResponseEntity.ok(responseDTO);
	}

	@GetMapping
	public List<ClienteResponseDTO> listar() {
		return service.listar();
	}
}
