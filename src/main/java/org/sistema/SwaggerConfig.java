package org.sistema;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@ApplicationPath("/api") // Prefijo base para tus rutas
@OpenAPIDefinition(
        info = @Info(
                title = "Sistema de Reservas",
                version = "1.0",
                description = "Documentación de la API del sistema de reservas"
        )
)
public class SwaggerConfig extends Application {
}
