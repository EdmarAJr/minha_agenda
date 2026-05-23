package br.ifba.edu.agenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

// 🚀 ESSA LINHA DIZ PARA O SWAGGER MANDAR AS REQUISIÇÕES PARA O CODESPACES!
@OpenAPIDefinition(
    servers = {
        @Server(url = "https://symmetrical-broccoli-7wxrp6r59g5cr7r6-8080.app.github.dev", description = "Ambiente Codespaces")
    }
)

@SpringBootApplication
public class MinhaAgendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinhaAgendaApplication.class, args);
	}

}
