package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@PostMapping
	public ResponseEntity<PedidoResponseDTO> inserir(@RequestBody PedidoRequestDTO dto) {
		PedidoResponseDTO response = service.inserir(dto);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<PedidoResponseDTO>> listar() {
		List<PedidoResponseDTO> pedidos = service.listar();
		return ResponseEntity.ok(pedidos);
	}

}
