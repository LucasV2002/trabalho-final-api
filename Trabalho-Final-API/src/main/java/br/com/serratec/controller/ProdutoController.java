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

import br.com.serratec.entity.Produto;
import br.com.serratec.repository.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto inserir(@Valid @RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@GetMapping
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
	    Optional<Produto> produto = produtoRepository.findById(id);
	    if (produto.isPresent()) {
	        return ResponseEntity.ok(produto.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Produto> deletar(@PathVariable Long id) {
		produtoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
		Optional<Produto> p = produtoRepository.findById(id);
		if (p.isPresent()) {
			produto.setId(id);
			return ResponseEntity.ok(produtoRepository.save(produto));
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
}
