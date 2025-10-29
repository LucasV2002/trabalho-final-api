package br.com.serratec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.serratec.config.MailConfig;
import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.entity.Cliente;
import br.com.serratec.exception.ClienteException;
import br.com.serratec.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private MailConfig mailConfig;

	public List<ClienteResponseDTO> listar() {
		List<ClienteResponseDTO> clientesDTO = new ArrayList<>();
		for (Cliente cliente : repository.findAll()) {
			cliente.getAuthorities();
			clientesDTO.add(new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail()));
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
        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setSenha(encoder.encode(clienteRequestDTO.getSenha()));
        cliente.setDataCriacao(LocalDate.now());

        cliente = repository.save(cliente);

      

        return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
	}
}
