package br.com.serratec.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Cliente;
import br.com.serratec.repository.ClienteRepository;
import jakarta.validation.Valid;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente save(@Valid Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cliente update(Long id, @Valid Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}
	

	


	
	
}
