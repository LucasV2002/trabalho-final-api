package br.com.serratec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Endereco;
import br.com.serratec.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endereço", description = "Consulta de endereços por CEP (integração com ViaCEP)")
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	
	@Operation(summary = "Consultar endereço por CEP", description = "Busca um endereço completo utilizando a API ViaCEP, com base no CEP informado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso", content = @Content(schema = @Schema(implementation = Endereco.class), mediaType = "application/json")),
    @ApiResponse(responseCode = "400", description = "CEP inválido ou mal formatado"),
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o CEP informado"),
    @ApiResponse(responseCode = "500", description = "Erro interno ao consultar o serviço ViaCEP")})
	@GetMapping("{cep}")
	public ResponseEntity<Endereco> buscarCep(@PathVariable String cep) {
		return ResponseEntity.ok(service.buscarCep(cep));
	}

}