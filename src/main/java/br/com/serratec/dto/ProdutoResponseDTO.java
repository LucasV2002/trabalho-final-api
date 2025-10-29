package br.com.serratec.dto;

import java.math.BigDecimal;

public class ProdutoResponseDTO {
	private Long id;
	private String nome;
	private BigDecimal preco;
	private CategoriaResponseDTO categoria ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public CategoriaResponseDTO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaResponseDTO categoria) {
		this.categoria = categoria;
	}
	
	
}
