<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Menú VetCare</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-success shadow-sm mb-4">
            <div class="container-fluid">
                <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/index.jsp">
                    <i class="bi bi-house-door-fill"></i> VetCare
                </a>

                <!-- Botón responsive -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                        data-bs-target="#menu" aria-controls="menu" aria-expanded="false">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Menú -->
                <div class="collapse navbar-collapse" id="menu">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                        <!-- Gestión -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="gestionDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-gear-fill"></i> Gestión
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="gestionDropdown">
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/clientes">
                                        <i class="bi bi-person-lines-fill"></i> Clientes
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/mascotas">
                                        <i class="bi bi-heart-pulse-fill"></i> Mascotas
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/productos">
                                        <i class="bi bi-bag-heart-fill"></i> Productos
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/proveedores">
                                        <i class="bi bi-truck"></i> Proveedores
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <!-- Servicios -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="serviciosDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-journal-medical"></i> Servicios
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="serviciosDropdown">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/citas"><i class="bi bi-calendar-check-fill"></i> Citas</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/historialmedico"><i class="bi bi-journal-medical"></i> Historial Médico</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/aplicacionvacuna"><i class="bi bi-droplet-fill"></i> Aplicación Vacuna</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/vacunas"><i class="bi bi-eyedropper"></i> Vacunas</a></li>
                            </ul>
                        </li>

                        <!-- Administración -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="adminDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-people-fill"></i> Administración
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="adminDropdown">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/usuarios"><i class="bi bi-people-fill"></i> Usuarios</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/roles"><i class="bi bi-person-badge-fill"></i> Roles</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/metodospago"><i class="bi bi-credit-card-2-front-fill"></i> Métodos de Pago</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/facturas"><i class="bi bi-file-earmark-text-fill"></i> Facturas</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ventas"><i class="bi bi-receipt"></i> Ventas</a></li>
                            </ul>
                        </li>

                        <!-- Otros -->
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/bitacora">
                                <i class="bi bi-clock-history"></i> Bitácora
                            </a>
                        </li>
                    </ul>

                    <!-- Botón salir -->
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link text-warning fw-bold" href="${pageContext.request.contextPath}/UsuarioServlet?accion=logout">
                                <i class="bi bi-box-arrow-right"></i> Salir
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
