package org.sistema;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;

import org.sistema.controller.ReservaController;
import org.sistema.controller.UsuarioController;

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

        @Override
        public Set<Class<?>> getClasses() {
                Set<Class<?>> classes = new HashSet<>();
                // Registrar tus controladores para que JAX-RS y Swagger los detecten
                classes.add(ReservaController.class);
                classes.add(UsuarioController.class);
                return classes;
        }
}