package controlador;

import dao.TokenDAO;
import modelo.ResetToken;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.Date;

@WebServlet("/ResetFormServlet")
public class ResetFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("token");
        if (token == null || token.isEmpty()) {
            response.sendRedirect("login.jsp?error=token");
            return;
        }

        TokenDAO tdao = new TokenDAO();
        ResetToken rt = tdao.buscarPorToken(token);
        if (rt == null || rt.getExpiracion().before(new Date())) {
            response.sendRedirect("login.jsp?error=tokenexp");
            return;
        }

        request.setAttribute("token", token);
        request.getRequestDispatcher("nuevaPassword.jsp").forward(request, response);
    }
}
