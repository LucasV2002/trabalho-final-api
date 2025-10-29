package br.com.serratec.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoResponseDTO {
		private Long id;
		private ClienteResponseDTO cliente;
		private List<ItemPedidoDTO> itens;
		private BigDecimal total;
		private StatusPedido status;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public ClienteResponseDTO getCliente() {
			return cliente;
		}
		public void setCliente(ClienteResponseDTO cliente) {
			this.cliente = cliente;
		}
		public List<ItemPedidoDTO> getItens() {
			return itens;
		}
		public void setItens(List<ItemPedidoDTO> itens) {
			this.itens = itens;
		}
		public BigDecimal getTotal() {
			return total;
		}
		public void setTotal(BigDecimal total) {
			this.total = total;
		}
		public StatusPedido getStatus() {
			return status;
		}
		public void setStatus(StatusPedido status) {
			this.status = status;
		}
		
		
}

// relacionado de muitos pra muitos.