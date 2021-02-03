
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
    <link rel="stylesheet" type="text/css" href="css\cssmostrarstock.css?1.0" media="all">
    
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
<h2 align="left">DISFRUTA <%=nombreusuario%>... </h2>

<h1 align="left">¿TE GUSTA LO QUE VES? </h1>

<form name="myForm" method="get">


<table id="tablaSTOCK"   bgcolor="#C0C0C0"  >
<TR> 
    <TH>Titulo</TH>
    <TH>Descripcion</TH>
    <TH>Precio</TH>
    <TH>Stock</TH>
    <TH>Foto</TH>
</TR>
<%
ArrayList listaArticulos = (ArrayList) request.getSession().getAttribute("listaART");
 
Iterator it = listaArticulos.iterator();
        
while(it.hasNext()){
Articulos articulo_tmp = (Articulos) it.next();
%> 
<tr>
    <TD  ><%=articulo_tmp.getTitulo()%></td> 
    <TD  ><%=articulo_tmp.getDescripcion()%></td>
    <TD  ><%=articulo_tmp.getPrecio()%></td>
    <TD  ><%=articulo_tmp.getUnidades()%></td>
    <TD  ><img src="<%=articulo_tmp.getFoto()%>" size="5%"></td>
</tr>
<%}
//request.getSession().removeAttribute("listaART");
%>
</table>


<br><br><br>


<input type="button" value="VOLVER AL MENÚ" onclick="DestinoA()">
<a download href="http://localhost:8080/excel/listado.xlsx" >LINK - DESCARGAR EXCEL</a>

</form>
<br>




</body>
</html>