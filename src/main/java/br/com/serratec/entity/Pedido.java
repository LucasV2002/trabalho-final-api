package br.com.serratec.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotBlank
	private String nome;

	@OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER)
	private Set<PedidoProduto> pedidoProduto = new HashSet<>();

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<PedidoProduto> getPedidoProduto() {
		return pedidoProduto;
	}

	public void setPedidoProduto(Set<PedidoProduto> pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
	}

}
