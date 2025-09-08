package dao;

import modelo.Conexion;
import modelo.Productos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // 📌 Listar todos los productos
    public List<Productos> listar() throws Exception {
        List<Productos> lista = new ArrayList<>();
        String sql = "SELECT id_producto, codigo_barras, nombre, marca, precio, id_proveedor, create_at, update_at FROM tbl_productos";
        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Productos p = new Productos();
                p.setId_producto(rs.getInt("id_producto"));
                p.setCodigo_barras(rs.getString("codigo_barras"));
                p.setNombre(rs.getString("nombre"));
                p.setMarca(rs.getString("marca"));
                p.setPrecio(rs.getBigDecimal("precio"));
                p.setId_proveedor(rs.getInt("id_proveedor"));

                lista.add(p);
            }
        }
        return lista;
    }

    // 📌 Buscar por ID
    public Productos findById(int id) throws Exception {
        String sql = "SELECT id_producto, codigo_barras, nombre, marca, precio, id_proveedor, create_at, update_at FROM tbl_productos WHERE id_producto = ?";
        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Productos p = new Productos();
                    p.setId_producto(rs.getInt("id_producto"));
                    p.setCodigo_barras(rs.getString("codigo_barras"));
                    p.setNombre(rs.getString("nombre"));
                    p.setMarca(rs.getString("marca"));
                    p.setPrecio(rs.getBigDecimal("precio"));
                    p.setId_proveedor(rs.getInt("id_proveedor"));

                    return p;
                }
            }
        }
        return null;
    }

    // 📌 Crear nuevo producto
    public void crear(Productos pdto) throws Exception {
        String sql = "INSERT INTO tbl_productos (codigo_barras, nombre, marca, precio, id_proveedor) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, pdto.getCodigo_barras());
            ps.setString(2, pdto.getNombre());
            ps.setString(3, pdto.getMarca());
            ps.setBigDecimal(4, pdto.getPrecio());
            ps.setInt(5, pdto.getId_proveedor());
            ps.executeUpdate();
        }
    }

    // 📌 Actualizar producto
    public void actualizar(Productos pdto) throws Exception {
        String sql = "UPDATE tbl_productos SET codigo_barras=?, nombre=?, marca=?, precio=?, id_proveedor=? WHERE id_producto=?";
        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, pdto.getCodigo_barras());
            ps.setString(2, pdto.getNombre());
            ps.setString(3, pdto.getMarca());
            ps.setBigDecimal(4, pdto.getPrecio());
            ps.setInt(5, pdto.getId_proveedor());
            ps.setInt(6, pdto.getId_producto());
            ps.executeUpdate();
        }
    }

    // 📌 Eliminar producto
    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM tbl_productos WHERE id_producto = ?";
        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
