package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.CategoriaRequestDTO;
import br.com.serratec.dto.CategoriaResponseDTO;
import br.com.serratec.entity.Categoria;
import br.com.serratec.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Categoria", description = "Catálogo de categorias")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService service;

	@Operation(summary = "Insere uma categoria", description = "A resposta é a categoria inserida")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
	@Content(schema = @Schema(implementation = Categoria.class), mediaType = "application/json") }, description = "Categoria cadastrada"),
	@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	@ApiResponse(responseCode = "403", description = "Não há permissão para acessar a Categoria"),
	@ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada"),
	@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CategoriaResponseDTO> inserir(@Valid @RequestBody CategoriaRequestDTO  categoriadto) {
		return ResponseEntity.ok(service.inserir(categoriadto));
		
	}
	@Operation(summary = "Atualizar categoria existente", description = "Atualiza os dados de uma categoria já cadastrada no sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
    @ApiResponse(responseCode = "400", description = "Erro de validação dos dados")})
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO categoriadto) {
		return ResponseEntity.ok(service.atualizar(id, categoriadto));
	}
	
	@Operation(summary = "Lista todas as categorias", description = "Retorna todas as categorias")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
	@Content(schema = @Schema(implementation = Categoria.class), mediaType = "application/json") }, description = "Categoria cadastrada"),
	@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
	@ApiResponse(responseCode = "403", description = "Não há permissão para acessar a Categoria"),
	@ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada"),
	@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
	}
	
	@Operation(summary = "Busca categoria por ID", description = "Consulta uma categoria específica pelo seu identificador.")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
	@ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada")})		
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(service.buscarId(id));
	}
}