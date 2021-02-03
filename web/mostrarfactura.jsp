<%@page import="Clases.Ventas"%>
<%@page import="java.util.Iterator"%>
<%@page import="Clases.Articulos"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


 <!DOCTYPE html>
<html>
<script>	

function DestinoA(){
     
		document.myForm.action='menuusuario.jsp';
		document.myForm.submit();
}
</script>
<head><title>BRAIN - SHELTER</title>
    <link rel="stylesheet" type="text/css" href="css\cssmostrarfactura.css?1.0" media="all">
    
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
<h2 align="left">TUS FACTURAS <%=nombreusuario%>... </h2>

<form name="myForm" method="get">


<table id="tuFACTURA"   bgcolor="#C0C0C0"  >
<TR> 
    <TH>Codigo DP</TH>
    <TH>Codigo AT</TH>
    <TH>Fecha</TH>
    <TH>Total</TH>
    <TH>Codigo CL</TH>
    <TH>Nombre</TH>
    <TH>Apellidos</TH>
</TR>
<%
ArrayList listaventas = (ArrayList) request.getSession().getAttribute("tuFACTURA");
 
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
</tr>
<%}
//request.getSession().removeAttribute("tuFACTURA");
%>
</table>


<br><br><br>


<input type="button" value="VOLVER AL MENÚ" onclick="DestinoA()">
<a download href="http://localhost:8080/excel/tufactura.xlsx" >LINK - DESCARGAR EXCEL</a>

</form>
<br>




</body>
</html>
