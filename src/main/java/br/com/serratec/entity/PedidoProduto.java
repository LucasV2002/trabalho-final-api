package br.com.serratec.entity;

import br.com.serratec.pk.PedidoProdutoPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class PedidoProduto {

	@EmbeddedId
	private PedidoProdutoPK id;
	private Integer quantidade;
	private Integer total;

	public PedidoProduto(Pedido pedido, Produto produto, Integer quantidade) {
		this.quantidade = quantidade;
	}

	public PedidoProdutoPK getPedidoProdutoPK() {
		return id;
	}

	public void setPedidoProdutoPK(PedidoProdutoPK id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Integer calcularTotal() {
		this.total = quantidade * id.getProduto().getPreço();
		return total;
	}
	

}
