package ru.raperan.abstracttaskexecutorservice.masterservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    servers = {
        @Server(
            description = "Production environment",
            url = "https://keycloak.raperan.ru/realms/abstract_task_executor"
        )
    },
    security = {
        @SecurityRequirement(
            name = "keycloak"
        )
    }
)
@SecurityScheme(
    name = "keycloak",
    type = SecuritySchemeType.OAUTH2,
    in = SecuritySchemeIn.HEADER,
    description = "Keycloak OAuth2 Client Credentials flow",
    flows = @OAuthFlows(
        password = @OAuthFlow(
            scopes = @OAuthScope(name="openid"),
            tokenUrl = "https://keycloak.raperan.ru/realms/abstract_task_executor/protocol/openid-connect/token"
        )
    )
)
public class OpenApiConfig {}
