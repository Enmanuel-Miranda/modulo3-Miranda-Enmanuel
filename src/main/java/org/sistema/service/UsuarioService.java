package org.sistema.service;

import org.sistema.dao.UsuarioDAO;
import org.sistema.entity.Usuario;

import java.util.List;

public class UsuarioService {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private boolean validarUsuario(Usuario usuario) {
        if (usuario.getUsuario() == null || usuario.getUsuario().trim().isEmpty() || usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            System.err.println("Datos del usuario incompletos.");
            return false;
        }

        List<Usuario> usuarios = usuarioDAO.listar();
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario.getUsuario()) && u.getId() != usuario.getId()) {
                System.err.println("Nombre de usuario duplicado.");
                return false;
            }
        }
        return true;
    }

    public void registrarUsuario(Usuario usuario) {
        if (validarUsuario(usuario)) {
            usuarioDAO.crear(new Usuario(usuario.getUsuario(), usuario.getNombre()));
        } else {
            System.err.println("No se pudo crear el usuario.");
        }
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioDAO.obtener(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }

    public void actualizarUsuario(Usuario usuario) {
        if (usuario.getId() <= 0) {
            System.err.println("ID de usuario no válido.");
            return;
        }
        if (usuarioDAO.obtener(usuario.getId()) == null) {
            System.err.println("Usuario no encontrado.");
            return;
        }
        if (validarUsuario(usuario)) {
            usuarioDAO.actualizar(usuario);
        } else {
            System.err.println("No se pudo actualizar el usuario.");
        }
    }

    public void eliminarUsuario(int id) {
        if (id <= 0) {
            System.err.println("ID de usuario no válido.");
            return;
        }
        if (usuarioDAO.obtener(id) == null) {
            System.err.println("Usuario no encontrado.");
            return;
        }
        usuarioDAO.eliminar(id);
    }
}
