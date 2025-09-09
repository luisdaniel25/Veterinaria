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
    Usuario u = dao.buscarPorId(id);
    if (u == null) {
        response.sendRedirect(request.getContextPath() + "/vistas/usuarios/listar.jsp");
        return;
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
            <input type="text" name="contrasena" class="form-control" value="<%= u.getContrasena()%>" required>
        </div>

        <button type="submit" class="btn btn-success">Actualizar</button>
        <a href="${pageContext.request.contextPath}/vistas/usuarios/listar.jsp" class="btn btn-secondary">Volver</a>
    </form>
</div>

<jsp:include page="/includes/footer.jsp"/>
