package dao;

import util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.MetodosPago;

public class MetodoPagoDAO {

    public List<MetodosPago> listar() throws Exception {
        List<MetodosPago> lista = new ArrayList<>();
        String sql = "SELECT id_metodo, nombre, descripcion FROM tbl_metodos_pago";

        try (Connection c = Conexion.conectarBD(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MetodosPago m = new MetodosPago();
                m.setId_metodo(rs.getInt("id_metodo"));
                m.setNombre(rs.getString("nombre"));
                m.setDescripcion(rs.getString("descripcion")); // 👈 opcional si lo usas
                lista.add(m);
            }
        }
        return lista;
    }
}
