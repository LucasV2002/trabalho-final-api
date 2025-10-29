package br.com.serratec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.PedidoProduto;
import br.com.serratec.entity.Produto;
import br.com.serratec.exception.ClienteException;
import br.com.serratec.pk.PedidoProdutoPK;
import br.com.serratec.repository.ClienteRepository;
import br.com.serratec.repository.PedidoRepository;
import br.com.serratec.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void atualizar() {}

	public PedidoResponseDTO inserir(PedidoRequestDTO dto) {
		if (dto.getClienteId() == null) {
			throw new ClienteException("Cliente não informado.");
		}
		if (dto.getItens() == null || dto.getItens().isEmpty()) {
			throw new RuntimeException("Nenhum item de pedido informado.");
		}

		Cliente cliente = clienteRepository.findById(UUID.fromString(dto.getClienteId()))
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataPedido(LocalDate.now());

		List<PedidoProduto> itensPedido = new ArrayList<>();

		for (PedidoRequestDTO.ProdutoPedidoDTO itemDTO : dto.getItens()) {
			Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
					.orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId()));

			// Cria a chave composta
			PedidoProdutoPK pk = new PedidoProdutoPK();
			pk.setPedido(pedido);
			pk.setProduto(produto);

			PedidoProduto item = new PedidoProduto(pedido, produto, itemDTO.getQuantidade());
			item.setPedidoProdutoPK(pk);
			item.setQuantidade(itemDTO.getQuantidade());
			item.calcularTotal();

			itensPedido.add(item);
		}

		pedido.setItens(itensPedido);
		pedido.setValorTotal(itensPedido.stream().mapToDouble(i -> i.getTotal()).sum());

		pedido = pedidoRepository.save(pedido);

		return new PedidoResponseDTO(pedido.getId(), pedido.getValorTotal(), pedido.getDataPedido(), pedido.getItens());
	}

	public List<PedidoResponseDTO> listar() {
		List<PedidoResponseDTO> pedidosDTO = new ArrayList<>();

		for (Pedido pedido : pedidoRepository.findAll()) {
			pedidosDTO.add(new PedidoResponseDTO(pedido.getId(), pedido.getValorTotal(), pedido.getDataPedido(),
					pedido.getItens()));
		}

		return pedidosDTO;
	}

	public PedidoResponseDTO buscarId(Long id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

		return new PedidoResponseDTO(pedido.getId(), pedido.getValorTotal(), pedido.getDataPedido(), pedido.getItens());
	}
	
}
