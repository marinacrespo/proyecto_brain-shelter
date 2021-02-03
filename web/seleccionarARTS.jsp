
<%@page import="java.util.Iterator"%>
<%@page import="Clases.Articulos"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


 <!DOCTYPE html>
<html>
<script>	

function DestinoA(){
     
		document.seleccionarcompra.action='menuusuario.jsp';
		document.seleccionarcompra.submit();
}
function DestinoB(){
     
		document.seleccionarcompra.action='ComprarArts';
		document.seleccionarcompra.submit();
}

</script>
<head><title>BRAIN - SHELTER</title>
    <link rel="stylesheet" type="text/css" href="css\csscomprarart.css?1.0" media="all">
    
<style>
body {
  background-image: url('imagenes/fondo15.png');
  background-size: 1680px;
}
</style> 
</head>

<%
    HttpSession sesion = request.getSession();
    String nombreusuario = (String) sesion.getAttribute ("login");
%>

<body>
<font color="black"  face="Cambria">
<br>
<h2 align="left">PARA TÍ, <%=nombreusuario%> </h2>
<br>

<input type="button" value="VOLVER AL MENÚ" onclick="DestinoA()">
<input type="button" class="comprar" value="FINALIZAR COMPRA" onclick="DestinoB()">

<form name="seleccionarcompra" method="get">

<table id="tablaCOMPRA"   bgcolor="#C0C0C0">
<TR>
    <th> Artículo </th>
    <th>Descripción</th>
    <th>Precio unidad</th>
    <th>Stock</th>
    <th>Foto</th>
    <th>Selección </th>
</TR>


<%
ArrayList listaArticulos = (ArrayList) request.getSession().getAttribute("listaART");
 
Iterator it = listaArticulos.iterator();
 int i=0;       
while(it.hasNext()){
    i++;
Articulos articulo_tmp = (Articulos) it.next();
%> 


<tr>
    <td><%=articulo_tmp.getTitulo()%></td>
    <td><%=articulo_tmp.getDescripcion()%></td>
    <td><%=articulo_tmp.getPrecio()%></td>
    <td><%=articulo_tmp.getUnidades()%></td>
    <td><img src="<%=articulo_tmp.getFoto()%>" size="5%"></td>
    <td>
        <input type="checkbox"  name="articulo<%=i%>" value="<%=articulo_tmp.getIdarticulo()%>"> ¿SI o NO?</input>
        <br><br><br>
        
        ¿Cuántos?
        <br>
        <input type="text" name="cantidad<%=i%>">
    </td>    
</tr> 
<%}
request.getSession().removeAttribute("listaART");
%>
</table>

<input type="hidden" name="pedido" value="<%=i%>">

</form>
</body>
</html>

