<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Revisar si el usuario ya inició sesión
    Object usuario = session.getAttribute("usuario");
    if (usuario != null) {
        // Redirigir al dashboard (o a donde quieras que vaya el usuario ya logueado)
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>VetCare Santa Marta</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container text-center py-5">
            <h1 class="fw-bold text-success">🐾 Bienvenido a VetCare Santa Marta</h1>
            <p class="text-muted">Sistema de gestión veterinaria - Santa Marta, Magdalena</p>

            <div class="mt-4">
                <a href="login.jsp" class="btn btn-success px-4">Iniciar Sesión</a>
                <a href="registro.jsp" class="btn btn-outline-success px-4">Registrarse</a>
            </div>
        </div>

        <footer class="text-center mt-5 text-muted">
            <small>&copy; 2025 VetCare Santa Marta - Todos los derechos reservados</small>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
