package controlador;

import modelo.Productos;
import dao.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ProductoDAO dao = new ProductoDAO();
            List<Productos> lista = dao.listar();
            req.setAttribute("productos", lista);
            req.getRequestDispatcher("/jsp/productos/list.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action"); // crear, actualizar, eliminar
            ProductoDAO dao = new ProductoDAO();
            if ("crear".equals(action)) {
                Productos p = new Productos();
                p.setCodigo_barras(req.getParameter("codigo"));
                p.setNombre(req.getParameter("nombre"));
                p.setPrecio(new BigDecimal(req.getParameter("precio")));
                p.setId_proveedor(Integer.parseInt(req.getParameter("id_provedoor")));
                dao.crear(p);
            } else if ("actualizar".equals(action)) {
                Productos p = new Productos();
                p.setId_producto(Integer.parseInt(req.getParameter("id")));
                p.setCodigo_barras(req.getParameter("codigo"));
                p.setNombre(req.getParameter("nombre"));
                p.setPrecio(new BigDecimal(req.getParameter("precio")));
                p.setId_proveedor(Integer.parseInt(req.getParameter("stock")));
                dao.actualizar(p);
            } else if ("eliminar".equals(action)) {
                dao.eliminar(Integer.parseInt(req.getParameter("id")));
            }
            resp.sendRedirect(req.getContextPath() + "/productos");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
