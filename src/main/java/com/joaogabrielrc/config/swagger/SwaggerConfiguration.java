package com.joaogabrielrc.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI apiForum() {
    Info info = new Info();
    SecurityRequirement securityItem = new SecurityRequirement();
    Components components = new Components();
    components.addSecuritySchemes(
      "Authorization",
      new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
    );
    return new OpenAPI().components(components)
      .addSecurityItem(securityItem);
  }

}
