package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;
import modelo.Citas;
import modelo.Estado;

public class CitasDAO {

    // Método para listar todas las citas
    public List<Citas> listar() {
        List<Citas> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_citas";

        try (Connection con = Conexion.conectarBD(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Citas cita = new Citas();
                cita.setId_cita(rs.getInt("id_cita"));
                cita.setId_mascota(rs.getInt("id_mascota"));
                cita.setFecha(rs.getDate("fecha").toLocalDate());
                cita.setHora(rs.getTime("hora").toLocalTime());
                cita.setMotivo(rs.getString("motivo"));
                cita.setEstado(Estado.valueOf(rs.getString("estado").toUpperCase()));

                lista.add(cita);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para insertar una cita
    public void insertar(Citas cita) {
        String sql = "INSERT INTO tbl_citas (id_mascota, fecha, hora, motivo, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cita.getId_mascota());
            ps.setDate(2, java.sql.Date.valueOf(cita.getFecha())); // LocalDate -> java.sql.Date
            ps.setTime(3, java.sql.Time.valueOf(cita.getHora()));  // LocalTime -> java.sql.Time
            ps.setString(4, cita.getMotivo());
            ps.setString(5, cita.getEstado().name().toLowerCase()); // Enum -> String en minúsculas

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar una cita existente
    public void actualizar(Citas cita) {
        String sql = "UPDATE tbl_citas SET id_mascota=?, fecha=?, hora=?, motivo=?, estado=? WHERE id_cita=?";

        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cita.getId_mascota());
            ps.setDate(2, java.sql.Date.valueOf(cita.getFecha()));
            ps.setTime(3, java.sql.Time.valueOf(cita.getHora()));
            ps.setString(4, cita.getMotivo());
            ps.setString(5, cita.getEstado().name().toLowerCase());
            ps.setInt(6, cita.getId_cita());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar una cita por ID
    public void eliminar(int id) {
        String sql = "DELETE FROM tbl_citas WHERE id_cita=?";

        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una cita por su ID
    public Citas obtenerPorId(int id) {
        String sql = "SELECT * FROM tbl_citas WHERE id_cita=?";
        Citas cita = null;

        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cita = new Citas();
                    cita.setId_cita(rs.getInt("id_cita"));
                    cita.setId_mascota(rs.getInt("id_mascota"));
                    cita.setFecha(rs.getDate("fecha").toLocalDate());
                    cita.setHora(rs.getTime("hora").toLocalTime());
                    cita.setMotivo(rs.getString("motivo"));
                    cita.setEstado(Estado.valueOf(rs.getString("estado").toUpperCase()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cita;
    }

}
