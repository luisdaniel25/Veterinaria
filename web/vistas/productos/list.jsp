<%@page contentType="text/html;charset=UTF-8"%>
<html><head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-..." crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-..." crossorigin="anonymous"></script>
<title>Productos</title></head><body>
<jsp:include page="/jsp/includes/header.jsp" />

<h3>Productos</h3>
<a href="<%=request.getContextPath()%>/jsp/productos/form.jsp">Nuevo Producto</a>
<table border="1">
  <tr><th>ID</th><th>Codigo</th><th>Nombre</th><th>Precio</th><th>Stock</th><th>Acciones</th></tr>
  <% for (Object _o : (java.util.List)request.getAttribute("productos")) { app.model.Producto p = (app.model.Producto)_o; %>
    <tr>
      <td><%=p.getId_producto()%></td>
      <td><%=p.getCodigo_barras()%></td>
      <td><%=p.getNombre()%></td>
      <td><%=p.getPrecio()%></td>
      <td><%=p.getStock()%></td>
      <td>
        <form method="post" action="<%=request.getContextPath()%>/productos" style="display:inline">
          <input type="hidden" name="action" value="eliminar"/>
          <input type="hidden" name="id" value="<%=p.getId_producto()%>"/>
          <input type="submit" value="Eliminar" onclick="return confirm('Eliminar?')"/>
        </form>
      </td>
    </tr>
  <% } %>
</table>
</body></html>
