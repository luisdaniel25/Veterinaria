<%@page contentType="text/html;charset=UTF-8" import="java.util.List, modelo.Cliente"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Clientes - VetCare</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <jsp:include page="../../includes/header.jsp" />

        <div class="container py-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h3 class="text-success">🐾 Clientes</h3>
                <div>
                    <a href="<%=request.getContextPath()%>/clientes" class="btn btn-success me-2">
                        <i class="bi bi-person-plus-fill"></i> Nuevo Cliente
                    </a>
                    <a href="<%=request.getContextPath()%>/" class="btn btn-secondary">
                        <i class="bi bi-house-fill"></i> Volver al Inicio
                    </a>
                </div>
            </div>

            <div class="card shadow-sm">
                <div class="card-body">
                    <table class="table table-striped table-hover align-middle">
                        <thead class="table-success">
                            <tr>
                                <th>ID</th>
                                <th>Cédula</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Dirección</th>
                                <th>Teléfono</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                                if (clientes != null && !clientes.isEmpty()) {
                                    for (Cliente c : clientes) {
                            %>
                            <tr>
                                <td><%=c.getId_cliente()%></td>
                                <td><%=c.getCedula_cliente()%></td>
                                <td><%=c.getNombre()%></td>
                                <td><%=c.getApellido()%></td>
                                <td><%=c.getDireccion()%></td>
                                <td><%=c.getTelefono()%></td>
                                <td>
                                    <!-- Botón Editar -->
                                    <a href="<%=request.getContextPath()%>/clientes?editar=<%=c.getId_cliente()%>" 
                                       class="btn btn-sm btn-warning">
                                        <i class="bi bi-pencil-fill"></i> Editar
                                    </a>

                                    <!-- Botón Eliminar -->
                                    <form method="post" action="<%=request.getContextPath()%>/clientes" style="display:inline">
                                        <input type="hidden" name="action" value="eliminar"/>
                                        <input type="hidden" name="id" value="<%=c.getId_cliente()%>"/>
                                        <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('¿Seguro de eliminar este cliente?')">
                                            <i class="bi bi-trash-fill"></i> Eliminar
                                        </button>
                                    </form>
                                </td>
                            </tr>
                            <%
                                }
                            } else {
                            %>
                            <tr>
                                <td colspan="7" class="text-center text-muted">No hay clientes registrados.</td>
                            </tr>
                            <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <jsp:include page="../../includes/footer.jsp" />
    </body>
</html>
