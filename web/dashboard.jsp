<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Obtener el usuario de la sesión
    modelo.Usuario usuario = (modelo.Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Dashboard Mejorado - VetCare</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            /* Banner de bienvenida */
            .welcome-banner {
                background: linear-gradient(rgba(0,0,0,0.4), rgba(0,0,0,0.4)),
                    url('https://cdn.pixabay.com/photo/2017/11/09/21/41/cat-2934720_1280.jpg') center/cover no-repeat;
                border-radius: 20px;
                padding: 80px 20px;
                color: white;
                text-shadow: 2px 2px 6px rgba(0,0,0,0.7);
                transition: transform 0.3s ease;
            }
            .welcome-banner:hover {
                transform: scale(1.02);
            }

            /* Cards de accesos rápidos */
            .access-card {
                transition: transform 0.3s, box-shadow 0.3s;
                border-radius: 20px;
            }
            .access-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 10px 20px rgba(0,0,0,0.2);
            }
            .access-card img {
                border-radius: 20px 20px 0 0;
                height: 180px;
                object-fit: cover;
            }
            .access-card .card-body h5 {
                font-weight: 600;
            }
        </style>
    </head>
    <body class="bg-light">

        <!-- Header y menú -->
        <jsp:include page="includes/header.jsp"/>
        <jsp:include page="includes/menu.jsp"/>

        <!-- Banner de bienvenida -->
        <div class="container my-5">
            <div class="welcome-banner text-center">
                <h1>🐾 Hola, <%= usuario.getNombre()%>!</h1>
                <p class="lead">Bienvenido a VetCare Santa Marta. Administra tus clientes, mascotas y servicios de manera rápida y segura.</p>
            </div>
        </div>

        <!-- Accesos rápidos -->
        <div class="container my-5">
            <div class="row g-4">


                <!-- Bloque de Productos y Mascotas Disponibles -->
                <div class="container my-5">
                    <h3 class="mb-4 text-center text-secondary">📦 Disponibles</h3>
                    <div class="row g-4">

                        <!-- Productos Disponibles -->
                        <div class="col-md-6">
                            <div class="card access-card shadow-sm h-100">
                                <img src="https://cdn.pixabay.com/photo/2016/03/05/19/02/dog-1238256_1280.jpg" class="card-img-top" alt="Productos">
                                <div class="card-body text-center">
                                    <h5 class="text-info"><i class="bi bi-basket3"></i> Productos</h5>
                                    <p class="text-muted">Revisa los productos disponibles para la venta, como alimentos, accesorios y medicamentos.</p>
                                    <a href="vistas/productos/listar.jsp" class="btn btn-outline-info w-100">Ver Productos</a>
                                </div>
                            </div>
                        </div>

                        <!-- Mascotas Disponibles -->
                        <div class="col-md-6">
                            <div class="card access-card shadow-sm h-100">
                                <img src="https://cdn.pixabay.com/photo/2017/09/25/13/12/dog-2785074_1280.jpg" class="card-img-top" alt="Mascotas">
                                <div class="card-body text-center">
                                    <h5 class="text-warning"><i class="bi bi-paw"></i> Mascotas</h5>
                                    <p class="text-muted">Consulta las mascotas registradas y su información básica: especie, edad y dueño.</p>
                                    <a href="vistas/mascotas/listar.jsp" class="btn btn-outline-warning w-100">Ver Mascotas</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <!-- Footer -->
                <jsp:include page="includes/footer.jsp"/>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
                </body>
                </html>
