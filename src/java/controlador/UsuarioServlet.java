package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.PasswordUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        if (accion == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else if (accion.equals("login")) {
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");
            Usuario u = dao.login(correo, contrasena);

            if (u != null) {
                request.getSession().setAttribute("usuario", u);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                request.setAttribute("error", "Credenciales inválidas");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else if (accion.equals("agregar")) {
            Usuario nuevo = new Usuario();
            nuevo.setNombre(request.getParameter("nombre"));
            nuevo.setCorreo(request.getParameter("correo"));
            nuevo.setContrasena(PasswordUtil.hash(request.getParameter("contrasena")));

            dao.crear(nuevo);
            response.sendRedirect(request.getContextPath() + "/vistas/usuarios/listar.jsp");
        } else if (accion.equals("actualizar")) {
            Usuario upd = new Usuario();
            upd.setId_usuario(Integer.parseInt(request.getParameter("id")));
            upd.setNombre(request.getParameter("nombre"));
            upd.setCorreo(request.getParameter("correo"));

            String nuevaClave = request.getParameter("contrasena");
            if (nuevaClave != null && !nuevaClave.isEmpty()) {
                upd.setContrasena(PasswordUtil.hash(nuevaClave));
            }

            dao.actualizar(upd);
            response.sendRedirect(request.getContextPath() + "/vistas/usuarios/listar.jsp");
        } else if (accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect(request.getContextPath() + "/vistas/usuarios/listar.jsp");
        } else if (accion.equals("logout")) {
            HttpSession ses = request.getSession(false);
            if (ses != null) {
                ses.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
