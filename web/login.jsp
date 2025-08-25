<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="includes/header.jsp"/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4 class="text-center text-success">Iniciar Sesión</h4>
                    <form action="UsuarioServlet" method="post">
                        <input type="hidden" name="accion" value="login">
                        <div class="mb-3">
                            <label>Correo</label>
                            <input type="email" name="correo" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>Contraseña</label>
                            <input type="password" name="contrasena" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-success w-100">Ingresar</button>
                        <div class="text-danger text-center mt-2">
                            <%= request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
