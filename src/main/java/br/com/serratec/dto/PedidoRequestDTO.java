package br.com.serratec.dto;

import java.util.List;

public class PedidoRequestDTO {

	private String clienteId;

	private List<ProdutoPedidoDTO> itens;

	public static class ProdutoPedidoDTO {
		private Long produtoId; 
		private Integer quantidade;

		public Long getProdutoId() {
			return produtoId;
		}

		public void setProdutoId(Long produtoId) {
			this.produtoId = produtoId;
		}

		public Integer getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(Integer quantidade) {
			this.quantidade = quantidade;
		}
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public List<ProdutoPedidoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ProdutoPedidoDTO> itens) {
		this.itens = itens;
	}
}
