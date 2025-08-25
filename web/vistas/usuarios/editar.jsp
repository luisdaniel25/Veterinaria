<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.UsuarioDAO,modelo.Usuario" %>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/menu.jsp"/>

<%
    int id = 0;
    try {
        id = Integer.parseInt(request.getParameter("id"));
    } catch (Exception e) {
        id = 0;
    }
    UsuarioDAO dao = new UsuarioDAO();
    Usuario u = dao.getById(id);
    if (u == null) {
        response.sendRedirect(request.getContextPath() + "/vistas/usuarios/listar.jsp");
        return;
    }

    String rolTexto = "";
    switch (u.getRol()) {
        case 1:
            rolTexto = "Administrador";
            break;
        case 2:
            rolTexto = "Veterinario";
            break;
        case 3:
            rolTexto = "Cliente";
            break;
        default:
            rolTexto = "Desconocido";
    }
%>

<div class="container">
    <h3 class="text-success">Editar Usuario</h3>
    <form action="${pageContext.request.contextPath}/UsuarioServlet" method="post">
        <input type="hidden" name="accion" value="actualizar">
        <input type="hidden" name="id" value="<%= u.getId_usuario()%>">
        <div class="mb-3">
            <label>Nombre</label>
            <input type="text" name="nombre" class="form-control" value="<%= u.getNombre()%>" required>
        </div>
        <div class="mb-3">
            <label>Correo</label>
            <input type="email" name="correo" class="form-control" value="<%= u.getCorreo()%>" required>
        </div>
        <div class="mb-3">
            <label>Clave</label>
            <input type="text" name="clave" class="form-control" value="<%= u.getContrasena()%>" required>
        </div>
        <div class="mb-3">
            <label>Rol</label>
            <select name="rol" class="form-control" required>
                <option value="1" <%= u.getRol() == 1 ? "selected" : ""%>>Administrador</option>
                <option value="2" <%= u.getRol() == 2 ? "selected" : ""%>>Veterinario</option>
                <option value="3" <%= u.getRol() == 3 ? "selected" : ""%>>Cliente</option>
            </select>
            <small class="text-muted">Rol actual: <%= rolTexto%></small>
        </div>
        <div class="mb-3">
            <label>ID Especialidad</label>
            <input type="number" name="id_especialidad" class="form-control" value="<%= u.getId_especialidad()%>">
        </div>
        <button type="submit" class="btn btn-success">Actualizar</button>
        <a href="${pageContext.request.contextPath}/vistas/usuarios/listar.jsp" class="btn btn-secondary">Volver</a>
    </form>
</div>

<jsp:include page="/includes/footer.jsp"/>
