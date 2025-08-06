package org.sistema.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa una reserva")
public class Reserva {

    @Schema(description = "ID de la reserva", example = "1")
    private int idReserva;

    @Schema(description = "ID del usuario que realiza la reserva", example = "10", required = true)
    private int idUsuario;

    @Schema(description = "Cantidad de personas para la reserva", example = "4", required = true)
    private int cantidadPersonas;

    @Schema(description = "Fecha y hora de la reserva", example = "2025-08-10T19:00:00", required = true)
    private LocalDateTime fechaHora;

    public Reserva(int idUsuario, int cantidadPersonas, LocalDateTime fechaHora) {
        this.idUsuario = idUsuario;
        this.cantidadPersonas = cantidadPersonas;
        this.fechaHora = fechaHora;
    }
}
