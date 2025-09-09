package dao;

import modelo.ResetToken;
import util.Conexion;

import java.sql.*;
import java.util.Date;

public class TokenDAO {

    public boolean crearToken(ResetToken t) {
        String sql = "INSERT INTO reset_tokens(id_usuario, token, expiracion) VALUES(?,?,?)";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, t.getId_usuario());
            ps.setString(2, t.getToken());
            ps.setTimestamp(3, new Timestamp(t.getExpiracion().getTime()));
            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        t.setId_tokens(keys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResetToken buscarPorToken(String token) {
        String sql = "SELECT * FROM reset_tokens WHERE token = ?";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ResetToken t = new ResetToken();
                    t.setId_tokens(rs.getInt("id_tokens"));
                    t.setId_usuario(rs.getInt("id_usuario"));
                    t.setToken(rs.getString("token"));
                    t.setExpiracion(new Date(rs.getTimestamp("expiracion").getTime()));
                    return t;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminarPorId(int id_tokens) {
        String sql = "DELETE FROM reset_tokens WHERE id_tokens = ?";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id_tokens);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void eliminarTokensExpirados() {
        String sql = "DELETE FROM reset_tokens WHERE expiracion < NOW()";
        try (Connection con = Conexion.conectarBD(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
