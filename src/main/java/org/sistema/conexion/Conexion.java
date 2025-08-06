package org.sistema.conexion;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/bd_reserva?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "miranda";

    public static Connection obtenerConexion() throws SQLException {
        // No se necesita el cast, ya que el tipo de retorno es correcto
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
