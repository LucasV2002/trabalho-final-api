package br.com.serratec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, String>{
	Optional<Endereco> findByCep(String cep);
}
