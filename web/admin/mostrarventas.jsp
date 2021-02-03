
<%@page import="Clases.Ventas"%>
<%@page import="java.util.Iterator"%>
<%@page import="Clases.Articulos"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


 <!DOCTYPE html>
<html>
<script>	

function DestinoA(){
     
		document.myForm.action='menuadmin.jsp';
		document.myForm.submit();
}

</script>
<head><title>BRAIN - SHELTER</title>
    <link rel="stylesheet" type="text/css" href="../css/cssmostrarventas.css?1.0" media="all">
    
<style>
body {
  background-image: url('../imagenes/fondo15admin.png');
  background-size: 1680px;
}
</style>   
</head>

<%
    HttpSession sesion = request.getSession();
    String nombreusuario = (String) sesion.getAttribute ("login");
%>


<font color="#000000"  face="Cambria">

<h2>HOLA <%=nombreusuario%>, BOSS </h2>

<h1>LISTADO DE VENTAS </h1>

<center>
<form name="myForm" method="get">


<table>
<TR> 
    <th>Código Pedido</th>
    <th>Código Artículo</th>
    <th>Fecha</th>
    <th>Total</th>
    <th>Código Cliente</th>
    <th>Nombre</th>
    <th>Apellidos</th>
</TR>
<%
ArrayList listaventas = (ArrayList) request.getSession().getAttribute("listaVENTAS");
 
Iterator it = listaventas.iterator();
        
while(it.hasNext()){
Ventas venta_tmp = (Ventas)it.next();
%> 
<tr>
    <td><%=venta_tmp.getCodped()%></td> 
    <td><%=venta_tmp.getCodart()%></td>
    <td><%=venta_tmp.getFecha_ped()%></td> 
    <td><%=venta_tmp.getTotal()%></td> 
    <td><%=venta_tmp.getCodcli()%></td> 
    <td><%=venta_tmp.getNombre()%></td> 
    <td><%=venta_tmp.getApellidos()%></td> 
<%}
request.getSession().removeAttribute("listaVENTAS");
%>
</table>

</form>
</center>

<input type="button" value="VOLVER AL MENÚ" onclick="DestinoA()">
<a download href="http://localhost:8080/excel/listadoventas.xlsx" >LINK - DESCARGAR EXCEL</a>

</body>
</html>
