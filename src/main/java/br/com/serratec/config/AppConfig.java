package br.com.serratec.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.serratec.security.JwtAuthenticationFilter;
import br.com.serratec.security.JwtAuthorizationFilter;
import br.com.serratec.security.JwtUtil;

@Configuration
@EnableWebSecurity
public class AppConfig {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.POST, "/clientes").permitAll() // cadastro
						.requestMatchers(HttpMethod.GET, "/clientes").permitAll() // listagem publica opcional
						.requestMatchers(HttpMethod.DELETE, "/clientes/excluir/**").permitAll() // DELETE
						.requestMatchers(HttpMethod.PUT, "/clientes/**").permitAll() // atualizar (ja sao 7h da manha T-T)
						
						.requestMatchers(HttpMethod.POST, "/pedidos/**").permitAll()
						
						// categorias
						.requestMatchers( "/categorias").permitAll()
						.requestMatchers(HttpMethod.GET, "/categorias").permitAll()
						.requestMatchers(HttpMethod.PUT, "/categorias/**").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/categorias/**").permitAll()

						// produtos (Deus me ajude)
						.requestMatchers(HttpMethod.POST, "/produtos").permitAll()
						.requestMatchers(HttpMethod.GET, "/produtos").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/produtos/excluir/**").permitAll()

						// Swagger
						.requestMatchers("/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()

						// H2
						.requestMatchers("/h2-console/**").permitAll()

						// Todo o resto precisa de autenticação
						.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.headers(headers -> headers.frameOptions().disable());

		http.addFilterBefore(
				new JwtAuthenticationFilter(
						authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil),
				UsernamePasswordAuthenticationFilter.class);
		
		http.addFilterBefore(new JwtAuthorizationFilter(
				authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil,
				userDetailsService), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	BCryptPasswordEncoder criptografar() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:2000"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedHeader("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
}
