package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
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
            return;
        }

        if (accion.equals("login")) {
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");
            Usuario u = dao.validar(correo, contrasena);
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
            nuevo.setContrasena(request.getParameter("contrasena"));
            try {
                nuevo.setRol(Integer.parseInt(request.getParameter("rol")));
            } catch (NumberFormatException ex) {
                nuevo.setRol(3); // por defecto cliente si falla
            }

            String idEspecialidadStr = request.getParameter("id_especialidad");
            if (idEspecialidadStr != null && !idEspecialidadStr.trim().isEmpty()) {
                try {
                    nuevo.setId_especialidad(Integer.parseInt(idEspecialidadStr));
                } catch (NumberFormatException ex) {
                    nuevo.setId_especialidad(0);
                }
            } else {
                nuevo.setId_especialidad(0);
            }

            dao.agregar(nuevo);
            response.sendRedirect(request.getContextPath() + "/vistas/usuarios/listar.jsp");
        } else if (accion.equals("actualizar")) {
            Usuario upd = new Usuario();
            try {
                upd.setId_usuario(Integer.parseInt(request.getParameter("id")));
            } catch (NumberFormatException ex) {
                upd.setId_usuario(0);
            }
            upd.setNombre(request.getParameter("nombre"));
            upd.setCorreo(request.getParameter("correo"));
            upd.setContrasena(request.getParameter("clave"));
            try {
                upd.setRol(Integer.parseInt(request.getParameter("rol")));
            } catch (NumberFormatException ex) {
                upd.setRol(3);
            }

            String idEspecialidadStr = request.getParameter("id_especialidad");
            if (idEspecialidadStr != null && !idEspecialidadStr.trim().isEmpty()) {
                try {
                    upd.setId_especialidad(Integer.parseInt(idEspecialidadStr));
                } catch (NumberFormatException ex) {
                    upd.setId_especialidad(0);
                }
            } else {
                upd.setId_especialidad(0);
            }

            dao.actualizar(upd);
            response.sendRedirect(request.getContextPath() + "/vistas/usuarios/listar.jsp");
        } else if (accion.equals("eliminar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(id);
            } catch (NumberFormatException ex) {
                // opcional: setAttribute error
            }
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
