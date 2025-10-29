package br.com.serratec.entity;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@MappedSuperclass
public class Pessoa {

	@NotBlank
	protected String nome;
	@CPF @NotBlank
	protected String cpf;
	@Email 	@NotBlank
	protected String email;
	@NotBlank
	protected String telefone;
	@NotBlank
	protected String cep;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
