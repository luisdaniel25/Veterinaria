<%@page contentType="text/html;charset=UTF-8" import="java.sql.*, app.util.Conexion, java.math.BigDecimal"%>
<html><head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-..." crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-..." crossorigin="anonymous"></script>
<title>Factura</title></head><body>
<jsp:include page="/jsp/includes/header.jsp" />

<h3>Factura - Imprimir</h3>
<% 
  String id = request.getParameter("id"); 
  if (id != null) {
    try (Connection c = app.util.Conexion.getConnection()) {
        PreparedStatement ps = c.prepareStatement("SELECT f.id_factura, f.subtotal, f.impuesto, f.total, f.fecha, cl.nombre, cl.apellido, m.nombre as metodo FROM tbl_facturas f JOIN tbl_ventas v ON f.id_venta = v.id_venta JOIN tbl_clientes cl ON v.id_cliente = cl.id_cliente JOIN tbl_metodos_pago m ON f.id_metodo = m.id_metodo WHERE f.id_factura = ?");
        ps.setInt(1, Integer.parseInt(id));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            out.println("<b>ID Factura:</b> " + rs.getInt("id_factura") + "<br/>"); 
            out.println("<b>Cliente:</b> " + rs.getString("nombre") + " " + rs.getString("apellido") + "<br/>"); 
            out.println("<b>Metodo:</b> " + rs.getString("metodo") + "<br/>"); 
            out.println("<b>Fecha:</b> " + rs.getDate("fecha") + "<br/>"); 
            out.println("<hr/>");
            out.println("<b>Subtotal:</b> " + rs.getBigDecimal("subtotal") + "<br/>"); 
            out.println("<b>Impuesto:</b> " + rs.getBigDecimal("impuesto") + "<br/>"); 
            out.println("<b>Total:</b> " + rs.getBigDecimal("total") + "<br/>"); 
            // listar detalles
            PreparedStatement pd = c.prepareStatement("SELECT d.id_producto, p.nombre, d.cantidad, d.precio FROM tbl_detallesventa d JOIN tbl_productos p ON d.id_producto = p.id_producto WHERE d.id_venta = (SELECT id_venta FROM tbl_facturas WHERE id_factura = ?)"); 
            pd.setInt(1, Integer.parseInt(id));
            ResultSet rd = pd.executeQuery();
            out.println("<h4>Detalles</h4><table border='1'><tr><th>Producto</th><th>Cantidad</th><th>Precio</th></tr>"); 
            while (rd.next()) {
                out.println("<tr><td>"+rd.getString("nombre")+"</td><td>"+rd.getInt("cantidad")+"</td><td>"+rd.getBigDecimal("precio")+"</td></tr>");
            }
            out.println("</table><hr/>"); 
            out.println("<button onclick='window.print()'>Imprimir / Guardar como PDF</button>");
        } else {
            out.println("Factura no encontrada.");
        }
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    }
  } else {
    out.println("ID de factura faltante.");
  }
%>
<br/><a href="<%=request.getContextPath()%>/">Volver</a>
</body></html>
