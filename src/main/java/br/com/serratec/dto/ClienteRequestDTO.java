package br.com.serratec.dto;

import br.com.serratec.entity.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteRequestDTO {

	@NotBlank
	private String nome;

	@Email
	private String email;

	@NotBlank
	private String senha;


	public ClienteRequestDTO() {
	}

	public ClienteRequestDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
