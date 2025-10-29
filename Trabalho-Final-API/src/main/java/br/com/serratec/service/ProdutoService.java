package br.com.serratec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Produto;
import br.com.serratec.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> getAll() {return produtoRepository.findAll();}
	
	public Produto save(Produto produto) {return produtoRepository.save(produto);}
	
	public void delete(Long id) {produtoRepository.deleteById(id);}
	
	public Produto update(Long id) {produtoRepository.findById(id);
	return null;} 
	
	
	

}
