package org.sistema.service;

import org.sistema.dao.ReservaDAO;
import org.sistema.dao.UsuarioDAO;
import org.sistema.entity.Reserva;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ReservaService {
    private final ReservaDAO reservaDAO = new ReservaDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private boolean validarReserva(Reserva reserva) {
        if (reserva.getIdUsuario() <= 0 || usuarioDAO.obtener(reserva.getIdUsuario()) == null) {
            System.err.println("Usuario de la reserva no existe.");
            return false;
        }
        if (reserva.getCantidadPersonas() <= 0 || reserva.getCantidadPersonas() > 8) {
            System.err.println("Cantidad de personas debe ser entre 1 y 8.");
            return false;
        }
        if (reserva.getFechaHora() == null || reserva.getFechaHora().isBefore(LocalDateTime.now())) {
            System.err.println("Fecha de reserva no válida.");
            return false;
        }
        LocalTime horaReserva = reserva.getFechaHora().toLocalTime();
        if (horaReserva.isBefore(LocalTime.of(13, 0)) || horaReserva.isAfter(LocalTime.of(23, 0))) {
            System.err.println("Horario de reserva: 13:00 a 23:00.");
            return false;
        }
        return true;
    }

    public void crearReserva(Reserva reserva) {
        if (validarReserva(reserva)) {
            reservaDAO.crear(new Reserva(reserva.getIdUsuario(), reserva.getCantidadPersonas(), reserva.getFechaHora()));
        } else {
            System.err.println("No se pudo crear la reserva.");
        }
    }

    public Reserva obtenerReserva(int id) {
        return reservaDAO.obtener(id);
    }

    public List<Reserva> listarReservas() {
        return reservaDAO.listar();
    }

    public void actualizarReserva(Reserva reserva) {
        if (reserva.getIdReserva() <= 0) {
            System.err.println("ID de reserva no válido.");
            return;
        }
        if (reservaDAO.obtener(reserva.getIdReserva()) == null) {
            System.err.println("Reserva no encontrada.");
            return;
        }
        if (validarReserva(reserva)) {
            reservaDAO.actualizar(reserva);
        } else {
            System.err.println("No se pudo actualizar la reserva.");
        }
    }

    public void eliminarReserva(int id) {
        if (id <= 0) {
            System.err.println("ID de reserva no válido.");
            return;
        }
        if (reservaDAO.obtener(id) == null) {
            System.err.println("Reserva no encontrada.");
            return;
        }
        reservaDAO.eliminar(id);
    }
}
