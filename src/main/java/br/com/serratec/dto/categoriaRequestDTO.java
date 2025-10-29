package br.com.serratec.dto;

import jakarta.validation.constraints.NotBlank;

public class categoriaRequestDTO {
		@NotBlank
		private String nome;

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}
		
		
		
}
