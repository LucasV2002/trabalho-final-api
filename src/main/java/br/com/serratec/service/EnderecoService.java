package br.com.serratec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.entity.Endereco;
import br.com.serratec.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repository;

	public Endereco buscarCep(String cep) {

		Optional<Endereco> endereco = repository.findByCep(cep);

		if (endereco.isPresent()) {
			return endereco.get();
		} else {
			RestTemplate rs = new RestTemplate();
			System.out.println(cep);
			String url = "https://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(url, Endereco.class));

			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				return inserir(enderecoViaCep.get());
			} else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}
		}

	}

	private Endereco inserir(Endereco endereco) {
		endereco = repository.save(endereco);
		return endereco;

	}
}
