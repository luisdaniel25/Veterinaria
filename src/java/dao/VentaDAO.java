package dao;

import modelo.DetallesVentas;
import util.Conexion;
import java.sql.*;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class VentaDAO {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.19");

    public int crearVentaConFactura(int id_cliente, List<DetallesVentas> detalles, int id_metodo) throws Exception {
        String insertVentaSQL = "INSERT INTO tbl_ventas (id_cliente, fecha) VALUES (?, CURDATE())";
        String insertDetalleSQL = "INSERT INTO tbl_detallesventa (id_venta, id_producto, cantidad, precio) VALUES (?, ?, ?, ?)";
        String insertFacturaSQL = "INSERT INTO tbl_facturas (id_venta, id_metodo, subtotal, impuesto, total, fecha) VALUES (?, ?, ?, ?, ?, CURDATE())";

        try (Connection conn = Conexion.conectarBD()) {
            conn.setAutoCommit(false);

            int idVenta;
            try (PreparedStatement psVenta = conn.prepareStatement(insertVentaSQL, Statement.RETURN_GENERATED_KEYS)) {
                psVenta.setInt(1, id_cliente);
                psVenta.executeUpdate();
                try (ResultSet rsKeys = psVenta.getGeneratedKeys()) {
                    if (!rsKeys.next()) {
                        throw new SQLException("No se obtuvo id_venta");
                    }
                    idVenta = rsKeys.getInt(1);
                }
            }

            BigDecimal subtotal = BigDecimal.ZERO;
            try (PreparedStatement psDetalle = conn.prepareStatement(insertDetalleSQL)) {
                for (DetallesVentas d : detalles) {
                    psDetalle.setInt(1, idVenta);
                    psDetalle.setInt(2, d.getId_producto());
                    psDetalle.setInt(3, d.getCantidad());
                    psDetalle.setBigDecimal(4, d.getPrecio());
                    psDetalle.executeUpdate();

                    subtotal = subtotal.add(d.getPrecio().multiply(new BigDecimal(d.getCantidad())));

                    // Restar stock
                    try (PreparedStatement psStock = conn.prepareStatement("UPDATE tbl_productos SET stock = stock - ? WHERE id_producto = ?")) {
                        psStock.setInt(1, d.getCantidad());
                        psStock.setInt(2, d.getId_producto());
                        psStock.executeUpdate();
                    }
                }
            }

            BigDecimal impuesto = subtotal.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
            BigDecimal total = subtotal.add(impuesto);

            int idFactura;
            try (PreparedStatement psFactura = conn.prepareStatement(insertFacturaSQL, Statement.RETURN_GENERATED_KEYS)) {
                psFactura.setInt(1, idVenta);
                psFactura.setInt(2, id_metodo);
                psFactura.setBigDecimal(3, subtotal);
                psFactura.setBigDecimal(4, impuesto);
                psFactura.setBigDecimal(5, total);
                psFactura.executeUpdate();
                try (ResultSet rsF = psFactura.getGeneratedKeys()) {
                    if (!rsF.next()) {
                        throw new SQLException("No se obtuvo id_factura");
                    }
                    idFactura = rsF.getInt(1);
                }
            }

            conn.commit();
            return idFactura;

        } catch (Exception ex) {
            throw ex;
        }
    }
}
