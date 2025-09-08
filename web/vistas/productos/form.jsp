<%@page contentType="text/html;charset=UTF-8"%><html><head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-..." crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-..." crossorigin="anonymous"></script>
<title>Nuevo Producto</title></head><body>
<jsp:include page="/jsp/includes/header.jsp" />
<h3>Crear Producto</h3><form action="<%=request.getContextPath()%>/productos" method="post"><input type="hidden" name="action" value="crear"/>Codigo barras: <input name="codigo"/><br/>Nombre: <input name="nombre"/><br/>Precio: <input name="precio"/><br/>Stock: <input name="stock" value="0"/><br/><input type="submit" value="Crear"/></form><a href="<%=request.getContextPath()%>/productos">Volver</a></body></html>