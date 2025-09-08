package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Conexion;
import static modelo.Conexion.conectarBD;

import modelo.HistorialMedico;

public class HistorialMedicoDAO {

    public List<HistorialMedico> listar() {
        List<HistorialMedico> lista = new ArrayList<>();
        String sentencia = "SELECT * FROM tbl_historialmedico";

        try (Connection conn = conectarBD(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sentencia)) {

            while (rs.next()) {
                HistorialMedico historial = new HistorialMedico(
                        rs.getInt("id_historial"),
                        rs.getInt("id_mascota"),
                        rs.getDate("fecha"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento"),
                        rs.getString("observaciones")
                );
                lista.add(historial);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void insertar(HistorialMedico historial) {
        String sentencia = "INSERT INTO tbl_historialmedico (id_mascota, fecha, diagnostico, tratamiento, observaciones) VALUES (?,?,?,?,?)";

        try {
            Connection conn = conectarBD();
            PreparedStatement ps = conn.prepareStatement(sentencia);

            ps.setInt(1, historial.getId_mascota());
            ps.setDate(2, new java.sql.Date(historial.getFecha().getTime()));
            ps.setString(3, historial.getDiagnostico());
            ps.setString(4, historial.getTratamiento());
            ps.setString(5, historial.getObservaciones());

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(HistorialMedico historial) {

        String sentencia = "UPDATE tbl_historialmedico SET id_mascota = ?, fecha = ?, diagnostico = ?, tratamiento = ?, observaciones = ? WHERE id_historial = ?";
        try {

            Connection conn = conectarBD();
            PreparedStatement ps = conn.prepareStatement(sentencia);

            ps.setInt(1, historial.getId_mascota());
            ps.setDate(2, new java.sql.Date(historial.getFecha().getTime()));
            ps.setString(3, historial.getDiagnostico());
            ps.setString(3, historial.getTratamiento());
            ps.setString(3, historial.getObservaciones());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(HistorialMedico historial) {
        String sentencia = "DELETE FROM tbl_historialmedico WHERE id_historial = ?";

        try {
            Connection conn = conectarBD();
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ps.setInt(1, historial.getId_historial());

            ps.executeUpdate();

            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
