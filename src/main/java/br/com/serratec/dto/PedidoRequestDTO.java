package br.com.serratec.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class PedidoRequestDTO {
		@NotNull
		private Long clienteId;
		
		@NotNull
		private List<ItemPedidoDTO> itens;

		public Long getClienteId() {
			return clienteId;
		}

		public void setClienteId(Long clienteId) {
			this.clienteId = clienteId;
		}

		public List<ItemPedidoDTO> getItens() {
			return itens;
		}

		public void setItens(List<ItemPedidoDTO> itens) {
			this.itens = itens;
		}
		
		
}
