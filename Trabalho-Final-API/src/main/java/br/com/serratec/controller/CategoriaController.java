package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.serratec.entity.Categoria;
import br.com.serratec.repository.CategoriaRepository;
import br.com.serratec.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria inserir(@Valid @RequestBody Categoria categoria) {
	    return categoriaService.save(categoria);
	}
	
	@GetMapping
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
	    Optional<Categoria> categoria = categoriaRepository.findById(id);

	    if (categoria.isPresent()) {
	        return ResponseEntity.ok(categoria.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Categoria> deletar(@PathVariable Long id) {
		categoriaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
	    try {
	        Categoria atualizado = categoriaService.update(id, categoria);
	        return ResponseEntity.ok(atualizado);
	    } catch (RuntimeException e) {
	        return ResponseEntity.notFound().build();
	    }
	}
	
}
