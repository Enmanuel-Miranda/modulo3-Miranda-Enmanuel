package org.sistema.dao;

import org.sistema.conexion.Conexion;
import org.sistema.entity.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    public void crear(Reserva reserva) {
        String sql = "INSERT INTO reserva (id_usuario, cantidad_personas, fecha_hora) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, reserva.getIdUsuario());
            stmt.setInt(2, reserva.getCantidadPersonas());
            stmt.setTimestamp(3, Timestamp.valueOf(reserva.getFechaHora()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    reserva.setIdReserva(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al crear la reserva: " + e.getMessage());
        }
    }

    public Reserva obtener(int id) {
        String sql = "SELECT id_reserva, id_usuario, cantidad_personas, fecha_hora FROM reserva WHERE id_reserva = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reserva(
                            rs.getInt("id_reserva"),
                            rs.getInt("id_usuario"),
                            rs.getInt("cantidad_personas"),
                            rs.getTimestamp("fecha_hora").toLocalDateTime()
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la reserva: " + e.getMessage());
        }
        return null;
    }

    public List<Reserva> listar() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT id_reserva, id_usuario, cantidad_personas, fecha_hora FROM reserva";
        try (Connection conn = Conexion.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                reservas.add(new Reserva(
                        rs.getInt("id_reserva"),
                        rs.getInt("id_usuario"),
                        rs.getInt("cantidad_personas"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las reservas: " + e.getMessage());
        }
        return reservas;
    }

    public void actualizar(Reserva reserva) {
        String sql = "UPDATE reserva SET id_usuario = ?, cantidad_personas = ?, fecha_hora = ? WHERE id_reserva = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdUsuario());
            stmt.setInt(2, reserva.getCantidadPersonas());
            stmt.setTimestamp(3, Timestamp.valueOf(reserva.getFechaHora()));
            stmt.setInt(4, reserva.getIdReserva());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar la reserva: " + e.getMessage());
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM reserva WHERE id_reserva = ?";
        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar la reserva: " + e.getMessage());
        }
    }
}
