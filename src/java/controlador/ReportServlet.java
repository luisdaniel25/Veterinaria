package controlador;

import dao.ClienteDAO;
import dao.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;
import modelo.Cliente;
import modelo.Productos;

@WebServlet("/reportes")
public class ReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo"); // productos | clientes
        try {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/csv");
            resp.setHeader("Content-Disposition", "attachment; filename=export_" + (tipo == null ? "todos" : tipo) + ".csv");
            PrintWriter out = resp.getWriter();
            if ("productos".equals(tipo)) {
                ProductoDAO dao = new ProductoDAO();
                List<Productos> lista = dao.listar();
                out.println("id_producto,codigo_barras,nombre,precio,stock");
                for (Productos p : lista) {
                    out.println(p.getId_producto() + "," + p.getCodigo_barras() + "," + p.getNombre() + "," + p.getPrecio() + "," + p.getId_proveedor());
                }
            } else {
                ClienteDAO dao = new ClienteDAO();
                List<Cliente> lista = dao.listar();
                out.println("id_cliente,cedula_cliente,nombre,apellido,telefono");
                for (Cliente c : lista) {
                    out.println(c.getId_cliente() + "," + c.getCedula_cliente() + "," + c.getNombre() + "," + c.getApellido() + "," + c.getTelefono());
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
