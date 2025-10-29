package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Produto;
import br.com.serratec.exception.ClienteException;
import br.com.serratec.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;


	public List<Produto> listar() {
		return repository.findAll();
	}

	public Produto inserirProduto(Produto produto) {
		return repository.save(produto);
	}

	@Transactional
	public Produto atualizarProduto(Long id, Produto update) {
		Produto produtoAtualizar = repository.findById(id)
				.orElseThrow(() -> new ClienteException("Produto não encontrado com o ID: " + id));

		if (update.getNome() != null && !update.getNome().trim().isEmpty()) {
			produtoAtualizar.setNome(update.getNome());
		}

		if (update.getPreco() != null) {
			produtoAtualizar.setPreco(update.getPreco());
		}

		return repository.save(produtoAtualizar);
	}

}