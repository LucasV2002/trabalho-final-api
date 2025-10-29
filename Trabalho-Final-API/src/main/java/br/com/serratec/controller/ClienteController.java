package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Cliente;
import br.com.serratec.repository.ClienteRepository;
import br.com.serratec.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente inserir(@Valid @RequestBody Cliente cliente) {
	    return clienteService.save(cliente);
	}
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
	    Optional<Cliente> cliente = clienteRepository.findById(id);

	    if (cliente.isPresent()) {
	        return ResponseEntity.ok(cliente.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Cliente> deletar(@PathVariable Long id) {
		clienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
	    try {
	        Cliente atualizado = clienteService.update(id, cliente);
	        return ResponseEntity.ok(atualizado);
	    } catch (RuntimeException e) {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Cliente>> buscarPorCep(@RequestParam(required = false) String cep) {
		
	    List<Cliente> clientes = clienteRepository.findAll();
	    
	    if (cep != null) {
	        clientes = clientes.stream()
	                           .filter(c -> c.getCep().equals(cep))
	                           .collect(Collectors.toList());
	    }
	    
	    return ResponseEntity.ok(clientes);
	}

	
	
	
}
