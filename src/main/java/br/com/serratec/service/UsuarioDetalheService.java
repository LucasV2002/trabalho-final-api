package br.com.serratec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Cliente;
import br.com.serratec.exception.ClienteException;
import br.com.serratec.repository.ClienteRepository;

@Service
public class UsuarioDetalheService implements UserDetailsService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Cliente> usuario = repository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		throw new ClienteException("Email não encontrado");
	}

}