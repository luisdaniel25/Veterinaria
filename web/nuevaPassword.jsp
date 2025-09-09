<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String token = request.getAttribute("token") != null
            ? (String) request.getAttribute("token")
            : request.getParameter("token");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Nueva Contraseña</title>
        <style>
            body {
                font-family: 'Segoe UI', Arial, sans-serif;
                background: linear-gradient(135deg, #ff9966, #ff5e62);
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .reset-box {
                background: #fff;
                padding: 30px;
                border-radius: 15px;
                box-shadow: 0 8px 25px rgba(0,0,0,0.25);
                width: 350px;
                text-align: center;
                animation: fadeIn 1s ease-in-out;
            }
            .reset-box h2 {
                margin-bottom: 20px;
                color: #ff5e62;
            }
            .reset-box input {
                width: 90%;
                padding: 12px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 8px;
                outline: none;
                font-size: 14px;
            }
            .reset-box input:focus {
                border-color: #ff5e62;
                box-shadow: 0 0 5px rgba(255,94,98,0.5);
            }
            .reset-box button {
                width: 95%;
                padding: 12px;
                background: #ff5e62;
                color: white;
                border: none;
                border-radius: 8px;
                font-size: 16px;
                font-weight: bold;
                cursor: pointer;
                margin-top: 15px;
                transition: background 0.3s;
            }
            .reset-box button:hover {
                background: #e0484d;
            }
            .message {
                margin-top: 15px;
                font-size: 14px;
                font-weight: bold;
            }
            .error {
                color: #e63946;
            }
            @keyframes fadeIn {
                from {
                    opacity:0;
                    transform: scale(0.9);
                }
                to {
                    opacity:1;
                    transform: scale(1);
                }
            }
        </style>
    </head>
    <body>
        <div class="reset-box">
            <h2>🔑 Nueva contraseña</h2>
            <form action="UpdatePasswordServlet" method="post">
                <input type="hidden" name="token" value="<%= token%>">
                <input type="password" name="password" placeholder="Escribe tu nueva contraseña" required>
                <input type="password" name="password2" placeholder="Confirma tu nueva contraseña" required>
                <button type="submit">Actualizar Contraseña</button>
            </form>

            <% if ("token".equals(request.getParameter("error"))) { %>
            <p class="message error">❌ El enlace no es válido o ya expiró</p>
            <% } else if ("nomatch".equals(request.getParameter("error"))) { %>
            <p class="message error">❌ Las contraseñas no coinciden</p>
            <% }%>
        </div>
    </body>
</html>
