package dao;

import modelo.Usuario;
import java.sql.*;
import java.util.*;
import modelo.Conexion;

public class UsuarioDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Usuario validar(String correo, String contrasena) {
        Usuario u = null;
        String sql = "SELECT id_usuario, nombre, correo, contrasena, id_rol, id_especialidad "
                + "FROM tbl_usuarios WHERE correo=? AND contrasena=?";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = mapUsuario(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        try {
            con = Conexion.conectarBD();
            ps = con.prepareStatement("SELECT * FROM tbl_usuarios ORDER BY id_usuario DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapUsuario(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return lista;
    }

    public Usuario getById(int id) {
        Usuario u = null;
        try {
            con = Conexion.conectarBD();
            ps = con.prepareStatement("SELECT * FROM tbl_usuarios WHERE id_usuario=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                u = mapUsuario(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return u;
    }

    public boolean agregar(Usuario u) {
        try {
            con = Conexion.conectarBD();
            ps = con.prepareStatement("INSERT INTO tbl_usuarios (nombre, correo, contrasena, id_rol, id_especialidad) VALUES (?,?,?,?,?)");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getContrasena());
            ps.setInt(4, u.getRol());
            ps.setInt(5, u.getId_especialidad());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }

    public boolean actualizar(Usuario u) {
        try {
            con = Conexion.conectarBD();
            ps = con.prepareStatement("UPDATE tbl_usuarios SET nombre=?, correo=?, contrasena=?, id_rol=?, id_especialidad=? WHERE id_usuario=?");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getContrasena());
            ps.setInt(4, u.getRol());
            ps.setInt(5, u.getId_especialidad());
            ps.setInt(6, u.getId_usuario());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }

    public boolean eliminar(int id) {
        try {
            con = Conexion.conectarBD();
            ps = con.prepareStatement("DELETE FROM tbl_usuarios WHERE id_usuario=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }

    private Usuario mapUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("contrasena"),
                rs.getInt("id_rol"),
                rs.getInt("id_especialidad")
        );
    }

    private void cerrar() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
        }
    }

    public int contar() {
        String sql = "SELECT COUNT(*) FROM tbl_usuarios";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
