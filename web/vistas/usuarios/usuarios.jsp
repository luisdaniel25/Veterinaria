<%-- 
    Document   : usuarios
    Created on : 21/08/2025, 12:59:20 p. m.
    Author     : Luis Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dao.UsuarioDAO,modelo.Usuario,java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Usuarios</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <style>
            .table-actions {
                white-space: nowrap;
            }
            .btn-sm {
                margin-right: 5px;
            }
        </style>
    </head>
    <body>
        <!-- Header -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-success">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <i class="fas fa-users"></i> Sistema de Gestión de Usuarios
                </a>
            </div>
        </nav>

        <div class="container mt-4">
            <!-- Mensajes de éxito o error -->
            <%
                String mensaje = (String) request.getAttribute("mensaje");
                String error = (String) request.getAttribute("error");

                if (mensaje != null) {
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <%= mensaje%>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
            <%
                }
                if (error != null) {
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <%= error%>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
            <%
                }
            %>

            <div class="card">
                <div class="card-header bg-success text-white">
                    <div class="d-flex justify-content-between align-items-center">
                        <h4 class="mb-0">
                            <i class="fas fa-list"></i> Lista de Usuarios
                        </h4>
                        <a href="nuevo.jsp" class="btn btn-light btn-sm">
                            <i class="fas fa-plus"></i> Nuevo Usuario
                        </a>
                    </div>
                </div>
                <div class="card-body">
                    <%
                        UsuarioDAO dao = new UsuarioDAO();
                        List<Usuario> lista = dao.listar();

                        if (lista.isEmpty()) {
                    %>
                    <div class="text-center py-4">
                        <i class="fas fa-users fa-3x text-muted mb-3"></i>
                        <h5 class="text-muted">No hay usuarios registrados</h5>
                        <p class="text-muted">Haz clic en "Nuevo Usuario" para agregar el primero.</p>
                    </div>
                    <%
                    } else {
                    %>
                    <div class="table-responsive">
                        <table class="table table-striped table-hover">
                            <thead class="table-success">
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre</th>
                                    <th>Correo</th>
                                    <th>Rol</th>
                                    <th>Especialidad ID</th>
                                    <th class="text-center">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
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
                                    <td><strong><%= u.getNombre()%></strong></td>
                                    <td><%= u.getCorreo()%></td>
                                    <td>
                                        <span class="badge 
                                              <%= u.getRol() == 1 ? "bg-primary"
                                                      : u.getRol() == 2 ? "bg-info"
                                                      : u.getRol() == 3 ? "bg-secondary" : "bg-warning"%>">
                                            <%= rolTexto%>
                                        </span>
                                    </td>
                                    <td>
                                        <%= u.getId_especialidad() != 0 ? u.getId_especialidad() : "N/A"%>
                                    </td>
                                    <td class="table-actions text-center">
                                        <a href="editar.jsp?id=<%= u.getId_usuario()%>" 
                                           class="btn btn-warning btn-sm" 
                                           title="Editar usuario">
                                            <i class="fas fa-edit"></i>
                                        </a>
                                        <form action="../../UsuarioServlet" method="post" style="display:inline;">
                                            <input type="hidden" name="accion" value="eliminar">
                                            <input type="hidden" name="id" value="<%= u.getId_usuario()%>">
                                            <button type="submit" 
                                                    class="btn btn-danger btn-sm" 
                                                    title="Eliminar usuario"
                                                    onclick="return confirm('¿Está seguro de eliminar al usuario <%= u.getNombre()%>?')">
                                                <i class="fas fa-trash"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                    <%
                        }
                    %>
                </div>
                <div class="card-footer">
                    <div class="row">
                        <div class="col-md-6">
                            <span class="text-muted">
                                Total de usuarios: <strong><%= lista.size()%></strong>
                            </span>
                        </div>
                        <div class="col-md-6 text-end">
                            <a href="../../index.jsp" class="btn btn-outline-secondary btn-sm">
                                <i class="fas fa-home"></i> Volver al Inicio
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                                                        // Auto-cierre de alertas después de 5 segundos
                                                        setTimeout(function () {
                                                            var alerts = document.querySelectorAll('.alert');
                                                            alerts.forEach(function (alert) {
                                                                new bootstrap.Alert(alert).close();
                                                            });
                                                        }, 5000);
        </script>
    </body>
</html>
