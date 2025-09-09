package controlador;

import dao.TokenDAO;
import dao.UsuarioDAO;
import modelo.ResetToken;
import modelo.Usuario;
import util.PasswordUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.Date;

@WebServlet("/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        String nueva = request.getParameter("password");
        String confirm = request.getParameter("password2");

        // Validar coincidencia de contraseñas
        if (nueva == null || confirm == null || !nueva.equals(confirm)) {
            response.sendRedirect("nuevaPassword.jsp?token=" + token + "&error=nomatch");
            return;
        }

        TokenDAO tdao = new TokenDAO();
        ResetToken rt = tdao.buscarPorToken(token);
        if (rt == null || rt.getExpiracion().before(new Date())) {
            System.out.println("error" + rt);
            response.sendRedirect("login.jsp?error=tokenexp");
            return;
        }

        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = udao.buscarPorId(rt.getId_usuario());
        if (u == null) {
            System.out.println("error" + u);
            response.sendRedirect("login.jsp?error=usuario");
            return;
        }

        // Guardar contraseña hasheada
        String hashed = PasswordUtil.hash(nueva);
        u.setContrasena(hashed);
        udao.actualizar(u);

        // Invalidar token
        tdao.eliminarPorId(rt.getId_tokens());

        response.sendRedirect("login.jsp?msg=restablecido");
    }
}
