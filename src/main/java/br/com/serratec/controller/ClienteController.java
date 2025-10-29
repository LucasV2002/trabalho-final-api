package br.com.serratec.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Clientes", description = "Gerenciamento de clientes cadastrados na aplicação")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@Operation(summary = "Cadastrar novo cliente", description = "Cria um novo registro de cliente no sistema e retorna as informações cadastradas.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso",content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
    @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do cliente"),
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")})
	@PostMapping
	public ClienteResponseDTO inserir(@RequestBody ClienteRequestDTO cliente) {
		return service.inserir(cliente);
	}

	@Operation(summary = "Excluir cliente", description = "Exclui um cliente do sistema com base no seu ID (UUID).")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso"),
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno ao excluir o cliente")})
	@DeleteMapping("/excluir/{id}")
	public HttpStatus deletar(@PathVariable UUID id) {
		return service.deletar(id);
	}

	@Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente já existente no sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso", content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
    @ApiResponse(responseCode = "400", description = "Erro de validação nos dados"),
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")})
	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponseDTO> atualizarCliente(
	        @Valid @PathVariable UUID id,
	        @RequestBody ClienteRequestDTO requestDTO) {

	    ClienteResponseDTO responseDTO = service.atualizarCliente(id, requestDTO);

	    return ResponseEntity.ok(responseDTO);
	}

	@Operation(summary = "Listar clientes", description = "Retorna uma lista com todos os clientes cadastrados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso", content = @Content(schema = @Schema(implementation = ClienteResponseDTO.class))),
    @ApiResponse(responseCode = "204", description = "Nenhum cliente encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno ao listar clientes")})
	@GetMapping
	public List<ClienteResponseDTO> listar() {
		return service.listar();
	}
}