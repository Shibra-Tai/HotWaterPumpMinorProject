package com.crm.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@OpenAPIDefinition(
  info =@Info(
    title = "HotWater CRM API",
    version = "1.0",
    contact = @Contact(
      name = "Mojo-365", email = "info@mojo-365.com", url = "mojo-365.com"
    ),
    license = @License(
      name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
    ),
    termsOfService = "${tos.uri}",
    description = "${api.description}"
  ),
  servers = @Server(
    url = "/",
    description = "This API is for CRM"
  )
)
public class OpenAPISecurityConfiguration {
	@Bean
	public OpenAPI customizeOpenAPI() {
		
	    final String securitySchemeName = "bearerAuth";
	    return new OpenAPI()
	      .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
	        .addList(securitySchemeName))
	      .components(new Components()
	        .addSecuritySchemes(securitySchemeName, new io.swagger.v3.oas.models.security.SecurityScheme()
	        		.name("bearerAuth")
	          .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
	          .scheme("bearer")
	          .bearerFormat("JWT")))
	      
	      	;
	    }
}