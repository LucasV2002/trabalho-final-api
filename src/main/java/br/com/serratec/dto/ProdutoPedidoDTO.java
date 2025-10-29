package br.com.serratec.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class ProdutoPedidoDTO {
	
	@NotNull
	private Long produtoId;
	
	@NotNull
	private Integer quantidade;
	
	private BigDecimal desconto;
	private BigDecimal valorUnitario;
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
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	
}
