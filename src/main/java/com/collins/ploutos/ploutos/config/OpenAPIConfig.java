package com.collins.ploutos.ploutos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:3000");
        devServer.setDescription("Development server");

        Contact contact = new Contact();
        contact.setEmail("contact@ploutos.com");
        contact.setName("Ploutos Support");
        contact.setUrl("https://www.ploutos.com");

        License mitLicense = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Ploutos API Documentation")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints for the Ploutos application.")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
