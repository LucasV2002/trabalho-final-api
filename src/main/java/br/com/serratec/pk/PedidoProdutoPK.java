package br.com.serratec.pk;

import java.io.Serializable;

import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.Produto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PedidoProdutoPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id.pedido")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id.produto")
	private Produto produto;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido Pedido) {
		this.pedido = Pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto Produto) {
		this.produto = Produto;
	}
	
	
	
}
