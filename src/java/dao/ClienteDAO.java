package dao;

import modelo.Cliente;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<Cliente> listar() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, cedula_cliente, nombre, apellido, direccion,telefono FROM tbl_clientes";
        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId_cliente(rs.getInt("id_cliente"));
                cl.setCedula_cliente(rs.getString("cedula_cliente"));
                cl.setNombre(rs.getString("nombre"));
                cl.setApellido(rs.getString("apellido"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setTelefono(rs.getString("telefono"));
                lista.add(cl);
            }
        }
        return lista;
    }

    public Cliente findById(int id) throws Exception {
        String sql = "SELECT id_cliente, cedula_cliente, nombre, apellido, direccion,telefono FROM tbl_clientes WHERE id_cliente = ?";
        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cl = new Cliente();
                    cl.setId_cliente(rs.getInt("id_cliente"));
                    cl.setCedula_cliente(rs.getString("cedula_cliente"));
                    cl.setNombre(rs.getString("nombre"));
                    cl.setApellido(rs.getString("apellido"));
                    cl.setDireccion(rs.getString("direccion"));
                    cl.setTelefono(rs.getString("telefono"));
                    return cl;
                }
            }
        }
        return null;
    }

    public void crear(Cliente cte) throws Exception {
        String sql = "INSERT INTO tbl_clientes (cedula_cliente, nombre, apellido, direccion,telefono) VALUES (?, ?, ?, ?,?)";
        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, cte.getCedula_cliente());
            ps.setString(2, cte.getNombre());
            ps.setString(3, cte.getApellido());
            ps.setString(4, cte.getDireccion());
            ps.setString(5, cte.getTelefono());
            ps.executeUpdate();
        }
    }

    public void actualizar(Cliente cte) throws Exception {
        String sql = "UPDATE tbl_clientes SET cedula_cliente=?, nombre=?, apellido=?,direccion=? ,telefono=? WHERE id_cliente=?";
        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, cte.getCedula_cliente());
            ps.setString(2, cte.getNombre());
            ps.setString(3, cte.getApellido());
            ps.setString(4, cte.getDireccion());
            ps.setString(5, cte.getTelefono());
            ps.setInt(6, cte.getId_cliente());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM tbl_clientes WHERE id_cliente = ?";
        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
