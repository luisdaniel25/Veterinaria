<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm mb-4">
    <div class="container">
        <a class="navbar-brand fw-bold text-success" href="${pageContext.request.contextPath}/index.jsp">
            <i class="bi bi-house-door-fill"></i> Inicio
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                data-bs-target="#menu" aria-controls="menu" aria-expanded="false">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="menu">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/usuarios/listar.jsp">
                        <i class="bi bi-people-fill"></i> Usuarios
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/clientes/listar.jsp">
                        <i class="bi bi-person-lines-fill"></i> Clientes
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/mascotas/listar.jsp">
                        <i class="bi bi-heart-pulse-fill"></i> Mascotas
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/citas/listar.jsp">
                        <i class="bi bi-calendar-check-fill"></i> Citas
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/vacunas/listar.jsp">
                        <i class="bi bi-eyedropper"></i> Vacunas
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/productos/listar.jsp">
                        <i class="bi bi-bag-heart-fill"></i> Productos
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ventas/listar.jsp">
                        <i class="bi bi-receipt"></i> Ventas
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/facturas/listar.jsp">
                        <i class="bi bi-file-earmark-text-fill"></i> Facturas
                    </a>
                </li>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link text-danger" href="${pageContext.request.contextPath}/UsuarioServlet?accion=logout">
                        <i class="bi bi-box-arrow-right"></i> Salir
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
