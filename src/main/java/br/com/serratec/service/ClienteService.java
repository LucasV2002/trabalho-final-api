package br.com.serratec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.serratec.config.MailConfig;
import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Endereco;
import br.com.serratec.exception.ClienteException;
import br.com.serratec.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private MailConfig mailConfig;

	public List<ClienteResponseDTO> listar() {
		List<ClienteResponseDTO> clientesDTO = new ArrayList<>();
		for (Cliente cliente : repository.findAll()) {
			ClienteResponseDTO response = new ClienteResponseDTO();

			response.setId(cliente.getId());
			response.setNome(cliente.getNome());
			response.setEmail(cliente.getEmail());
			response.setCpf(cliente.getCpf());
			response.setTelefone(cliente.getTelefone());
			response.setCep(cliente.getCep());

			if (cliente.getEndereco() != null) {
				response.setLogradouro(cliente.getEndereco().getLogradouro());
				response.setBairro(cliente.getEndereco().getBairro());
				response.setCidade(cliente.getEndereco().getLocalidade());
				response.setEstado(cliente.getEndereco().getUf());
			}

			clientesDTO.add(response);
		}
		return clientesDTO;
	}

	@Transactional
	public ClienteResponseDTO inserir(ClienteRequestDTO clienteRequestDTO) {
		var user = repository.findByEmail(clienteRequestDTO.getEmail());
		if (user.isPresent()) {
			throw new ClienteException("Email já cadastrado!");
		}

		Cliente cliente = new Cliente();
		Endereco endereco = enderecoService.buscarCep(clienteRequestDTO.getCep());
		cliente.setNome(clienteRequestDTO.getNome());
		cliente.setEmail(clienteRequestDTO.getEmail());
		cliente.setSenha(encoder.encode(clienteRequestDTO.getSenha()));
		cliente.setCep(clienteRequestDTO.getCep());
		cliente.setCpf(clienteRequestDTO.getCpf());
		cliente.setTelefone(clienteRequestDTO.getTelefone());
		cliente.setDataCriacao(LocalDate.now());
		cliente.setEndereco(endereco);

		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Nome de usuário: ").append(cliente.getNome()).append("\n").append("Email: ")
				.append(cliente.getEmail());
		cliente = repository.save(cliente);
		mailConfig.enviarEmail(cliente.getEmail(), "Cadastro concluido.", messageBuilder.toString());
		ClienteResponseDTO response = new ClienteResponseDTO();

		response.setId(cliente.getId());
		response.setNome(cliente.getNome());
		response.setEmail(cliente.getEmail());
		response.setCpf(cliente.getCpf());
		response.setTelefone(cliente.getTelefone());
		response.setCep(cliente.getCep());

		if (cliente.getEndereco() != null) {
			response.setLogradouro(cliente.getEndereco().getLogradouro());
			response.setBairro(cliente.getEndereco().getBairro());
			response.setCidade(cliente.getEndereco().getLocalidade());
			response.setEstado(cliente.getEndereco().getUf());
		}
		return response;
	}

	public HttpStatus deletar(UUID id) {
		repository.deleteById(id);
		return HttpStatus.OK;
	}

	@Transactional
	public ClienteResponseDTO atualizarCliente(UUID id, ClienteRequestDTO dto) {
		Cliente cliente = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

		// Atualiza os campos do cliente a partir do RequestDTO
		if (dto.getNome() != null)
			cliente.setNome(dto.getNome());
		if (dto.getEmail() != null)
			cliente.setEmail(dto.getEmail());
		if (dto.getCpf() != null)
			cliente.setCpf(dto.getCpf());
		if (dto.getTelefone() != null)
			cliente.setTelefone(dto.getTelefone());
		if (dto.getCep() != null)
			cliente.setCep(dto.getCep());
		if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
			cliente.setSenha(encoder.encode(dto.getSenha())); // pega do RequestDTO
		}

		repository.save(cliente);

		// Constrói o ResponseDTO para retornar ao cliente
		ClienteResponseDTO response = new ClienteResponseDTO();
		response.setId(cliente.getId());
		response.setNome(cliente.getNome());
		response.setEmail(cliente.getEmail());
		response.setCpf(cliente.getCpf());
		response.setTelefone(cliente.getTelefone());
		response.setCep(cliente.getCep());

		if (cliente.getEndereco() != null) {
			response.setLogradouro(cliente.getEndereco().getLogradouro());
			response.setBairro(cliente.getEndereco().getBairro());
			response.setCidade(cliente.getEndereco().getLocalidade());
			response.setEstado(cliente.getEndereco().getUf());
		}

		return response;
	}

}
