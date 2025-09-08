<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.List, app.model.Producto, app.model.Cliente, app.model.MetodoPago" %>
<html><head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-..." crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-..." crossorigin="anonymous"></script>
<title>Crear Factura</title></head>
<body>
<jsp:include page="/jsp/includes/header.jsp" />

<h3>Crear Factura</h3>
<form method="post" action="<%=request.getContextPath()%>/factura">
  Cliente:
  <select name="clienteId">
    <% for (Object _o: (List)request.getAttribute("clientes")) { Cliente c = (Cliente)_o; %>
      <option value="<%=c.getId_cliente()%>"><%=c.getNombre()%> <%=c.getApellido()%></option>
    <% } %>
  </select><br/>
  Metodo de Pago:
  <select name="metodoId">
    <% for (Object _o: (List)request.getAttribute("metodos")) { MetodoPago m = (MetodoPago)_o; %>
      <option value="<%=m.getId_metodo()%>"><%=m.getNombre()%></option>
    <% } %>
  </select><br/>
  <h4>Productos</h4>
  <table id="tbl" border="1">
    <tr><th>Producto</th><th>Cantidad</th><th>Precio</th><th></th></tr>
  </table>
  <select id="selProd">
    <% for (Object _o: (List)request.getAttribute("productos")) { Producto p = (Producto)_o; %>
      <option value="<%=p.getId_producto()%>" data-price="<%=p.getPrecio()%>"><%=p.getNombre()%> - <%=p.getPrecio()%></option>
    <% } %>
  </select>
  Cantidad: <input id="qty" type="number" value="1" min="1"/>
  <button type="button" onclick="addRow()">Agregar</button>
  <br/><br/>
  <input type="submit" value="Generar Factura"/>
</form>
<script>
function addRow(){
  var sel = document.getElementById('selProd');
  var idx = sel.selectedIndex;
  var pid = sel.options[idx].value;
  var price = sel.options[idx].getAttribute('data-price');
  var name = sel.options[idx].text;
  var qty = document.getElementById('qty').value;
  var tbl = document.getElementById('tbl');
  var row = tbl.insertRow(-1);
  row.innerHTML = '<td><input type="hidden" name="prodId" value="'+pid+'">'+name+'</td>' +
                  '<td><input type="hidden" name="qty" value="'+qty+'">'+qty+'</td>' +
                  '<td><input type="hidden" name="price" value="'+price+'">'+price+'</td>' +
                  '<td><button type="button" onclick="this.closest(\'tr\').remove()">Quitar</button></td>';
}
</script>
</body></html>
