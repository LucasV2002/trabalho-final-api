package br.com.serratec.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	private Integer preço;

	@OneToMany(mappedBy = "id.produto", fetch = FetchType.EAGER)
	private Set<PedidoProduto> pedidoProduto = new HashSet<>();

	public Set<PedidoProduto> getPedidoProduto() {
		return pedidoProduto;
	}

	public void setPedidoProduto(Set<PedidoProduto> pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
	}

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

	public Integer getPreço() {
		return preço;
	}

	public void setPreço(Integer preço) {
		this.preço = preço;
	}

}
