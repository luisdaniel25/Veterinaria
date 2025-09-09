package dao;

import java.sql.*;
import java.util.*;
import modelo.Bitacora;
import util.Conexion;
import modelo.TipoOperacion;

public class BitacoraDAO {

    // Método para insertar un registro en la bitácora
    public void insertar(Bitacora bitacora) {
        String sql = "INSERT INTO tbl_bitacora(accion, tabla_afectada, id_referencia, detalle, id_usuario, tipo_operacion, fecha_hora, modulo, estado_previos, estado_nuevo, ip_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bitacora.getAccion());
            ps.setString(2, bitacora.getTabla_afectada());
            ps.setString(3, bitacora.getId_referencia());
            ps.setString(4, bitacora.getDetalle());
            ps.setInt(5, bitacora.getId_usuario());
            ps.setString(6, bitacora.getTipo_operacion().name()); // Enum -> String
            ps.setTimestamp(7, Timestamp.valueOf(bitacora.getFecha_hora())); // LocalDateTime -> Timestamp
            ps.setString(8, bitacora.getModulo());
            ps.setString(9, bitacora.getEstado_previos());
            ps.setString(10, bitacora.getEstado_nuevo());
            ps.setString(11, bitacora.getIp_usuario());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos los registros
    public List<Bitacora> listar() {
        List<Bitacora> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_bitacora ORDER BY fecha_hora DESC";

        try (Connection con = Conexion.conectarBD(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Bitacora bitacora = new Bitacora();
                bitacora.setId_log(rs.getInt("id_log"));
                bitacora.setAccion(rs.getString("accion"));
                bitacora.setTabla_afectada(rs.getString("tabla_afectada"));
                bitacora.setId_referencia(rs.getString("id_referencia"));
                bitacora.setDetalle(rs.getString("detalle"));
                bitacora.setId_usuario(rs.getInt("id_usuario"));
                bitacora.setTipo_operacion(TipoOperacion.valueOf(rs.getString("tipo_operacion")));
                bitacora.setFecha_hora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                bitacora.setModulo(rs.getString("modulo"));
                bitacora.setEstado_previos(rs.getString("estado_previos"));
                bitacora.setEstado_nuevo(rs.getString("estado_nuevo"));
                bitacora.setIp_usuario(rs.getString("ip_usuario"));

                lista.add(bitacora);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
