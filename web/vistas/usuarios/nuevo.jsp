<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/menu.jsp"/>

<div class="container">
    <h3 class="text-success">Agregar Usuario</h3>
    <form action="${pageContext.request.contextPath}/UsuarioServlet" method="post">
        <input type="hidden" name="accion" value="agregar">
        <div class="mb-3">
            <label>Nombre</label>
            <input type="text" name="nombre" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Correo</label>
            <input type="email" name="correo" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Clave</label>
            <input type="text" name="clave" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Rol</label>
            <select name="rol" class="form-control" required>
                <option value="1">Administrador</option>
                <option value="2">Veterinario</option>
                <option value="3" selected>Cliente</option>
            </select>
        </div>
        <div class="mb-3">
            <label>ID Especialidad</label>
            <input type="number" name="id_especialidad" class="form-control" value="0">
        </div>
        <button type="submit" class="btn btn-success">Agregar</button>
        <a href="${pageContext.request.contextPath}/vistas/usuarios/listar.jsp" class="btn btn-secondary">Volver</a>
    </form>
</div>

<jsp:include page="/includes/footer.jsp"/>
