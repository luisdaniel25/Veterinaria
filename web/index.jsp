<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="includes/header.jsp"/>
<jsp:include page="includes/menu.jsp"/>

<div class="container">
    <div class="text-center mb-5">
        <h2 class="fw-bold text-success">Bienvenido a VetCare Santa Marta 🐾</h2>
        <p class="text-muted">Sistema de gestión veterinaria - Santa Marta, Magdalena</p>
    </div>

    <div class="row g-4">
        <!-- Usuarios -->
        <div class="col-md-3">
            <div class="card shadow-sm border-0 h-100">
                <div class="card-body text-center">
                    <i class="bi bi-people-fill fs-1 text-success"></i>
                    <h5 class="mt-3">Usuarios</h5>
                    <a href="vistas/usuarios/listar.jsp" class="btn btn-outline-success btn-sm mt-2">Gestionar</a>
                </div>
            </div>
        </div>

        <!-- Clientes -->
        <div class="col-md-3">
            <div class="card shadow-sm border-0 h-100">
                <div class="card-body text-center">
                    <i class="bi bi-person-lines-fill fs-1 text-success"></i>
                    <h5 class="mt-3">Clientes</h5>
                    <a href="vistas/clientes/listar.jsp" class="btn btn-outline-success btn-sm mt-2">Gestionar</a>
                </div>
            </div>
        </div>

        <!-- Mascotas -->
        <div class="col-md-3">
            <div class="card shadow-sm border-0 h-100">
                <div class="card-body text-center">
                    <i class="bi bi-heart-pulse-fill fs-1 text-success"></i>
                    <h5 class="mt-3">Mascotas</h5>
                    <a href="vistas/mascotas/listar.jsp" class="btn btn-outline-success btn-sm mt-2">Gestionar</a>
                </div>
            </div>
        </div>

        <!-- Productos -->
        <div class="col-md-3">
            <div class="card shadow-sm border-0 h-100">
                <div class="card-body text-center">
                    <i class="bi bi-bag-heart-fill fs-1 text-success"></i>
                    <h5 class="mt-3">Productos</h5>
                    <a href="vistas/productos/listar.jsp" class="btn btn-outline-success btn-sm mt-2">Gestionar</a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
