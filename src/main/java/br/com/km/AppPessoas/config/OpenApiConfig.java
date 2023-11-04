package br.com.km.AppPessoas.config;


import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicScheme",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info()
                        .title("App Pessoas")
                        .description("Esta aplicação faz o gerenciamento de um sistema de\n" +
                                "cadastro de Pessoas e seus Contatos,")
                        .contact(new Contact().name("Nome").email("email").url("url"))
                        .version("Versão 0.0.1-SNAPSHOT"));
    }

}

