package controlador;

import dao.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import modelo.Cliente;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ClienteDAO dao = new ClienteDAO();

            String editarId = req.getParameter("editar");
            if (editarId != null) {
                int id = Integer.parseInt(editarId);
                Cliente c = dao.findById(id);
                req.setAttribute("cliente", c);
                req.getRequestDispatcher("/vistas/clientes/form.jsp").forward(req, resp);
                return;
            }

            List<Cliente> lista = dao.listar();
            req.setAttribute("clientes", lista);
            req.getRequestDispatcher("/vistas/clientes/list.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            ClienteDAO dao = new ClienteDAO();

            if ("crear".equals(action)) {
                Cliente c = new Cliente();
                c.setCedula_cliente(req.getParameter("cedula"));
                c.setNombre(req.getParameter("nombre"));
                c.setApellido(req.getParameter("apellido"));
                c.setDireccion(req.getParameter("direccion"));
                c.setTelefono(req.getParameter("telefono"));
                dao.crear(c);

            } else if ("actualizar".equals(action)) {
                Cliente c = new Cliente();
                c.setId_cliente(Integer.parseInt(req.getParameter("id")));
                c.setCedula_cliente(req.getParameter("cedula"));
                c.setNombre(req.getParameter("nombre"));
                c.setApellido(req.getParameter("apellido"));
                c.setDireccion(req.getParameter("direccion"));
                c.setTelefono(req.getParameter("telefono"));
                dao.actualizar(c);

            } else if ("eliminar".equals(action)) {
                dao.eliminar(Integer.parseInt(req.getParameter("id")));
            }

            // Redirigir al servlet de lista de clientes
            resp.sendRedirect(req.getContextPath() + "/clientes");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
