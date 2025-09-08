<%@page contentType="text/html;charset=UTF-8" import="modelo.Cliente, modelo.Usuario"%>
<%
    // Validación de usuario admin
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u == null || u.getRol() != 1) { // 1 = admin
        response.sendRedirect("login.jsp");
        return;
    }

    // Cliente para edición
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    boolean esEdicion = (cliente != null);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><%= esEdicion ? "Actualizar Cliente" : "Nuevo Cliente"%></title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <jsp:include page="../../includes/header.jsp" />

        <div class="container mt-4">
            <h3><%= esEdicion ? "Actualizar Cliente" : "Crear Cliente"%></h3>
            <form action="<%=request.getContextPath()%>/clientes" method="post" class="mt-3">
                <% if (esEdicion) {%>
                <input type="hidden" name="id" value="<%=cliente.getId_cliente()%>"/>
                <% }%>

                <input type="hidden" name="action" value="<%= esEdicion ? "actualizar" : "crear"%>"/>

                <div class="mb-3">
                    <label for="cedula" class="form-label">Cédula</label>
                    <input type="text" id="cedula" name="cedula" class="form-control"
                           value="<%= esEdicion ? cliente.getCedula_cliente() : ""%>" required>
                </div>

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" id="nombre" name="nombre" class="form-control"
                           value="<%= esEdicion ? cliente.getNombre() : ""%>" required>
                </div>

                <div class="mb-3">
                    <label for="apellido" class="form-label">Apellido</label>
                    <input type="text" id="apellido" name="apellido" class="form-control"
                           value="<%= esEdicion ? cliente.getApellido() : ""%>" required>
                </div>

                <div class="mb-3">
                    <label for="direccion" class="form-label">Dirección</label>
                    <input type="text" id="direccion" name="direccion" class="form-control"
                           value="<%= esEdicion ? cliente.getDireccion() : ""%>">
                </div>

                <div class="mb-3">
                    <label for="telefono" class="form-label">Teléfono</label>
                    <input type="text" id="telefono" name="telefono" class="form-control"
                           value="<%= esEdicion ? cliente.getTelefono() : ""%>">
                </div>

                <button type="submit" class="btn btn-primary">
                    <%= esEdicion ? "Actualizar" : "Crear"%>
                </button>
                <a href="<%=request.getContextPath()%>/clientes" class="btn btn-secondary ms-2">Volver</a>
            </form>
        </div>

        <jsp:include page="../../includes/footer.jsp" />
    </body>
</html>
