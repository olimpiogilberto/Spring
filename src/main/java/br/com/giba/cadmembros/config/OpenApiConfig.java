package br.com.giba.cadmembros.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI gestaoApi() {
        return new OpenAPI()
                .servers(List.of(new Server().url("/").description("Servidor atual")))
                .info(new Info()
                .title("API de Gestão de Empresas e Trabalhadores")
                .description("""
                        API REST para cadastro e consulta de empresas, pessoas, vínculos empregatícios,
                        endereços e faturamentos anuais. CPF e CNPJ podem ser enviados formatados ou
                        somente com dígitos; a API os normaliza para dígitos.
                        """)
                .version("1.0.0")
                .contact(new Contact().name("Projeto Spring").url("https://github.com/olimpiogilberto/Spring")));
    }
}
