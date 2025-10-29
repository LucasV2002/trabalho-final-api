package main.java.br.com.serratec.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
		
	@Value("${dominio.openapi.dev-uri}")
	private String devUri;
	
	@Value("${dominio.openapi.dev-uri}")
	private String devProd;
	
	@Bean
	OpenAPI myOpenApi() {
		Server devServer = new Server();
		devServer.setUrl(devUri);
		devServer.setDescription("Uri do servidor de Dev.");
		
		Server prodServer = new Server();
		prodServer.setUrl(devProd);
		prodServer.setDescription("Uri do servidor de Prod.");;
		
		Contact contact = new Contact();
		contact.setEmail("grupotresserratec@gmail.com");
		contact.setName("Grupo 3");
		contact.setUrl("www.meudominio.com.br");
		
		Info info = new Info().title("API TRABALHO FINAL").version("1.0")
				.contact(contact).termsOfService("Api usada para E-commerce");
		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}
}
