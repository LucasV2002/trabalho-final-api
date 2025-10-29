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
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Produto;
import br.com.serratec.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Produtos", description = "Gerenciamento dos produtos da papelaria")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@Operation(summary = "Listar produtos", description = "Retorna a lista completa de produtos cadastrados no sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso", content = @Content(schema = @Schema(implementation = Produto.class))),
    @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado"),
    @ApiResponse(responseCode = "500", description = "Erro interno ao listar produtos")})
	@GetMapping
	public List<Produto> listar() {
		return service.listar();
	}

	@Operation(summary = "Cadastrar produto", description = "Cria um novo produto no sistema com base nos dados informados.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso", content = @Content(schema = @Schema(implementation = Produto.class))),
    @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do produto"),
    @ApiResponse(responseCode = "500", description = "Erro interno ao cadastrar produto")})
	@PostMapping
	public ResponseEntity<Produto> inserir(@Valid @RequestBody Produto produto) {
		Produto Produto = service.inserirProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(Produto);
	}

	@Operation(summary = "Atualizar produto", description = "Atualiza as informações de um produto existente com base no ID informado.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",content = @Content(schema = @Schema(implementation = Produto.class))),
    @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    @ApiResponse(responseCode = "400", description = "Erro de validação nos dados informados"),
    @ApiResponse(responseCode = "500", description = "Erro interno ao atualizar produto")})
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
		Produto atualizado = service.atualizarProduto(id, produto);
		if (atualizado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(atualizado);
	}
}
