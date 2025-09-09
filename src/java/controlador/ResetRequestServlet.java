package controlador;

import dao.TokenDAO;
import dao.UsuarioDAO;
import modelo.ResetToken;
import modelo.Usuario;
import util.EnviarCorreo;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@WebServlet("/ResetRequestServlet")
public class ResetRequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        UsuarioDAO udao = new UsuarioDAO();
        Usuario usuario = udao.buscarPorCorreo(correo);

        if (usuario == null) {
            response.sendRedirect("reset.jsp?msg=noexist");
            return;
        }

        String token = UUID.randomUUID().toString();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 2);
        Date expiracion = cal.getTime();

        ResetToken rt = new ResetToken(usuario.getId_usuario(), token, expiracion);
        TokenDAO tdao = new TokenDAO();
        tdao.crearToken(rt);

        String link = request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath()
                + "/ResetFormServlet?token=" + token;

        String asunto = "Recuperación de contraseña";
        String cuerpo = "Hola " + usuario.getNombre() + ",\n\n"
                + "Has solicitado restablecer tu contraseña.\n"
                + "Haz clic en el siguiente enlace para crear una nueva contraseña:\n"
                + link + "\n\n"
                + "Este enlace expirará en 2 horas.\n"
                + "Si no solicitaste este cambio, ignora este mensaje.";

        try {
            EnviarCorreo.enviar(usuario.getCorreo(), asunto, cuerpo);
            response.sendRedirect("reset.jsp?msg=enviado");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("reset.jsp?msg=enviado&link="
                    + java.net.URLEncoder.encode(link, "UTF-8"));
        }
    }
}
