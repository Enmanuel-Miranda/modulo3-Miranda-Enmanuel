package org.sistema.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa a un usuario")
public class Usuario {

    @Schema(description = "ID del usuario", example = "1")
    private int id;

    @Schema(description = "Nombre de usuario (username)", example = "jdoe", required = true)
    private String usuario;

    @Schema(description = "Nombre completo del usuario", example = "Juan Doe", required = true)
    private String nombre;

    public Usuario(String usuario, String nombre) {
        this.usuario = usuario;
        this.nombre = nombre;
    }
}
