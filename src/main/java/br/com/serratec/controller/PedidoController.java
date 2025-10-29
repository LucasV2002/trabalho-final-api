package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pedidos", description = "Gerenciamento de pedidos de clientes")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@Operation(summary = "Registrar novo pedido", description = "Cria um novo pedido a partir dos dados informados no corpo da requisição.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso", content = @Content(schema = @Schema(implementation = PedidoResponseDTO.class), mediaType = "application/json")),
    @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do pedido"),
    @ApiResponse(responseCode = "404", description = "Cliente ou produto não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno no processamento do pedido")})
	@PostMapping
	public ResponseEntity<PedidoResponseDTO> inserir(@RequestBody PedidoRequestDTO dto) {
		PedidoResponseDTO response = service.inserir(dto);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Listar pedidos", description = "Retorna a lista de todos os pedidos cadastrados no sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso",content = @Content(schema = @Schema(implementation = PedidoResponseDTO.class), mediaType = "application/json")),
    @ApiResponse(responseCode = "204", description = "Nenhum pedido encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno ao listar pedidos")})
	@GetMapping
	public ResponseEntity<List<PedidoResponseDTO>> listar() {
		List<PedidoResponseDTO> pedidos = service.listar();
		return ResponseEntity.ok(pedidos);
	}

}