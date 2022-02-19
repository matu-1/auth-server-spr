package com.codemon.authserver.configs;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearer",
        paramName = "Authorization",
        description = "Por favor ingrese el Token",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        scheme = "Bearer"
)
public class SwaggerConfig {
    @Bean
    public OpenAPI authOpenApi() {
        return new OpenAPI()
                .info(new Info()
                    .title("AuthServer API")
                    .description("Auth Simple de muestra con Spring Boot")
                    .version("v1")
                    .contact(new Contact().name("Linkedin").url("https://www.linkedin.com/in/matias-manuel-flores-ricaldez-34740a205/"))
                ).addSecurityItem(new SecurityRequirement().addList("bearer"));
    }
}
