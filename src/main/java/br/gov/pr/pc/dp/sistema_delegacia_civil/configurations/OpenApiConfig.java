package br.gov.pr.pc.dp.sistema_delegacia_civil.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Sistema Delegacia Civil")
                        .version("1.0")
                        .description("API REST para gerenciamento de boletins, inquéritos e apreensões da delegacia")
                        .contact(new Contact()
                                .name("Guilherme Momolli")
                                .email("guilhermemomolli.comercial@gmail.com")
                                .url("https://github.com/guilherme-momolli")
                        )
                );
    }
}