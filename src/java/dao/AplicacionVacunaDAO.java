package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.sql.*;
import modelo.AplicacionVacuna;
import static modelo.Conexion.conectarBD;

public class AplicacionVacunaDAO {

    AplicacionVacuna aplicacion = new AplicacionVacuna();

    public List<AplicacionVacuna> listar() {
        List<AplicacionVacuna> lista = new ArrayList<>();
        String sql = "SELECT * FROM aplicacion_vacuna";

        try (Connection conn = conectarBD(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AplicacionVacuna a = new AplicacionVacuna(
                        rs.getInt("id_aplicacionVacuna"),
                        rs.getInt("id_mascota"),
                        rs.getInt("id_vacuna"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getString("via_aplicacion"),
                        rs.getString("observaciones")
                );
                lista.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void insertar(AplicacionVacuna a) {
        String sql = "INSERT INTO aplicacion_vacuna (id_mascota, id_vacuna, fecha, via_aplicacion, observaciones) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conectarBD(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, a.getId_mascota());
            ps.setInt(2, a.getId_vacuna());
            ps.setDate(3, Date.valueOf(a.getFecha()));
            ps.setString(4, a.getVia_aplicacion());
            ps.setString(5, a.getObservaciones());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM aplicacion_vacuna WHERE id_aplicacionVacuna = ?";

        try (Connection conn = conectarBD(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
