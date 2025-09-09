package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Mascotas;
import util.Conexion;

public class MascotasDAO {

    public List<Mascotas> listarPorCliente(int id_cliente) throws SQLException {
        List<Mascotas> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_mascotas WHERE id_cliente=?";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_cliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mascotas mascota = new Mascotas();
                mascota.setId_mascota(rs.getInt("id_mascota"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setId_cliente(rs.getInt("id_cliente"));
                lista.add(mascota);
            }
        }
        return lista;
    }
}
