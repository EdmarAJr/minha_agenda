package br.ifba.edu.agenda.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import org.springframework.context.annotation.Bean;

// public class SwaggerConfig {
//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info()
//                         .title("API de Agenda")
//                         .version("1.0")
//                         .description("Documentação da API de agenda"));
//     }
// }

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Garante que o Spring Boot carregue esta configuração ao subir a aplicação
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        
        return new OpenAPI()
                .info(new Info()
                        .title("API de Agenda")
                        .version("1.0")
                        .description("Documentação da API de agenda"))
                // 1. Aplica o requisito de segurança globalmente para todos os endpoints expostos
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // 2. Define o esquema que adiciona o cadeado e ensina o Swagger a gerenciar o Token
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Insira apenas o token JWT obtido no login (sem a palavra Bearer).")));
    }
}