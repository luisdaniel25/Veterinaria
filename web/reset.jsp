<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Restablecer Contraseña</title>
        <style>
            body {
                font-family: 'Segoe UI', Arial, sans-serif;
                background: linear-gradient(135deg, #11998e, #38ef7d);
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
                width: 360px;
                text-align: center;
                animation: fadeIn 1s ease-in-out;
            }
            .reset-box h2 {
                margin-bottom: 20px;
                color: #11998e;
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
                border-color: #11998e;
                box-shadow: 0 0 5px rgba(17,153,142,0.5);
            }
            .reset-box button {
                width: 95%;
                padding: 12px;
                background: #11998e;
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
                background: #0f7e71;
            }
            .message {
                margin-top: 15px;
                font-size: 14px;
            }
            .error {
                color: #e63946;
                font-weight: bold;
            }
            .success {
                color: #2a9d8f;
                font-weight: bold;
            }
            .test-link {
                margin-top: 10px;
                font-size: 13px;
                background: #f1f1f1;
                padding: 8px;
                border-radius: 6px;
            }
            .test-link a {
                color: #0072ff;
                text-decoration: none;
                font-weight: bold;
            }
            .test-link a:hover {
                text-decoration: underline;
            }
            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: scale(0.9);
                }
                to {
                    opacity: 1;
                    transform: scale(1);
                }
            }
        </style>
    </head>
    <body>
        <div class="reset-box">
            <h2>🔒 Restablecer contraseña</h2>
            <form action="ResetRequestServlet" method="post">
                <input type="email" name="correo" placeholder="Ingresa tu correo" required>
                <button type="submit">Enviar enlace</button>
            </form>

            <% if ("noexist".equals(request.getParameter("msg"))) { %>
            <p class="message error">❌ Correo no existe.</p>
            <% } else if ("enviado".equals(request.getParameter("msg"))) { %>
            <p class="message success">✅ Enlace enviado. Revisa tu correo.</p>
            <% String link = request.getParameter("link");
            if (link != null) {%>
            <div class="test-link">
                Enlace de prueba:
                <a href="<%= java.net.URLDecoder.decode(link, "UTF-8")%>">Abrir</a>
            </div>
            <% } %>
            <% }%>
        </div>
    </body>
</html>
