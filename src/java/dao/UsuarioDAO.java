package dao;

import modelo.Usuario;
import util.Conexion;
import util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // LOGIN: obtiene usuario por correo y verifica password con BCrypt
    public Usuario login(String correo, String passwordPlain) {
        String sql = "SELECT * FROM tbl_usuarios WHERE correo = ?";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashed = rs.getString("contrasena");
                    if (PasswordUtil.check(passwordPlain, hashed)) {
                        Usuario u = new Usuario();
                        u.setId_usuario(rs.getInt("id_usuario"));
                        u.setNombre(rs.getString("nombre"));
                        u.setCorreo(rs.getString("correo"));
                        u.setContrasena(hashed);
                        return u;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // BUSCAR POR CORREO
    public Usuario buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM tbl_usuarios WHERE correo = ?";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId_usuario(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreo(rs.getString("correo"));
                    u.setContrasena(rs.getString("contrasena"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR TODOS
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre, correo, contrasena FROM tbl_usuarios ORDER BY id_usuario DESC";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setContrasena(rs.getString("contrasena"));
                lista.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // CREAR USUARIO
    public boolean crear(Usuario u) {
        String sql = "INSERT INTO tbl_usuarios(nombre, correo, contrasena) VALUES(?,?,?)";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getContrasena()); // ya hashed
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM tbl_usuarios WHERE id_usuario = ?";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId_usuario(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreo(rs.getString("correo"));
                    u.setContrasena(rs.getString("contrasena"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ACTUALIZAR USUARIO
    public boolean actualizar(Usuario u) {
        String sql = "UPDATE tbl_usuarios SET nombre=?, correo=?, "
                + (u.getContrasena() != null && !u.getContrasena().isEmpty() ? "contrasena=? " : "")
                + "WHERE id_usuario=?";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {
            int idx = 1;
            ps.setString(idx++, u.getNombre());
            ps.setString(idx++, u.getCorreo());
            if (u.getContrasena() != null && !u.getContrasena().isEmpty()) {
                ps.setString(idx++, u.getContrasena());
            }
            ps.setInt(idx++, u.getId_usuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR USUARIO
    public boolean eliminar(int id) {
        String sql = "DELETE FROM tbl_usuarios WHERE id_usuario = ?";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
