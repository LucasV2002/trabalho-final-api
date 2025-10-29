package br.com.serratec.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoRequestDTO {
		@NotBlank
		private String nome;
		
		@NotNull
		private BigDecimal preco; // trabalha com números em base decimal, garantindo resultados precisos.
		
		@NotNull
		private Long categoriaId;

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

		public Long getCategoriaId() {
			return categoriaId;
		}

		public void setCategoriaId(Long categoriaId) {
			this.categoriaId = categoriaId;
		}
		
		
}
