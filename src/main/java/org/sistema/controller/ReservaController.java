package org.sistema.controller;

import org.sistema.entity.Reserva;
import org.sistema.service.ReservaService;

import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/reservas")
@Tag(name = "ReservaController", description = "Operaciones relacionadas a reservas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservaController {

    private final ReservaService reservaService = new ReservaService();

    @POST
    @Path("/crear")
    @Operation(summary = "Crear una nueva reserva")
    public void crearReserva(
            @Parameter(description = "Datos de la reserva", required = true)
            Reserva reserva) {
        reservaService.crearReserva(reserva);
    }

    @GET
    @Path("/obtener")
    @Operation(summary = "Obtener una reserva por ID")
    public Reserva obtenerReserva(
            @Parameter(description = "ID de la reserva", required = true)
            @QueryParam("id") int id) {
        return reservaService.obtenerReserva(id);
    }

    @GET
    @Path("/listar")
    @Operation(summary = "Listar todas las reservas")
    public List<Reserva> listarReservas() {
        return reservaService.listarReservas();
    }

    @PUT
    @Path("/actualizar")
    @Operation(summary = "Actualizar una reserva")
    public void actualizarReserva(
            @Parameter(description = "Datos actualizados de la reserva", required = true)
            Reserva reserva) {
        reservaService.actualizarReserva(reserva);
    }

    @DELETE
    @Path("/eliminar")
    @Operation(summary = "Eliminar una reserva por ID")
    public void eliminarReserva(
            @Parameter(description = "ID de la reserva", required = true)
            @QueryParam("id") int id) {
        reservaService.eliminarReserva(id);
    }

}
