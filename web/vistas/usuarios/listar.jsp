<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.UsuarioDAO,modelo.Usuario,java.util.*" %>
<jsp:include page="/includes/header.jsp"/>
<jsp:include page="/includes/menu.jsp"/>

<div class="container">
    <h3 class="text-success">Gestión de Usuarios</h3>
    <a href="${pageContext.request.contextPath}/vistas/usuarios/nuevo.jsp" class="btn btn-success btn-sm mb-3">+ Nuevo Usuario</a>

    <table class="table table-bordered">
        <thead class="table-success">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Rol</th>
                <th>Especialidad</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                UsuarioDAO dao = new UsuarioDAO();
                List<Usuario> lista = dao.listar();
                for (Usuario u : lista) {
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
                            rolTexto = "Desconocido (" + u.getRol() + ")";
                    }
            %>
            <tr>
                <td><%= u.getId_usuario()%></td>
                <td><%= u.getNombre()%></td>
                <td><%= u.getCorreo()%></td>
                <td><%= rolTexto%></td>
                <td><%= u.getId_especialidad() != 0 ? u.getId_especialidad() : "N/A"%></td>
                <td>
                    <a href="${pageContext.request.contextPath}/vistas/usuarios/editar.jsp?id=<%= u.getId_usuario()%>" class="btn btn-warning btn-sm">Editar</a>
                    <form action="${pageContext.request.contextPath}/UsuarioServlet" method="post" style="display:inline;">
                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" name="id" value="<%= u.getId_usuario()%>">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('¿Está seguro de eliminar este usuario?')">Eliminar</button>
                    </form>
                </td>
            </tr>
            <% }%>
        </tbody>
    </table>
</div>

<jsp:include page="/includes/footer.jsp"/>
