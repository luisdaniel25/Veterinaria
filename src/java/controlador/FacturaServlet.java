package controlador;

import dao.VentaDAO;
import modelo.Productos;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.ClienteDAO;
import dao.MetodoPagoDAO;
import dao.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.Conexion;
import modelo.DetallesVentas;

@WebServlet("/factura")
public class FacturaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            ProductoDAO pdao = new ProductoDAO();
            ClienteDAO cdao = new ClienteDAO();
            MetodoPagoDAO mdao = new MetodoPagoDAO();
            req.setAttribute("productos", pdao.listar());
            req.setAttribute("clientes", cdao.listar());
            req.setAttribute("metodos", mdao.listar());
            req.getRequestDispatcher("/jsp/factura/create.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error en doGet: " + e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String clienteId = req.getParameter("clienteId");
            String metodoId = req.getParameter("metodoId");
            String[] prodIds = req.getParameterValues("prodId");
            String[] qtys = req.getParameterValues("qty");
            String[] prices = req.getParameterValues("price");

            List<DetallesVentas> detalles = new ArrayList<>();
            if (prodIds != null && qtys != null && prices != null) {
                for (int i = 0; i < prodIds.length; i++) {
                    int pid = Integer.parseInt(prodIds[i]);
                    int q = Integer.parseInt(qtys[i]);
                    BigDecimal p = new BigDecimal(prices[i]);
                    detalles.add(new DetallesVentas(pid, q, p));
                }
            }

            VentaDAO vdao = new VentaDAO();
            int idFactura = vdao.crearVentaConFactura(
                    Integer.parseInt(clienteId),
                    detalles,
                    Integer.parseInt(metodoId)
            );

            // PDF automático
            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition",
                    "attachment; filename=Factura_" + idFactura + ".pdf");

            OutputStream out = resp.getOutputStream();
            Document document = new Document();

            PdfWriter.getInstance(document, out);
            document.open();

            try (Connection c = Conexion.conectarBD()) {
                PreparedStatement ps = c.prepareStatement(
                        "SELECT f.id_factura, f.subtotal, f.impuesto, f.total, f.fecha, "
                        + "cl.nombre, cl.apellido, m.nombre as metodo, v.id_venta "
                        + "FROM tbl_facturas f "
                        + "JOIN tbl_ventas v ON f.id_venta = v.id_venta "
                        + "JOIN tbl_clientes cl ON v.id_cliente = cl.id_cliente "
                        + "JOIN tbl_metodos_pago m ON f.id_metodo = m.id_metodo "
                        + "WHERE f.id_factura = ?"
                );
                ps.setInt(1, idFactura);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    document.add(new Paragraph("Factura ID: " + rs.getInt("id_factura")));
                    document.add(new Paragraph("Cliente: " + rs.getString("nombre") + " " + rs.getString("apellido")));
                    document.add(new Paragraph("Método de Pago: " + rs.getString("metodo")));
                    document.add(new Paragraph("Fecha: " + rs.getDate("fecha")));
                    document.add(new Paragraph(" "));

                    // Tabla de productos
                    PdfPTable table = new PdfPTable(4);
                    table.setWidthPercentage(100);
                    table.addCell("Producto");
                    table.addCell("Cantidad");
                    table.addCell("Precio Unit.");
                    table.addCell("Subtotal");

                    int idVenta = rs.getInt("id_venta");
                    PreparedStatement pd = c.prepareStatement(
                            "SELECT d.id_producto, p.nombre, d.cantidad, d.precio "
                            + "FROM tbl_detallesventa d "
                            + "JOIN tbl_productos p ON d.id_producto = p.id_producto "
                            + "WHERE d.id_venta = ?"
                    );
                    pd.setInt(1, idVenta);
                    ResultSet rd = pd.executeQuery();

                    while (rd.next()) {
                        table.addCell(rd.getString("nombre"));
                        int q = rd.getInt("cantidad");
                        table.addCell(String.valueOf(q));
                        BigDecimal price = rd.getBigDecimal("precio");
                        table.addCell(price.toString());
                        BigDecimal sub = price.multiply(new BigDecimal(q));
                        table.addCell(sub.toString());
                    }
                    document.add(table);

                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("Subtotal: " + rs.getBigDecimal("subtotal")));
                    document.add(new Paragraph("Impuesto: " + rs.getBigDecimal("impuesto")));
                    document.add(new Paragraph("Total: " + rs.getBigDecimal("total")));
                } else {
                    document.add(new Paragraph("Factura no encontrada."));
                }
            }

            document.close();
            out.flush();
        } catch (Exception e) {
            throw new ServletException("Error en doPost: " + e.getMessage(), e);
        }
    }
}
