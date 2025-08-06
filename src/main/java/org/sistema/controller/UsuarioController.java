package org.sistema.controller;

import org.sistema.entity.Usuario;
import org.sistema.service.UsuarioService;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Path("/usuarios")
@Tag(name = "UsuarioController", description = "Operaciones relacionadas a usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();

    @POST
    @Path("/crear")
    @Operation(summary = "Crear un nuevo usuario")
    public void crearUsuario(
            @Parameter(description = "Datos del nuevo usuario", required = true)
            Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
    }

    @GET
    @Path("/obtener")
    @Operation(summary = "Obtener un usuario por ID")
    public Usuario obtenerUsuario(
            @Parameter(description = "ID del usuario", required = true)
            @QueryParam("id") int id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @GET
    @Path("/listar")
    @Operation(summary = "Listar todos los usuarios")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PUT
    @Path("/actualizar")
    @Operation(summary = "Actualizar datos de un usuario")
    public void actualizarUsuario(
            @Parameter(description = "Datos actualizados del usuario", required = true)
            Usuario usuario) {
        usuarioService.actualizarUsuario(usuario);
    }

    @DELETE
    @Path("/eliminar")
    @Operation(summary = "Eliminar un usuario por ID")
    public void eliminarUsuario(
            @Parameter(description = "ID del usuario", required = true)
            @QueryParam("id") int id) {
        usuarioService.eliminarUsuario(id);
    }
}
